package csc1035.project2.model;
import org.hibernate.Session;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;

/**
 * This class is used to allow queries to the StudentModule table and so that entries can be added directly to the table
 * It contains Module id and Student ID
 *
 * @author Ben Mitchell
 * JavaDoc and Comments by Ben Mitchell
 *
 * Named Queries:
 *      "studentModule_getAll" - Returns all of the records in the StudentModule table
 *
 */
@Entity
@Table(name = "StudentModule")
@IdClass(StudentModule.class)
@NamedQueries({
        @NamedQuery(name = "studentModule_getAll", query = "FROM StudentModule"),
})
public class StudentModule implements Serializable {
    //Create variables, assign them to the columns to the StudentModule Table and set Primary key field.
    @Id
    @Column(name="module_id")
    private String moduleId;

    @Id
    @Column(name="student_id")
    private String studentId;

    public StudentModule() {}

    /**
     * @param module_id This will be the identifier for the Module
     * @param student_id This will be the identifier for the student
     */
    public StudentModule(String module_id, String student_id) {
        this.moduleId = module_id;
        this.studentId = student_id;
    }

    //Create all of the get and setter methods so yuo are able to set and return individual attributes
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * @return returns human readable print of the Student Module class
     */
    @Override
    public String toString() {
        return "StudentModule{" +
                "moduleId='" + moduleId + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }

    /**
     * @return returns the hash integer of studentId + moduleId
     */
    @Override
    public int hashCode() {
        return (int)(this.studentId + this.moduleId).hashCode();
    }

    /**
     * @param obj object parameter representing StudentModule
     * @return Returns pk.studentId to this.studentId & pk.moduleId to this.moduleId
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof StudentModule)) return false;
        StudentModule pk = (StudentModule) obj;
        return pk.studentId.equals(this.studentId) && pk.moduleId.equals(this.moduleId);
    }

    /**
     * @param session Creates DataBase session
     * @return Returns all records in the StudentModule table
     */
    public List<StaffModule> getAll(Session session) {
        return session
                .createNamedQuery("staffModule_getAll", StaffModule.class)
                .getResultList();
    }

}
