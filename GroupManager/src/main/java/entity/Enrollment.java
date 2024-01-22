/*
 * Clase Entity correspondiente a la tabla Matricula en la base de datos
 */
package entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "MATRICULA_VT04")
@NamedQueries({
    @NamedQuery(name = "Enrollment.findById", query = "SELECT e FROM Enrollment e WHERE e.idEnrollment = :idEnrollment"),
    @NamedQuery(name = "Enrollment.findByStudent", query = "SELECT e FROM Enrollment e WHERE e.student = :student"),
    @NamedQuery(name = "Enrollment.findByModule", query = "SELECT e FROM Enrollment e WHERE e.module = :module"),
    @NamedQuery(name = "Enrollment.findByDescription", query = "SELECT e FROM Enrollment e WHERE e.description = :description")
})
public class Enrollment {
    
    @Id
    @Column(name = "IDMATRICULA")
    private int idEnrollment;
    @Column(name = "DESCRIPCION")
    private String description;

    //relaciones con las tablas Estudiante y Modulo
    @ManyToOne
    @JoinColumn(name="NIA")
    private Student student; 
    @ManyToOne
    @JoinColumn(name = "CODMODULO")
    private Module module;

    // setters y getters

    public int getIdEnrollment() {
        return idEnrollment;
    }

    public void setIdEnrollment(int idEnrollment) {
        this.idEnrollment = idEnrollment;
    }

  

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    
    public Enrollment() {
    }
    
    public Enrollment(int id, String description, Student student, Module module) {
        this.description = description;
        this.module = module;
        this.idEnrollment = id; 
        this.student=student;
    }
    

}
