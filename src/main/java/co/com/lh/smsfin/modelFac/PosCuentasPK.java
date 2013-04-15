package co.com.lh.smsfin.modelFac;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 12/03/2012
 * Time: 05:02:16 PM
 */
public class PosCuentasPK implements Serializable {
    private String cueMovto;

    @Column(name = "CUE_MOVTO")
    @Id
    public String getCueMovto() {
        return cueMovto;
    }

    public void setCueMovto(String cueMovto) {
        this.cueMovto = cueMovto;
    }

    private String cueNaturaleza;

    @Column(name = "CUE_NATURALEZA")
    @Id
    public String getCueNaturaleza() {
        return cueNaturaleza;
    }

    public void setCueNaturaleza(String cueNaturaleza) {
        this.cueNaturaleza = cueNaturaleza;
    }

    private String cueCuentaConcatenada;

    @Column(name = "CUE_CUENTA_CONCATENADA")
    @Id
    public String getCueCuentaConcatenada() {
        return cueCuentaConcatenada;
    }

    public void setCueCuentaConcatenada(String cueCuentaConcatenada) {
        this.cueCuentaConcatenada = cueCuentaConcatenada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosCuentasPK that = (PosCuentasPK) o;

        if (cueCuentaConcatenada != null ? !cueCuentaConcatenada.equals(that.cueCuentaConcatenada) : that.cueCuentaConcatenada != null)
            return false;
        if (cueMovto != null ? !cueMovto.equals(that.cueMovto) : that.cueMovto != null) return false;
        if (cueNaturaleza != null ? !cueNaturaleza.equals(that.cueNaturaleza) : that.cueNaturaleza != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cueMovto != null ? cueMovto.hashCode() : 0;
        result = 31 * result + (cueNaturaleza != null ? cueNaturaleza.hashCode() : 0);
        result = 31 * result + (cueCuentaConcatenada != null ? cueCuentaConcatenada.hashCode() : 0);
        return result;
    }
}
