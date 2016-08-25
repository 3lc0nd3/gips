package co.com.lh.smsfin.modelFac;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 13/06/2012
 * Time: 04:11:38 PM
 */
@Entity
@Table( name = "POS_FUNC_ESTADO")
@IdClass(co.com.lh.smsfin.modelFac.PosFuncEstadoPK.class)
public class PosFuncEstado {
    private long fesCedula;

    @Id
    @Column(name = "FES_CEDULA")
    public long getFesCedula() {
        return fesCedula;
    }

    public void setFesCedula(long fesCedula) {
        this.fesCedula = fesCedula;
    }

    private Integer fesIdFuncionario;

    @Basic
    @Column(name = "FES_ID_FUNCIONARIO")
    public Integer getFesIdFuncionario() {
        return fesIdFuncionario;
    }

    public void setFesIdFuncionario(Integer fesIdFuncionario) {
        this.fesIdFuncionario = fesIdFuncionario;
    }

    private Integer fesIdPos;

    @Id
    @Column(name = "FES_ID_POS")
    public Integer getFesIdPos() {
        return fesIdPos;
    }

    public void setFesIdPos(Integer fesIdPos) {
        this.fesIdPos = fesIdPos;
    }

    private String fesEstado;

    @Basic
    @Column(name = "FES_ESTADO")
    public String getFesEstado() {
        return fesEstado;
    }

    public void setFesEstado(String fesEstado) {
        this.fesEstado = fesEstado;
    }

    private Date fesFecmod;

    @Basic
    @Column(name = "FES_FECMOD")
    public Date getFesFecmod() {
        return fesFecmod;
    }

    public void setFesFecmod(Date fesFecmod) {
        this.fesFecmod = fesFecmod;
    }

    private String fesUsuario;

    @Basic
    @Column(name = "FES_USUARIO")
    public String getFesUsuario() {
        return fesUsuario;
    }

    public void setFesUsuario(String fesUsuario) {
        this.fesUsuario = fesUsuario;
    }

    private String fesTerminal;

    @Basic
    @Column(name = "FES_TERMINAL")
    public String getFesTerminal() {
        return fesTerminal;
    }

    public void setFesTerminal(String fesTerminal) {
        this.fesTerminal = fesTerminal;
    }
}
