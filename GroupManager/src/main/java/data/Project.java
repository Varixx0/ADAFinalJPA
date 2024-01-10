/*
 * * Clase Entity correspondiente a la tabla Project en la base de datos
 */
package data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.ForeignKey;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "PROYECTO_CONVOCATORIA_VT04")
public class Project {
    @Id
    @Column(name = "CODPROYECTO")
    private String projectId;

    @Column(name = "TITULO")
    private String title;

    @Column(name = "NIA")
    private String studentId;
    
    // Relacion con la tabla student
    
    @OneToOne
    @JoinColumn(name = "NIA")
    private Student student;
    
    //Setters y Getters

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

   
}
