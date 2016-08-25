package co.com.lh.smsfin.modelFac;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 12/03/2012
 * Time: 05:02:28 PM
 */
public class PosRecibeDePosPK implements Serializable {
    private Integer prpPosId;

    @Column(name = "PRP_POS_ID")
    @Id
    public Integer getPrpPosId() {
        return prpPosId;
    }

    public void setPrpPosId(Integer prpPosId) {
        this.prpPosId = prpPosId;
    }

    private String prpMoaTipo;

    @Column(name = "PRP_MOA_TIPO")
    @Id
    public String getPrpMoaTipo() {
        return prpMoaTipo;
    }

    public void setPrpMoaTipo(String prpMoaTipo) {
        this.prpMoaTipo = prpMoaTipo;
    }

    private Integer prpMoaConsec;

    @Column(name = "PRP_MOA_CONSEC")
    @Id
    public Integer getPrpMoaConsec() {
        return prpMoaConsec;
    }

    public void setPrpMoaConsec(Integer prpMoaConsec) {
        this.prpMoaConsec = prpMoaConsec;
    }

    private Integer prpIdElemento;

    @Column(name = "PRP_ID_ELEMENTO")
    @Id
    public Integer getPrpIdElemento() {
        return prpIdElemento;
    }

    public void setPrpIdElemento(Integer prpIdElemento) {
        this.prpIdElemento = prpIdElemento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosRecibeDePosPK that = (PosRecibeDePosPK) o;

        if (prpIdElemento != null ? !prpIdElemento.equals(that.prpIdElemento) : that.prpIdElemento != null)
            return false;
        if (prpMoaConsec != null ? !prpMoaConsec.equals(that.prpMoaConsec) : that.prpMoaConsec != null) return false;
        if (prpMoaTipo != null ? !prpMoaTipo.equals(that.prpMoaTipo) : that.prpMoaTipo != null) return false;
        if (prpPosId != null ? !prpPosId.equals(that.prpPosId) : that.prpPosId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prpPosId != null ? prpPosId.hashCode() : 0;
        result = 31 * result + (prpMoaTipo != null ? prpMoaTipo.hashCode() : 0);
        result = 31 * result + (prpMoaConsec != null ? prpMoaConsec.hashCode() : 0);
        result = 31 * result + (prpIdElemento != null ? prpIdElemento.hashCode() : 0);
        return result;
    }
}
