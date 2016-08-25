package co.com.lh.smsfin.modelFac;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 13/06/2012
 * Time: 04:11:41 PM
 */
public class PosFuncEstadoPK implements Serializable {
    private long fesCedula;

    @Column(name = "FES_CEDULA")
    @Id
    public long getFesCedula() {
        return fesCedula;
    }

    public void setFesCedula(long fesCedula) {
        this.fesCedula = fesCedula;
    }

    private Integer fesIdPos;

    @Column(name = "FES_ID_POS")
    @Id
    public Integer getFesIdPos() {
        return fesIdPos;
    }

    public void setFesIdPos(Integer fesIdPos) {
        this.fesIdPos = fesIdPos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosFuncEstadoPK that = (PosFuncEstadoPK) o;

        if (fesCedula != that.fesCedula) return false;
        if (fesIdPos != null ? !fesIdPos.equals(that.fesIdPos) : that.fesIdPos != null) return false;

        return true;
    }


}
