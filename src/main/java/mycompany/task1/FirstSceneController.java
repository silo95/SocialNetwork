package mycompany.task1;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javax.xml.bind.*;

public class FirstSceneController implements Initializable {
    public static Long loggedUserId;
    //public DBManager db;
    private DBManager db;
    public static String username, password;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    
    @FXML
    public void loginSetOnAction(ActionEvent event){ //modificare nel caso
        errorLabel.setText("");
        
        if(!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()){
            username = String.valueOf(usernameField.getText());
            password = hash(String.valueOf(passwordField.getText()));
            loggedUserId = db.login(username, password);
            
            if(loggedUserId > 0){
                MainApp.username = username;
                MainApp.loggedUserId = loggedUserId;
                usernameField.clear();
                passwordField.clear();
                try{
                    MainApp.homeScene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/HomeScene.fxml")));
                    MainApp.homeScene.getStylesheets().add("/styles/Style.css");
                    MainApp.getStage().setScene(MainApp.homeScene);
                }catch(IOException ioe){
                    System.err.println("Error in loading /fxml/HomeScene.fxml");
                }
                
            }            
            else{
                errorLabel.setText("Username or Password not correct. Please, try again. ");
            }
        }
        else{
            errorLabel.setText("Insert all the required fields. ");
        }
 
    }
    
    public void registrationSetOnAction(ActionEvent event){
        errorLabel.setText("");
        MainApp.getStage().setScene(MainApp.registrationScene);
    }
    
    private String hash(String psw){
        byte[] hash;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(psw.getBytes("UTF-8")); 
            return DatatypeConverter.printHexBinary(hash);
        }catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        db = MainApp.db;
    }
       
}
