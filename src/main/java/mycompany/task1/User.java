package mycompany.task1;
import javafx.beans.property.*;
import javax.persistence.*;

@Entity
@Table(name = "User")
public class User{
    @Column(name="IdUser")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idUser;
    
    @Column(name="Username", length=50, nullable=false, unique=false)
    private String username;
    
    @Column(name="Password", length=64, nullable=false, unique=false)
    private String password;

    public Long getIdPost(){
        return idUser;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
       return password;
    } 

    public void setIdUser(Long idUser){
        this.idUser = idUser;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }


}