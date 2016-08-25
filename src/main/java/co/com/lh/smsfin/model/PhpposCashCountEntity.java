package co.com.lh.smsfin.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 5/03/2012
 * Time: 01:29:47 PM
 */
@Entity
@Table( name = "phppos_cash_count")
public class PhpposCashCountEntity {
    private int cashCountId;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "cash_count_id")
    public int getCashCountId() {
        return cashCountId;
    }

    public void setCashCountId(int cashCountId) {
        this.cashCountId = cashCountId;
    }

    private Timestamp logTime;

    @Basic
    @Column(name = "log_time")
    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
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

    private int employeeId;

    @Basic
    @Column(name = "employee_id")
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    private String comment;

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhpposCashCountEntity that = (PhpposCashCountEntity) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        if (cashCountId != that.cashCountId) return false;
        if (employeeId != that.employeeId) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (logTime != null ? !logTime.equals(that.logTime) : that.logTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = cashCountId;
        result = 31 * result + (logTime != null ? logTime.hashCode() : 0);
        temp = amount != +0.0d ? Double.doubleToLongBits(amount) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + employeeId;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
