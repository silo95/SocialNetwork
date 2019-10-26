package mycompany.task1;

import java.io.IOException;
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
        /*LevelDBManager levelDB = new LevelDBManager();
        levelDB.putKeyValue("Username",1,"nascita","20-11-1995");
        levelDB.putKeyValue("Username",1,"citta","Nicotera");
        levelDB.putKeyValue("Username",2,"nascita","20-11-2000");
        levelDB.putKeyValue("Username",1,"sesso","maschio");
        levelDB.putKeyValue("Username",3,"nascita","20-11-3000");
        try {
            List<String> res = levelDB.getValue("Username",1);
            for(int i = 0; i < res.size(); i++){
                String[] attribute =res.get(i).split(":");
                System.out.println(attribute[0] + ":" + attribute[1]);
              //  System.out.println(res.get(i));
            }

        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        levelDB.close();
       // System.out.println(levelDB.getValue("Capitale"));*/
    }

}
