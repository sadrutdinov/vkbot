import com.vk.api.sdk.objects.apps.App;

import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private int id;
    private String token;

    public ConfigReader() {
        try {
            Properties prop = new Properties();
            prop.load(App.class.getClassLoader().getResourceAsStream("application.properties"));
            id = Integer.parseInt(prop.getProperty("id"));
            token = prop.getProperty("token");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }


}
