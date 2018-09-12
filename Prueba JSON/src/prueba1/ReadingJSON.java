package prueba1;

import prueba1.MainParser;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ReadingJSON {
    public static void main(String[] args){
        String inputJson = "{ \"email\":\"valvaro707@gmail.com\", \"login\":true}";
    
        ObjectMapper mapper = new ObjectMapper();
        
        try{
            MainParser mp = mapper.readValue(inputJson, MainParser.class);
            System.out.println(mp.isLogin());
        }      
        catch (JsonParseException e) {
			e.printStackTrace();	} 
        catch (JsonMappingException e) {
			e.printStackTrace();
	} 
        catch (IOException e) {
			e.printStackTrace();
	}
		
    }
}
