package co.com.lh.smsfin.modelFac;

import javax.persistence.*;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 18/03/2013
 * Time: 11:51:23 AM
 */
@Entity
@Table(name = "POS_LOG_ENTRADAS")
public class PosLogEntradas {

    private int idLogEnt;

    @Id
    @Column(name = "LOG_ID")
    public int getIdLogEnt() {
        return idLogEnt;
    }

    public void setIdLogEnt(int idLogEnt) {
        this.idLogEnt = idLogEnt;
    }

    private int idPosLogEnt;

    @Basic
    @Column(name = "LOG_POS_ID")
    public int getIdPos() {
        return idPosLogEnt;
    }

    public void setIdPos(int idPosLogEnt) {
        this.idPosLogEnt = idPosLogEnt;
    }

    private int idElemento;

    @Basic
    @Column(name = "LOG_ID_ELEMENTO")
    public int getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(int idElemento) {
        this.idElemento = idElemento;
    }

    private int fecha;

    @Basic
    @Column(name = "LOG_FECHA")
    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    private int hora;

    @Basic
    @Column(name = "LOG_HORA")
    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    private int cantidad;

    @Basic
    @Column(name = "LOG_CANTIDAD")
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    private String tipo;

    @Basic
    @Column(name = "LOG_TIPO")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    private int idSolicitud;

    @Basic
    @Column(name = "LOG_ID_SOLICITUD")
    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
}
