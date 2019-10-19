
package mycompany.provamaven;

import java.sql.*;
import javafx.beans.property.*;

public class Comment{

    private final SimpleIntegerProperty idComment;
    private final SimpleStringProperty strComment;
    private final SimpleStringProperty user;
    private final SimpleIntegerProperty post;
    private final SimpleObjectProperty<Timestamp> date;

    public Comment(int i, String s, String u, int p, Timestamp d){
        idComment = new SimpleIntegerProperty(i);
        strComment = new SimpleStringProperty(s);
        user = new SimpleStringProperty(u);
        post = new SimpleIntegerProperty(p);
        date = new SimpleObjectProperty<>(d);
    }

    public int getIdComment(){
        return idComment.get();
    }

    public String getStrComment(){
       return strComment.get();
    } 

    public String getUser(){
       return user.get();
    } 

    public int getPost(){
        return post.get();
    }

    public Timestamp getDate(){
       return date.get();
    } 

    public void setIdComment(int s){
        idComment.set(s);
    }

    public void setUser(String s){
        user.set(s);
    }

    public void setPost(int s){
        post.set(s);
    }

    public void setStrComment(String s){
        strComment.set(s);
    }

    public void setDate(Timestamp s){
        date.set(s);
    }

}