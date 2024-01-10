/*
 * * Clase Entity correspondiente a la tabla Alumno en la base de datos
 */
package data;

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
    @JoinColumn(name = "CODGRUPO", foreignKey = @ForeignKey(name = "FK_GROUP_STUDENT"))
    private Group group;

    // Add getters and setters
}
