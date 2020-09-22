package com.capston.demo.config;

import com.capston.demo.model.File;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
@Getter
@Setter
public class FileUploadProperties {

    private String uploadDir;
    private File file;

    @Getter
    @Setter
    public static class profile {
        private String dir;
        private String defaultImg;
    }

    @Getter
    @Setter
    public static class project {
        private String dir;
        private String defaultImg;
    }
}
