package mycompany.task1;
import javax.persistence.*;

@Entity
@Table(name = "Person")
public class Person{
    @Column(name="idPerson")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idPerson;
    
    @Column(name="Username", length=50, nullable=false, unique=true)
    private String username;
    
    @Column(name="Password", length=64, nullable=false, unique=false)
    private String password;

    public Long getIdPerson(){
        return idPerson;
    }
    
    public void setIdUser(Long idPerson){
        this.idPerson = idPerson;
    }

    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }

    
    public String getPassword(){
       return password;
    } 

    public void setPassword(String password){
        this.password = password;
    }


}