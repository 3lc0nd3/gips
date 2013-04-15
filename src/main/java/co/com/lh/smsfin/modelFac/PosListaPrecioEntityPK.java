package co.com.lh.smsfin.modelFac;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 7/03/2012
 * Time: 12:42:02 PM
 */
public class PosListaPrecioEntityPK implements Serializable {
    private BigInteger pcaPosId;

    @Column(name = "PCA_POS_ID")
    @Id
    public BigInteger getPcaPosId() {
        return pcaPosId;
    }

    public void setPcaPosId(BigInteger pcaPosId) {
        this.pcaPosId = pcaPosId;
    }

    private int pcaIdElemento;

    @Column(name = "PCA_ID_ELEMENTO")
    @Id
    public int getPcaIdElemento() {
        return pcaIdElemento;
    }

    public void setPcaIdElemento(int pcaIdElemento) {
        this.pcaIdElemento = pcaIdElemento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosListaPrecioEntityPK that = (PosListaPrecioEntityPK) o;

        if (pcaIdElemento != that.pcaIdElemento) return false;
        if (pcaPosId != null ? !pcaPosId.equals(that.pcaPosId) : that.pcaPosId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pcaPosId != null ? pcaPosId.hashCode() : 0;
        result = 31 * result + pcaIdElemento;
        return result;
    }
}
