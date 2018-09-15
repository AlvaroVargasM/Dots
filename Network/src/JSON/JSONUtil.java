package JSON;
    
//Imports for exception
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;

//Import mapper for reading and writing
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
    private static ObjectMapper mapper;
    static{
        mapper = new ObjectMapper();
    }
    
    //Method for Java to JSON
    public static String convertJavaToJson(Object object){
        String jsonResult = "";
        try{
            jsonResult = mapper.writeValueAsString(object);
        }
        catch (JsonParseException e) {
            System.out.println("Exception Occured while converting Java Object into JSON "+e.getMessage());
        } 
        catch (JsonMappingException e) {
            System.out.println("Exception Occured while converting Java Object into JSON "+e.getMessage());
	} 
        catch (IOException e) {
            System.out.println("Exception Occured while converting Java Object into JSON "+e.getMessage());
	}
	return jsonResult;	
    }  
    
    //Method for JSON to Java
    public static <T> T convertJsonToJava (String jsonString, Class <T> cls){
        T result = null;
        try {
        result = mapper.readValue(jsonString, cls);
        }
        catch (JsonParseException e) {
            System.out.println("Exception Occured while converting JSON into Java Object "+e.getMessage());
        } 
        catch (JsonMappingException e) {
            System.out.println("Exception Occured while converting JSON into Java Object "+e.getMessage());
	} 
        catch (IOException e) {
            System.out.println("Exception Occured while converting JSON into Java Object "+e.getMessage());
	}
        return result;
    }
}