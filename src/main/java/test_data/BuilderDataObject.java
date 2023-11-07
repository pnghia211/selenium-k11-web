package test_data;

import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BuilderDataObject {
    public  static  <T> T buildDataObject(String fileLocation, Class<T> dataClass) {
        T dataObject;
        String currentFileLocation = System.getProperty("user.dir");
        String fileAbsoluteLocation = currentFileLocation + fileLocation;
        try (Reader jsonReader = Files.newBufferedReader(Paths.get(fileAbsoluteLocation));) {
            Gson gson = new Gson();
            dataObject = gson.fromJson(jsonReader, dataClass);
        } catch (Exception e) {
            throw new RuntimeException("[ERR] error while reading " + fileAbsoluteLocation);
        }
        return dataObject;
    }
}
