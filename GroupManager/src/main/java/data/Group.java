    /*
 * Clase Entity correspondiente a la tabla GRUPO en la base de datos
 */
package data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "GRUPO_VT04")
public class Group {
    @Id
    @Column(name = "CODGRUPO")
    private int groupId;

    @Column(name = "DESCRIPCION")
    private String description;

    @Column(name = "AULA")
    private String classroom;

    // Add getters and setters
}



