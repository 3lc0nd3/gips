package co.com.lh.smsfin.model;

import javax.persistence.*;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 5/03/2012
 * Time: 01:29:48 PM
 */
@Entity
@Table( name = "phppos_customers")
public class PhpposCustomersEntity {
    private int personId;

    @Id
    @Column(name = "person_id")
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    private String accountNumber;

    @Basic
    @Column(name = "account_number")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    private int taxable;

    @Basic
    @Column(name = "taxable")
    public int getTaxable() {
        return taxable;
    }

    public void setTaxable(int taxable) {
        this.taxable = taxable;
    }

    private double debt;

    @Basic
    @Column(name = "debt")
    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    private double level_debt;

    @Basic
    @Column(name = "level_debt")
    public double getLevel_debt() {
        return level_debt;
    }

    public void setLevel_debt(double level_debt) {
        this.level_debt = level_debt;
    }

    private String pass;

    @Basic
    @Column(name = "pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
