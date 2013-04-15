package co.com.lh.smsfin.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 5/03/2012
 * Time: 01:29:46 PM
 */
@Entity
@Table( name = "phppos_box")
public class PhpposBoxEntity {
    private int boxId;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "box_id")
    public int getBoxId() {
        return boxId;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }

    private Date boxDate;

    @Basic
    @Column(name = "box_date")
    public Date getBoxDate() {
        return boxDate;
    }

    public void setBoxDate(Date boxDate) {
        this.boxDate = boxDate;
    }

    private double amount;

    @Basic
    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    private double vale;

    @Basic
    @Column(name = "vale")
    public double getVale() {
        return vale;
    }

    public void setVale(double vale) {
        this.vale = vale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhpposBoxEntity that = (PhpposBoxEntity) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        if (boxId != that.boxId) return false;
        if (Double.compare(that.vale, vale) != 0) return false;
        if (boxDate != null ? !boxDate.equals(that.boxDate) : that.boxDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = boxId;
        result = 31 * result + (boxDate != null ? boxDate.hashCode() : 0);
        temp = amount != +0.0d ? Double.doubleToLongBits(amount) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = vale != +0.0d ? Double.doubleToLongBits(vale) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
