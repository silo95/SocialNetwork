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
        errorLabel.setText("");
        errorLabel.setStyle("-fx-text-fill: #e50000");
        //passwordField.setText(pass);
        
        List<String> info = MainApp.ldb.getValuesFromUser(levelDBString, userId);
        int internalIndex = 0;
        //System.out.println("stampo valori da db: " + info);
        if(!info.isEmpty()){
            for(int i = 0; i < fields.size(); i++){
                
                String[] splittedElement = info.get(internalIndex).split(":");
                if(splittedElement.length == 2){

                    if(splittedElement[0].compareTo(fields.get(i)) == 0){
                        if(splittedElement[0].compareTo("gender") == 0){
                            genderComboBox.setValue(splittedElement[1]);
                        }
                        else if(splittedElement[0].compareTo("dateBirth") == 0){
                            calendar.getEditor().setText(splittedElement[1]);
                        }
                        else{
                            //System.out.println("stampo fields in posizione " + i + fields.get(i));
                          //  System.out.println("stampo il valore a capo");
                            //System.out.println(splittedElement[1]);
                            if(splittedElement[1] != null)
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
    }
    
    
    @FXML
    public void saveButtonOnAction(){
        String user = usernameField.getText();
        errorLabel.setText("");
        //errorLabel.setStyle("-fx-text-fill: #e50000");
        
        if(!passwordField.getText().isEmpty()){
            String pass = hash(String.valueOf(passwordField.getText())); 
            if(!user.equals(username)){
                Long idUserTemp = db.getIdByUser(user);
                if(idUserTemp < 0){          
                    Person p = new Person(user, pass);
                    p.setIdUser(loggedUserId);
                    db.updatePerson(p);
                    errorLabel.setStyle("-fx-text-fill: green");
                    errorLabel.setText("Profile has ben correctly updated.");
                }
                else{
                    
                    errorLabel.setText("Username not available. "); 
                    return;
                }
            }else{
                if(!pass.equals(password)){
                    Person p = new Person(username, pass); 
                    p.setIdUser(loggedUserId);
                    db.updatePerson(p);
                    errorLabel.setStyle("-fx-text-fill: green");
                    errorLabel.setText("Profile has ben correctly updated.");
                }
                
            }
            
            
            
        }
        else{
            errorLabel.setText("You must enter a new password or re-insert the old one. ");
            return;
        }
        
        //MODIFICA CAMPI
        
        //TO DO: Considerare variazioni?
        
        //System.out.println("Prendo i valori presenti");
        for(int i = 0; i < fields.size(); i++){
            String key = "username:"+ loggedUserId + ":" + fields.get(i);
            //System.out.println("Relativo al campo " + fields.get(i));

            if(fields.get(i).compareTo("gender") == 0){
                //System.out.println("campo gender: " + genderComboBox.getValue());
                if(genderComboBox.getValue() != null)
                MainApp.ldb.putValuesToUser(key, (String) genderComboBox.getValue());            
            }
            else if(fields.get(i).compareTo("dateBirth") == 0){
               // System.out.println(calendar.getEditor().getText());
                if(calendar.getEditor().getText().isEmpty())
                    MainApp.ldb.deleteSingleValueFromUser(key);            

                else
                    MainApp.ldb.putValuesToUser(key,(String)calendar.getEditor().getText());            
            }
            else{
                TextField t = infoUser.get(fields.get(i));
                if(t == null)
                    continue;
                else{
                    if(t.getText().isEmpty()){
                        //System.out.println("sto cancellando questo attributo: " + fields.get(i));
                        MainApp.ldb.deleteSingleValueFromUser(key);
                    }
                    
                    else{
                       // System.out.println("sto aggiungendo questo attributo: " + fields.get(i) + " e aggiungo questo valore: " + (String)t.getText());
                        MainApp.ldb.putValuesToUser(key,(String)t.getText());                        
                    }
                }
                
            }
        }
        //levelDB -> altre info utente
        
        
        
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
