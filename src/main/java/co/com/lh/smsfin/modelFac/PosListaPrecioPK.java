package co.com.lh.smsfin.modelFac;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 12/03/2012
 * Time: 05:02:22 PM
 */
public class PosListaPrecioPK implements Serializable {
    private Integer pcaPosId;

    @Column(name = "PCA_POS_ID")
    @Id
    public Integer getPcaPosId() {
        return pcaPosId;
    }

    public void setPcaPosId(Integer pcaPosId) {
        this.pcaPosId = pcaPosId;
    }

    private Integer pcaGrupo;

    @Column(name = "PCA_GRUPO")
    @Id
    public Integer getPcaGrupo() {
        return pcaGrupo;
    }

    public void setPcaGrupo(Integer pcaGrupo) {
        this.pcaGrupo = pcaGrupo;
    }

    private Integer pcaSubgru;

    @Column(name = "PCA_SUBGRU")
    @Id
    public Integer getPcaSubgru() {
        return pcaSubgru;
    }

    public void setPcaSubgru(Integer pcaSubgru) {
        this.pcaSubgru = pcaSubgru;
    }

    private Integer pcaGenerico;

    @Column(name = "PCA_GENERICO")
    @Id
    public Integer getPcaGenerico() {
        return pcaGenerico;
    }

    public void setPcaGenerico(Integer pcaGenerico) {
        this.pcaGenerico = pcaGenerico;
    }

    private Integer pcaSecuen;

    @Column(name = "PCA_SECUEN")
    @Id
    public Integer getPcaSecuen() {
        return pcaSecuen;
    }

    public void setPcaSecuen(Integer pcaSecuen) {
        this.pcaSecuen = pcaSecuen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosListaPrecioPK that = (PosListaPrecioPK) o;

        if (pcaGenerico != null ? !pcaGenerico.equals(that.pcaGenerico) : that.pcaGenerico != null) return false;
        if (pcaGrupo != null ? !pcaGrupo.equals(that.pcaGrupo) : that.pcaGrupo != null) return false;
        if (pcaPosId != null ? !pcaPosId.equals(that.pcaPosId) : that.pcaPosId != null) return false;
        if (pcaSecuen != null ? !pcaSecuen.equals(that.pcaSecuen) : that.pcaSecuen != null) return false;
        if (pcaSubgru != null ? !pcaSubgru.equals(that.pcaSubgru) : that.pcaSubgru != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pcaPosId != null ? pcaPosId.hashCode() : 0;
        result = 31 * result + (pcaGrupo != null ? pcaGrupo.hashCode() : 0);
        result = 31 * result + (pcaSubgru != null ? pcaSubgru.hashCode() : 0);
        result = 31 * result + (pcaGenerico != null ? pcaGenerico.hashCode() : 0);
        result = 31 * result + (pcaSecuen != null ? pcaSecuen.hashCode() : 0);
        return result;
    }
}
