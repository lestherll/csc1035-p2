package csc1035.project2;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Module")
public class Module {

    @Id
    @Column(name = "module_id")
    private String moduleId;

    @Column(name = "name")
    private String name;

    @Column(name = "credits")
    private int credits;

    @Column(name = "weeks")
    private int weeks;

    @OneToMany(mappedBy = "module")
    private List<Booking> booking;
}




