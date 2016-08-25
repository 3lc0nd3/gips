package co.com.lh.smsfin.modelFac;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 12/03/2012
 * Time: 05:02:26 PM
 */
@Entity
@Table(name = "POS_RECIBE_DE_POS")
@IdClass(co.com.lh.smsfin.modelFac.PosRecibeDePosPK.class)
public class PosRecibeDePos {
    private Integer prpPosId;

    @Id
    @Column(name = "PRP_POS_ID")
    public Integer getPrpPosId() {
        return prpPosId;
    }

    public void setPrpPosId(Integer prpPosId) {
        this.prpPosId = prpPosId;
    }

    private String prpMoaTipo;

    @Id
    @Column(name = "PRP_MOA_TIPO")
    public String getPrpMoaTipo() {
        return prpMoaTipo;
    }

    public void setPrpMoaTipo(String prpMoaTipo) {
        this.prpMoaTipo = prpMoaTipo;
    }

    private Integer prpMoaConsec;

    @Id
    @Column(name = "PRP_MOA_CONSEC")
    public Integer getPrpMoaConsec() {
        return prpMoaConsec;
    }

    public void setPrpMoaConsec(Integer prpMoaConsec) {
        this.prpMoaConsec = prpMoaConsec;
    }

    private Integer prpFecha;

    @Basic
    @Column(name = "PRP_FECHA")
    public Integer getPrpFecha() {
        return prpFecha;
    }

    public void setPrpFecha(Integer prpFecha) {
        this.prpFecha = prpFecha;
    }

    private Integer prpIdElemento;

    @Id
    @Column(name = "PRP_ID_ELEMENTO")
    public Integer getPrpIdElemento() {
        return prpIdElemento;
    }

    public void setPrpIdElemento(Integer prpIdElemento) {
        this.prpIdElemento = prpIdElemento;
    }

    private Integer prpIdFuncionario;

    @Basic
    @Column(name = "PRP_ID_FUNCIONARIO")
    public Integer getPrpIdFuncionario() {
        return prpIdFuncionario;
    }

    public void setPrpIdFuncionario(Integer prpIdFuncionario) {
        this.prpIdFuncionario = prpIdFuncionario;
    }

    private String prpFormaPago;

    @Basic
    @Column(name = "PRP_FORMA_PAGO")
    public String getPrpFormaPago() {
        return prpFormaPago;
    }

    public void setPrpFormaPago(String prpFormaPago) {
        this.prpFormaPago = prpFormaPago;
    }

    private String prpEstado;

    @Basic
    @Column(name = "PRP_ESTADO")
    public String getPrpEstado() {
        return prpEstado;
    }

    public void setPrpEstado(String prpEstado) {
        this.prpEstado = prpEstado;
    }

    private Integer prpCantidad;

    @Basic
    @Column(name = "PRP_CANTIDAD")
    public Integer getPrpCantidad() {
        return prpCantidad;
    }

    public void setPrpCantidad(Integer prpCantidad) {
        this.prpCantidad = prpCantidad;
    }

    private Integer prpValorTotal;

    @Basic
    @Column(name = "PRP_VALOR_TOTAL")
    public Integer getPrpValorTotal() {
        return prpValorTotal;
    }

    public void setPrpValorTotal(Integer prpValorTotal) {
        this.prpValorTotal = prpValorTotal;
    }

    private Integer prpCcResponsable;

    @Basic
    @Column(name = "PRP_CC_RESPONSABLE")
    public Integer getPrpCcResponsable() {
        return prpCcResponsable;
    }

    public void setPrpCcResponsable(Integer prpCcResponsable) {
        this.prpCcResponsable = prpCcResponsable;
    }

    private String prpUsuario;

    @Basic
    @Column(name = "PRP_USUARIO")
    public String getPrpUsuario() {
        return prpUsuario;
    }

    public void setPrpUsuario(String prpUsuario) {
        this.prpUsuario = prpUsuario;
    }

    private String prpTerminal;

    @Basic
    @Column(name = "PRP_TERMINAL")
    public String getPrpTerminal() {
        return prpTerminal;
    }

    public void setPrpTerminal(String prpTerminal) {
        this.prpTerminal = prpTerminal;
    }

    private Date prpFecmod;

    @Basic
    @Column(name = "PRP_FECMOD")
    public Date getPrpFecmod() {
        return prpFecmod;
    }

    public void setPrpFecmod(Date prpFecmod) {
        this.prpFecmod = prpFecmod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosRecibeDePos that = (PosRecibeDePos) o;

        if (prpCantidad != null ? !prpCantidad.equals(that.prpCantidad) : that.prpCantidad != null) return false;
        if (prpCcResponsable != null ? !prpCcResponsable.equals(that.prpCcResponsable) : that.prpCcResponsable != null)
            return false;
        if (prpEstado != null ? !prpEstado.equals(that.prpEstado) : that.prpEstado != null) return false;
        if (prpFecha != null ? !prpFecha.equals(that.prpFecha) : that.prpFecha != null) return false;
        if (prpFecmod != null ? !prpFecmod.equals(that.prpFecmod) : that.prpFecmod != null) return false;
        if (prpFormaPago != null ? !prpFormaPago.equals(that.prpFormaPago) : that.prpFormaPago != null) return false;
        if (prpIdElemento != null ? !prpIdElemento.equals(that.prpIdElemento) : that.prpIdElemento != null)
            return false;
        if (prpIdFuncionario != null ? !prpIdFuncionario.equals(that.prpIdFuncionario) : that.prpIdFuncionario != null)
            return false;
        if (prpMoaConsec != null ? !prpMoaConsec.equals(that.prpMoaConsec) : that.prpMoaConsec != null) return false;
        if (prpMoaTipo != null ? !prpMoaTipo.equals(that.prpMoaTipo) : that.prpMoaTipo != null) return false;
        if (prpPosId != null ? !prpPosId.equals(that.prpPosId) : that.prpPosId != null) return false;
        if (prpTerminal != null ? !prpTerminal.equals(that.prpTerminal) : that.prpTerminal != null) return false;
        if (prpUsuario != null ? !prpUsuario.equals(that.prpUsuario) : that.prpUsuario != null) return false;
        if (prpValorTotal != null ? !prpValorTotal.equals(that.prpValorTotal) : that.prpValorTotal != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prpPosId != null ? prpPosId.hashCode() : 0;
        result = 31 * result + (prpMoaTipo != null ? prpMoaTipo.hashCode() : 0);
        result = 31 * result + (prpMoaConsec != null ? prpMoaConsec.hashCode() : 0);
        result = 31 * result + (prpFecha != null ? prpFecha.hashCode() : 0);
        result = 31 * result + (prpIdElemento != null ? prpIdElemento.hashCode() : 0);
        result = 31 * result + (prpIdFuncionario != null ? prpIdFuncionario.hashCode() : 0);
        result = 31 * result + (prpFormaPago != null ? prpFormaPago.hashCode() : 0);
        result = 31 * result + (prpEstado != null ? prpEstado.hashCode() : 0);
        result = 31 * result + (prpCantidad != null ? prpCantidad.hashCode() : 0);
        result = 31 * result + (prpValorTotal != null ? prpValorTotal.hashCode() : 0);
        result = 31 * result + (prpCcResponsable != null ? prpCcResponsable.hashCode() : 0);
        result = 31 * result + (prpUsuario != null ? prpUsuario.hashCode() : 0);
        result = 31 * result + (prpTerminal != null ? prpTerminal.hashCode() : 0);
        result = 31 * result + (prpFecmod != null ? prpFecmod.hashCode() : 0);
        return result;
    }
}
