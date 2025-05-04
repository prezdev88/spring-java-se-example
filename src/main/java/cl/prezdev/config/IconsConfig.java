package cl.prezdev.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "gui")
public class IconsConfig {

    private Map<String, String> icons;

    public String getIconPath(String key) {
        return icons != null ? icons.get(key) : null;
    }
}