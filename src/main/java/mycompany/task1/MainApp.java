package mycompany.task1;

import javafx.application.*;
import static javafx.application.Application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;


public class MainApp extends Application {
    private static Stage stage;
    public static Scene firstScene;
    public static Scene homeScene;
    public static Scene registrationScene;
    public static Scene profileScene;
    public static Scene otherProfileScene;
    public static String username;
    public static long loggedUserId;
    public static DBManager db;
    public static LevelDBManager ldb;
    
    public static OtherProfileSceneController otherProfileController;
    public static MyProfileSceneController myProfileController;
    public static HomeSceneController homeController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        db = new DBManager();
        ldb = new LevelDBManager();
        stage = primaryStage;    
        firstScene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/FirstScene.fxml")));     
        registrationScene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/RegistrationScene.fxml")));      
        
        FXMLLoader otherSceneLoader = new FXMLLoader(getClass().getResource("/fxml/OtherProfileScene.fxml"));
        otherProfileScene = new Scene(otherSceneLoader.load());
        otherProfileController = (OtherProfileSceneController)otherSceneLoader.getController();
        
        FXMLLoader profileSceneLoader = new FXMLLoader(getClass().getResource("/fxml/MyProfileScene.fxml"));
        profileScene = new Scene(profileSceneLoader.load());
        myProfileController = (MyProfileSceneController)profileSceneLoader.getController();
        
        FXMLLoader homeSceneLoader = new FXMLLoader(getClass().getResource("/fxml/HomeScene.fxml"));
        homeScene = new Scene(homeSceneLoader.load());
        homeController = (HomeSceneController)homeSceneLoader.getController();
        

        stage.setTitle("Social Network");
        stage.setScene(firstScene);
        stage.show();     
        
    }


    public static void main(String[] args) {
        launch(args);
        
        
    }
    
    public static Stage getStage() {
        return stage;
    }
    
    
       //Commenti da inserire nel main
    
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
