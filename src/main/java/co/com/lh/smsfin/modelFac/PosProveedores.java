package co.com.lh.smsfin.modelFac;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 12/03/2012
 * Time: 05:02:25 PM
 */
@Entity
@Table(name = "POS_PROVEEDORES")
public class PosProveedores {
    private Integer popProveedorId;

    @Id
    @Column(name = "POP_PROVEEDOR_ID")
    public Integer getPopProveedorId() {
        return popProveedorId;
    }

    public void setPopProveedorId(Integer popProveedorId) {
        this.popProveedorId = popProveedorId;
    }

    private Integer popNitProveedor;

    @Basic
    @Column(name = "POP_NIT_PROVEEDOR")
    public Integer getPopNitProveedor() {
        return popNitProveedor;
    }

    public void setPopNitProveedor(Integer popNitProveedor) {
        this.popNitProveedor = popNitProveedor;
    }

    private String popNombreProveedor;

    @Basic
    @Column(name = "POP_NOMBRE_PROVEEDOR")
    public String getPopNombreProveedor() {
        return popNombreProveedor;
    }

    public void setPopNombreProveedor(String popNombreProveedor) {
        this.popNombreProveedor = popNombreProveedor;
    }

    private String popUsuario;

    @Basic
    @Column(name = "POP_USUARIO")
    public String getPopUsuario() {
        return popUsuario;
    }

    public void setPopUsuario(String popUsuario) {
        this.popUsuario = popUsuario;
    }

    private String popTerminal;

    @Basic
    @Column(name = "POP_TERMINAL")
    public String getPopTerminal() {
        return popTerminal;
    }

    public void setPopTerminal(String popTerminal) {
        this.popTerminal = popTerminal;
    }

    private Date popFecmod;

    @Basic
    @Column(name = "POP_FECMOD")
    public Date getPopFecmod() {
        return popFecmod;
    }

    public void setPopFecmod(Date popFecmod) {
        this.popFecmod = popFecmod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosProveedores that = (PosProveedores) o;

        if (popFecmod != null ? !popFecmod.equals(that.popFecmod) : that.popFecmod != null) return false;
        if (popNitProveedor != null ? !popNitProveedor.equals(that.popNitProveedor) : that.popNitProveedor != null)
            return false;
        if (popNombreProveedor != null ? !popNombreProveedor.equals(that.popNombreProveedor) : that.popNombreProveedor != null)
            return false;
        if (popProveedorId != null ? !popProveedorId.equals(that.popProveedorId) : that.popProveedorId != null)
            return false;
        if (popTerminal != null ? !popTerminal.equals(that.popTerminal) : that.popTerminal != null) return false;
        if (popUsuario != null ? !popUsuario.equals(that.popUsuario) : that.popUsuario != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = popProveedorId != null ? popProveedorId.hashCode() : 0;
        result = 31 * result + (popNitProveedor != null ? popNitProveedor.hashCode() : 0);
        result = 31 * result + (popNombreProveedor != null ? popNombreProveedor.hashCode() : 0);
        result = 31 * result + (popUsuario != null ? popUsuario.hashCode() : 0);
        result = 31 * result + (popTerminal != null ? popTerminal.hashCode() : 0);
        result = 31 * result + (popFecmod != null ? popFecmod.hashCode() : 0);
        return result;
    }
}
