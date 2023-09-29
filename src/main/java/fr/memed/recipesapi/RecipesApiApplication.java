package fr.memed.recipesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "fr.memed.recipesapi"})
public class RecipesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipesApiApplication.class, args);
    }

}
