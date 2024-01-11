/*
     * Clase Entity correspondiente a la tabla GRUPO en la base de datos
 */
package entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


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

    
    
    //Constructores

    public Group(int groupId, String description, String classroom) {
        this.groupId = groupId;
        this.description = description;
        this.classroom = classroom;
    }
    
    public Group() {
    }

}
