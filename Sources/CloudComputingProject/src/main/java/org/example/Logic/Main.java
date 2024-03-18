package org.example.Logic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        Executors.newSingleThreadScheduledExecutor().schedule(
                Main::openHomePage, 5, TimeUnit.SECONDS
        );
    }

    private static void openHomePage() {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                URI homePage = new URI("http://localhost:8080/");
                desktop.browse(homePage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
