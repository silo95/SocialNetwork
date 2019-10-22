package mycompany.task1;

import java.io.*;
import java.nio.file.*;
import java.security.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javax.xml.bind.DatatypeConverter;

public class Controller {
    private Person loggedUser;
    private String username, password;
    private final TableView<Post> postTable;
    private final TableView<Comment> commentTable;
    private DBManager db;
    private Label welcomeLabel, errorLabel, userLabel, passwordLabel;
    private TextField usernameField;
    private PasswordField passwordField ;
    private Button loginButton, logoutButton, addPost, addComment, deletePost, deleteComment;
    private ObservableList<Comment> commentOl;  
    private ObservableList<Post> postOl; 
    private TextArea insertPostAndComment;
    private TableColumn<Post, String> postCol;
    private TableColumn<Comment, String> commentCol;

    
    public Controller(ObservableList<Post> pol, ObservableList<Comment> col, String user, String psw,
            TextField userFld, PasswordField pswFld, Label elbl, Label wlbl,
            Button logBtn, Button logoutBtn, Button addP, Button addC, Button delP, Button delC,
            TableView<Post> postT, TableView<Comment> commT, Person loggU, TextArea ipac, TableColumn<Post, 
            String> pcl, TableColumn<Comment, String> ccol){
       postOl = pol;
       commentOl = col;
       username = user;
       password = psw;
       usernameField = userFld;
       passwordField = pswFld;
       errorLabel = elbl;
       welcomeLabel = wlbl;
       loginButton = logBtn; 
       logoutButton = logoutBtn;
       addPost = addP;
       addComment = addC;
       deletePost = delP;
       deleteComment = delC;
       postTable = postT;
       commentTable = commT;
       loggedUser = loggU;
       insertPostAndComment = ipac;
       postCol = pcl;
       commentCol = ccol;
       db = getCredential();//new DBManager("localhost", "root", "070689");
    }
    
    private DBManager getCredential(){
        String srvr, usr, psw;
        srvr = usr = psw = null;
         try (BufferedReader br = new BufferedReader(new InputStreamReader(
                           this.getClass().getResourceAsStream("/config.txt")))) {

            // read line by line
            String line = null;
            int i = 0;
            while (i<3 && (line = br.readLine()) != null) {
                switch (i) {
                    case 0:
                        srvr = line;
                        break;
                    case 1:
                        usr = line; 
                        break;
                    default:
                        psw = line;
                        break;
                }
                i++;
            }
           return new DBManager(srvr, usr, psw);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }  
         return null;
        
    }
    
    public void loginButtonSetOnAction(){
        loginButton.setOnAction((ActionEvent ev) -> {
            username = String.valueOf(usernameField.getText());
            password = hash(String.valueOf(passwordField.getText())); 
            
            if(!username.isEmpty() && !password.isEmpty()){
                errorLabel.setText("");
                loggedUser = new Person(username, password);
            
                if(db.isRegistered(username) && db.login(loggedUser)){
                    loginButton.setDisable(true);
                    logoutButton.setDisable(false);
                    usernameField.setEditable(false);
                    usernameField.setStyle("-fx-text-inner-color: #bfbfbf;");
                    passwordField.setEditable(false);
                    passwordField.setStyle("-fx-text-inner-color: #bfbfbf;");
                    errorLabel.setText("");
                    welcomeLabel.setText("Hello " + username + ", you've written " + 
                            db.getNumberOfPosts(username) +" posts and " +
                            db.getNumberOfComments(username)+ " comments");
                    postOl = db.getPosts();
                    postTable.setItems(postOl);
                    addPost.setDisable(false);
                    addComment.setDisable(false);
                    deleteComment.setDisable(false);
                    deletePost.setDisable(false);

                }
                else if(!db.isRegistered(username) && db.register(loggedUser)){
                    loginButton.setDisable(true);
                    logoutButton.setDisable(false);
                    usernameField.setEditable(false);
                    usernameField.setStyle("-fx-text-inner-color: #bfbfbf;");
                    passwordField.setEditable(false);
                    passwordField.setStyle("-fx-text-inner-color: #bfbfbf;");
                    errorLabel.setText("");
                    welcomeLabel.setText("Hello " + username + ", your registration has been successfully completed!");
                    postOl = db.getPosts();
                    postTable.setItems(postOl);
                    addPost.setDisable(false);
                    addComment.setDisable(false);
                    deleteComment.setDisable(false);
                    deletePost.setDisable(false);
                }
                else{
                    errorLabel.setText("Username or Password not correct. Please, try again. ");
                }
            }
            else{
                errorLabel.setText("Insert all the required fields.");
            }
                                 
        });
    }
    
