/*
 * * Clase Entity correspondiente a la tabla Project en la base de datos
 */
package entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "PROYECTO_CONVOCATORIA_VT04")
@NamedQueries({
    @NamedQuery(name = "Project.findById", query = "SELECT p FROM Project p WHERE p.projectId = :projectId"),
    @NamedQuery(name = "Project.findByTitle", query = "SELECT p FROM Project p WHERE p.title = :title"),
    @NamedQuery(name = "Project.findByStudent", query = "SELECT p FROM Project p WHERE p.student = :student")
})
public class Project {
    @Id
    @Column(name = "CODPROYECTO")
    private String projectId;

    @Column(name = "TITULO")
    private String title;

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

   
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Project() {
    }

    public Project(String projectId, String title, Student student) {
        this.projectId = projectId;
        this.title = title;
        this.student = student;
    }
}
