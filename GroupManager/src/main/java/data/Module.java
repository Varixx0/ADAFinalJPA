/*
 * * Clase Entity correspondiente a la tabla Modulo en la base de datos
 */
package data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "MODULO_VT04")
public class Module {
    @Id
    @Column(name = "CODMODULO")
    private int moduleId;

    @Column(name = "DESCRIPCION")
    private String description;

    @Column(name = "NUMHORAS")
    private int numHours;

    // Add getters and setters
}

