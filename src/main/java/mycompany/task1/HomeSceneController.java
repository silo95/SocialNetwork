package mycompany.task1;

import java.io.IOException;

import java.net.*;
import java.util.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;

public class HomeSceneController implements Initializable {
    private Long loggedUserId;
    public static DBManager db;
    private String username, password;
    private Long currentPost;
    
    private ObservableList<PostBeans> postOl;
    private ObservableList<CommentBeans> commentOl = FXCollections.observableArrayList();   
    
    @FXML private TextField searchPost, searchComment;
    @FXML private TextField searchUser;
    @FXML private Label welcomeLabel;
    @FXML private Label errorLabel;
    @FXML private Button profileButton, logoutButton, addPost, addComment, deletePost, deleteComment,
                   searchWordPost, searchUserPost, searchWordComment, searchUserComment;
    @FXML private TableView<PostBeans> postTable;
    @FXML private TableView<CommentBeans> commentTable;
    @FXML private TextArea insertPostAndComment;

    @FXML TableColumn<PostBeans, String> userCol = new TableColumn<>("User");
    @FXML TableColumn<PostBeans, Integer> commentsCol = new TableColumn<>("Comments");
    @FXML TableColumn<PostBeans, Integer> dateCol = new TableColumn<>("Date");
    @FXML TableColumn<PostBeans, String> postCol = new TableColumn<>("Post");
    
    @FXML TableColumn<CommentBeans, String> userCommentCol = new TableColumn<>("User");
    @FXML TableColumn<CommentBeans, String> commentCol = new TableColumn<>("Comment");
    @FXML TableColumn<CommentBeans, String> commentDateCol = new TableColumn<>("Date");
    
    public HomeSceneController(){
        db = MainApp.db;       
        username = MainApp.username;
        loggedUserId = MainApp.loggedUserId;   
    }
    
    @FXML
    public void logoutButtonSetOnAction(ActionEvent event){
        //usernameField.clear();
        //passwordField.clear();
        welcomeLabel.setText("");
        errorLabel.setText("");
        //usernameField.setStyle("-fx-text-inner-color: black;");
        //passwordField.setStyle("-fx-text-inner-color: black;");
        postOl.clear();
        commentOl.clear(); 
        MainApp.getStage().setScene(MainApp.firstScene);
    }
    
    @FXML
    public void profileButtonOnAction(ActionEvent event){
        MainApp.getStage().setScene(MainApp.profileScene);
    }
    
    @FXML
    public void searchButtonOnAction(ActionEvent event){
        String user = searchUser.getText();
        
        if(!user.isEmpty()){
            Long idUser = db.getIdByUser(user);
            
            if(idUser > 0){
                
                MainApp.otherProfileController.getInfoUser(user, idUser);
                MainApp.getStage().setScene(MainApp.otherProfileScene);
            }
            else{
                //utente non presente
            }
            

        }
        else{
            //devi inserire lo username dell'utente da cercare
        }
    }
    
    @FXML
    public void addPostSetOnAction(ActionEvent event){
        String content = insertPostAndComment.getText();
        if(content.length() > 50){
            errorLabel.setText("Text too long. Inserted "+content.length() +" characters. (The maximum is 50)");
        } else if(content.length() == 0){
                errorLabel.setText("The content of post is empty");
        } else{
            errorLabel.setText("");
            db.addPost(content, MainApp.loggedUserId);
            postOl = db.getPosts();
            postTable.setItems(postOl);
            insertPostAndComment.clear();
            welcomeLabel.setText("Hello " + MainApp.username + ", you've written " + 
                        db.getNumberOfPosts(MainApp.loggedUserId) +" posts and " +
                        db.getNumberOfComments(MainApp.loggedUserId)+ " comments");
        }
    }
    
    @FXML
    public void searchPostByUser(ActionEvent event){
        String user = searchPost.getText();
        if(!user.isEmpty() ){
            if(user.equals("All")){
                postOl = db.getPosts();
                postTable.setItems(postOl);
            }
            else{
                postOl = db.searchPostsByUser(user);
                postTable.setItems(postOl);
            } 
            searchPost.clear();
        }   
    }
    
    @FXML
    public void searchCommentByUser(ActionEvent event){
        String user = searchComment.getText();
        if(!user.isEmpty()){
            commentOl = db.searchCommentsByUser(user, currentPost);
            commentTable.setItems(commentOl);
            searchComment.clear();
        }   
    }
    
    @FXML
    public void addCommentSetOnAction(ActionEvent event){
        if(postTable.getSelectionModel().getSelectedItem() != null){
            errorLabel.setText("");
            PostBeans p = postTable.getFocusModel().getFocusedItem();
            int index = postTable.getFocusModel().getFocusedIndex();
            String content = insertPostAndComment.getText();

            if(content.length() > 50){
                errorLabel.setText("Text too long. Inserted "+content.length() +" characters. (The maximum is 50)");
            } else if(content.length() == 0){
                errorLabel.setText("The content of comment is empty");
            } else{
                errorLabel.setText("");
                db.addComment(content, MainApp.loggedUserId, p.getIdPost());
                commentOl = db.getComments(p.getIdPost());
                commentTable.setItems(commentOl);
                postOl = db.getPosts();
                postTable.setItems(postOl);
                insertPostAndComment.clear();

                postTable.requestFocus();
                postTable.getSelectionModel().select(index);
                postTable.getFocusModel().focus(index);

                welcomeLabel.setText("Hello " + MainApp.username + ", you've written " + 
                            db.getNumberOfPosts(MainApp.loggedUserId) +" posts and " +
                            db.getNumberOfComments(MainApp.loggedUserId)+ " comments");
            }
        }
        else{
            errorLabel.setText("Select the post you want to comment");
        }
    }
            
