package co.com.lh.smsfin.modelFac;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 12/03/2012
 * Time: 05:02:20 PM
 */
@Entity
@Table(name = "POS_LISTA_PRECIO")
@IdClass(co.com.lh.smsfin.modelFac.PosListaPrecioPK.class)
public class PosListaPrecio {
    private Integer pcaPosId;

    @Id
    @Column(name = "PCA_POS_ID")
    public Integer getPcaPosId() {
        return pcaPosId;
    }

    public void setPcaPosId(Integer pcaPosId) {
        this.pcaPosId = pcaPosId;
    }

    private Integer pcaIdElemento;

    @Basic
    @Column(name = "PCA_ID_ELEMENTO")
    public Integer getPcaIdElemento() {
        return pcaIdElemento;
    }

    public void setPcaIdElemento(Integer pcaIdElemento) {
        this.pcaIdElemento = pcaIdElemento;
    }

    private Integer pcaGrupo;

    @Id
    @Column(name = "PCA_GRUPO")
    public Integer getPcaGrupo() {
        return pcaGrupo;
    }

    public void setPcaGrupo(Integer pcaGrupo) {
        this.pcaGrupo = pcaGrupo;
    }

    private Integer pcaSubgru;

    @Id
    @Column(name = "PCA_SUBGRU")
    public Integer getPcaSubgru() {
        return pcaSubgru;
    }

    public void setPcaSubgru(Integer pcaSubgru) {
        this.pcaSubgru = pcaSubgru;
    }

    private Integer pcaGenerico;

    @Id
    @Column(name = "PCA_GENERICO")
    public Integer getPcaGenerico() {
        return pcaGenerico;
    }

    public void setPcaGenerico(Integer pcaGenerico) {
        this.pcaGenerico = pcaGenerico;
    }

    private Integer pcaSecuen;

    @Id
    @Column(name = "PCA_SECUEN")
    public Integer getPcaSecuen() {
        return pcaSecuen;
    }

    public void setPcaSecuen(Integer pcaSecuen) {
        this.pcaSecuen = pcaSecuen;
    }

    private String pcaDescripcion;

    @Basic
    @Column(name = "PCA_DESCRIPCION")
    public String getPcaDescripcion() {
        return pcaDescripcion;
    }

    public void setPcaDescripcion(String pcaDescripcion) {
        this.pcaDescripcion = pcaDescripcion;
    }

    private Integer pcaCantidad;

    @Basic
    @Column(name = "PCA_CANTIDAD")
    public Integer getPcaCantidad() {
        return pcaCantidad;
    }

    public void setPcaCantidad(Integer pcaCantidad) {
        this.pcaCantidad = pcaCantidad;
    }

    private Integer pcaPrecioVenta;

    @Basic
    @Column(name = "PCA_PRECIO_VENTA")
    public Integer getPcaPrecioVenta() {
        return pcaPrecioVenta;
    }

    public void setPcaPrecioVenta(Integer pcaPrecioVenta) {
        this.pcaPrecioVenta = pcaPrecioVenta;
    }

    private String pcaEstado;

    @Basic
    @Column(name = "PCA_ESTADO")
    public String getPcaEstado() {
        return pcaEstado;
    }

    public void setPcaEstado(String pcaEstado) {
        this.pcaEstado = pcaEstado;
    }

    private String pcaUsuario;

    @Basic
    @Column(name = "PCA_USUARIO")
    public String getPcaUsuario() {
        return pcaUsuario;
    }

    public void setPcaUsuario(String pcaUsuario) {
        this.pcaUsuario = pcaUsuario;
    }

    private String pcaTerminal;

    @Basic
    @Column(name = "PCA_TERMINAL")
    public String getPcaTerminal() {
        return pcaTerminal;
    }

    public void setPcaTerminal(String pcaTerminal) {
        this.pcaTerminal = pcaTerminal;
    }

    private Date pcaFecmod;

    @Basic
    @Column(name = "PCA_FECMOD")
    public Date getPcaFecmod() {
        return pcaFecmod;
    }

    public void setPcaFecmod(Date pcaFecmod) {
        this.pcaFecmod = pcaFecmod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosListaPrecio that = (PosListaPrecio) o;

        if (pcaCantidad != null ? !pcaCantidad.equals(that.pcaCantidad) : that.pcaCantidad != null) return false;
        if (pcaDescripcion != null ? !pcaDescripcion.equals(that.pcaDescripcion) : that.pcaDescripcion != null)
            return false;
        if (pcaEstado != null ? !pcaEstado.equals(that.pcaEstado) : that.pcaEstado != null) return false;
        if (pcaFecmod != null ? !pcaFecmod.equals(that.pcaFecmod) : that.pcaFecmod != null) return false;
        if (pcaGenerico != null ? !pcaGenerico.equals(that.pcaGenerico) : that.pcaGenerico != null) return false;
        if (pcaGrupo != null ? !pcaGrupo.equals(that.pcaGrupo) : that.pcaGrupo != null) return false;
        if (pcaIdElemento != null ? !pcaIdElemento.equals(that.pcaIdElemento) : that.pcaIdElemento != null)
            return false;
        if (pcaPosId != null ? !pcaPosId.equals(that.pcaPosId) : that.pcaPosId != null) return false;
        if (pcaPrecioVenta != null ? !pcaPrecioVenta.equals(that.pcaPrecioVenta) : that.pcaPrecioVenta != null)
            return false;
        if (pcaSecuen != null ? !pcaSecuen.equals(that.pcaSecuen) : that.pcaSecuen != null) return false;
        if (pcaSubgru != null ? !pcaSubgru.equals(that.pcaSubgru) : that.pcaSubgru != null) return false;
        if (pcaTerminal != null ? !pcaTerminal.equals(that.pcaTerminal) : that.pcaTerminal != null) return false;
        if (pcaUsuario != null ? !pcaUsuario.equals(that.pcaUsuario) : that.pcaUsuario != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pcaPosId != null ? pcaPosId.hashCode() : 0;
        result = 31 * result + (pcaIdElemento != null ? pcaIdElemento.hashCode() : 0);
        result = 31 * result + (pcaGrupo != null ? pcaGrupo.hashCode() : 0);
        result = 31 * result + (pcaSubgru != null ? pcaSubgru.hashCode() : 0);
        result = 31 * result + (pcaGenerico != null ? pcaGenerico.hashCode() : 0);
        result = 31 * result + (pcaSecuen != null ? pcaSecuen.hashCode() : 0);
        result = 31 * result + (pcaDescripcion != null ? pcaDescripcion.hashCode() : 0);
        result = 31 * result + (pcaCantidad != null ? pcaCantidad.hashCode() : 0);
        result = 31 * result + (pcaPrecioVenta != null ? pcaPrecioVenta.hashCode() : 0);
        result = 31 * result + (pcaEstado != null ? pcaEstado.hashCode() : 0);
        result = 31 * result + (pcaUsuario != null ? pcaUsuario.hashCode() : 0);
        result = 31 * result + (pcaTerminal != null ? pcaTerminal.hashCode() : 0);
        result = 31 * result + (pcaFecmod != null ? pcaFecmod.hashCode() : 0);
        return result;
    }
}
