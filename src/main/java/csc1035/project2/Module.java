package csc1035.project2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
}


