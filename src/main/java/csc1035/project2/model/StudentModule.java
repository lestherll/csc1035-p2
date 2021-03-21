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
    private String module_id;

    @Id
    @Column(name="student_id")
    private String student_id;

    public StudentModule() {}

    public StudentModule(String module_id, String student_id) {
        this.module_id = module_id;
        this.student_id = student_id;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

}
