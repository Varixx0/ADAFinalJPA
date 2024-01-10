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

    @OneToOne
    @JoinColumn(name = "NIA", foreignKey = @ForeignKey(name = "FK_STUDENT_PROJECT_CALL"))
    private Student student;

    // Add getters and setters
}
