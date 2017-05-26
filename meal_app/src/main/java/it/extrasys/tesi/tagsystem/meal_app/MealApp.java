package it.extrasys.tesi.tagsystem.meal_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot starter class.
 *
 * @author marco
 *
 */
@SpringBootApplication
public class MealApp implements CommandLineRunner {

    /**
     * Main method to start the app.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MealApp.class, args);

    }

    @Override
    public void run(String... arg0) throws Exception {
        // TODO Auto-generated method stub

    }
}
