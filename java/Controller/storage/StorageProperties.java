package Controller.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "src/main/resources/static/assets/imageMovie";
//src/main/resources/static/assets/imageMovie
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
