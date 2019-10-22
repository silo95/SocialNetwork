package mycompany.task1;

import javax.persistence.*;
import java.sql.*;


@Entity
@Table(name="Post")
public class Post{
    @Column(name="IdPost")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;  
    
    @Column(name="strPost", length=50, nullable=false, unique=false)
    private String strPost;
    
    @Column(name="person", nullable=false, unique=false)
    private Long person;
    
    @Column(name="postDate")
    private Timestamp postDate;
    
    public Long getIdPost(){
        return idPost;
    }
    
    public void setIdPost(Long idPost){
        this.idPost = idPost;
    }


    public String getStrPost(){
       return strPost;
    } 
    
    public void setStrPost(String strPost){
        this.strPost = strPost;
    }

    public Long getPerson(){
       return person;
    } 
    
    public void setPerson(Long person){
        this.person = person;
    }

    public Timestamp getPostDate(){
       return postDate;
    } 
    
    public void setPostDate(Timestamp postDate){
        this.postDate = postDate;
    }
}