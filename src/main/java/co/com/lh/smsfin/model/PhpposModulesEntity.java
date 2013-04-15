package co.com.lh.smsfin.model;

import javax.persistence.*;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 5/03/2012
 * Time: 01:29:51 PM
 */
@Entity
@Table( name = "phppos_modules")
public class PhpposModulesEntity {
    private String nameLangKey;

    @Basic
    @Column(name = "name_lang_key")
    public String getNameLangKey() {
        return nameLangKey;
    }

    public void setNameLangKey(String nameLangKey) {
        this.nameLangKey = nameLangKey;
    }

    private String descLangKey;

    @Basic
    @Column(name = "desc_lang_key")
    public String getDescLangKey() {
        return descLangKey;
    }

    public void setDescLangKey(String descLangKey) {
        this.descLangKey = descLangKey;
    }

    private int sort;

    @Basic
    @Column(name = "sort")
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    private String moduleId;

    @Id
    @Column(name = "module_id")
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhpposModulesEntity that = (PhpposModulesEntity) o;

        if (sort != that.sort) return false;
        if (descLangKey != null ? !descLangKey.equals(that.descLangKey) : that.descLangKey != null) return false;
        if (moduleId != null ? !moduleId.equals(that.moduleId) : that.moduleId != null) return false;
        if (nameLangKey != null ? !nameLangKey.equals(that.nameLangKey) : that.nameLangKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nameLangKey != null ? nameLangKey.hashCode() : 0;
        result = 31 * result + (descLangKey != null ? descLangKey.hashCode() : 0);
        result = 31 * result + sort;
        result = 31 * result + (moduleId != null ? moduleId.hashCode() : 0);
        return result;
    }
}
