package mycompany.task1;

import java.sql.*;
import javafx.beans.property.*;

public class Post{

    private final SimpleIntegerProperty idPost;
    private final SimpleStringProperty strPost;
    private final SimpleStringProperty user;
    private final SimpleIntegerProperty comments;
    private final SimpleObjectProperty<Timestamp> date;

    public Post(int i, String s, String u, Timestamp d, int c){
        idPost = new SimpleIntegerProperty(i);
        strPost = new SimpleStringProperty(s);
        user = new SimpleStringProperty(u);
        date = new SimpleObjectProperty<>(d);
        comments = new SimpleIntegerProperty(c);
    }

    public int getIdPost(){
        return idPost.get();
    }

    public String getStrPost(){
       return strPost.get();
    } 

    public String getUser(){
       return user.get();
    } 

    public Timestamp getDate(){
       return date.get();
    } 

    public int getComments(){
        return comments.get();
    }
    
    public void setIdPost(int s){
        idPost.set(s);
    }

    public void setUser(String s){
        user.set(s);
    }

    public void setStrPost(String s){
        strPost.set(s);
    }

    public void setDate(Timestamp s){
        date.set(s);
    }
    
    public void setComments(int s){
        comments.set(s);
    }

}