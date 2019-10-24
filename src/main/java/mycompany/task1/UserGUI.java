
package mycompany.task1;

import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import javax.persistence.*;

public class UserGUI extends Application {
    private Person loggedUser;
    private String username = new String();
    private String password = new String();
    private final TableView<Post> postTable = new TableView<>();
    private ObservableList<Post> postOl;  
    private final TableView<Comment> commentTable = new TableView<>();
    private ObservableList<Comment> commentOl;  
    private Label welcomeLabel, errorLabel, userLabel, passwordLabel;
    private TextField usernameField, searchPost, searchComment;
    private PasswordField passwordField ;
    private Button loginButton, logoutButton, addPost, addComment, deletePost, deleteComment;
    private Button searchWordPost, searchUserPost, searchWordComment, searchUserComment;
    private VBox vbInsert, vbUserInfo, vbCommentTable, vbPostTable;
    private Controller contr;
    private TextArea insertPostAndComment;
    private TableColumn<Comment, String> commentCol;
    private TableColumn<Post, String> postCol;
    private StackPane stack;
    
    
    
    @Override
    public void start(Stage primaryStage) {   
        setMessage();
        setLogin();
        setInsert();
        setPostTable();
        setCommentTable();
        setController();
        Scene scene = new Scene(setInterface(), 870, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.setTitle("Social Network");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);    
        
        EntityManagerFactory factory;
        EntityManager entityManager = null;
        
        factory = Persistence.createEntityManagerFactory("SocialNetwork");
        
        Person person = new Person();
        person.setUsername("Lavagna");
        person.setPassword("ciclamino");
        System.out.println(person.toString());
        try{
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(person);
            entityManager.getTransaction().commit();
            System.out.println("done");
        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        
        factory.close();
        
        
    }
    
   private void inserisco(Person p){
       
   }
    
    
    
    
    private void setMessage(){
        Rectangle rect = new Rectangle(0.0, 0.0, 820, 40);
        rect.setFill(Color.web("#b0d2ff"));
        rect.setArcHeight(4);
        rect.setArcWidth(4);
        
        VBox vbTop = new VBox();
        stack = new StackPane();
        welcomeLabel = new Label("Insert username and password for login or registration");
        welcomeLabel.setStyle("-fx-font-weight: bold;");
        errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");
        
        vbTop.getChildren().addAll(welcomeLabel, errorLabel);
        vbTop.setAlignment(Pos.CENTER);
        vbTop.setSpacing(15);
        vbTop.setStyle("-fx-padding: 35.0 0.0 0.0 0.0;"); 
        stack.getChildren().addAll(rect, vbTop);
    }

    private void setLogin() {
        vbUserInfo = new VBox();
        HBox hbBox = new HBox();
        userLabel = new Label("Username");
        passwordLabel = new Label("Password");
        usernameField = new TextField();
        usernameField.setPrefWidth(210);
        passwordField = new PasswordField();
        passwordField.setPrefWidth(210);
        loginButton = new Button("Login");
        loginButton.setId("loginButton");
        
        logoutButton = new Button("Logout");
        logoutButton.setId("logoutButton");
        logoutButton.setDisable(true);
        
        hbBox.getChildren().addAll(loginButton, logoutButton);
        hbBox.setSpacing(10);
        
        vbUserInfo.getChildren().addAll(userLabel, usernameField, passwordLabel, passwordField, hbBox);
        vbUserInfo.setStyle("-fx-padding: 30.0 50.0 40.0 40.0;");
        vbUserInfo.setSpacing(5);
        vbUserInfo.setAlignment(Pos.TOP_CENTER);   
    }

    private void setInsert() {
        Rectangle rect = new Rectangle(0.0, 0.0, 210, 30);
        rect.setFill(Color.web("#FFD8B0"));
        rect.setArcHeight(4);
        rect.setArcWidth(4);
        StackPane stackInsert = new StackPane();
        
        vbInsert = new VBox();
        HBox hbInsert = new HBox();
        
        Label text = new Label("Insert post or comment");
        vbInsert.setAlignment(Pos.TOP_CENTER); 
        
        stackInsert.getChildren().addAll(rect, text);
        insertPostAndComment = new TextArea();
        insertPostAndComment.lengthProperty();
        insertPostAndComment.setWrapText(true);
       
        insertPostAndComment.setPrefWidth(210);
        insertPostAndComment.setPrefHeight(80);
        addPost = new Button("Add post");
        addPost.setDisable(true);
        addComment = new Button("Add comment");
        addComment.setDisable(true);
        addPost.setId("addPost");
        addComment.setId("addComment");
        
        hbInsert.getChildren().addAll(addPost, addComment);
        hbInsert.setSpacing(10);
        
        vbInsert.getChildren().addAll(stackInsert, insertPostAndComment, hbInsert);
        vbInsert.setStyle("-fx-padding: 40.0 50.0 0.0 40.0;");
        vbInsert.setSpacing(5);
    }    
    
    private void setPostTable() {
        postTable.setId("postTable");
        TableColumn<Post, String> userCol = new TableColumn<>("User");
        userCol.setCellValueFactory(new PropertyValueFactory<>("user"));
        postCol = new TableColumn<>("Post");
        postCol.setCellValueFactory(new PropertyValueFactory<>("strPost"));
        postCol.setCellFactory(TextFieldTableCell.forTableColumn()); //per modificare la cella
           
        TableColumn<Post, Integer> commentsCol = new TableColumn<>("Comments");
        commentsCol.setCellValueFactory(new PropertyValueFactory<>("comments"));
        TableColumn<Post, Integer> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
            
        postTable.getColumns().addAll(userCol, postCol, commentsCol, dateCol);
 
        deletePost = new Button("Delete");
        deletePost.setDisable(true);
        deletePost.setId("deletePost");
        
        searchPost = new TextField();
        searchPost.setPrefWidth(210);
        
        searchUserPost = new Button("Search by User");
        searchUserPost.setDisable(true);
        searchUserPost.setId("searchUserPost");
        
        searchWordPost = new Button("Search by Word");
        searchWordPost.setDisable(true);
        searchWordPost.setId("searchWordPost");
        
        vbPostTable = new VBox();
        HBox buttons = new HBox();
        buttons.getChildren().addAll(searchPost, searchWordPost, searchUserPost, deletePost);
        buttons.setSpacing(2);
        //buttons.setAlignment(Pos.CENTER_RIGHT);
               
        userCol.setPrefWidth(70);
        postCol.setPrefWidth(250);
        commentsCol.setPrefWidth(80);
        dateCol.setPrefWidth(150);
        
        vbPostTable.getChildren().addAll(postTable, buttons);
        vbPostTable.setSpacing(5); 
    }

    private void setCommentTable() {
        commentTable.setId("commentTable");
        commentOl = FXCollections.observableArrayList();
        TableColumn<Comment, String> userCommentCol = new TableColumn<>("User");
        userCommentCol.setCellValueFactory(new PropertyValueFactory<>("user"));
        commentCol = new TableColumn<>("Comment");
        commentCol.setCellValueFactory(new PropertyValueFactory<>("strComment"));
        TableColumn<Comment, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        commentCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        commentTable.getColumns().addAll(userCommentCol, commentCol, dateCol);
              
        deleteComment = new Button("Delete");
        deleteComment.setDisable(true);
        deleteComment.setId("deleteComment");
        
        searchComment = new TextField();
        searchComment.setPrefWidth(210);
        
        searchUserComment = new Button("Search by User");
        searchUserComment.setDisable(true);
        searchUserComment.setId("searchUserComment");
        
        searchWordComment = new Button("Search by Word");
        searchWordComment.setDisable(true);
        searchWordComment.setId("searchWordPost");
        
        vbCommentTable = new VBox();
        HBox buttons = new HBox();
        buttons.getChildren().addAll(searchComment, searchWordComment, searchUserComment, deleteComment);
        buttons.setSpacing(2);
        //buttons.setAlignment(Pos.CENTER_RIGHT);
          
        userCommentCol.setPrefWidth(70);
        commentCol.setPrefWidth(330);
        dateCol.setPrefWidth(150);
        
        vbCommentTable.getChildren().addAll(commentTable, buttons);
        vbCommentTable.setSpacing(5);
    }


    private VBox setInterface() {               
        VBox vb = new VBox();
        HBox hbMiddle = new HBox();
        HBox hbBottom = new HBox();

        hbMiddle.getChildren().addAll(vbUserInfo, vbPostTable);
        hbMiddle.setStyle("-fx-padding: 20.0 0.0 30.0 0.0;");
        
        hbBottom.getChildren().addAll(vbInsert, vbCommentTable);  

        vb.getChildren().addAll(stack, hbMiddle, hbBottom);
        return vb;
    }  
    
    private void setController(){
        contr = new Controller(postOl, commentOl, username, password,
            usernameField, passwordField, errorLabel, welcomeLabel,
            loginButton, logoutButton, addPost, addComment, deletePost, deleteComment,             
            postTable, commentTable, loggedUser, insertPostAndComment, postCol, commentCol);
        /*
        
        contr.loginButtonSetOnAction();
        contr.logoutButtonSetOnAction();
        contr.addPostSetOnAction();
        contr.addCommentSetOnAction();
        contr.deletePostSetOnAction();
        contr.deleteCommentSetOnAction();
        contr.commentTableSetRowFactory();
        contr.postTableSetRowFactory();
        contr.postColSetOnEditCommit();
        contr.commentColSetOnEditCommit();
*/
    }
            
}

