package mycompany.task1;

import java.io.*;
import static java.lang.Long.*;
import java.util.*;
import javafx.application.Platform;
import static org.iq80.leveldb.impl.Iq80DBFactory.*;
import org.iq80.leveldb.*;

public class LevelDBManager {
private DB db;

     
 public LevelDBManager()  {
      try {
          Options options = new Options();
          db = factory.open(new File("leveldb"), options);
        } catch (IOException e){
            e.printStackTrace();
            System.err.println("ERROR: Application closed, unable to open levelDB folder");
            Platform.exit();
        }
    }

 
    public void deleteValuesFromUser(ArrayList<String> toDelete){
        for(int i = 0; i < toDelete.size(); i++){
            String key = toDelete.get(i);
            db.delete(bytes(key));
        }
    }
    
    public void deleteSingleValueFromUser(String key){
        db.delete(bytes(key));
    }
    
    //[username:id:attribute] [attribute_value]
    public void putValuesToUser(String key, String value){
        db.put(bytes(key), bytes(value));  
    }
 
    public List<String> getValuesFromUser(String entity, Long id){
       
        List<String> result = new ArrayList();  
  
        try(
            DBIterator keyIterator = db.iterator();
        ){
            
            String myKey = entity + ":" + id;
            keyIterator.seek(bytes(myKey));
            while (keyIterator.hasNext()) {
                String key = asString(keyIterator.peekNext().getKey());
                String[] keySplit = key.split(":"); 
                if (parseLong(keySplit[1]) != id) {
                        break;
                    }
                String resultAttribute = new String(keySplit[keySplit.length - 1] + ":" + asString(db.get(bytes(key))));
                result.add(resultAttribute);
                keyIterator.next();
            }
            keyIterator.close();
        }
        
        catch(IOException e){
           e.printStackTrace();
        }
        return result;
    }
    
    public void close(){
       try{
           db.close();
       }catch(IOException e){           
           e.printStackTrace();
           Platform.exit();
       }
    }
 
        
}
