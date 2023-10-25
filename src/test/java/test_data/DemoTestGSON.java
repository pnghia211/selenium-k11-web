package test_data;

import com.google.gson.Gson;

public class DemoTestGSON {

    // Convert from JSON to Java Object
    // Convert form Java object to GSON

    public static void main(String[] args) {
            String jsonStr = "  {\n" +
                    "    \"processorType\": \"Fast\",\n" +
                    "    \"ram\": \"8 GB\",\n" +
                    "    \"hdd\": \"400 GB\",\n" +
                    "    \"software\": \"Image Viewer\"\n" +
                    "  }";

            Gson gson = new Gson();
        }

}
