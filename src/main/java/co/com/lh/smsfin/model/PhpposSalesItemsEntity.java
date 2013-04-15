package co.com.lh.smsfin.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 5/03/2012
 * Time: 01:29:54 PM
 */
@Entity
@Table( name = "phppos_sales_items")
@IdClass(PhpposSalesItemsEntityPK.class)
public class PhpposSalesItemsEntity {
    private int saleId;

    @Id
    @Column(name = "sale_id")
    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    private int itemId;

    @Id
    @Column(name = "item_id")
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    private int quantityPurchased;

    @Basic
    @Column(name = "quantity_purchased")
    public int getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    private BigDecimal itemCostPrice;

    @Basic
    @Column(name = "item_cost_price")
    public BigDecimal getItemCostPrice() {
        return itemCostPrice;
    }

    public void setItemCostPrice(BigDecimal itemCostPrice) {
        this.itemCostPrice = itemCostPrice;
    }

    private double itemUnitPrice;

    @Basic
    @Column(name = "item_unit_price")
    public double getItemUnitPrice() {
        return itemUnitPrice;
    }

    public void setItemUnitPrice(double itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
    }

    private int discountPercent;

    @Basic
    @Column(name = "discount_percent")
    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhpposSalesItemsEntity that = (PhpposSalesItemsEntity) o;

        if (discountPercent != that.discountPercent) return false;
        if (itemId != that.itemId) return false;
        if (Double.compare(that.itemUnitPrice, itemUnitPrice) != 0) return false;
        if (quantityPurchased != that.quantityPurchased) return false;
        if (saleId != that.saleId) return false;
        if (itemCostPrice != null ? !itemCostPrice.equals(that.itemCostPrice) : that.itemCostPrice != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = saleId;
        result = 31 * result + itemId;
        result = 31 * result + quantityPurchased;
        result = 31 * result + (itemCostPrice != null ? itemCostPrice.hashCode() : 0);
        temp = itemUnitPrice != +0.0d ? Double.doubleToLongBits(itemUnitPrice) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + discountPercent;
        return result;
    }
}
