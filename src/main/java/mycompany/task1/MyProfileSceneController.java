package mycompany.task1;

import java.io.*;
import java.net.*;
import java.security.*;
import java.time.*;
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
    @FXML private TextField nameField, surnameField, countryField, cityField, streetField, phoneField;
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
        MainApp.homeController.welcomeLabel.setText("Hello " + username + ", you've written " + 
            db.getNumberOfPosts(loggedUserId) +" posts and " +
            db.getNumberOfComments(loggedUserId)+ " comments");
        MainApp.homeController.viewAllPosts(event);
        MainApp.homeController.commentOl.clear();
        MainApp.getStage().setScene(MainApp.homeScene);
    }
    

    public void getInfoUser(String user, String pass, Long userId){
        username = user;
        password = pass;
        loggedUserId = userId;
        usernameField.setText(user);
        errorLabel.setText("");
        errorLabel.setStyle("-fx-text-fill: #e50000");
        
        List<String> info = MainApp.ldb.getValuesFromUser(levelDBString, userId);
        int internalIndex = 0;
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
        
        if(!user.isEmpty() && user.compareTo(username) != 0){
            //in case of username modification
            Long idUserTemp = db.getIdByUser(user);
            if(idUserTemp < 0){   
                String pass = password;
                if(!passwordField.getText().isEmpty()){
                    pass = hash(String.valueOf(passwordField.getText()));
                }

                Person p = new Person(user, pass);
                p.setIdUser(loggedUserId);
                db.updatePerson(p);
                username = user;
                MainApp.homeController.username = user;
                
                errorLabel.setStyle("-fx-text-fill: green");
                errorLabel.setText("Profile has ben correctly updated.");
            }
            else{
                errorLabel.setText("Username not available. "); 
                return;
            }  
        }     
        else if (user.compareTo(username) == 0){
            if(!passwordField.getText().isEmpty()){
                String pass = hash(String.valueOf(passwordField.getText()));
                Person p = new Person(username, pass);
                p.setIdUser(loggedUserId);
                db.updatePerson(p);
                
            }
        }

        for(int i = 0; i < fields.size(); i++){
            String key = levelDBString +":"+ loggedUserId + ":" + fields.get(i);

            if(fields.get(i).compareTo("gender") == 0){
                
                if(genderComboBox.getValue() != null){
                    if(genderComboBox.getSelectionModel().getSelectedItem().toString().equals("Prefer not to say"))
                        MainApp.ldb.deleteSingleValueFromUser(key);
                    else
                        MainApp.ldb.putValuesToUser(key, (String) genderComboBox.getValue());  
                }
                
            }
            else if(fields.get(i).compareTo("dateBirth") == 0){
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
                    if(t.getText().isEmpty()) 
                        MainApp.ldb.deleteSingleValueFromUser(key);
                    
                    else
                        MainApp.ldb.putValuesToUser(key,(String)t.getText());                        
                    
                }
                
            }
        }
        
        errorLabel.setStyle("-fx-text-fill: green");
        errorLabel.setText("Profile has ben correctly updated.");

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
        infoUser.put("name", nameField);
        infoUser.put("phone", phoneField);
        infoUser.put("street", streetField);
        infoUser.put("surname", surnameField);
        
        genderComboBox.getItems().addAll("Female", "Male", "Prefer not to say");
        
        usernameField.focusedProperty().addListener((ov, oldv, newv) -> {
            if(!newv){
                if(usernameField.getText().isEmpty())
                    usernameField.setText(username);
            }
        });
        
        phoneField.textProperty().addListener((ov, oldv, newValue) -> {
            if(!newValue.matches("\\d*")){
                phoneField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        calendar.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) > 0 );
            }
        });
        
    }     
}
