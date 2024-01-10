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
    
    //Setters y Getters
    
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

    public int getNumHours() {
        return numHours;
    }

    public void setNumHours(int numHours) {
        this.numHours = numHours;
    }

    
}