    @FXML
    public void deletePostSetOnAction(ActionEvent event){
        if(postTable.getSelectionModel().getSelectedItem() != null){
            errorLabel.setText("");
            PostBeans p = postTable.getFocusModel().getFocusedItem();

            if(!MainApp.username.equals(p.getUser())){
                errorLabel.setText("You can delete only your post!");
                postTable.refresh();             
            } else {
                db.deletePost(p.getIdPost());
                postTable.getItems().remove(p);    
                welcomeLabel.setText("Hello " + MainApp.username + ", you've written " + 
                            db.getNumberOfPosts(MainApp.loggedUserId) +" posts and " +
                            db.getNumberOfComments(MainApp.loggedUserId)+ " comments");
            }
        }
        else{
            errorLabel.setText("Select a post");
        }
        postTable.setEditable(false);   
    }
    
    @FXML
    public void deleteCommentSetOnAction(ActionEvent event){
        if(commentTable.getSelectionModel().getSelectedItem() != null){
            errorLabel.setText("");
            CommentBeans c = commentTable.getFocusModel().getFocusedItem();

            if(!MainApp.username.equals(c.getUser())){
                errorLabel.setText("You can delete only your comment!");
                commentTable.refresh();  
            } else{  
                db.deleteComment(c.getIdComment());
                commentTable.getItems().remove(c);
                commentTable.refresh();  
                //TableView.TableViewFocusModel<PostBeans> fm =  postTable.getFocusModel(); 

                PostBeans p = postTable.getSelectionModel().getSelectedItem();
                int index = postTable.getSelectionModel().getSelectedIndex();

                p.setComments(p.getComments() -1);
                postOl.set(index, p);
                postTable.setItems(postOl);
                postTable.refresh();

                postTable.requestFocus();
                postTable.getSelectionModel().select(index);
                postTable.getFocusModel().focus(index);

                welcomeLabel.setText("Hello " + MainApp.username + ", you've written " + 
                            db.getNumberOfPosts(MainApp.loggedUserId) +" posts and " +
                            db.getNumberOfComments(MainApp.loggedUserId)+ " comments");

                }    
        }
        else{
            errorLabel.setText("Select a comment");
        }
        commentTable.setEditable(false);        
    }
    
    @FXML
    public void postColSetOnEditCommit(TableColumn.CellEditEvent<Post, String> postStringCellEditEvent){
        PostBeans post = postTable.getSelectionModel().getSelectedItem();
           
        if(!MainApp.username.equals(post.getUser())){
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
    }
    
    @FXML
    public void commentColSetOnEditCommit(TableColumn.CellEditEvent<Comment, String> commentStringCellEditEvent){
        CommentBeans c = commentTable.getSelectionModel().getSelectedItem();
        if(commentStringCellEditEvent.getNewValue().length() > 50){
            errorLabel.setText("Comment too long. Inserted " + commentStringCellEditEvent.getNewValue().length() +" characters. (The maximum is 50)");
            commentTable.refresh(); 
        }
        else{
            errorLabel.setText("");
            db.updateComment(c.getIdComment(), commentStringCellEditEvent.getNewValue());
        }
    }
    
    @FXML
    public void commentTableSetRowFactory(){
        
        commentTable.setRowFactory( tv ->{
            TableRow<CommentBeans> row = new TableRow<>();
            row.setOnMouseClicked((Event e) ->{
                CommentBeans c = row.getItem();
                errorLabel.setText("");
                if(!MainApp.username.equals(c.getUser())){         
                    commentTable.setEditable(false);
                } else {
                    commentTable.setEditable(true);
                }         
            });
                      
            return row;
        });              
    }
    
    @FXML 
    public void postTableSetRowFactory(){ 
        postTable.setRowFactory( tv ->{
            TableRow<PostBeans> row = new TableRow<>();
                        
            row.setOnMouseClicked((Event e) ->{
                PostBeans p = row.getItem();
                currentPost = p.getIdPost();
                commentOl = db.getComments(currentPost);
                commentTable.setItems(commentOl);
                errorLabel.setText("");
                if(!MainApp.username.equals(p.getUser())){
                    postTable.setEditable(false);
                } else {
                    postTable.setEditable(true);
                }         
            });
                      
            return row;
        });
       
    }   
    
    
    @FXML
    public void clickUser(){
        userCol.setCellFactory(tc -> {
            TableCell<PostBeans, String> cell = new TableCell<PostBeans, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty) ;
                    setText(empty ? null : item);
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    String user = cell.getItem();
                    if(!user.equals(username)){     // in questo modo visualizziamo solamente il profilo dei nostri amici
                        MainApp.getStage().setScene(MainApp.profileScene);
                    }
                }
            });
            return cell ;
        });
    }
    /*
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
    
    /*
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
           return new DBManager();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }  
         return null;
        
    }
    */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        welcomeLabel.setText("Hello " + username + ", you've written " + 
            db.getNumberOfPosts(loggedUserId) +" posts and " +
            db.getNumberOfComments(loggedUserId)+ " comments");
        
        insertPostAndComment.setWrapText(true);
          
        userCol.setCellValueFactory(new PropertyValueFactory<>("user"));
        postCol.setCellValueFactory(new PropertyValueFactory<>("strPost"));
        postCol.setCellFactory(TextFieldTableCell.forTableColumn());
        commentsCol.setCellValueFactory(new PropertyValueFactory<>("comments"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        userCommentCol.setCellValueFactory(new PropertyValueFactory<>("user"));       
        commentCol.setCellValueFactory(new PropertyValueFactory<>("strComment"));      
        commentDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        commentCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        postOl = db.getPosts();
        postTable.setItems(postOl);
        
        commentTableSetRowFactory();
        postTableSetRowFactory();
        clickUser();
    }    
}
