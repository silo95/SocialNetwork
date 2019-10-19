package mycompany.task1;
import javafx.beans.property.*;

public class User{
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;
    public User(String u, String p){
        username = new SimpleStringProperty(u);
        password = new SimpleStringProperty(p);
    }
    public String getUsername(){
        return username.get();
    }
    public String getPassword(){
       return password.get();
    } 
    public void setUsername(String s){
        username.set(s);
    }
    public void setPassword(String s){
        password.set(s);
    }
}