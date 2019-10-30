package mycompany.task1;

import java.sql.*;
import java.time.*;
import java.util.*;
import javafx.collections.*;
import javax.persistence.*;

public class DBManager{
    
    private final EntityManagerFactory factory;
    private EntityManager entityManager;

    
    public DBManager(){
        factory = Persistence.createEntityManagerFactory("SocialNetwork");      
    }
    
    public void exit(){
        factory.close();
    }
    
    public ObservableList<CommentBeans> getComments(Long post){
        
        ObservableList<CommentBeans> ol = FXCollections.observableArrayList();
        try{
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Query q = entityManager.createNativeQuery("Select * From Comment where post = ? order by commentDate desc",Comment.class);
            q.setParameter(1,post);
            List<Comment> commentList = q.getResultList();
            CommentBeans comm;
            for(int i=0; i<commentList.size(); i++){
                comm = new CommentBeans(commentList.get(i).getIdComment(), commentList.get(i).getStrComment(), 
                        commentList.get(i).getPerson().getUsername(), commentList.get(i).getPost().getIdPost(), 
                        commentList.get(i).getDate(), commentList.get(i).getPerson().getIdPerson());
                ol.add(comm);
            }           
              
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
    
    public ObservableList<PostBeans> getPosts(){ 
        
        ObservableList<PostBeans> ol = FXCollections.observableArrayList();
        try{
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Query q = entityManager.createNativeQuery("Select * From Post order by postDate desc",Post.class);
            List<Post> postList = q.getResultList();
            
            PostBeans post;
            for(int i=0; i<postList.size(); i++){
                post = new PostBeans(postList.get(i).getIdPost(), postList.get(i).getStrPost(), 
                        postList.get(i).getPerson().getUsername(), postList.get(i).getPostDate(),
                        postList.get(i).getComments().size() ,postList.get(i).getPerson().getIdPerson());
                ol.add(post);
            }   
              
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            entityManager.close();
        }
        return ol;
        
    }
    
    public ObservableList<PostBeans> searchPostsByUser(String user){ 
        
        ObservableList<PostBeans> ol = FXCollections.observableArrayList();
        try{
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Query q = entityManager.createNativeQuery("Select idPerson From Person where username = ?");       
            q.setParameter(1,user);
            long idPerson = ((Number)q.getSingleResult()).longValue();
            
            Person p = entityManager.find(Person.class, idPerson);
                        
            PostBeans post;
            for(int i=0; i<p.getPosts().size(); i++){
                post = new PostBeans(p.getPosts().get(i).getIdPost(), p.getPosts().get(i).getStrPost(), 
                        p.getPosts().get(i).getPerson().getUsername(), p.getPosts().get(i).getPostDate(),
                        p.getPosts().get(i).getComments().size(), p.getPosts().get(i).getPerson().getIdPerson());
                ol.add(post);
            }   
              
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            entityManager.close();
        }
        return ol;    
    }
    
    public ObservableList<CommentBeans> searchCommentsByUser(String user, Long idPost){ 
        
        ObservableList<CommentBeans> ol = FXCollections.observableArrayList();
        try{
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Query q = entityManager.createNativeQuery("Select idPerson From Person where username = ?");       
            q.setParameter(1,user);
            long idPerson = ((Number)q.getSingleResult()).longValue();
            
            Query q1 = entityManager.createNativeQuery("Select * From Comment where person = ? and post = ?", Comment.class);   
            q1.setParameter(1,idPerson);
            q1.setParameter(2,idPost);
            
            List<Comment> commentList = q1.getResultList();
                         
            CommentBeans comm;
            for(int i=0; i<commentList.size(); i++){
                comm = new CommentBeans(commentList.get(i).getIdComment(), commentList.get(i).getStrComment(), 
                        commentList.get(i).getPerson().getUsername(), commentList.get(i).getPost().getIdPost(), 
                        commentList.get(i).getDate(), commentList.get(i).getPerson().getIdPerson());
                ol.add(comm);
            }   
              
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            entityManager.close();
        }
        return ol;    
    }
    
    public ObservableList<PostBeans> searchPostsByContent(String search){ 
        
        ObservableList<PostBeans> ol = FXCollections.observableArrayList();
        try{
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Query q = entityManager.createNativeQuery("Select * From Post where strPost LIKE ?", Post.class);   
            search = "%" + search + "%";
            q.setParameter(1,search);
    
            List<Post> postList = q.getResultList();
            
            PostBeans post;
            for(int i=0; i<postList.size(); i++){
                post = new PostBeans(postList.get(i).getIdPost(), postList.get(i).getStrPost(), 
                        postList.get(i).getPerson().getUsername(), postList.get(i).getPostDate(),
                        postList.get(i).getComments().size() ,postList.get(i).getPerson().getIdPerson());
                ol.add(post);
            } 
              
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            entityManager.close();
        }
        return ol;    
    }
    
        public ObservableList<CommentBeans> searchCommentsByContent(String search, Long idPost){ 
        
        ObservableList<CommentBeans> ol = FXCollections.observableArrayList();
        try{
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Query q = entityManager.createNativeQuery("Select * From Comment where post = ? AND strComment LIKE ?", Comment.class);   
            search = "%" + search + "%";
            q.setParameter(1,idPost);
            q.setParameter(2,search);
    
            List<Comment> commentList = q.getResultList();
            
            CommentBeans comm;
            for(int i=0; i<commentList.size(); i++){
                comm = new CommentBeans(commentList.get(i).getIdComment(), commentList.get(i).getStrComment(), 
                        commentList.get(i).getPerson().getUsername(), commentList.get(i).getPost().getIdPost(), 
                        commentList.get(i).getDate(), commentList.get(i).getPerson().getIdPerson());
                ol.add(comm);
            }
              
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
    

    public Long login(String username, String password){ 
        try{
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Query q = entityManager.createNativeQuery("Select idPerson From Person where Username = ? "
                    + "and Password = ?");
            q.setParameter(1, username);
            q.setParameter(2, password);
            
            return ((Number)q.getSingleResult()).longValue();
                
        }catch(Exception e){
            e.printStackTrace();
            return new Long(-1);
        }finally{
            entityManager.close();
        }
    }
   
    public Long register(String username, String password){ 
        try{                
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Person p = new Person(username, password);
            entityManager.persist(p);
            entityManager.getTransaction().commit();
            
            Query q = entityManager.createNativeQuery("Select idPerson From Person where Username = ?");
            q.setParameter(1, username);
            
            return ((Number)q.getSingleResult()).longValue();
          
        }catch(Exception e){
            e.printStackTrace();
            return new Long(-1);
           
        }finally{
            entityManager.close();
        } 
    }
    /*
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
    
    public boolean updatePerson(Person person){
        try{ 
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(person);
            entityManager.getTransaction().commit();
            return true;            
        }catch(Exception e){
            e.printStackTrace();
            return false;
           
        }finally{
            entityManager.close();
        } 
    }
    
    public boolean updatePost(Long idPost,String newPost){
        try{                

            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Post p = entityManager.getReference(Post.class,idPost);
            p.setStrPost(newPost);
            entityManager.merge(p);
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
            entityManager.merge(c);
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