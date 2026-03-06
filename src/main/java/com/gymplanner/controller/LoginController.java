package com.gymplanner.controller;

import com.gymplanner.model.User;
import com.gymplanner.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** Clasa pentru gestionarea operatiunilor legate de exercitii
 * @author Bratu Tudor-Ionut
 * @version 11 Ianuarie 2026
 */
@Controller
public class LoginController {

    @Autowired private UserRepository userRepository;

    // afisare pagina de login
    @GetMapping("/")
    public String showLoginPage(Model model, HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            return "redirect:/calendar";
        }
        // trimitere lista utilizatori catre pagina
        model.addAttribute("users", userRepository.findAll());
        return "login";
    }

    // procesare autentificare utilizator
    @PostMapping("/login")
    public String processLogin(@RequestParam Integer userId, HttpSession session) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            session.setAttribute("currentUser", user);
            return "redirect:/calendar";
        }
        return "redirect:/";
    }

    // creare utilizator nou
    @PostMapping("/register")
    public String registerUser(@RequestParam String username) {
        // validare lungime si existenta utilizator
        if (username != null && username.length() <= 30 && userRepository.findByUsername(username) == null) {
            User newUser = new User(username);
            userRepository.save(newUser);
        }
        return "redirect:/";
    }

    // deconectare utilizator
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // stergere utilizator din sistem
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Integer id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Eroare la stergere user " + e.getMessage());
        }
        return "redirect:/";
    }
}