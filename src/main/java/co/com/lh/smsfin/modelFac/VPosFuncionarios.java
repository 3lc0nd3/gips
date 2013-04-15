package co.com.lh.smsfin.modelFac;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 13/06/2012
 * Time: 04:11:42 PM
 */
@Entity
@Table( name = "V_POS_FUNCIONARIOS")
public class VPosFuncionarios {
    private Integer fesIdFuncionario;

    @Basic
    @Column(name = "FES_ID_FUNCIONARIO")
    public Integer getFesIdFuncionario() {
        return fesIdFuncionario;
    }

    public void setFesIdFuncionario(Integer fesIdFuncionario) {
        this.fesIdFuncionario = fesIdFuncionario;
    }

    private Integer fesIdPos;

    @Basic
    @Column(name = "FES_ID_POS")
    public Integer getFesIdPos() {
        return fesIdPos;
    }

    public void setFesIdPos(Integer fesIdPos) {
        this.fesIdPos = fesIdPos;
    }

    private String fesEstado;

    @Basic
    @Column(name = "FES_ESTADO")
    public String getFesEstado() {
        return fesEstado;
    }

    public void setFesEstado(String fesEstado) {
        this.fesEstado = fesEstado;
    }


    private long funCedula;

    @Id
    @Column(name = "FUN_CEDULA")
    public long getFunCedula() {
        return funCedula;
    }

    public void setFunCedula(long funCedula) {
        this.funCedula = funCedula;
    }

    private String funNombres;

    @Basic
    @Column(name = "FUN_NOMBRES")
    public String getFunNombres() {
        return funNombres;
    }

    public void setFunNombres(String funNombres) {
        this.funNombres = funNombres;
    }

    private String funApellidos;

    @Basic
    @Column(name = "FUN_APELLIDOS")
    public String getFunApellidos() {
        return funApellidos;
    }

    public void setFunApellidos(String funApellidos) {
        this.funApellidos = funApellidos;
    }

    private Integer funEndeudamiento;

    @Basic
    @Column(name = "FUN_ENDEUDAMIENTO")
    public Integer getFunEndeudamiento() {
        return funEndeudamiento;
    }

    public void setFunEndeudamiento(Integer funEndeudamiento) {
        this.funEndeudamiento = funEndeudamiento;
    }

    private int funAaaa;

    @Basic
    @Column(name = "FUN_AAAA")
    public int getFunAaaa() {
        return funAaaa;
    }

    public void setFunAaaa(int funAaaa) {
        this.funAaaa = funAaaa;
    }

    private BigInteger funMm;

    @Basic
    @Column(name = "FUN_MM")
    public BigInteger getFunMm() {
        return funMm;
    }

    public void setFunMm(BigInteger funMm) {
        this.funMm = funMm;
    }

    private String funUnidad;

    @Basic
    @Column(name = "FUN_UNIDAD")
    public String getFunUnidad() {
        return funUnidad;
    }

    public void setFunUnidad(String funUnidad) {
        this.funUnidad = funUnidad;
    }

    private Integer funClave;

    @Basic
    @Column(name = "FUN_CLAVE")
    public Integer getFunClave() {
        return funClave;
    }

    public void setFunClave(Integer funClave) {
        this.funClave = funClave;
    }

    private String funHabilitado;

    @Basic
    @Column(name = "FUN_HABILITADO")
    public String getFunHabilitado() {
        return funHabilitado;
    }

    public void setFunHabilitado(String funHabilitado) {
        this.funHabilitado = funHabilitado;
    }

    private Integer funConsumoUltimoMes;

    @Basic
    @Column(name = "FUN_CONSUMO_ULTIMO_MES")
    public Integer getFunConsumoUltimoMes() {
        return funConsumoUltimoMes;
    }

    public void setFunConsumoUltimoMes(Integer funConsumoUltimoMes) {
        this.funConsumoUltimoMes = funConsumoUltimoMes;
    }

    private Date funFechaCambioClave;

    //@Basic
    //@Column(name = "FUN_FECHA_CAMBIO_CLAVE")
	@Transient
    public Date getFunFechaCambioClave() {
        return funFechaCambioClave;
    }

    public void setFunFechaCambioClave(Date funFechaCambioClave) {
        this.funFechaCambioClave = funFechaCambioClave;
    }

    private String funGrado;

    @Basic
    @Column(name = "FUN_GRADO")
    public String getFunGrado() {
        return funGrado;
    }

    public void setFunGrado(String funGrado) {
        this.funGrado = funGrado;
    }

    private Integer funSueldo;

    @Basic
    @Column(name = "FUN_SUELDO")
    public Integer getFunSueldo() {
        return funSueldo;
    }

    public void setFunSueldo(Integer funSueldo) {
        this.funSueldo = funSueldo;
    }

    private String funTipo;

    @Basic
    @Column(name = "FUN_TIPO")
    public String getFunTipo() {
        return funTipo;
    }

    public void setFunTipo(String funTipo) {
        this.funTipo = funTipo;
    }

    private Integer funNitCxc;

    //@Basic
    //@Column(name = "FUN_NIT_CXC")
	@Transient
    public Integer getFunNitCxc() {
        return funNitCxc;
    }

    public void setFunNitCxc(Integer funNitCxc) {
        this.funNitCxc = funNitCxc;
    }

    private String funFormaPago;

    @Basic
    @Column(name = "FUN_FORMA_PAGO")
    public String getFunFormaPago() {
        return funFormaPago;
    }

    public void setFunFormaPago(String funFormaPago) {
        this.funFormaPago = funFormaPago;
    }

    private String funAportaFondo;

    @Basic
    @Column(name = "FUN_APORTA_FONDO")
    public String getFunAportaFondo() {
        return funAportaFondo;
    }

    public void setFunAportaFondo(String funAportaFondo) {
        this.funAportaFondo = funAportaFondo;
    }

}
