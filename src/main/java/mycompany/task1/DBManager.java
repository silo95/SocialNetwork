package mycompany.task1;

import java.math.BigInteger;
import java.sql.*;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import javafx.collections.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DBManager{
    
    private final EntityManagerFactory factory;
    private EntityManager entityManager;

    
    public DBManager(){
        factory = Persistence.createEntityManagerFactory("SocialNetwork");      
    }
    
    public void exit(){
        factory.close();
    }
    
    public ObservableList<Comment> getComments(Long post){
        
        ObservableList<Comment> ol = FXCollections.observableArrayList();
        try{
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Query q = entityManager.createNativeQuery("Select * From Comment where post = ? order by commentDate desc",Comment.class);
            q.setParameter(1,post);
            List<Comment> commentList = q.getResultList();
            ol.addAll(commentList);
            
              
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            entityManager.close();
        }
        return ol;
        
         
    }
    
    public int getNumberOfComments(Long id){
        int counter = 0;
        try{
        
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Query q = entityManager.createNativeQuery("Select count(*) From Comment where person = ?");
            q.setParameter(1,id);
            counter = ((Number)q.getSingleResult()).intValue();
                            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            entityManager.close();
        } 
        
        return counter;
    }
    
    public ObservableList<Post> getPosts(){ 
        
        ObservableList<Post> ol = FXCollections.observableArrayList();
        try{
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Query q = entityManager.createNativeQuery("Select * From Post order by postDate desc",Post.class);
            List<Post> postList = q.getResultList();
            ol.addAll(postList);
            
              
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            entityManager.close();
        }
        return ol;
        
    }
    
    
    
    
    public int getNumberOfPosts(Long id){
        int counter = 0;
        try{
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Query q = entityManager.createNativeQuery("Select count(*) From Post where person = ?");
            q.setParameter(1,id);
            counter = ((Number)q.getSingleResult()).intValue();
              
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            entityManager.close();
        }
        
        return counter;
    }
    

   /* public boolean login(Person u){ 
        try{
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
    }*/
    
    public boolean updatePost(Long idPost,String newPost){
        try{                

            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Post p = entityManager.getReference(Post.class,idPost);
            p.setStrPost(newPost);
            entityManager.persist(p);
            entityManager.getTransaction().commit();
            return true;  
          
        }catch(Exception e){
            e.printStackTrace();
            return false;
           
        }finally{
            entityManager.close();
        } 
    }
    
    public boolean updateComment(Long idComment,String newComment){
        try{                

            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Comment c = entityManager.getReference(Comment.class,idComment);
            c.setStrComment(newComment);
            entityManager.persist(c);
            entityManager.getTransaction().commit();
            return true;  
          
        }catch(Exception e){
            e.printStackTrace();
            return false;
           
        }finally{
            entityManager.close();
        } 
    }
    
    public boolean addComment(String content, Long user,Long post){ 
        try{   
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Person per = entityManager.getReference(Person.class,user);
            Post pos = entityManager.getReference(Post.class,post);
            Comment c;
            c = new Comment(content,per,pos,Timestamp.valueOf(LocalDateTime.now()));
            entityManager.persist(c);
            entityManager.getTransaction().commit();
            return true;  
            
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            entityManager.close();
        }   
    }
    
    public boolean addPost(String content, Long user){ 
        try{   
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Person per = entityManager.getReference(Person.class,user);         
            Post p = new Post(content,per,Timestamp.valueOf(LocalDateTime.now()));
            entityManager.persist(p);
            entityManager.getTransaction().commit();
            return true;  
            
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        finally{
            entityManager.close();
        }
    }
    

    public boolean deleteComment(Long id){ 
     /*   if(c== null){
            System.out.println("Err: no comment selected");
            return false;
        }  */      
        try{                
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Comment c = entityManager.getReference(Comment.class,id);         
            entityManager.remove(c);
            entityManager.getTransaction().commit();
            return true;  
            
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            entityManager.close();
        } 
    }
    
    public boolean deletePost(Long id){ 
        try{                
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Post p = entityManager.getReference(Post.class,id);         
            entityManager.remove(p);
            entityManager.getTransaction().commit();
            return true;  
            
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            entityManager.close();
        }      
    }
}  