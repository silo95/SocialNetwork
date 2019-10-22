package mycompany.task1;

import java.sql.*;
import javafx.collections.*;

public class DBManager{
    
    private final String connectionString;
    
    public DBManager(String s, String u, String p){
        connectionString = "jdbc:mysql://" + s + ":"
                + "3306/SocialNetwork?useUnicode=true&allowPublicKeyRetrieval=true&useSSL=false&useJDBCCompliantTimezoneShift=true"
                + "&useLegacyDatetimeCode=false&serverTimezone=UTC&user=" 
                + u + "&password=" + p + "";
    }
    
    public ObservableList<Comment> getComments(int post){
        ObservableList<Comment> ol = FXCollections.observableArrayList();
        try(Connection co = DriverManager.getConnection(connectionString);
            PreparedStatement ps = co.prepareStatement("Select * From Comment where post = ? order by Date desc");
        ){
            Comment c;
            ps.setInt(1,post);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                c = new Comment(rs.getInt("IdComment"), rs.getString("strComment"),
                rs.getString("User"), rs.getInt("Post"),(Timestamp)rs.getObject("Date")); 
                ol.add(c);
            }
              
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return ol;
         
    }
    
    public int getNumberOfComments(String username){
        int counter = 0;
        try(Connection co = DriverManager.getConnection(connectionString);
            PreparedStatement ps = co.prepareStatement("Select count(*) From Comment where user = ? ");
        ){
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                counter = rs.getInt(1);
            }
              
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return counter;
    }
    
    public ObservableList<Post> getPosts(){ 
        
        ObservableList<Post> ol = FXCollections.observableArrayList();
        try(Connection co = DriverManager.getConnection(connectionString);
            PreparedStatement ps = co.prepareStatement("SELECT distinct IdPost, strPost, P.User, P.Date,count(IdComment) as commenti\n" +
                " FROM Post P left outer join comment C on C.Post = P.IdPost\n" +
                " group by IdPost order by Date desc;");
        ){
            Post p;
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                p = new Post(rs.getInt("IdPost"), rs.getString("strPost"),
                    rs.getString("User"), (Timestamp)rs.getObject("Date"), rs.getInt("commenti"));  // modificare la query aggiungendo il numero di commenti al post
                ol.add(p);
            }
              
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return ol;
    }
    
    
    public int getNumberOfPosts(String username){
        int counter = 0;
        try(Connection co = DriverManager.getConnection(connectionString);
            PreparedStatement ps = co.prepareStatement("Select count(*) From Post where user = ? ");
        ){
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                counter = rs.getInt(1);
            }
              
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return counter;
    }
    
    
    public boolean login(Person u){ 
        try(Connection co = DriverManager.getConnection(connectionString);
            PreparedStatement ps = co.prepareStatement("SELECT * FROM User "
                    + "WHERE Username = ? AND Password = ?");
        ){
            ps.setString(1,u.getUsername()); 
            ps.setString(2,u.getPassword()); 
            ResultSet rs = ps.executeQuery();
            return rs.next(); 
            
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
   
    public boolean register(Person u){ 
        try(Connection co = DriverManager.getConnection(connectionString);
            PreparedStatement ps = co.prepareStatement("INSERT INTO User VALUES (?,?)");
        ){
            ps.setString(1,u.getUsername());
            ps.setString(2,u.getPassword());
            ps.execute();
            return true;  
            
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean isRegistered(String u){ 
        try(Connection co = DriverManager.getConnection(connectionString);
            PreparedStatement ps = co.prepareStatement("SELECT * FROM User "
                    + "WHERE Username = ?");
        ){
            ps.setString(1,u); 
            ResultSet rs = ps.executeQuery();
            return rs.next();
         
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }          
    }
    
    public boolean updatePost(int IdPost,String newPost){
     try(Connection co = DriverManager.getConnection(connectionString);
            PreparedStatement ps = co.prepareStatement("UPDATE Post SET strPost = ? where IdPost = ?");           
        ){                
            ps.setString(1,newPost);
            ps.setInt(2, IdPost);
            ps.executeUpdate();
            return true;  
            
        }catch(SQLException e){
            e.printStackTrace();
            return false;
           
        }
    }
    
    public boolean updateComment(int IdComment,String newComment){
        try(Connection co = DriverManager.getConnection(connectionString);
               PreparedStatement ps = co.prepareStatement("UPDATE Comment SET strComment = ? where IdComment = ?");           
            ){                
               ps.setString(1,newComment);
               ps.setInt(2, IdComment);
               ps.executeUpdate();
               return true;  
               
           }catch(SQLException e){
               e.printStackTrace();
               return false;

           }
    }
    
    public boolean addComment(String content, String user,int post){ 
        try(Connection co = DriverManager.getConnection(connectionString);
            PreparedStatement ps = co.prepareStatement("INSERT INTO comment(strComment,User,Post,Date)\n" +
            "VALUES (?,?,?,CURRENT_TIMESTAMP);");           
        ){                
            ps.setString(1, content);
            ps.setString(2, user);
            ps.setInt(3, post);

            ps.executeUpdate();
            return true;  
            
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }    
    }
    
    public boolean addPost(String content, String user){ 
        try(Connection co = DriverManager.getConnection(connectionString);
            PreparedStatement ps = co.prepareStatement("INSERT INTO post(strPost,User,Date)\n" +
            "VALUES (?,?,CURRENT_TIMESTAMP);");           
        ){                
            ps.setString(1, content);
            ps.setString(2, user);

            ps.executeUpdate();
            return true;  
            
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }    
    }
    

    public boolean deleteComment(Comment c){ 
        if(c== null){
            System.out.println("Err: no comment selected");
            return false;
        }        
        try(Connection co = DriverManager.getConnection(connectionString);
            PreparedStatement ps = co.prepareStatement("DELETE FROM comment WHERE idComment = ?;");           
        ){                
            ps.setInt(1, c.getIdComment());
            ps.executeUpdate();
            return true;  
            
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }    
    }
    
    public boolean deletePost(Post p){ 
        if(p == null){
            System.out.println("Err: no comment selected");
            return false;
        }        
        try(Connection co = DriverManager.getConnection(connectionString);
            PreparedStatement ps = co.prepareStatement("DELETE FROM post WHERE idPost = ?;");           
        ){                
            ps.setInt(1, p.getIdPost());
            ps.executeUpdate();
            return true;  
            
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }    
    }
      
}