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
 * Time: 12:42:04 PM
 */
public class PosRecibeDePosEntityPK implements Serializable {
    private BigInteger prpPosId;

    @Column(name = "PRP_POS_ID")
    @Id
    public BigInteger getPrpPosId() {
        return prpPosId;
    }

    public void setPrpPosId(BigInteger prpPosId) {
        this.prpPosId = prpPosId;
    }

    private BigInteger prpMoaTipo;

    @Column(name = "PRP_MOA_TIPO")
    @Id
    public BigInteger getPrpMoaTipo() {
        return prpMoaTipo;
    }

    public void setPrpMoaTipo(BigInteger prpMoaTipo) {
        this.prpMoaTipo = prpMoaTipo;
    }

    private int prpMoaConsec;

    @Column(name = "PRP_MOA_CONSEC")
    @Id
    public int getPrpMoaConsec() {
        return prpMoaConsec;
    }

    public void setPrpMoaConsec(int prpMoaConsec) {
        this.prpMoaConsec = prpMoaConsec;
    }

    private int prpIdElemento;

    @Column(name = "PRP_ID_ELEMENTO")
    @Id
    public int getPrpIdElemento() {
        return prpIdElemento;
    }

    public void setPrpIdElemento(int prpIdElemento) {
        this.prpIdElemento = prpIdElemento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosRecibeDePosEntityPK that = (PosRecibeDePosEntityPK) o;

        if (prpIdElemento != that.prpIdElemento) return false;
        if (prpMoaConsec != that.prpMoaConsec) return false;
        if (prpMoaTipo != null ? !prpMoaTipo.equals(that.prpMoaTipo) : that.prpMoaTipo != null) return false;
        if (prpPosId != null ? !prpPosId.equals(that.prpPosId) : that.prpPosId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prpPosId != null ? prpPosId.hashCode() : 0;
        result = 31 * result + (prpMoaTipo != null ? prpMoaTipo.hashCode() : 0);
        result = 31 * result + prpMoaConsec;
        result = 31 * result + prpIdElemento;
        return result;
    }
}
