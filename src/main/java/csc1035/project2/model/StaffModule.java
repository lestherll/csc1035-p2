package csc1035.project2.model;
import org.hibernate.Session;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is used to allow queries to the StaffModule table and so that entries can be added directly to the table.
 * It contains Module id and Staff ID
 *
 * @author Ben Mitchell
 * JavaDoc and Comments by Ben Mitchell
 *
 * Named Queries:
 *      "StaffModule_getAll" - Returns all records in the StaffModule table
 *
 */
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

    /**
     * @param module_id This will be the identifier for the Module
     * @param staff_id This will be the identifier for the Staff member
     */
    public StaffModule(String module_id, String staff_id) {
        this.moduleId = module_id;
        this.staffId = staff_id;
    }

    //Create all of the get and setter methods so yuo are able to set and return individual attributes
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
     * @return Returns human readable print of the StaffModule Class
     */
    @Override
    public String toString() {
        return "StaffModule{" +
                "module_id='" + moduleId + '\'' +
                ", staff_id='" + staffId + '\'' +
                '}';
    }

    /**
     * @return returns the hash integer of staffId + moduleId
     */
    @Override
    public int hashCode() {
        return (int)(this.staffId + this.moduleId).hashCode();
    }

    /**
     *
     * @param obj object parameter representing StaffModule
     * @return Returns pk.studentId to this.staffId & pk.moduleId to this.moduleId
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof StudentModule)) return false;
        StaffModule pk = (StaffModule) obj;
        return pk.staffId.equals(this.staffId) && pk.moduleId.equals(this.moduleId);
    }

    /**
     *
     * @param session Creates DataBase session
     * @return Returns all records in the StaffModule table
     */
    public List<StaffModule> getAll(Session session) {
        return session
                .createNamedQuery("staffModule_getAll", StaffModule.class)
                .getResultList();
    }
}
