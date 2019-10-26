
package mycompany.task1;

import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
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
 
    public void putKeyValue(String entity, int id, String attribute, String value){
        String key = entity + ":" + id + ":" + attribute;
        db.put(bytes(key),bytes(value));      
    }
 
    public List<String> getValue(String entity, int id) throws IOException{
       DBIterator keyIterator = db.iterator();
       String myKey = entity + ":" + id;
       List<String> result = new ArrayList();
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
