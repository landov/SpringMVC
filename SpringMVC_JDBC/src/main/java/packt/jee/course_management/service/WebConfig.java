package packt.jee.course_management.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
 
    @Override
    public void addFormatters(FormatterRegistry registry) {
    	System.out.println("lefutunk?");
        registry.addConverter(new CourseConverter());
    }
}
