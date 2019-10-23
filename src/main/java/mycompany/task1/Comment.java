
package mycompany.task1;

import java.sql.*;
import javafx.beans.property.*;
import javax.persistence.*;

@Entity
@Table(name = "Comment")
public class Comment{
    @Column(name="idComment")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idComment;
    
    @Column(name="person", length=50, nullable=false, unique=false)
    private String person;
    
    @Column(name="strComment", length=50, nullable=false, unique=false)
    private String strComment;
    
    @Column(name="post", nullable=false, unique=false)
    private Long post;
    
    @Column(name="commentDate", nullable=false, unique=false)
    private Timestamp commentDate;

    
    public Long getIdComment(){
        return idComment;
    }
    
    public void setIdComment(Long s){
        this.idComment = s;
    }

    public String getStrComment(){
       return strComment;
    } 

    public void setStrComment(String str){
        this.strComment = str;
    }
    
    public String getPerson(){
       return person;
    } 
    
    public void setPerson(String str){
        this.person = str;
    }
    
    public Long getPost(){
        return post;
    }

    public void setPost(Long p){
        this.post = p;
    }
    
    public Timestamp getDate(){
       return commentDate;
    } 

    public void setDate(Timestamp d){
        this.commentDate= d;
    }
    
    @Override
    public String toString(){
        return "Comment{ " + 
                "id=" + idComment +
                ", strPost=" + strComment +
                ", post=" + post +
                ", personId=" + person +
                ", date=" + commentDate +
                " }";
    }
    
}