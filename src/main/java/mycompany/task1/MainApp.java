package mycompany.task1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.*;
import static javafx.application.Application.launch;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SocialNetworkInterface.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Style.css");
        
        stage.setTitle("Social Network");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args){
        launch(args);
       /* LevelDBManager levelDB = new LevelDBManager();
        
        List<String> res = levelDB.getValuesFromUser("Username",1);
        for(int i = 0; i < res.size(); i++){
            String[] attribute = res.get(i).split(":");
            System.out.println(attribute[0] + ":" + attribute[1]);
          //  System.out.println(res.get(i));
        }
        
        System.out.println("---------------------------");
        ArrayList<ArrayList<String>> toAdd = new ArrayList<ArrayList<String>>();
        ArrayList<String> singleList = new ArrayList<String>();
        singleList.add("Username:1:nazione");
        singleList.add("Italia");
        toAdd.add(singleList);

        ArrayList<String> anotherList = new ArrayList<String>();
        anotherList.add("Username:1:indirizzo");
        anotherList.add("procapite");

        toAdd.add(anotherList);
        
        
       ArrayList<String> toDelete = new ArrayList<String>();
       toDelete.add("Username:1:nascita");
       
       levelDB.putValuesToUser(toAdd);

        
        List<String> res1 = levelDB.getValuesFromUser("Username",1);
        for(int i = 0; i < res1.size(); i++){
            String[] attribute = res1.get(i).split(":");
            System.out.println(attribute[0] + ":" + attribute[1]);
          //  System.out.println(res.get(i));
        }

         
        levelDB.close();*/
    }

}
