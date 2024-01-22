/*
 * * Clase Entity correspondiente a la tabla Modulo en la base de datos
 */
package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "MODULO_VT04")
@NamedQueries({
    @NamedQuery(name = "Module.findById", query = "SELECT m FROM Module m WHERE m.moduleId = :moduleId"),
    @NamedQuery(name = "Module.findByDescription", query = "SELECT m FROM Module m WHERE m.description = :description"),
    @NamedQuery(name = "Module.findByNumHours", query = "SELECT m FROM Module m WHERE m.numHours = :numHours")
})
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

    public Module() {
    }

    public Module(int moduleId, String description, int numHours) {
        this.moduleId = moduleId;
        this.description = description;
        this.numHours = numHours;
    }
    

}

