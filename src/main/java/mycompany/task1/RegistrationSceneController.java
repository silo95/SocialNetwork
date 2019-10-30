package mycompany.task1;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;
import javafx.beans.value.ChangeListener;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javax.xml.bind.*;

public class RegistrationSceneController implements Initializable{
    private Long loggedUserId;
    private DBManager db;
    private final String levelDBString = "username";
    //public static String username, password;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    @FXML private TextField usernameField;
    @FXML private TextField nameField, surnameField, 
                            countryField, cityField, streetField, phoneField;
    @FXML private ComboBox genderComboBox;
    @FXML private DatePicker calendar;
    
        
    private void cleanFields(){
        usernameField.clear();
        passwordField.clear();
        cityField.clear();
        countryField.clear();
        nameField.clear();
        phoneField.clear();
        streetField.clear();
        surnameField.clear();     
        genderComboBox.getSelectionModel().clearSelection();
        calendar.getEditor().clear();
    }
      
    public void backSetOnAction(ActionEvent event){
        cleanFields();
        MainApp.getStage().setScene(MainApp.firstScene);
    }
    

    
    private void getValuesFromForm(){
        
        if(!nameField.getText().isEmpty()){            
            MainApp.ldb.putValuesToUser(levelDBString +":"+ loggedUserId + ":name", nameField.getText());
        }

        if(!surnameField.getText().isEmpty()){    
            MainApp.ldb.putValuesToUser(levelDBString +":"+ loggedUserId+":surname", surnameField.getText());
        }
        
        
        if(!genderComboBox.getSelectionModel().isEmpty()){ 
            String gender = genderComboBox.getSelectionModel().getSelectedItem().toString();
            MainApp.ldb.putValuesToUser(levelDBString +":"+ loggedUserId+":gender", gender);
        }
        
        
        if(!calendar.getEditor().getText().isEmpty()){
            String date = calendar.getValue().toString();
            MainApp.ldb.putValuesToUser(levelDBString +":"+ loggedUserId+":dateBirth", date);
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
            
            /*
            List<String> res = MainApp.ldb.getValuesFromUser(levelDBString, loggedUserId);
            
            for(int i = 0; i < res.size(); i++){
                String[] attribute = res.get(i).split(":");
                System.out.println(attribute[0] + ":" + attribute[1]);
            }
            */
            
            MainApp.homeController.setParameters(username, password, loggedUserId);  
            MainApp.getStage().setScene(MainApp.homeScene);
            cleanFields();            
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
        }catch (UnsupportedEncodingException|NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
    public void initialize(URL url, ResourceBundle rb) {
        db = MainApp.db; 
        genderComboBox.getItems().addAll("Female", "Male");
        
    } 
    
    
}
