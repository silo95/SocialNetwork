package mycompany.task1;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javax.xml.bind.*;

public class MyProfileSceneController implements Initializable{
    private Long loggedUserId;
    private DBManager db;
    private String username, password;
    private final String levelDBString = "username";
    private final HashMap<String, TextField> infoUser = new HashMap<>();
    private final List<String> fields = Arrays.asList(new String[]{"city", "country", "dateBirth", "gender",
                                                                    "name", "phone", "street", "surname"});
    
    
    @FXML private Label errorLabel;
    @FXML private TextField usernameField; 
    @FXML private PasswordField passwordField;
    @FXML private TextField nameField, surnameField, /*genderField, dateBirthField,*/
                            countryField, cityField, streetField, phoneField;
    @FXML private ComboBox genderComboBox;
    @FXML private DatePicker calendar;
    

    private void cleanFields(){
        usernameField.clear();
        passwordField.clear();
        cityField.clear();
        countryField.clear();
        //dateBirthField.clear();
        nameField.clear();
        phoneField.clear();
        streetField.clear();
        surnameField.clear();    
        genderComboBox.getSelectionModel().clearSelection();
        calendar.getEditor().clear();
    }
    
    
    public void backSetOnAction(ActionEvent event){
        cleanFields();
        MainApp.getStage().setScene(MainApp.homeScene);
    }
    

    public void getInfoUser(String user, String pass, Long userId){
        username = user;
        password = pass;
        loggedUserId = userId;
        
        usernameField.setText(user);
        passwordField.setText(pass);
        
        List<String> info = MainApp.ldb.getValuesFromUser(levelDBString, userId);
        int internalIndex = 0;
                
        if(!info.isEmpty()){
            for(int i = 0; i < fields.size(); i++){
                String[] splittedElement = info.get(internalIndex).split(":");

                if(splittedElement[0].compareTo(fields.get(i)) == 0){
                    if(splittedElement[0].compareTo("gender") == 0){
                        genderComboBox.setValue(splittedElement[1]);
                    }
                    else if(splittedElement[0].compareTo("dateBirth") == 0){
                        calendar.getEditor().setText(splittedElement[1]);
                    }
                    else{
                        infoUser.get(fields.get(i)).setText(splittedElement[1]);
                    }

                    internalIndex++;
                    if(internalIndex == info.size()){
                        return;
                    }
                }
                
                
                
            }   
        }
    }
    
    
    @FXML
    public void saveButtonOnAction(){
        //dobbiamo controllare che lo username non sia già presente e fare l'update sul db e sul ldb
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = MainApp.db;
        
        infoUser.put("city", cityField);
        infoUser.put("country", countryField);
        //infoUser.put("dateBirth", dateBirthField);
        infoUser.put("name", nameField);
        infoUser.put("phone", phoneField);
        infoUser.put("street", streetField);
        infoUser.put("surname", surnameField);
        
        genderComboBox.getItems().addAll("Female", "Male");
    }     
}
