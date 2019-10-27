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
    
    ArrayList<ArrayList<String>> toAdd = new ArrayList<ArrayList<String>>();
    ArrayList<String> singleList = new ArrayList<String>();
    
    
    
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
                singleList.add(levelDBString +":"+ loggedUserId+":name");
                singleList.add(nameField.getText());            
                toAdd.add(singleList);
        }

        if(!surnameField.getText().isEmpty()){               
            singleList.add(levelDBString +":"+ loggedUserId+":surname");
            singleList.add(nameField.getText());            
            toAdd.add(singleList);
        }

        if(!genderField.getText().isEmpty()){               
            singleList.add(levelDBString +":"+ loggedUserId+":gender");
            singleList.add(nameField.getText());            
            toAdd.add(singleList);
        }
        if(!dateBirthField.getText().isEmpty()){               
            singleList.add(levelDBString +":"+ loggedUserId+":dateBirth");
            singleList.add(nameField.getText());            
            toAdd.add(singleList);
        }

        if(!countryField.getText().isEmpty()){               
            singleList.add(levelDBString +":"+ loggedUserId+":country");
            singleList.add(nameField.getText());            
            toAdd.add(singleList);
        }

        if(!cityField.getText().isEmpty()){               
            singleList.add(levelDBString +":"+ loggedUserId+":city");
            singleList.add(nameField.getText());            
            toAdd.add(singleList);
        }

        if(!streetField.getText().isEmpty()){               
            singleList.add(levelDBString +":"+ loggedUserId+":street");
            singleList.add(nameField.getText());            
            toAdd.add(singleList);
        }

        if(!phoneField.getText().isEmpty()){               
            singleList.add(levelDBString +":"+ loggedUserId+":phone");
            singleList.add(nameField.getText());            
            toAdd.add(singleList);
        }
    }
    
    @FXML
    public void registerOnAction(){
        errorLabel.setText("");
        
        if(!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()){
            String username = String.valueOf(usernameField.getText());
            String password = hash(String.valueOf(passwordField.getText()));
            
            loggedUserId = db.login(username, password); //cambiare con register
            
            //dobbiamo controllare che lo username non sia già presente nel DB
           
            getValuesFromForm();
            
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
