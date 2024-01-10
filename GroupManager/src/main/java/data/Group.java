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

   // Setters y Getters

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
    
    
}



