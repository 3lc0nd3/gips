package co.com.lh.smsfin.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Edward L. Ramirez A.
 * cel 300-554-3367
 * email elramireza@gmail.com
 * Date: 5/03/2012
 * Time: 01:00:09 PM
 */
public class PhpposPermissionsEntityPK implements Serializable {
    private String moduleId;

    @Column(name = "module_id")
    @Id
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    private int personId;

    @Column(name = "person_id")
    @Id
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhpposPermissionsEntityPK that = (PhpposPermissionsEntityPK) o;

        if (personId != that.personId) return false;
        if (moduleId != null ? !moduleId.equals(that.moduleId) : that.moduleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = moduleId != null ? moduleId.hashCode() : 0;
        result = 31 * result + personId;
        return result;
    }
}
