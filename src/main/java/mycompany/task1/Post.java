package mycompany.task1;

import javax.persistence.*;
import java.sql.*;
import java.util.*;


@Entity
@Table(name="Post")
public class Post{
    @Column(name="IdPost")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;  
    
    @Column(name="strPost", length=50, nullable=false, unique=false)
    private String strPost;
    
   /* @Column(name="person", nullable=false, unique=false)
    private Long person;*/
    
    @Column(name="postDate")
    private Timestamp postDate;
    
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;
    
    @ManyToOne
    @JoinColumn(name="person", nullable=false, unique=false)
    private Person person;
    
    public Post(){
        
    }
    public Post(String strPost, Person person,Timestamp t){
        //this.idPost = idPost;
        this.strPost = strPost;
        this.person = person;
        this.postDate = t;
    }
    
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

    public Person getPerson(){
       return person;
    } 
    
    public void setPerson(Person person){
        this.person = person;
    }

    public Timestamp getPostDate(){
       return postDate;
    } 
    
    public void setPostDate(Timestamp postDate){
        this.postDate = postDate;
    }
    
    public List<Comment> getComments(){
        return comments;
    }
    
    public void setComments(List<Comment> comments){
        this.comments = comments;
    }
    
    @Override
    public String toString(){
        return "Post{ " + 
                "id=" + idPost +
                ", strPost=" + strPost +
                //", personId=" + person +
                ", date=" + postDate +
                " }";
    }
}