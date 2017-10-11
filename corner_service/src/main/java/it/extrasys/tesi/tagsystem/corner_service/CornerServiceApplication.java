package it.extrasys.tesi.tagsystem.corner_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CornerServiceApplication implements CommandLineRunner {

  /**
   * The main method.
   *
   * @param args
   *          the arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(CornerServiceApplication.class, args);
  }

  @Override
  public void run(String... arg0) throws Exception {
    // TODO Auto-generated method stub
    System.getenv().forEach((f, k) -> System.out.println("Var : " + f + "val : " + k));

  }
}