    public void logoutButtonSetOnAction(){
         logoutButton.setOnAction((ActionEvent ev) -> {
            loginButton.setDisable(false);
            logoutButton.setDisable(true);
            usernameField.clear();
            passwordField.clear();
            usernameField.setEditable(true);
            passwordField.setEditable(true);
            welcomeLabel.setText("Insert username and password for login or registration");
            errorLabel.setText("");
            usernameField.setStyle("-fx-text-inner-color: black;");
            passwordField.setStyle("-fx-text-inner-color: black;");
            usernameField.requestFocus();
            addPost.setDisable(true);
            addComment.setDisable(true);
            deleteComment.setDisable(true);
            deletePost.setDisable(true);           
            postOl.clear();
            commentOl.clear();            
        });
       
    }
    
    public void addPostSetOnAction(){
        addPost.setOnAction((ActionEvent ev) -> {
            String content = insertPostAndComment.getText();
            if(content.length() > 50){
                errorLabel.setText("Text too long. Inserted "+content.length() +" characters. (The maximum is 50)");
            } else if(content.length() == 0){
                    errorLabel.setText("The content of post is empty");
            } else{
                errorLabel.setText("");
                db.addPost(content, username);
                postOl = db.getPosts();
                postTable.setItems(postOl);
                insertPostAndComment.clear();
                welcomeLabel.setText("Hello " + username + ", you've written " + 
                            db.getNumberOfPosts(username) +" posts and " +
                            db.getNumberOfComments(username)+ " comments");
            }
            
        });  
    }
    
    public void addCommentSetOnAction(){
        addComment.setOnAction((ActionEvent ev) -> {
                      
            if(postTable.getSelectionModel().getSelectedItem() != null){
                errorLabel.setText("");
                Post p = postTable.getFocusModel().getFocusedItem();
                int index = postTable.getFocusModel().getFocusedIndex();
                String content = insertPostAndComment.getText();

                if(content.length() > 50){
                    errorLabel.setText("Text too long. Inserted "+content.length() +" characters. (The maximum is 50)");
                } else if(content.length() == 0){
                    errorLabel.setText("The content of comment is empty");
                } else{
                    errorLabel.setText("");
                    db.addComment(content, username, p.getIdPost());
                    commentOl = db.getComments(p.getIdPost());
                    commentTable.setItems(commentOl);
                    postOl = db.getPosts();
                    postTable.setItems(postOl);
                    insertPostAndComment.clear();
                    
                    postTable.requestFocus();
                    postTable.getSelectionModel().select(index);
                    postTable.getFocusModel().focus(index);

                    welcomeLabel.setText("Hello " + username + ", you've written " + 
                                db.getNumberOfPosts(username) +" posts and " +
                                db.getNumberOfComments(username)+ " comments");
                }
            }
            else{
                errorLabel.setText("Select the post you want to comment");
            }
        });
    }
    
    public void deletePostSetOnAction(){
        deletePost.setOnAction((ActionEvent ev) -> {
            
            if(postTable.getSelectionModel().getSelectedItem() != null){
                errorLabel.setText("");
                Post p = postTable.getFocusModel().getFocusedItem();
                
                if(!username.equals(p.getUser())){
                    errorLabel.setText("You can delete only your post!");
                    postTable.refresh();             
                } else {
                    db.deletePost(p);
                    postTable.getItems().remove(p);    
                    welcomeLabel.setText("Hello " + username + ", you've written " + 
                                db.getNumberOfPosts(username) +" posts and " +
                                db.getNumberOfComments(username)+ " comments");
                }
            }
            else{
                errorLabel.setText("Select a post");
            }
            postTable.setEditable(false);
            
        });
    }
    
