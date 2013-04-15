package co.com.lh.smsfin.modelFac;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 12/03/2012
 * Time: 05:02:17 PM
 */
@Entity
@Table(name = "POS_FUNCIONARIOS")
public class PosFuncionarios {
    private Integer funId;

    @Basic
    @Column(name = "FUN_ID")
    public Integer getFunId() {
        return funId;
    }

    public void setFunId(Integer funId) {
        this.funId = funId;
    }

    private Long funCedula;

    @Id
    @Column(name = "FUN_CEDULA")
    public Long getFunCedula() {
        return funCedula;
    }

    public void setFunCedula(Long funCedula) {
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

    private Integer funAaaa;

    @Basic
    @Column(name = "FUN_AAAA")
    public Integer getFunAaaa() {
        return funAaaa;
    }

    public void setFunAaaa(Integer funAaaa) {
        this.funAaaa = funAaaa;
    }

    private Integer funMm;

    @Basic
    @Column(name = "FUN_MM")
    public Integer getFunMm() {
        return funMm;
    }

    public void setFunMm(Integer funMm) {
        this.funMm = funMm;
    }

    private String funUnidad;

//    @Basic
//    @Column(name = "FUN_UNIDAD")
    @Transient
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

    @Basic
    @Column(name = "FUN_FECHA_CAMBIO_CLAVE")
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

    private String funEstado;

    @Basic
    @Column(name = "FUN_ESTADO")
    public String getFunEstado() {
        return funEstado;
    }

    public void setFunEstado(String funEstado) {
        this.funEstado = funEstado;
    }

    private String funUsuario;

    @Basic
    @Column(name = "FUN_USUARIO")
    public String getFunUsuario() {
        return funUsuario;
    }

    public void setFunUsuario(String funUsuario) {
        this.funUsuario = funUsuario;
    }

    private String funTerminal;

    @Basic
    @Column(name = "FUN_TERMINAL")
    public String getFunTerminal() {
        return funTerminal;
    }

    public void setFunTerminal(String funTerminal) {
        this.funTerminal = funTerminal;
    }

    private Date funFecmod;

    @Basic
    @Column(name = "FUN_FECMOD")
    public Date getFunFecmod() {
        return funFecmod;
    }

    public void setFunFecmod(Date funFecmod) {
        this.funFecmod = funFecmod;
    }
}
