package csc1035.project2.model;
import org.hibernate.Session;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;

@Entity
@Table(name = "StudentModule")
@IdClass(StudentModule.class)
@NamedQueries({
        @NamedQuery(name = "studentModule_getAll", query = "FROM StudentModule"),
})
public class StudentModule implements Serializable {

    @Id
    @Column(name="module_id")
    private String moduleId;

    @Id
    @Column(name="student_id")
    private String studentId;

    public StudentModule() {}

    public StudentModule(String module_id, String student_id) {
        this.moduleId = module_id;
        this.studentId = student_id;
    }

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

    @Override
    public String toString() {
        return "StudentModule{" +
                "moduleId='" + moduleId + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return (int)(this.studentId + this.moduleId).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof StudentModule)) return false;
        StudentModule pk = (StudentModule) obj;
        return pk.studentId.equals(this.studentId) && pk.moduleId.equals(this.moduleId);
    }


    public List<StaffModule> getBookingsThisWeek(Session session) {
        return session
                .createNamedQuery("staffModule_getAll", StaffModule.class)
                .getResultList();
    }

}
