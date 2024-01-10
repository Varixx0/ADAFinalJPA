/*
 * Clase Entity correspondiente a la tabla Matricula en la base de datos
 */
package Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "MATRICULA_VT04")
public class Enrollment {
    @Id
    @Column(name = "NIA")
    private String studentNIA;

    @Id
    @Column(name = "CODMODULO")
    private int moduleId;

    @Column(name = "DESCRIPCION")
    private String description;

    //relaciones con las tablas Estudiante y Modulo

    @ManyToOne
    @JoinColumn(name = "NIA")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "CODMODULO")
    private Module module;

    // setters y getters

    public String getStudentId() {
        return studentNIA;
    }

    public void setStudentId(String studenNIA) {
        this.studentNIA = studentNIA;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Enrollment() {
    }

    public Enrollment(String studentNIA, int moduleId, String description, Student student, Module module) {
        this.studentNIA = studentNIA;
        this.moduleId = moduleId;
        this.description = description;
        this.student = student;
        this.module = module;
    }
    

}
