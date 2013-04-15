package co.com.lh.smsfin.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 5/03/2012
 * Time: 01:00:11 PM
 */
public class PhpposSalesItemsTaxesEntityPK implements Serializable {
    private int saleId;

    @Column(name = "sale_id")
    @Id
    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    private int itemId;

    @Column(name = "item_id")
    @Id
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    private String name;

    @Column(name = "name")
    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private double percent;

    @Column(name = "percent")
    @Id
    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhpposSalesItemsTaxesEntityPK that = (PhpposSalesItemsTaxesEntityPK) o;

        if (itemId != that.itemId) return false;
        if (Double.compare(that.percent, percent) != 0) return false;
        if (saleId != that.saleId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = saleId;
        result = 31 * result + itemId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = percent != +0.0d ? Double.doubleToLongBits(percent) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
