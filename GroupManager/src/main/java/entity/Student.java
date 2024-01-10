/*
 * * Clase Entity correspondiente a la tabla Alumno en la base de datos
 */
package entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.JoinColumn;


@Entity
@Table(name = "ALUMNO_VT04")
public class Student {
    @Id
    @Column(name = "NIA")
    private String nia;

    @Column(name = "NOMBRE")
    private String name;

    @Column(name = "APELLIDOS")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "CODGRUPO")
    private Group group;

    //Setters y Getters

    public String getNia() {
        return nia;
    }

    public void setNia(String nia) {
        this.nia = nia;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Student(String nia, String name, String lastName, Group group) {
        this.nia = nia;
        this.name = name;
        this.lastName = lastName;
        this.group = group;
    }

    public Student() {
    }

    
}
