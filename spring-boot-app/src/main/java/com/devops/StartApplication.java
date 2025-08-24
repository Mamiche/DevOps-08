package com.devops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class StartApplication {

    @GetMapping("/")
    public String index(final Model model) {
        // Ajout des attributs à la vue
        model.addAttribute("title", "J'ai réussi à créer une application Spring Boot avec Maven");
        model.addAttribute("msg", "Cette application est déployée sur Kubernetes en utilisant Argo CD");
        return "index"; // Retourne la page index.html
    }

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args); // Démarre l'application Spring Boot
    }

}
