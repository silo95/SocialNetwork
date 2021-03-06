package mycompany.task1;

import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = "Comment")
public class Comment{
    @Column(name="idComment")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idComment;
    
    @Column(name="strComment", length=50, nullable=false, unique=false)
    private String strComment;
    
    @ManyToOne
    @JoinColumn(name="post", nullable=false, unique=false)
    private Post post;
    
    @ManyToOne
    @JoinColumn(name="person", nullable=false, unique=false)
    private Person person;
    
    @Column(name="commentDate", unique=false)
    private Timestamp commentDate;

    public Comment(){
        
    }
        
    public Comment(String strComment, Person person, Post post,Timestamp t){
        this.strComment = strComment;
        this.person = person;
        this.post = post;
        this.commentDate = t;
    }
    
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
    
    public Person getPerson(){
       return person;
    } 
    
    public void setPerson(Person person){
        this.person = person;
    }
    
    public Post getPost(){
        return post;
    }

    public void setPost(Post p){
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