package co.com.lh.smsfin.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 5/03/2012
 * Time: 01:00:10 PM
 */
public class PhpposSalesItemsEntityPK implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhpposSalesItemsEntityPK that = (PhpposSalesItemsEntityPK) o;

        if (itemId != that.itemId) return false;
        if (saleId != that.saleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = saleId;
        result = 31 * result + itemId;
        return result;
    }
}
