package com.gymplanner.controller;

import com.gymplanner.model.Exercise;
import com.gymplanner.model.User;
import com.gymplanner.service.ExerciseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/** Clasa pentru gestionarea operatiunilor legate de exercitii
 * @author Bratu Tudor-Ionut
 * @version 11 Ianuarie 2026
 */
@Controller
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    // afisare pagina cu lista de exercitii
    @GetMapping
    public String viewExercisesPage(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/";

        // afisam exercitiile relevante
        model.addAttribute("listaExercitii", exerciseService.getAllExercisesForUser(currentUser.getId()));
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("currentUserId", currentUser.getId());

        return "exercises";
    }

    // adaugare exercitiu nou in baza de date
    @PostMapping("/add")
    public String addExercise(@ModelAttribute Exercise exercise, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/";

        exerciseService.addExercise(exercise, currentUser);
        return "redirect:/exercises";
    }

    // afisare formular pentru editare
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        // permitem oricui sa editeze
        Exercise exercise = exerciseService.getExerciseById(id);
        model.addAttribute("exercise", exercise);
        return "exercise_edit";
    }

    // actualizare date exercitiu existent
    @PostMapping("/edit/{id}")
    public String updateExercise(@PathVariable Integer id, @ModelAttribute Exercise exerciseForm, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/";

        // luam exercitiul original din baza de date
        Exercise existingExercise = exerciseService.getExerciseById(id);

        // actualizam datele pastrand proprietarul original
        existingExercise.setExerciseName(exerciseForm.getExerciseName());
        existingExercise.setTargetMuscleGroup(exerciseForm.getTargetMuscleGroup());

        // salvam varianta actualizata
        exerciseService.addExercise(existingExercise, existingExercise.getUser());

        return "redirect:/exercises";
    }

    // stergere exercitiu din baza de date
    @GetMapping("/delete/{id}")
    public String deleteExercise(@PathVariable Integer id) {
        try {
            exerciseService.deleteExercise(id);
        } catch (Exception e) {
            System.out.println("Eroare la stergere" + e.getMessage());
        }
        return "redirect:/exercises";
    }
}