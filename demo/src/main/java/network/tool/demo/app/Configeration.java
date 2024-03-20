package network.tool.demo.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
@Configuration
@ComponentScan(basePackages ="network.tool.demo" )
public class Configeration {
	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Or use "/**" to apply to all endpoints
                .allowedOrigins("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
