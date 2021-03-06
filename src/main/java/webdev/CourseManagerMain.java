package webdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "webdev")
public class CourseManagerMain {

    public static void main(String[] args) {
        SpringApplication.run(CourseManagerMain.class, args);
    }
}