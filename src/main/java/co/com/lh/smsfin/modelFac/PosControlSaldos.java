package co.com.lh.smsfin.modelFac;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 20/06/2012
 * Time: 06:01:24 PM
 */
@Entity
@Table( name = "POS_CONTROL_SALDOS")
public class PosControlSaldos {

    private int idPosControlSaldos;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "CSA_ID")
    public int getIdPosControlSaldos() {
        return idPosControlSaldos;
    }

    public void setIdPosControlSaldos(int idPosControlSaldos) {
        this.idPosControlSaldos = idPosControlSaldos;
    }

    private int idSolicitud;

    @Basic
    @Column(name = "CSA_ID_SOLICITUD")
    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    private int csaPosId;

    @Basic
    @Column(name = "CSA_POS_ID")
    public int getCsaPosId() {
        return csaPosId;
    }

    public void setCsaPosId(int csaPosId) {
        this.csaPosId = csaPosId;
    }

    private int csaIdElemento;

    @Basic
    @Column(name = "CSA_ID_ELEMENTO")
    public int getCsaIdElemento() {
        return csaIdElemento;
    }

    public void setCsaIdElemento(int csaIdElemento) {
        this.csaIdElemento = csaIdElemento;
    }

    private int csaCantidad;

    @Basic
    @Column(name = "CSA_CANTIDAD")
    public int getCsaCantidad() {
        return csaCantidad;
    }

    public void setCsaCantidad(int csaCantidad) {
        this.csaCantidad = csaCantidad;
    }

    private Double csaValorTotal;

    @Basic
    @Column(name = "CSA_VALOR_TOTAL")
    public Double getCsaValorTotal() {
        return csaValorTotal;
    }

    public void setCsaValorTotal(Double csaValorTotal) {
        this.csaValorTotal = csaValorTotal;
    }

    private Date csaFechaHora;

    @Basic
    @Column(name = "CSA_FECHA_HORA")
    public Date getCsaFechaHora() {
        return csaFechaHora;
    }

    public void setCsaFechaHora(Date csaFechaHora) {
        this.csaFechaHora = csaFechaHora;
    }

    
}
