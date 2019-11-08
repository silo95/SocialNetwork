package mycompany.task1;

import java.sql.*;
import javafx.beans.property.*;

public class CommentBeans{

    private final SimpleLongProperty idComment;
    private final SimpleStringProperty strComment;
    private final SimpleStringProperty user;
    private final SimpleLongProperty post;
    private final SimpleObjectProperty<Timestamp> date;
    private final SimpleLongProperty idUser;

    public CommentBeans(long i, String s, String u, long p, Timestamp d, long id){
        idComment = new SimpleLongProperty(i);
        strComment = new SimpleStringProperty(s);
        user = new SimpleStringProperty(u);
        post = new SimpleLongProperty(p);
        date = new SimpleObjectProperty<>(d);
        idUser = new SimpleLongProperty(id);
    }

    public long getIdComment(){
        return idComment.get();
    }

    public String getStrComment(){
       return strComment.get();
    } 

    public String getUser(){
       return user.get();
    } 

    public long getIdUser(){
        return idUser.get();
    }
    
    public long getPost(){
        return post.get();
    }

    public Timestamp getDate(){
       return date.get();
    } 

    public void setIdComment(long s){
        idComment.set(s);
    }

    public void setUser(String s){
        user.set(s);
    }
    
    public void setIdUser(long s){
        idUser.set(s);
    }

    public void setPost(long s){
        post.set(s);
    }

    public void setStrComment(String s){
        strComment.set(s);
    }

    public void setDate(Timestamp s){
        date.set(s);
    }

}