    public void deleteCommentSetOnAction(){
        deleteComment.setOnAction((ActionEvent ev) -> {
            
            if(commentTable.getSelectionModel().getSelectedItem() != null){
                errorLabel.setText("");
                Comment c = commentTable.getFocusModel().getFocusedItem();

                if(!username.equals(c.getUser())){
                    errorLabel.setText("You can delete only your comment!");
                    commentTable.refresh();  
                } else{  
                    db.deleteComment(c);
                    commentTable.getItems().remove(c);
                    commentTable.refresh();  
                    TableView.TableViewFocusModel<Post> fm =  postTable.getFocusModel();

                    Post p = postTable.getSelectionModel().getSelectedItem();
                    int index = postTable.getSelectionModel().getSelectedIndex();
                    
                    p.setComments(p.getComments() -1);
                    postOl.set(index, p);
                    postTable.setItems(postOl);
                    postTable.refresh();

                    postTable.requestFocus();
                    postTable.getSelectionModel().select(index);
                    postTable.getFocusModel().focus(index);

                    welcomeLabel.setText("Hello " + username + ", you've written " + 
                                db.getNumberOfPosts(username) +" posts and " +
                                db.getNumberOfComments(username)+ " comments");

                    }    
            }
            else{
                errorLabel.setText("Select a comment");
            }
            commentTable.setEditable(false);     
        });
    }
    
    public void commentTableSetRowFactory(){
        commentTable.setRowFactory( tv ->{
            TableRow<Comment> row = new TableRow<>();
            row.setOnMouseClicked((Event e) ->{
                Comment c = row.getItem();
                errorLabel.setText("");
                if(!username.equals(c.getUser())){         
                    commentTable.setEditable(false);
                } else {
                    commentTable.setEditable(true);
                }         
            });
                      
            return row;
        });              
    }
    
    public void postTableSetRowFactory(){
        postTable.setRowFactory( tv ->{
            TableRow<Post> row = new TableRow<>();
            row.setOnMouseClicked((Event e) ->{
                Post p = row.getItem();
                commentOl = db.getComments(p.getIdPost());
                commentTable.setItems(commentOl);
                errorLabel.setText("");
                if(!username.equals(p.getUser())){
                    postTable.setEditable(false);
                } else {
                    postTable.setEditable(true);
                }         
            });
                      
            return row;
        });
    }
    
    public void postColSetOnEditCommit(){
        postCol.setOnEditCommit((TableColumn.CellEditEvent<Post, String> postStringCellEditEvent) -> {
            Post post = postTable.getSelectionModel().getSelectedItem();
           
            if(!username.equals(post.getUser())){
                errorLabel.setText("Warning: only the author can modify the post");
                postTable.refresh();   
            }
            else{
                if(postStringCellEditEvent.getNewValue().length() > 50){
                    errorLabel.setText("Post too long. Inserted " + postStringCellEditEvent.getNewValue().length()+" characters. (The maximum is 50)");
                    postTable.refresh(); 
                }
                else{
                    errorLabel.setText("");
                    db.updatePost(post.getIdPost(), postStringCellEditEvent.getNewValue());
                    post.setStrPost(postStringCellEditEvent.getNewValue());
                }
            }          
        });
    }
    
    public void commentColSetOnEditCommit(){
        commentCol.setOnEditCommit((TableColumn.CellEditEvent<Comment, String> commentStringCellEditEvent) -> {
            Comment c = commentTable.getSelectionModel().getSelectedItem();
            if(commentStringCellEditEvent.getNewValue().length() > 50){
                errorLabel.setText("Comment too long. Inserted " + commentStringCellEditEvent.getNewValue().length() +" characters. (The maximum is 50)");
                commentTable.refresh(); 
            }
            else{
                errorLabel.setText("");
                db.updateComment(c.getIdComment(), commentStringCellEditEvent.getNewValue());
            }
            
        });
    }
    
    private String hash(String psw){
        byte[] hash;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(psw.getBytes("UTF-8")); 
            return DatatypeConverter.printHexBinary(hash);
        }catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
 }
