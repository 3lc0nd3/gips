package co.com.lh.smsfin.dao;

import co.com.lh.smsfin.model.*;
import co.com.lh.smsfin.modelFac.*;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.util.List;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Edward L. Ramirez A.
 * cel 300 554 3367
 * email elramireza@gmail.com
 * User: usuariox
 * Date: Jul 27, 2011
 * Time: 3:26:33 PM
 */
//@SuppressWarnings(value = "unchecked")
@SuppressWarnings({
        "deprecation", "unchecked"
})
public class FacDAO extends HibernateDaoSupport{

    /**
     * pone email en un people
     * @param idPeople
     */
    public void test(final int idPeople, final String email){
        getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(
                        "update PhpposPeopleEntity set address1 = 'U', email = ? where personId = ? "
                );
                query.setString(0, email);
                query.setInteger(1, idPeople);
                query.executeUpdate();
                return null;
            }
        });
    }

    /**
     * trae un item
     * @param idItemPos id
     * @return item
     */
    public PhpposItemsEntity getItemPos(int idItemPos){
        return (PhpposItemsEntity) getHibernateTemplate().get(PhpposItemsEntity.class, idItemPos);
    }

    /**
     * trae todos los Items en el POS
     * @return list
     */
    public List<PhpposItemsEntity> getItemsEntities(){
        return getHibernateTemplate().find("from PhpposItemsEntity ");
    }

    private FacOracleDAO facOracleDAO;

    public void setFacOracleDAO(FacOracleDAO casurOracleDAO) {
        this.facOracleDAO = casurOracleDAO;
    }

    public FacOracleDAO getFacOracleDAO() {
        return facOracleDAO;
    }

    public PhpposPeopleEntity getPeopleEntity(int idPeople){
        return (PhpposPeopleEntity) getHibernateTemplate().get(PhpposPeopleEntity.class, idPeople);
    }

	public PhpposPeopleEntity getPeopleEntityFromCedula(long cedula){
		List<PhpposPeopleEntity> peopleEntities = getHibernateTemplate().find(
				"from PhpposPeopleEntity where zip = ? ", String.valueOf(cedula)
		);
		if (peopleEntities.size()>0) {
			return peopleEntities.get(0);
		} else {
			return null;
		}
	}

	/**
     * Trae People segun la Cedula de un cliente
     * @param cedula c
     * @return people
     */
    public PhpposPeopleEntity getPhpposCustomersEntityFromCedula(long cedula){
        logger.debug("cedula = " + cedula);
        List<PhpposCustomersEntity> customersList = getHibernateTemplate().find(
                "from PhpposCustomersEntity where accountNumber = ?", String.valueOf(cedula)
        );
        if (customersList.size()>0){
            return getPeopleEntity(customersList.get(0).getPersonId());
        } else {
            return null;
        }
    }

    public PhpposPeopleEntity getPeopleFromName(String nombre,
                                                String apellido){

        logger.debug("nombre = " + nombre+"\tapellido = " + apellido);
        List<PhpposPeopleEntity> gente;

        DetachedCriteria criteria = DetachedCriteria.forClass(PhpposPeopleEntity.class);
        if (nombre != null) {
            criteria.add(Restrictions.like("firstName", ("%"+nombre+"%")));
        }
        if (apellido != null) {
            criteria.add(Restrictions.like("lastName", ("%"+apellido+"%")));
        }
        gente = getHibernateTemplate().findByCriteria(criteria);
        logger.debug("gente.size() = " + gente.size());
        if (gente.size()>0){
            return gente.get(0);
        } else {
            return null;
        }
    }

    /**
     * Crea un PhpposCustomersEntity en MySQL segun los datos de ORACLE
     * @param funcionario VIENE DE ORACLE
     */
    public void saveNewCustomer(final VPosFuncionarios funcionario){
		//  TOCA MIRAR SI EXISTE EN PEOPLE Y EN CUSTOMERS

		Session hbSession = getSession();        // SESSION MYSQL
		Transaction ts = hbSession.beginTransaction();
		boolean success = false;

		PhpposPeopleEntity peopleEntity = getPeopleEntityFromCedula(funcionario.getFunCedula());

		if (getPhpposCustomersEntityFromCedula(funcionario.getFunCedula()) == null) { // EL FUNCIONARIO NO EXISTE EN CUSTOMER

			Integer idPeople1;
			// PREGUNTO SI EXISTE EN PEOPLE
			if (peopleEntity==null) {  // NO EXISTE EN PEOPLE, TOCA CREARLO
				peopleEntity = new PhpposPeopleEntity();

				String nombre= "";
				if (funcionario.getFunGrado()!= null){
					nombre+=funcionario.getFunGrado()+" ";
				}
				if (funcionario.getFunApellidos()!= null){
					nombre+=funcionario.getFunApellidos();
				}
				peopleEntity.setFirstName(nombre);
				peopleEntity.setLastName(funcionario.getFunNombres()==null?"":funcionario.getFunNombres());
				peopleEntity.setPhoneNumber(" ");
				peopleEntity.setEmail(" ");
				peopleEntity.setAddress1(" ");
				peopleEntity.setAddress2(" ");
				peopleEntity.setCity(" ");
				peopleEntity.setState(" ");
				peopleEntity.setZip(String.valueOf(funcionario.getFunCedula()));
				peopleEntity.setCountry(" ");
				peopleEntity.setComments(" ");

				// CREO EL PEOPLE EN EL POS MYSQL
				idPeople1 = (Integer) getHibernateTemplate().save(peopleEntity);


			} else {
				idPeople1 = peopleEntity.getPersonId();
				logger.info("");
				logger.info(" ===== FUNCIONARIO YA EXISTIA  ========================= ");
				logger.info("idPeople = " + idPeople1);
				logger.info("funcionario.getFunCedula() = " + funcionario.getFunCedula());
				logger.info("funcionario.getFunNombres() = " + funcionario.getFunNombres());
				logger.info("funcionario.getFunApellidos() = " + funcionario.getFunApellidos());
				logger.info(" ======================================================= ");
				logger.info("");
			}

			final Integer idPeople = idPeople1;

            // JOIN CON LA TABLA FUNCIONARIO DE SINFAD

			try {
				facOracleDAO.getHibernateTemplate().execute(new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery(
								"update PosFuncEstado set fesIdFuncionario = ?, fesEstado = 'A' where fesCedula = ? and fesIdPos = ?"
						);
						query.setInteger(0, idPeople);
						query.setLong(1, funcionario.getFunCedula());
						query.setInteger(2, getIdPos());

						query.executeUpdate();
						return null;  //To change body of implemented methods use File | Settings | File Templates.
					}
				});
				success = true;
			} catch (DataAccessException e) {
				e.printStackTrace();  //
			} finally {
				if (success) {
					ts.commit();
					hbSession.flush();
					hbSession.close();
					logger.info("");
					logger.info(" ===== FUNCIONARIO CREADO ============================== ");
					logger.info("idPeople = " + idPeople1);
					logger.info("funcionario.getFunCedula() = " + funcionario.getFunCedula());
					logger.info("funcionario.getFunNombres() = " + funcionario.getFunNombres());
					logger.info("funcionario.getFunApellidos() = " + funcionario.getFunApellidos());
					logger.info(" ======================================================= ");
					logger.info("");
				} else {
					ts.rollback();
					hbSession.flush();
					hbSession.close();
				}
			}


			// CREO EL CUSTOMER EN EL POS

            PhpposCustomersEntity customersEntity = new PhpposCustomersEntity();

            customersEntity.setPersonId(idPeople);
            customersEntity.setTaxable(0);
            customersEntity.setAccountNumber(String.valueOf(funcionario.getFunCedula()));
			if (funcionario.getFunClave() != null && !funcionario.getFunClave().equals("")) {
				customersEntity.setPass(getMD5(String.valueOf(funcionario.getFunClave())));
			} else {
				customersEntity.setPass("");
			}
			if (funcionario.getFunEndeudamiento()!=null) {
				customersEntity.setLevel_debt(funcionario.getFunEndeudamiento());
			} else {
				customersEntity.setLevel_debt(0);
			}
			if (funcionario.getFunConsumoUltimoMes() != null) {
				customersEntity.setDebt(funcionario.getFunConsumoUltimoMes());
			} else {
				customersEntity.setDebt(0);
			}

			getHibernateTemplate().save(customersEntity);

        }  // END IF EXISTE FUNCIONARIO EN POS
    }

	/**
	 * actualiza cliente
	 * <br> si el cliente ha sido desactivado, lo vuelve a a activar
	 *
	 * @param funcionario QUE VIENE DE ORACLE
	 */
    public void updateCustomer(VPosFuncionarios funcionario){
//		TRAIGO UNA PEOPLE SEGUN LA CEDULA
        PhpposPeopleEntity peopleEntity = getPhpposCustomersEntityFromCedula(funcionario.getFunCedula());
        if (peopleEntity == null){ // NO HAY CUSTOMER: POR 'I'
//			PREGUNTO SI EN ORACLE HAY UN ID_PEOPLE EN USO PARA ESTA CEDULA
            PosFuncEstado posFuncEstado = facOracleDAO.getPosFuncEstado(getIdPos(), funcionario.getFunCedula());
            if (posFuncEstado.getFesIdFuncionario() != null) { // SI EXISTIA
                peopleEntity = getPeopleEntity(posFuncEstado.getFesIdFuncionario());
            } 
        }
		int idPos = getIdPos();
		logger.info(" ========  ITEM CLIENTE FUNCIONARIO  =========================== ");
        logger.info("idPos = " + idPos);
        logger.info("funcionario.getFunCedula() = " + funcionario.getFunCedula());
        logger.info("funcionario.getFunNombres() = " + funcionario.getFunNombres());
        logger.info("funcionario.getFunApellidos() = " + funcionario.getFunApellidos());
		logger.info("funcionario.getFesIdFuncionario() = " + funcionario.getFesIdFuncionario());
		logger.info("funcionario.getFesIdPos() = " + funcionario.getFesIdPos());
		logger.info(" =============================================================== ");
		logger.info("");

        String nombre= "";
        if (funcionario.getFunGrado()!= null){
            nombre+=funcionario.getFunGrado()+" ";
        }
        if (funcionario.getFunApellidos()!= null){
            nombre+=funcionario.getFunApellidos();
        }
		if (peopleEntity == null) {
			logger.info(" ====  PEOPLE ENTITY NULO. NO SE PUDO ENCONTAR EN LOCAL  ======= ");
			logger.info(" ====  SE CREARA UNO NUEVO  ==================================== ");
			logger.info(" =============================================================== ");

			// HAGO DE CUENTA QUE VIENE EN ESTADO N
			saveNewCustomer(funcionario);
		} else {
//			NO ES NULO EL PEOPLE
			peopleEntity.setFirstName(nombre);
			peopleEntity.setLastName(funcionario.getFunNombres() == null ? "" : funcionario.getFunNombres());

			peopleEntity.setPhoneNumber(" ");
			peopleEntity.setEmail(" ");
			peopleEntity.setAddress1(" ");
			peopleEntity.setAddress2(" ");
			peopleEntity.setCity(" ");
			peopleEntity.setState(" ");
			peopleEntity.setZip(String.valueOf(funcionario.getFunCedula()));
			peopleEntity.setCountry(" ");
			peopleEntity.setComments(" ");

			getHibernateTemplate().update(peopleEntity);

			//VERIFICO QUE ESTA VOLVIENDO AL POS DESPUES DE ESTAR INACTIVO : I -> U -> A
			PhpposCustomersEntity customersEntity = getCustomer(peopleEntity.getPersonId());
			// PREGUNTO SI ESTA EN PEOPLE PERO NO EN CUSTOMER
			if (customersEntity != null) { // YA EXISTE EL CUSTOMER
				logger.info("******  YA EXISTE EL CUSTOMER ");
				customersEntity.setAccountNumber(String.valueOf(funcionario.getFunCedula()));
				customersEntity.setPass(getMD5(String.valueOf(funcionario.getFunClave())));
				if (funcionario.getFunEndeudamiento() !=null) {
					customersEntity.setLevel_debt(funcionario.getFunEndeudamiento());
				} else {
					customersEntity.setLevel_debt(0);
				}
				if (funcionario.getFunConsumoUltimoMes()!=null) {
					customersEntity.setDebt(funcionario.getFunConsumoUltimoMes());
				} else {
					customersEntity.setDebt(0);
				}

				getHibernateTemplate().update(customersEntity);

				// ACTUALIZAR EN ORACLE EL ID PEOPLE

				PosFuncEstado funcEstado = facOracleDAO.getPosFuncEstado(idPos, funcionario.getFunCedula());
				if (funcEstado != null) {
					logger.info(" ====  SE ACTUALIZARA EN ORACLE CON EL PEOPLE ID  ============== ");
					logger.info(" ====  " + customersEntity.getPersonId() + " ============= ");
					logger.info(" =============================================================== ");
					funcEstado.setFesIdFuncionario(customersEntity.getPersonId());

					facOracleDAO.getHibernateTemplate().update(funcEstado);
				} else {
					logger.info(" ====  HUBO UN PROBLEMA UBICANDO AL Func_estado  =============== ");
					logger.info(" ====  cedula: "+  funcionario.getFunCedula()+" ================== ");
					logger.info(" ====  idpos : "+  idPos+" ================== ");
					logger.info(" =============================================================== ");
				}


			} else { // EL CUSTOMER ESTABA FUERA
				logger.info("****** EL CUSTOMER ESTABA FUERA");
				customersEntity = new PhpposCustomersEntity();

				customersEntity.setPersonId(peopleEntity.getPersonId());
				customersEntity.setAccountNumber(String.valueOf(funcionario.getFunCedula()));
				customersEntity.setTaxable(0);

				getHibernateTemplate().save(customersEntity);
			}

			// ACTUALIZA LA TABLA FUNCIONARIO DE SINFAD
			PosFuncEstado posFuncEstado = facOracleDAO.getPosFuncEstado(
					getIdPos(), funcionario.getFunCedula());
			posFuncEstado.setFesEstado("A");
			facOracleDAO.getHibernateTemplate().update(posFuncEstado); // se usa el facOracleDao

			logger.info(" ===== FUNCIONARIO ACTUALIZADO   ======================= ");
			logger.info("peopleEntity.getPersonId() = " + peopleEntity.getPersonId());
			logger.info("funcionario.getFunCedula() = " + funcionario.getFunCedula());
			logger.info("funcionario.getFunNombres() = " + funcionario.getFunNombres());
			logger.info("funcionario.getFunApellidos() = " + funcionario.getFunApellidos());
			logger.info(" ======================================================= ");
		}

	}

    /**
     * inactiva cliente
     * @param funcionario f
     */
    public void inactiveCustormer(VPosFuncionarios funcionario){

       // OBTENGO EL CUSTOMER
        PosFuncEstado posFuncEstado = facOracleDAO.getPosFuncEstado(
                getIdPos(), funcionario.getFunCedula());
        if (posFuncEstado.getFesIdFuncionario()!=null) { // DE PRONTO VAYA SIN ID EN MYSQL
            PhpposCustomersEntity customersEntity = getCustomer(posFuncEstado.getFesIdFuncionario());

            if (customersEntity != null) { // YA HA SIDO DESACTIVADO
                getHibernateTemplate().delete(customersEntity);

                logger.info(" ===== FUNCIONARIO INACTIVADO    ======================= ");
                logger.info("funcionario.getFunId() = " + posFuncEstado.getFesIdFuncionario());
                logger.info("funcionario.getFunCedula() = " + funcionario.getFunCedula());
                logger.info("funcionario.getFunNombres() = " + funcionario.getFunNombres());
                logger.info("funcionario.getFunApellidos() = " + funcionario.getFunApellidos());
                logger.info(" ======================================================= ");
            }
        }

        posFuncEstado.setFesEstado("I");
        facOracleDAO.getHibernateTemplate().update(posFuncEstado); // se usa el facOracleDao
    }

    /**
     * crea nuevo iten en el Pos
     * @param itemOracle i
	 * @param idPos      ip
     */
    public void saveNewItem(PosListaPrecio itemOracle,
                            int idPos){
		if (itemOracle.getPcaPrecioVenta() == null) {
			logger.info(" ========  ITEM NO CREADO Precio de Venta NULO  ======== ");
			logger.info("itemOracle.getPcaIdElemento() = " + itemOracle.getPcaIdElemento());
			logger.info("itemOracle.getPcaDescripcion() = " + itemOracle.getPcaDescripcion());
			logger.info("itemOracle.getPcaPrecioVenta() = " + itemOracle.getPcaPrecioVenta());
			logger.info("itemOracle.getPcaCantidad() = " + itemOracle.getPcaCantidad());
			logger.info(" ======================================================= ");
		} else if (itemOracle.getPcaPrecioVenta() == 0) {
			logger.info(" ========  ITEM NO CREADO Precio de Venta en Cero (0) == ");
			logger.info("itemOracle.getPcaIdElemento() = " + itemOracle.getPcaIdElemento());
			logger.info("itemOracle.getPcaDescripcion() = " + itemOracle.getPcaDescripcion());
			logger.info("itemOracle.getPcaPrecioVenta() = " + itemOracle.getPcaPrecioVenta());
			logger.info("itemOracle.getPcaCantidad() = " + itemOracle.getPcaCantidad());
			logger.info(" ======================================================= ");
		} else {
			// PREGUNTO EN ORACLE SI EL ITEM DE MYSQL EXISTE
			if (itemOracle.getPcaIdElemento() == null){ // EL ITEM NO EXISTE EN EL POS
				// CREAR UNO NUEVO
				PhpposItemsEntity itemPos = new PhpposItemsEntity();

				itemPos.setName(itemOracle.getPcaDescripcion());
				itemPos.setCategory("producto");
				itemPos.setSupplierId(10); // TODO APLICAR UN CRITERIO MEJOR A ESTE ASUNTO
				itemPos.setItemNumber(String.valueOf(System.currentTimeMillis()));
				itemPos.setDescription(itemOracle.getPcaDescripcion());
				itemPos.setCostPrice(itemOracle.getPcaPrecioVenta());
				itemPos.setUnitPrice(itemOracle.getPcaPrecioVenta());
				itemPos.setQuantity(itemOracle.getPcaCantidad());
				itemPos.setReorderLevel(1);

				Integer idItemPos = (Integer) getHibernateTemplate().save(itemPos);

				// LOG ENTRADAS

				itemOracle.setPcaIdElemento(idItemPos);
				saveLogEntrada(itemOracle, "N", idPos);

				// JOIN CON LA TABLA LISTA PRECIOS DE SINFAD

				itemOracle.setPcaIdElemento(idItemPos);
				// ACTUALIZO EN CERO (0) EN ORACLE
				itemOracle.setPcaCantidad(0);
				itemOracle.setPcaEstado("A");
				facOracleDAO.getHibernateTemplate().update(itemOracle); // se usa el facOracleDao

				// CREAR EL IMPUESTO - INICIALMENTE CERO( 0 )

				PhpposItemsTaxesEntity taxesItemPos = new PhpposItemsTaxesEntity();

				taxesItemPos.setItemId(idItemPos);
				taxesItemPos.setName("IVA");
				taxesItemPos.setPercent(0);

				getHibernateTemplate().save(taxesItemPos);

				logger.info(" ");
				logger.info(" ========  ITEM CREADO  =============================== ");
				logger.info("idItemPos = " + idItemPos);
				logger.info("itemPos.getDescription() = " + itemPos.getDescription());
				logger.info("itemPos.getUnitPrice() = " + itemPos.getUnitPrice());
				logger.info("itemPos.getQuantity() = " + itemPos.getQuantity());
				logger.info(" ======================================================= ");
				logger.info(" ");


			}  // END IF EXISTE ITEM EN POS
		}
	}
    /**
     * actualiza el item en el POS
     * @param itemOracle i
     */
    public void updateItem(PosListaPrecio itemOracle,
                           int idPos){
		// PREGUNTO SI EL ID DEL ITEM VIENE EN NULO
        if (itemOracle.getPcaIdElemento() != null) {
			//TRAIGO EL ITEM POS SEGUN EL PCIDELEMENTO DE ORACLE
            PhpposItemsEntity itemPos = getItemPos(itemOracle.getPcaIdElemento());

			if (itemPos != null) {  // SI EXISTE EN EL POS

				Session hbSession = getSession();        // SESSION MYSQL
				Transaction ts = hbSession.beginTransaction();

				itemPos.setName(itemOracle.getPcaDescripcion());
				itemPos.setDescription(itemOracle.getPcaDescripcion());
				itemPos.setQuantity(itemPos.getQuantity() + itemOracle.getPcaCantidad());
				itemPos.setCostPrice(itemOracle.getPcaPrecioVenta());
				itemPos.setUnitPrice(itemOracle.getPcaPrecioVenta());

				getHibernateTemplate().update(itemPos);

				boolean successMySQL = false;
				boolean successOracle = false; // se usa el facOracleDao

				try {
					// ACTUALIZO EN CERO (0) EN ORACLE
					successOracle = facOracleDAO.updatePosListaPrecioCeroA(itemOracle);
					successMySQL = true;
				} catch (Exception e) {
					e.printStackTrace();  //
				} finally {
					if (successMySQL && successOracle) {
						ts.commit();
						hbSession.flush();
						hbSession.close();
						// LOG ENTRADAS
						saveLogEntrada(itemOracle, "U", idPos);

						logger.info(" ");
						logger.info(" ========  ITEM ACTUALIZADO  =========================== ");
						logger.info("itemPos.getItemId() = " + itemPos.getItemId());
						logger.info("itemPos.getDescription() = " + itemPos.getDescription());
						logger.info("itemPos.getUnitPrice() = " + itemPos.getUnitPrice());
						logger.info("itemPos.getQuantity() = " + itemPos.getQuantity());
						logger.info(" ======================================================= ");
						logger.info(" ");
					} else {
						ts.rollback();
						hbSession.flush();
						hbSession.close();
					}
				}
			} else {  // NO EXISTE EN EL POS
				logger.info(" ========  ITEM CON PROBLEMAS DE UPDATE  =============== ");
				logger.info(" ========  NO EXISTE ITEM EN EL POS CON: =============== ");
				logger.info("itemOracle.getPcaIdElemento() = " + itemOracle.getPcaIdElemento());
				logger.info("itemOracle.getPcaDescripcion() = " + itemOracle.getPcaDescripcion());
				logger.info("itemOracle.getPcaPosId() = " + itemOracle.getPcaPosId());
				logger.info("idPos = " + idPos);
				logger.info(" ======================================================= ");
			}
		} else {
			logger.info(" ========  ITEM CON getPcaIdElemento NULL  ============= ");
			logger.info("itemOracle.getPcaIdElemento() = " + itemOracle.getPcaIdElemento());
			logger.info("itemOracle.getPcaDescripcion() = " + itemOracle.getPcaDescripcion());
			logger.info("itemOracle.getPcaPosId() = " + itemOracle.getPcaPosId());
			logger.info("idPos = " + idPos);
			logger.info(" ======================================================= ");
		}
    }

    /**
     * Servicio de sincronizacion llamado por el CRON
     */
    public void sincronizaTodo(){
//        logger.info(" ========  ITEM ACTUALIZADO  =========================== ");
        System.out.println(" ===============  CLIENTES  ========================================== ");
        logger.info(" ======================================================= ");
        sincronizaClientes();
        System.out.println(" ===============  ARTICULOS  ========================================= ");
        sincronizaArticulos();
        System.out.println(" ===============  VENTAS  ============================================ ");
        sincronizaVentas();
        System.out.println(" ===============  SOLICITUDES  ======================================= ");
        try {
            solicitudes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sincronizaClientes(){
        PhpposAppConfigEntity appConfig = getAppConfig("id_pos");
        if (appConfig != null) {
            List<VPosFuncionarios> vPosFuncionarios = facOracleDAO.getVPosFuncionarios(
                    Integer.parseInt(appConfig.getValue()));
            for(VPosFuncionarios funcionario: vPosFuncionarios){
                try {
                    char c = funcionario.getFesEstado().toCharArray()[0];
                    logger.debug("c = " + c);
                    switch (c){
                        case 'N':
                            saveNewCustomer(funcionario);
                            break;
                        case 'U':
                            updateCustomer(funcionario);
                            break;
                        case 'S':
                            inactiveCustormer(funcionario);
                            break;
                    }
                } catch (DataAccessException e) {
//                e.printStackTrace();
                    logger.info("e.getMessage() = " + e.getMessage());
                } catch (Exception e){
                    e.printStackTrace();
                    logger.info("e.getMessage() = " + e.getMessage());
                }
            }
        }
    }


    /**
     * crea cliente en POS segun los Funcionarios FAC
     */
    public void sincronizaClientesOLD(){
        // TRAIGO TODOS LOS FUNCIONARIOS
        List<PosFuncionarios> funcionarios = facOracleDAO.getFuncionarios();
        for (PosFuncionarios funcionario : funcionarios) {
            try {
                char c = funcionario.getFunEstado().toCharArray()[0];
                logger.debug("c = " + c);
                switch (c){
                    case 'N':
//                        saveNewCustomer(funcionario);
                        break;
                    case 'U':
//                        updateCustomer(funcionario);
                        break;
                    case 'S':
//                        inactiveCustormer(funcionario);
                        break;
                }
                // traigo un People segun Cedula

            } catch (DataAccessException e) {
//                e.printStackTrace();
                logger.info("e.getMessage() = " + e.getMessage());
            } catch (Exception e){
                e.printStackTrace();
                logger.info("e.getMessage() = " + e.getMessage());
            }
        }
    }

    public void saveLogEntrada(PosListaPrecio itemOracle,
                               String tipo,
                               int idPos){
		logger.info("itemOracle.getPcaIdElemento() = " + itemOracle.getPcaIdElemento());
		logger.info("idPos = " + idPos);
		logger.info("tipo = " + tipo);

		try {
			PhpposLogEntrada logEntrada = new PhpposLogEntrada();
			logEntrada.setFechaCreacion(new java.sql.Timestamp(System.currentTimeMillis()));
			logEntrada.setTipo(tipo);
			logEntrada.setIdPos(idPos);
			logEntrada.setIdElemento(itemOracle.getPcaIdElemento());
			logEntrada.setFecha(Integer.parseInt(f.format(new java.util.Date())));
			logEntrada.setHora(Integer.parseInt(h.format(new java.util.Date())));
			logEntrada.setCantidad(itemOracle.getPcaCantidad());
			getHibernateTemplate().save(logEntrada);
		} catch (NumberFormatException e) {
			e.printStackTrace();  
		} catch (DataAccessException e) {
			e.printStackTrace();  
		} catch (HibernateException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

    public void sincronizaArticulos(){
		// TRAIGO EL ID POS
        PhpposAppConfigEntity appConfig = getAppConfig("id_pos");
        // PREGUNTAR SI EL ID POS ESTA CONFIGURADO
		if (appConfig != null){
            int idPos = Integer.parseInt(appConfig.getValue());
			logger.info("****>>> idPos = " + idPos);
			// TRAIGO LA LISTA DE PRECIOS DE ORACLE
            List<PosListaPrecio> listaPrecios = facOracleDAO.getListaPrecios(idPos);
            logger.info("listaPrecios = " + listaPrecios.size());
            for (PosListaPrecio itemOracle : listaPrecios) {
                try {
                    char estadoItemOracle = itemOracle.getPcaEstado().toCharArray()[0];
					logger.info("****>>> itemOracle.getPcaDescripcion() = " + itemOracle.getPcaDescripcion()+"\t****>>> estadoItemOracle = " + estadoItemOracle);
                    switch (estadoItemOracle){
                        case 'N':
                            saveNewItem(itemOracle, idPos);
                            break;
                        case 'U':
                            updateItem(itemOracle, idPos);
                            break;
                    
                    }
                } catch (DataAccessException e){
                    logger.info("e.getMessage() = " + e.getMessage());
                } catch (Exception e){
                    e.printStackTrace();
                    logger.info("e.getMessage() = " + e.getMessage());
                }
            }
        }
    }

    Format f = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat h = new SimpleDateFormat("HHmmss");

    public void sincronizaVentas(){

        PhpposAppConfigEntity idPos = getAppConfig("id_pos");

        for (PhpposSalesEntity movimientoPos : getMovimientosPos()) {
            String tipoMovimiento = getTipoMovimientoPos(movimientoPos.getTipoMovimiento());
            logger.info("tipoMovimiento = " + tipoMovimiento + "\tmovimientoPos.getTipoMovimiento() = " + movimientoPos.getTipoMovimiento());

            PosRecibeDePos movimientoOracle;

			if (movimientoPos.getCustomerId() == null) {
				movimientoPos.setCustomerId(10);
			}

            // TRAIGO EL CLIENTE PARA SACAR SU CEDULA
            PhpposCustomersEntity customersEntity = getCustomer(movimientoPos.getCustomerId());

            // LA CEDULA ESTA EN EL ACCOUNT NUMBER
            long fesCedula = 0;
            try {
                fesCedula = Long.parseLong(customersEntity.getAccountNumber());
            } catch (NumberFormatException e) {
                logger.info("e.getMessage() = " + e.getMessage());
            }
            facOracleDAO.estadoUTodosFuncionarios(
                    fesCedula
            );// PARA QUE LO REPLIQUE EN TODOS LOS POS 

            for (PhpposSalesItemsEntity itemMovPos: getItemsDeMovimientoPos(movimientoPos)) {
                logger.info("itemId() = " + itemMovPos.getItemId()+"\titemPrice() = " + itemMovPos.getItemUnitPrice());

                movimientoOracle = new PosRecibeDePos();

                movimientoOracle.setPrpPosId(Integer.parseInt(idPos.getValue()));
                movimientoOracle.setPrpMoaTipo(tipoMovimiento);
                movimientoOracle.setPrpMoaConsec(movimientoPos.getSaleId()); // ID DE VENTA
                movimientoOracle.setPrpIdElemento(itemMovPos.getItemId());

                movimientoOracle.setPrpFecha(Integer.parseInt(f.format(movimientoPos.getSaleTime())));
                logger.debug("movimientoPos.getSaleTime() = " + movimientoPos.getSaleTime());
                logger.debug("movimientoOracle.getPrpFecha() = " + movimientoOracle.getPrpFecha());
                movimientoOracle.setPrpIdFuncionario(movimientoPos.getCustomerId());  // EL QUE COMPRA

                movimientoOracle.setPrpFormaPago(
//                        movimientoPos.getPaymentType().length()>0?movimientoPos.getPaymentType().substring(0,1):"N"
                        getTipoPago(movimientoPos.getPaymentType())
                );
                logger.debug("movimientoOracle.getPrpFormaPago() = " + movimientoOracle.getPrpFormaPago());
                movimientoOracle.setPrpEstado("N");
                movimientoOracle.setPrpCantidad(itemMovPos.getQuantityPurchased());
                movimientoOracle.setPrpValorTotal(
                        (int)(itemMovPos.getQuantityPurchased() * itemMovPos.getItemUnitPrice())
                );
                logger.debug("movimientoOracle.getPrpValorTotal() = " + movimientoOracle.getPrpValorTotal());

                //* TRAIGO EL EMPLEADO PEOPLE
                PhpposPeopleEntity vendedorPeople = getPeopleEntity(movimientoPos.getEmployeeId());
                movimientoOracle.setPrpCcResponsable(Integer.parseInt(vendedorPeople.getPhoneNumber()));
                movimientoOracle.setPrpUsuario("");
                movimientoOracle.setPrpTerminal("");
                movimientoOracle.setPrpFecmod(new Date(System.currentTimeMillis()));

                facOracleDAO.getHibernateTemplate().save(movimientoOracle);


                // ACTUALIZO EL POS
                movimientoPos.setEstadoMovimiento("A");
                getHibernateTemplate().update(movimientoPos);

            }

        }
    }

    /**
     * movimientos del Pos donde el estado sea N
     * @return lista
     */
    public List<PhpposSalesEntity> getMovimientosPos(){
        return getHibernateTemplate().find("from PhpposSalesEntity where estadoMovimiento = 'N' ");
    }

    /**
     * Lista de Movimientos entre fechas,... para luego manejarla con <br>
     * <b>getItemsDeMovimientoPos</b>
     * @param desde d
     * @param hasta h
     * @return lista
     */
    public List<PhpposSalesEntity> getMovimientosPosFromFecha(Date desde,
                                                              Date hasta){
        DetachedCriteria criteria = DetachedCriteria.forClass(PhpposSalesEntity.class);
        criteria.add(Restrictions.between("saleTime", desde, hasta));
        return getHibernateTemplate().findByCriteria(criteria);
    }



    /**
     * Lista de items en un movimiento
     * @param movimientoPos m
     * @return lista de items
     */
    public List<PhpposSalesItemsEntity> getItemsDeMovimientoPos(PhpposSalesEntity movimientoPos){
        return getHibernateTemplate().find(
                "from PhpposSalesItemsEntity where saleId = ?",
                movimientoPos.getSaleId());
    }

    /**
     * <br> Venta -> sale -> V
     * <br> Baja -> fall -> B
     * <br> Devolucion -> return -> D
     * <br>
     * @param tipo posT
     * @return oracleT
     */
    public String getTipoMovimientoPos(String tipo){
        if (tipo.equals("sale")){
            return "V";
        } else if (tipo.equals("fall")){
            return "B";
        } else if(tipo.equals("return")){
            return "D";
        }
        return "";
    }

    /**
     * <br> Efectivo -> E
     * <br> Vale -> V
     * <br> Voucher -> O
     * <br> Cuenta de Cobro -> C
     * <br> Prorrateo -> P
     * <br> Cargo a Fondo -> F
     * <br>
     * @param tipo t
     * @return Letra
     */
    public String getTipoPago(String tipo){
        if (tipo.equals("Efectivo")){
            return "E";
        } else if (tipo.equals("Vale")){
            return "V";
        } else if(tipo.equals("Voucher")){
            return "O";
        } else if(tipo.equals("Cuenta de Cobro")){
            return "C";
        } else if(tipo.equals("Prorrateo")){
            return "P";
        } else if(tipo.equals("Cargo a Fondo")){
            return "F";
        }
        return "N";
    }

    /**
     * trae un cliente del POs segun el id de la persona
     * @param idPeople id p en cliente
     * @return cliente
     */
    public PhpposCustomersEntity getCustomer(int idPeople){
        return (PhpposCustomersEntity) getHibernateTemplate().get(PhpposCustomersEntity.class, idPeople);
    }

    /**
     * trae la key de la cong de la app
     * @param key k
     * @return config
     */
    public PhpposAppConfigEntity getAppConfig(String key){
        return (PhpposAppConfigEntity) getHibernateTemplate().get(PhpposAppConfigEntity.class, key);
    }

    public String getMD5(String yourString){
//        yourString = "123456789";
		if (yourString != null) {
			byte[] bytesOfMessage = yourString.getBytes();

			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] thedigest = md.digest(bytesOfMessage);

				StringBuffer hexString = new StringBuffer();
				for (byte aThedigest : thedigest) {
					String hex = Integer.toHexString(0xff & aThedigest);
					if (hex.length() == 1) hexString.append('0');
					hexString.append(hex);
				}
				logger.info("Digest(in hex format):: " + hexString.toString());
				return hexString.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return "";
			}
		} else {
			return "";
		}
	}

    public int getIdPos(){
        PhpposAppConfigEntity appConfig = getAppConfig("id_pos");
        return Integer.parseInt(appConfig.getValue());
    }

    public void solicitudes(){
        // REVISA SOLICITUDES
        List<PosSolicitud> solicitudes = facOracleDAO.getSolicitudesNuevasFromPos(getIdPos());
        for (PosSolicitud solicitud : solicitudes){
            logger.info("solicitud.getIdSolicitud() = " + solicitud.getIdSolicitud() +
                    "\tsolicitud.getSolTipoSolicitud() = " + solicitud.getSolTipoSolicitud());
			facOracleDAO.solicitudRealizada(solicitud.getIdSolicitud());
            if (solicitud.getSolTipoSolicitud().equals("S")){ // CONTROL DE SALDOS - CANTIDADES
                if (solicitud.getSolIdElemento() == null){ // TRAIGO TODOS LOS ELEMENTOS
                    List<PhpposItemsEntity> items = getItemsEntities();
                    for(PhpposItemsEntity item : items){
                        try {
                            PosControlSaldos controlSaldo =
                                    getPosControlSaldosFromPosItem(item, solicitud.getIdSolicitud());
                            facOracleDAO.getHibernateTemplate().save(controlSaldo);
                        } catch (DataAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else { // UN SOLO ELEMENTO
                    try {
                        PosControlSaldos controlSaldo = getPosControlSaldosFromPosItem(
                                getItemPos(solicitud.getSolIdElemento()),
                                solicitud.getIdSolicitud()
                        );
                        facOracleDAO.getHibernateTemplate().save(controlSaldo);
                    } catch (DataAccessException e) {
                        e.printStackTrace();
                    }
                }
            } else if (solicitud.getSolTipoSolicitud().equals("T")){ // TRANSACCIONES
                List<PhpposSalesEntity> movimientos = getMovimientosPosFromFecha(solicitud.getSolFechaDesde(), solicitud.getSolFechaHasta());
                for(PhpposSalesEntity movimientoPos : movimientos){
                    String tipoMovimiento = getTipoMovimientoPos(movimientoPos.getTipoMovimiento());
                    // TRAIGO EL EMPLEADO PARA SACAR SU CEDULA
                    PhpposPeopleEntity peopleEntity = getPeopleEntity(movimientoPos.getEmployeeId());
                    // LA CEDULA ESTA EN EL PHONE NUMBER
                    long fesCedula = 0;
                    try {
                        fesCedula = Long.parseLong(peopleEntity.getPhoneNumber());
                    } catch (NumberFormatException e) {
                        logger.info("e.getMessage() = " + e.getMessage());
                    }
                    // RECORRO LOS ITEMS
                    for (PhpposSalesItemsEntity itemMovPos: getItemsDeMovimientoPos(movimientoPos)) {
                        try {
                            PosControlTransacciones transaccion =
                                    getPosControlTransaccionesFromItemDeMovimiento(
                                            itemMovPos,
                                            fesCedula,
                                            movimientoPos,
                                            solicitud
                                    );
							facOracleDAO.getHibernateTemplate().save(transaccion);
                        } catch (DataAccessException e) {
                            e.printStackTrace();
                        }
                    } // END FOR ITEMS FROM MOVIMIENTO

                } // END FOR MOVIMIENTOS
            }           // END IF TRANSACCIONES
                else if (solicitud.getSolTipoSolicitud().equals("E")){  // LOG ENTRADA
                System.out.println("solicitud.getSolFechaDesde() = " + solicitud.getSolFechaDesde());
                System.out.println("solicitud.getSolFechaHasta() = " + solicitud.getSolFechaHasta());

                List<PhpposLogEntrada> logEntradas = getPosLogEntradasFromFecha(solicitud.getSolFechaDesde(), solicitud.getSolFechaHasta());
                logger.info("logEntradas.size() = " + logEntradas.size());
                for (PhpposLogEntrada logEntrada : logEntradas){
                    PosLogEntradas oracleLogEntrada = new PosLogEntradas();
                    oracleLogEntrada.setIdPos(logEntrada.getIdPos());
                    oracleLogEntrada.setIdElemento(logEntrada.getIdElemento());
                    oracleLogEntrada.setFecha(logEntrada.getFecha());
                    oracleLogEntrada.setHora(logEntrada.getHora());
                    oracleLogEntrada.setCantidad(logEntrada.getCantidad());
                    oracleLogEntrada.setTipo(logEntrada.getTipo());
                    oracleLogEntrada.setIdSolicitud(solicitud.getIdSolicitud());
                    facOracleDAO.getHibernateTemplate().save(oracleLogEntrada);
                }

            }
        }
    }

    public List<PhpposLogEntrada> getPosLogEntradasFromFecha(Date desde,
                                                           Date hasta){
        DetachedCriteria criteria = DetachedCriteria.forClass(PhpposLogEntrada.class);
        criteria.add(Restrictions.between("fechaCreacion", desde, hasta));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    public PosControlTransacciones getPosControlTransaccionesFromItemDeMovimiento(PhpposSalesItemsEntity itemMovPos,
                                                                                  long fesCedula,
                                                                                  PhpposSalesEntity movimientoPos,
                                                                                  PosSolicitud solicitud){
        PosControlTransacciones transaccion = new PosControlTransacciones();

        transaccion.setCtrCantidad(itemMovPos.getQuantityPurchased());
        transaccion.setCtrCcResponsable(fesCedula);
        transaccion.setCtrEstado(movimientoPos.getEstadoMovimiento());
        transaccion.setCtrFecha(Integer.parseInt(f.format(movimientoPos.getSaleTime())));
        transaccion.setCtrFormaPago(getTipoPago(movimientoPos.getPaymentType()));
        transaccion.setCtrIdElemento(itemMovPos.getItemId());
        transaccion.setCtrIdFuncionario(movimientoPos.getCustomerId());
        transaccion.setCtrMoaConsec(movimientoPos.getSaleId()); //  ID DEL MOVIMIENTO
        transaccion.setCtrMoaTipo(getTipoMovimientoPos(movimientoPos.getTipoMovimiento()));
        transaccion.setCtrPosId(getIdPos());
        transaccion.setCtrValorTotal(
                (int)(itemMovPos.getQuantityPurchased() * itemMovPos.getItemUnitPrice())
        );
        transaccion.setIdSolicitud(solicitud.getIdSolicitud());

        return transaccion;
    }

    /**
     * get Control Saldo para enviar a Oracle
     * @param item i
     * @param idSolicitud sol
     * @return controlSaldo para Oracle
     */
    public PosControlSaldos getPosControlSaldosFromPosItem(PhpposItemsEntity item,
                                                           int idSolicitud){
        PosControlSaldos controlSaldo = new PosControlSaldos();

        controlSaldo.setCsaCantidad(item.getQuantity());
        controlSaldo.setCsaFechaHora(new Date(System.currentTimeMillis()));
        controlSaldo.setCsaIdElemento(item.getItemId());
        controlSaldo.setCsaPosId(getIdPos());
        controlSaldo.setCsaValorTotal(item.getUnitPrice());
        controlSaldo.setIdSolicitud(idSolicitud);

        return controlSaldo;
    }



	public void testTransaction(){
		Session hbSession = getSession();

		Transaction ts = hbSession.beginTransaction();

		PhpposItemsEntity items3 = (PhpposItemsEntity) hbSession.get(PhpposItemsEntity.class, 3);
		System.out.println("items3.getName() = " + items3.getName());
		items3.setName( (new java.util.Date()).toString());
		hbSession.update(items3);

		ts.commit();
		System.out.println("con comit");
		hbSession.close();
	}
}









