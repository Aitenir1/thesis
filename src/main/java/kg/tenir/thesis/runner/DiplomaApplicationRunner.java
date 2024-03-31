package kg.tenir.thesis.runner;

import kg.tenir.thesis.service.props.MinioProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DiplomaApplicationRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        // Your startup logic here
        MinioProperties minioProperties = new MinioProperties();
//        System.out.println(greatest);
        System.out.println("My application has started!");
    }
}
