package co.com.lh.smsfin.modelFac;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 12/03/2012
 * Time: 05:02:24 PM
 */
public class PosNovedadesPK implements Serializable {
    private Integer novCedula;

    @Column(name = "NOV_CEDULA")
    @Id
    public Integer getNovCedula() {
        return novCedula;
    }

    public void setNovCedula(Integer novCedula) {
        this.novCedula = novCedula;
    }

    private String novCodigoConcepto;

    @Column(name = "NOV_CODIGO_CONCEPTO")
    @Id
    public String getNovCodigoConcepto() {
        return novCodigoConcepto;
    }

    public void setNovCodigoConcepto(String novCodigoConcepto) {
        this.novCodigoConcepto = novCodigoConcepto;
    }

    private String novUnidad;

    @Column(name = "NOV_UNIDAD")
    @Id
    public String getNovUnidad() {
        return novUnidad;
    }

    public void setNovUnidad(String novUnidad) {
        this.novUnidad = novUnidad;
    }

    private Integer novAaaamm;

    @Column(name = "NOV_AAAAMM")
    @Id
    public Integer getNovAaaamm() {
        return novAaaamm;
    }

    public void setNovAaaamm(Integer novAaaamm) {
        this.novAaaamm = novAaaamm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosNovedadesPK that = (PosNovedadesPK) o;

        if (novAaaamm != null ? !novAaaamm.equals(that.novAaaamm) : that.novAaaamm != null) return false;
        if (novCedula != null ? !novCedula.equals(that.novCedula) : that.novCedula != null) return false;
        if (novCodigoConcepto != null ? !novCodigoConcepto.equals(that.novCodigoConcepto) : that.novCodigoConcepto != null)
            return false;
        if (novUnidad != null ? !novUnidad.equals(that.novUnidad) : that.novUnidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = novCedula != null ? novCedula.hashCode() : 0;
        result = 31 * result + (novCodigoConcepto != null ? novCodigoConcepto.hashCode() : 0);
        result = 31 * result + (novUnidad != null ? novUnidad.hashCode() : 0);
        result = 31 * result + (novAaaamm != null ? novAaaamm.hashCode() : 0);
        return result;
    }
}
