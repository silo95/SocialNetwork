package mycompany.task1;

import java.sql.*;
import javafx.beans.property.*;

public class PostBeans{

    private final SimpleLongProperty idPost;
    private final SimpleStringProperty strPost;
    private final SimpleStringProperty user;
    private final SimpleIntegerProperty comments;
    private final SimpleObjectProperty<Timestamp> date;
    private final SimpleLongProperty idUser;

    public PostBeans(long i, String s, String u, Timestamp d, int c, long id){
        idPost = new SimpleLongProperty(i);
        strPost = new SimpleStringProperty(s);
        user = new SimpleStringProperty(u);
        date = new SimpleObjectProperty<>(d);
        comments = new SimpleIntegerProperty(c);
        idUser = new SimpleLongProperty(id);
    }

    public long getIdPost(){
        return idPost.get();
    }

    public String getStrPost(){
       return strPost.get();
    } 

    public String getUser(){
       return user.get();
    } 
    
    public long getIdUser(){
        return idUser.get();
    }

    public Timestamp getDate(){
       return date.get();
    } 

    public int getComments(){
        return comments.get();
    }
    
    public void setIdPost(long s){
        idPost.set(s);
    }

    public void setUser(String s){
        user.set(s);
    }
    
    public void setIdUser(long s){
        idUser.set(s);
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