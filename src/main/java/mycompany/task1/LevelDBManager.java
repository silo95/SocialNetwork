
package mycompany.task1;

import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import static javax.management.Query.value;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;
import static org.iq80.leveldb.impl.Iq80DBFactory.asString;
import static org.iq80.leveldb.impl.Iq80DBFactory.bytes;
import static org.iq80.leveldb.impl.Iq80DBFactory.factory;
import org.iq80.leveldb.DB;
import static org.iq80.leveldb.impl.Iq80DBFactory.factory;
import org.iq80.leveldb.Options;

public class LevelDBManager {
private DB db;

     
 public LevelDBManager()  {
      try {
          Options options = new Options();
          db = factory.open(new File("leveldb"), options);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

 
    public void deleteValuesFromUser(ArrayList<String> toDelete){
        System.out.println("dentro Delete: " + toDelete.size());
        for(int i = 0; i < toDelete.size(); i++){
            String key = toDelete.get(i);
            db.delete(bytes(key));
        }
    }
    public void putValuesToUser(ArrayList<ArrayList<String>> toAdd){
        for(int i = 0; i < toAdd.size(); i++){
            String key = toAdd.get(i).get(0);
            String value = toAdd.get(i).get(1);
            db.put(bytes(key), bytes(value));
        }
        
    }
 
    public List<String> getValuesFromUser(String entity, int id){
       
        List<String> result = new ArrayList();  
  
        try(
            DBIterator keyIterator = db.iterator();
        ){
            
            String myKey = entity + ":" + id;
            keyIterator.seek(bytes(myKey));
            while (keyIterator.hasNext()) {
                String key = asString(keyIterator.peekNext().getKey());
                String[] keySplit = key.split(":"); 
                if (parseInt(keySplit[1]) != id) {
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
       }
    }
 
        
}