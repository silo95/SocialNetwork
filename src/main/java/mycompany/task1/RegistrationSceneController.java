package mycompany.task1;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javax.xml.bind.*;

public class RegistrationSceneController {
    public static Long loggedUserId;
    private final DBManager db;
    private final String levelDBString = "username";
    //public static String username, password;
    @FXML private TextField usernameField;
    @FXML private TextField nameField, surnameField, genderField, dateBirthField,
                            countryField, cityField, streetField, phoneField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    

    public RegistrationSceneController(){
        db = MainApp.db;       
        //username = MainApp.username;
        //loggedUserId = MainApp.loggedUserId;   
    }
      
    public void backSetOnAction(ActionEvent event){
        MainApp.getStage().setScene(MainApp.firstScene);
    }
    
    private void getValuesFromForm(){
        
        if(!nameField.getText().isEmpty()){            
            MainApp.ldb.putValuesToUser(levelDBString +":"+ loggedUserId + ":name", nameField.getText());
        }

        if(!surnameField.getText().isEmpty()){    
            MainApp.ldb.putValuesToUser(levelDBString +":"+ loggedUserId+":surname", surnameField.getText());
        }

        if(!genderField.getText().isEmpty()){   
            MainApp.ldb.putValuesToUser(levelDBString +":"+ loggedUserId+":gender", genderField.getText());
        }
        
        if(!dateBirthField.getText().isEmpty()){ 
            MainApp.ldb.putValuesToUser(levelDBString +":"+ loggedUserId+":dateBirth", dateBirthField.getText());
        }

        if(!countryField.getText().isEmpty()){  
            MainApp.ldb.putValuesToUser(levelDBString +":"+ loggedUserId+":country", countryField.getText());
        }

        if(!cityField.getText().isEmpty()){  
            MainApp.ldb.putValuesToUser(levelDBString +":"+ loggedUserId+":city", cityField.getText());
        }

        if(!streetField.getText().isEmpty()){ 
            MainApp.ldb.putValuesToUser(levelDBString +":"+ loggedUserId+":street", streetField.getText());
        }

        if(!phoneField.getText().isEmpty()){     
            MainApp.ldb.putValuesToUser(levelDBString +":"+ loggedUserId+":phone", phoneField.getText());
        }
        
    }
    
    @FXML
    public void registerOnAction(){
        errorLabel.setText("");
        
        if(!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()){
            String username = String.valueOf(usernameField.getText());
            
            //dobbiamo controllare che lo username non sia già presente nel DB
            
            String password = hash(String.valueOf(passwordField.getText())); 
            loggedUserId = db.register(username, password);
            
           
            getValuesFromForm();
            
            List<String> res = MainApp.ldb.getValuesFromUser(levelDBString, loggedUserId);
            
            System.out.println("size list " + res.size());
            
            for(int i = 0; i < res.size(); i++){
                String[] attribute = res.get(i).split(":");
                System.out.println(attribute[0] + ":" + attribute[1]);
             
            }
            
            /*
            
            MainApp.username = username;
            MainApp.loggedUserId = loggedUserId;
            try{
                MainApp.homeScene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/HomeScene.fxml")));
                MainApp.homeScene.getStylesheets().add("/styles/Style.css");
                MainApp.getStage().setScene(MainApp.homeScene);

            }catch(IOException ioe){
                System.err.println("Error in loading /fxml/HomeScene.fxml");
            }
            */
            
        }
        else{
            errorLabel.setText("Insert all the required fields. ");
        }   
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

    } 
    
    
}
