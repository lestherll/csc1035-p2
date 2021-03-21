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

public class StaffModule implements Serializable {
    //Create variables, assign them to the columns to the staff_module Table and set Primary key field.
    @Id
    @Column(name = "module_id")
    private String module_id;

    @Id
    @Column(name = "staff_id")
    private String staff_id;

    public StaffModule(){}

    public StaffModule(String module_id, String staff_id) {
        this.module_id = module_id;
        this.staff_id = staff_id;
    }
}
