package csc1035.project2.model;
import org.hibernate.Session;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "StudentModule")
@IdClass(StudentModule.class)
@NamedQueries({
        @NamedQuery(name = "StaffModule_getAll", query = "FROM StaffModule"),
})
public class StaffModule implements Serializable {
    //Create variables, assign them to the columns to the staff_module Table and set Primary key field.
    @Id
    @Column(name = "module_id")
    private String moduleId;

    @Id
    @Column(name = "staff_id")
    private String staffId;

    public StaffModule(){}

    public StaffModule(String module_id, String staff_id) {
        this.moduleId = module_id;
        this.staffId = staff_id;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    /**
     *
     * @return Returns human readable print of the StaffModule Class
     */
    @Override
    public String toString() {
        return "StaffModule{" +
                "module_id='" + moduleId + '\'' +
                ", staff_id='" + staffId + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return (int)(this.staffId + this.moduleId).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof StudentModule)) return false;
        StaffModule pk = (StaffModule) obj;
        return pk.staffId.equals(this.staffId) && pk.moduleId.equals(this.moduleId);
    }

    public List<StaffModule> getAll(Session session) {
        return session
                .createNamedQuery("staffModule_getAll", StaffModule.class)
                .getResultList();
    }
}
