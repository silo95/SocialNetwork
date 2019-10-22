package mycompany.task1;

import javax.persistence.*;
import java.sql.*;


@Entity
@Table(name="Post")

public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;  
    
    private String strPost;   
    private Long user;
    private Timestamp date;
    
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

    public Long getUser(){
       return user;
    } 
    
    public void setUser(Long user){
        this.user = user;
    }

    public Timestamp getDate(){
       return date;
    } 
    
    public void setDate(Timestamp date){
        this.date = date;
    }
}