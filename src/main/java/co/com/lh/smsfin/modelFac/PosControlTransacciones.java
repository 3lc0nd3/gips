package co.com.lh.smsfin.modelFac;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 20/06/2012
 * Time: 06:01:25 PM
 */
@Entity
@Table( name = "POS_CONTROL_TRANSACCIONES")
public class PosControlTransacciones {

    private int idPosControlTransacciones;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "CTR_ID")
    public int getIdPosControlTransacciones() {
        return idPosControlTransacciones;
    }

    public void setIdPosControlTransacciones(int idPosControlTransacciones) {
        this.idPosControlTransacciones = idPosControlTransacciones;
    }

    private int idSolicitud;

    @Basic
    @Column(name = "CTR_ID_SOLICITUD")
    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    private int ctrPosId;

    @Basic
    @Column(name = "CTR_POS_ID")
    public int getCtrPosId() {
        return ctrPosId;
    }

    public void setCtrPosId(int ctrPosId) {
        this.ctrPosId = ctrPosId;
    }

    private String ctrMoaTipo;

    @Basic
    @Column(name = "CTR_MOA_TIPO")
    public String getCtrMoaTipo() {
        return ctrMoaTipo;
    }

    public void setCtrMoaTipo(String ctrMoaTipo) {
        this.ctrMoaTipo = ctrMoaTipo;
    }

    private int ctrMoaConsec;

    @Basic
    @Column(name = "CTR_MOA_CONSEC")
    public int getCtrMoaConsec() {
        return ctrMoaConsec;
    }

    public void setCtrMoaConsec(int ctrMoaConsec) {
        this.ctrMoaConsec = ctrMoaConsec;
    }

    private int ctrFecha;

    @Basic
    @Column(name = "CTR_FECHA")
    public int getCtrFecha() {
        return ctrFecha;
    }

    public void setCtrFecha(int ctrFecha) {
        this.ctrFecha = ctrFecha;
    }

    private int ctrIdElemento;

    @Basic
    @Column(name = "CTR_ID_ELEMENTO")
    public int getCtrIdElemento() {
        return ctrIdElemento;
    }

    public void setCtrIdElemento(int ctrIdElemento) {
        this.ctrIdElemento = ctrIdElemento;
    }

    private int ctrIdFuncionario;

    @Basic
    @Column(name = "CTR_ID_FUNCIONARIO")
    public int getCtrIdFuncionario() {
        return ctrIdFuncionario;
    }

    public void setCtrIdFuncionario(int ctrIdFuncionario) {
        this.ctrIdFuncionario = ctrIdFuncionario;
    }

    private String ctrFormaPago;

    @Basic
    @Column(name = "CTR_FORMA_PAGO")
    public String getCtrFormaPago() {
        return ctrFormaPago;
    }

    public void setCtrFormaPago(String ctrFormaPago) {
        this.ctrFormaPago = ctrFormaPago;
    }

    private String ctrEstado;

    @Basic
    @Column(name = "CTR_ESTADO")
    public String getCtrEstado() {
        return ctrEstado;
    }

    public void setCtrEstado(String ctrEstado) {
        this.ctrEstado = ctrEstado;
    }

    private int ctrCantidad;

    @Basic
    @Column(name = "CTR_CANTIDAD")
    public int getCtrCantidad() {
        return ctrCantidad;
    }

    public void setCtrCantidad(int ctrCantidad) {
        this.ctrCantidad = ctrCantidad;
    }

    private double ctrValorTotal;

    @Basic
    @Column(name = "CTR_VALOR_TOTAL")
    public double getCtrValorTotal() {
        return ctrValorTotal;
    }

    public void setCtrValorTotal(double ctrValorTotal) {
        this.ctrValorTotal = ctrValorTotal;
    }

    private long ctrCcResponsable;

    @Basic
    @Column(name = "CTR_CC_RESPONSABLE")
    public long getCtrCcResponsable() {
        return ctrCcResponsable;
    }

    public void setCtrCcResponsable(long ctrCcResponsable) {
        this.ctrCcResponsable = ctrCcResponsable;
    }
    
}
