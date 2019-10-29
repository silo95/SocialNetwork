package mycompany.task1;


import java.net.*;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class OtherProfileSceneController {
    private static final String levelDBString = "username";
    
    @FXML private TextField usernameField;  
    @FXML private TextField nameField, surnameField, genderField, dateBirthField,
                            countryField, cityField, streetField, phoneField;
    
    private List<String> fields = Arrays.asList(new String[]{"city", "country", "dateBirth", "gender", "name", "phone", "street", "surname"});
    private HashMap<String, TextField> infoUser = new HashMap<>();
    
    public void OtherProfileSceneController(){
        infoUser.put("city", cityField);
        infoUser.put("country", countryField);
        infoUser.put("dateBirth", dateBirthField);
        infoUser.put("gender", genderField);
        infoUser.put("name", nameField);
        infoUser.put("phone", phoneField);
        infoUser.put("street", streetField);
        infoUser.put("surname", surnameField);   
    }
    
    
    public void getInfoUser(String username, Long userId){
        usernameField.setText(username);
        
        List<String> info = MainApp.ldb.getValuesFromUser(levelDBString, userId);
        int internalIndex = 0;
        
        
        for(int i = 0; i < fields.size(); i++){
            String[] splittedElement = info.get(internalIndex).split(":");
            System.out.println("elemento splittato " + splittedElement[0]);
            System.out.println("elemento di fields " + fields.get(i));
            System.out.println("value " + splittedElement[1]);
            System.out.println("hash map " + infoUser.get(fields.get(i)));
            
            if(splittedElement[0].compareTo(fields.get(i)) != 0){
                infoUser.get(fields.get(i)).setText(splittedElement[1]);
                internalIndex++;
            }
        }
        
    }

    
    public void backSetOnAction(ActionEvent event){
        MainApp.getStage().setScene(MainApp.homeScene);
    }
    
    
    public void initialize(URL url, ResourceBundle rb) {
        
    }     
}
