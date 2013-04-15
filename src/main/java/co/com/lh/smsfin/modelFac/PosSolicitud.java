package co.com.lh.smsfin.modelFac;

import javax.persistence.*;
import java.sql.Date;
import java.security.Timestamp;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 20/06/2012
 * Time: 06:01:26 PM
 */
@Entity
@Table( name = "POS_SOLICITUD")
public class PosSolicitud {

    private int idSolicitud;

    @Id
    @Column(name = "SOL_ID_SOLICITUD")
    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    private int solPosId;

    @Basic
    @Column(name = "SOL_POS_ID")
    public int getSolPosId() {
        return solPosId;
    }

    public void setSolPosId(int solPosId) {
        this.solPosId = solPosId;
    }

    private String solTipoSolicitud;

    @Basic
    @Column(name = "SOL_TIPO_SOLICITUD")
    public String getSolTipoSolicitud() {
        return solTipoSolicitud;
    }

    public void setSolTipoSolicitud(String solTipoSolicitud) {
        this.solTipoSolicitud = solTipoSolicitud;
    }

    private String solEstado;

    @Basic
    @Column(name = "SOL_ESTADO")
    public String getSolEstado() {
        return solEstado;
    }

    public void setSolEstado(String solEstado) {
        this.solEstado = solEstado;
    }

    private Date solFechaHora;

    @Basic
    @Column(name = "SOL_FECHA_HORA")
    public Date getSolFechaHora() {
        return solFechaHora;
    }

    public void setSolFechaHora(Date solFechaHora) {
        this.solFechaHora = solFechaHora;
    }

    private Integer solIdElemento;

    @Basic
    @Column(name = "SOL_ID_ELEMENTO")
    public Integer getSolIdElemento() {
        return solIdElemento;
    }

    public void setSolIdElemento(Integer solIdElemento) {
        this.solIdElemento = solIdElemento;
    }

    private Date solFechaDesde;

    @Basic
    @Column(name = "SOL_FECHA_DESDE")
    public Date getSolFechaDesde() {
        return solFechaDesde;
    }

    public void setSolFechaDesde(Date solFechaDesde) {
        this.solFechaDesde = solFechaDesde;
    }

    private Date solFechaHasta;

    @Basic
    @Column(name = "SOL_FECHA_HASTA")
    public Date getSolFechaHasta() {
        return solFechaHasta;
    }

    public void setSolFechaHasta(Date solFechaHasta) {
        this.solFechaHasta = solFechaHasta;
    }

    private String solUsuario;

    @Basic
    @Column(name = "SOL_USUARIO")
    public String getSolUsuario() {
        return solUsuario;
    }

    public void setSolUsuario(String solUsuario) {
        this.solUsuario = solUsuario;
    }

    private String solTerminal;

    @Basic
    @Column(name = "SOL_TERMINAL")
    public String getSolTerminal() {
        return solTerminal;
    }

    public void setSolTerminal(String solTerminal) {
        this.solTerminal = solTerminal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosSolicitud that = (PosSolicitud) o;

        if (solIdElemento != that.solIdElemento) return false;
        if (solPosId != that.solPosId) return false;
        if (solEstado != null ? !solEstado.equals(that.solEstado) : that.solEstado != null) return false;
        if (solFechaDesde != null ? !solFechaDesde.equals(that.solFechaDesde) : that.solFechaDesde != null)
            return false;
        if (solFechaHasta != null ? !solFechaHasta.equals(that.solFechaHasta) : that.solFechaHasta != null)
            return false;
        if (solFechaHora != null ? !solFechaHora.equals(that.solFechaHora) : that.solFechaHora != null) return false;
        if (solTerminal != null ? !solTerminal.equals(that.solTerminal) : that.solTerminal != null) return false;
        if (solTipoSolicitud != null ? !solTipoSolicitud.equals(that.solTipoSolicitud) : that.solTipoSolicitud != null)
            return false;
        if (solUsuario != null ? !solUsuario.equals(that.solUsuario) : that.solUsuario != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = solPosId;
        result = 31 * result + (solTipoSolicitud != null ? solTipoSolicitud.hashCode() : 0);
        result = 31 * result + (solEstado != null ? solEstado.hashCode() : 0);
        result = 31 * result + (solFechaHora != null ? solFechaHora.hashCode() : 0);
        result = 31 * result + solIdElemento;
        result = 31 * result + (solFechaDesde != null ? solFechaDesde.hashCode() : 0);
        result = 31 * result + (solFechaHasta != null ? solFechaHasta.hashCode() : 0);
        result = 31 * result + (solUsuario != null ? solUsuario.hashCode() : 0);
        result = 31 * result + (solTerminal != null ? solTerminal.hashCode() : 0);
        return result;
    }
}
