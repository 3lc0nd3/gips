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
 * Time: 12:41:58 PM
 */
public class PosEnvioAPosEntityPK implements Serializable {
    private BigInteger pepPosId;

    @Column(name = "PEP_POS_ID")
    @Id
    public BigInteger getPepPosId() {
        return pepPosId;
    }

    public void setPepPosId(BigInteger pepPosId) {
        this.pepPosId = pepPosId;
    }

    private BigInteger pepMoaTipo;

    @Column(name = "PEP_MOA_TIPO")
    @Id
    public BigInteger getPepMoaTipo() {
        return pepMoaTipo;
    }

    public void setPepMoaTipo(BigInteger pepMoaTipo) {
        this.pepMoaTipo = pepMoaTipo;
    }

    private int pepMoaConsec;

    @Column(name = "PEP_MOA_CONSEC")
    @Id
    public int getPepMoaConsec() {
        return pepMoaConsec;
    }

    public void setPepMoaConsec(int pepMoaConsec) {
        this.pepMoaConsec = pepMoaConsec;
    }

    private int pepIdElemento;

    @Column(name = "PEP_ID_ELEMENTO")
    @Id
    public int getPepIdElemento() {
        return pepIdElemento;
    }

    public void setPepIdElemento(int pepIdElemento) {
        this.pepIdElemento = pepIdElemento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosEnvioAPosEntityPK that = (PosEnvioAPosEntityPK) o;

        if (pepIdElemento != that.pepIdElemento) return false;
        if (pepMoaConsec != that.pepMoaConsec) return false;
        if (pepMoaTipo != null ? !pepMoaTipo.equals(that.pepMoaTipo) : that.pepMoaTipo != null) return false;
        if (pepPosId != null ? !pepPosId.equals(that.pepPosId) : that.pepPosId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pepPosId != null ? pepPosId.hashCode() : 0;
        result = 31 * result + (pepMoaTipo != null ? pepMoaTipo.hashCode() : 0);
        result = 31 * result + pepMoaConsec;
        result = 31 * result + pepIdElemento;
        return result;
    }
}
