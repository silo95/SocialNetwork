package mycompany.task1;
import javafx.beans.property.*;
import javax.persistence.*;

@Entity
@Table(name = "User")
public class User{
    @Column(name="IdUser")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idUser;
    
    @Column(name="Username", length=50, nullable=false, unique=false)
    private String username;
    
    @Column(name="Password", length=64, nullable=false, unique=false)
    private String password;

    public String getUsername(){
        return username.get();
    }
    public String getPassword(){
       return password.get();
    } 
    public void setUsername(String s){
        username.set(s);
    }
    public void setPassword(String s){
        password.set(s);
    }
}