package co.com.lh.smsfin.model;

import javax.persistence.*;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 5/03/2012
 * Time: 01:29:50 PM
 */
@Entity
@Table( name = "phppos_items_taxes")
@IdClass(PhpposItemsTaxesEntityPK.class)
public class PhpposItemsTaxesEntity {
    private int itemId;

    @Id
    @Column(name = "item_id")
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    private String name;

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private double percent;

    @Id
    @Column(name = "percent")
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

        PhpposItemsTaxesEntity that = (PhpposItemsTaxesEntity) o;

        if (itemId != that.itemId) return false;
        if (Double.compare(that.percent, percent) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = itemId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = percent != +0.0d ? Double.doubleToLongBits(percent) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
