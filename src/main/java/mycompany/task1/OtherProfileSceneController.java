package mycompany.task1;

import java.net.*;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class OtherProfileSceneController implements Initializable{
    private final String levelDbString = "username";
    private final HashMap<String, TextField> infoUser = new HashMap<>();
    private final List<String> fields = Arrays.asList(new String[]{"city", "country", "dateBirth", "gender",
                                                                    "name", "phone", "street", "surname"});
    
    @FXML private Label welcomeLabel;
    @FXML private TextField usernameField;  
    @FXML private TextField nameField, surnameField, genderField, dateBirthField,
                            countryField, cityField, streetField, phoneField;
    
    
    
    public void getInfoUser(String username, Long userId){
        welcomeLabel.setText("This is "+ username + "'s profile");
        usernameField.setText(username);
        
        List<String> info = MainApp.ldb.getValuesFromUser(levelDbString, userId);
        int internalIndex = 0;
        
        if(!info.isEmpty()){
            for(int i = 0; i < fields.size(); i++){
                String[] splittedElement = info.get(internalIndex).split(":");

                if(splittedElement[0].compareTo(fields.get(i)) == 0){
                    infoUser.get(fields.get(i)).setText(splittedElement[1]);
                    internalIndex++;
                    if(internalIndex == info.size()){
                        return;
                    }
                }
            }   
        }  
 
    }
    
    private void cleanFields(){
        usernameField.clear();
        cityField.clear();
        countryField.clear();
        dateBirthField.clear();
        genderField.clear();
        nameField.clear();
        phoneField.clear();
        streetField.clear();
        surnameField.clear();           
    }

    
    public void backSetOnAction(ActionEvent event){
        cleanFields();
        MainApp.getStage().setScene(MainApp.homeScene);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        infoUser.put("city", cityField);
        infoUser.put("country", countryField);
        infoUser.put("dateBirth", dateBirthField);
        infoUser.put("gender", genderField);
        infoUser.put("name", nameField);
        infoUser.put("phone", phoneField);
        infoUser.put("street", streetField);
        infoUser.put("surname", surnameField); 
    }     
}
