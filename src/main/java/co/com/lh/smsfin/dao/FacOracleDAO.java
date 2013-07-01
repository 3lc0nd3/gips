package co.com.lh.smsfin.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.dao.DataAccessException;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;

import co.com.lh.smsfin.modelFac.*;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 7/03/2012
 * Time: 12:18:56 PM
 */

@SuppressWarnings({
        "deprecation", "unchecked"
})
public class FacOracleDAO extends HibernateDaoSupport {


    /**
     * Pone estado 'U' a todos los funcionarios en todos los POS
     * @param cedula cedula Func
     */
    public void estadoUTodosFuncionarios(final long cedula){
        getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("update PosFuncEstado set fesEstado = 'U' where fesCedula = ?");
                query.setLong(0, cedula);
                query.executeUpdate();
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
	
	public void solicitudRealizada(final int idSolicitud){
		logger.info("Id Solicitud " + idSolicitud);
		getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("update PosSolicitud set solEstado = 'A' where idSolicitud = ?");
                query.setLong(0, idSolicitud);
                query.executeUpdate();
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
	}
	

    /**
     * trae el registro de la tabla de estados nueva
     * @param idPos pos
     * @param cedula funcionario
     * @return elemento Estado
     */
    public PosFuncEstado getPosFuncEstado(int idPos, long cedula){
        Object o[] = {idPos, cedula};
        List<PosFuncEstado> list = getHibernateTemplate().find(
                "from PosFuncEstado where fesIdPos = ? and fesCedula = ?",
                o
        );
        if (list.size()>0){
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     *
     * @param cedula c
     * @return funcionario
     */
    public PosFuncionarios getFuncionario(long cedula){
        return (PosFuncionarios) getHibernateTemplate().get(PosFuncionarios.class, cedula);
    }

    public List<PosFuncionarios> getFuncionarios(){
        return getHibernateTemplate().find("from PosFuncionarios ");
    }

    /**
     * Trae todos los funcionarios
     * @param posID id pos
     * @return lista segun vista
     */
    public List<VPosFuncionarios> getVPosFuncionarios(int posID){
        return getHibernateTemplate().find("from VPosFuncionarios where fesIdPos = ?", posID);
    }

    /**
     * lista de precios segun el id pos. Hay varios POS por Unidad.
     * @param posID id del punto de venta.
     * @return lista
     */
    public List<PosListaPrecio> getListaPrecios(int posID){
		logger.info("posID = " + posID);
        return getHibernateTemplate().find("from PosListaPrecio where pcaPosId = ?", posID);
    }

    /**
     * trae solicitudes en estado X <br>
     * segun el id pos. <br><br>
     * Las solicitudes son independientes a cada <b>POS</b>
     * @param idPos es para cada pos
     * @return l
     */
    public List<PosSolicitud> getSolicitudesNuevasFromPos(int idPos){
        try {
            return getHibernateTemplate().find(
                    "from PosSolicitud where solEstado = 'X' and solPosId = ? ", idPos);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<PosSolicitud>();
        }
    }

	public PosListaPrecio updatePosListaPrecioCeroA(final PosListaPrecio itemOracle){

		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(
						"update PosListaPrecio set pcaCantidad = 0, pcaEstado = 'A'" +
								" where pcaPosId = ? and pcaIdElemento = ? "
				);
				query.setInteger(0, itemOracle.getPcaPosId());
				query.setInteger(1, itemOracle.getPcaIdElemento());
				query.executeUpdate();
				return null;  //To change body of implemented methods use File | Settings | File Templates.
			}
		});
		itemOracle.setPcaCantidad(0);
		itemOracle.setPcaEstado("A");

		return itemOracle;
	}


}
