package com.gymplanner.controller;

import com.gymplanner.model.*;
import com.gymplanner.repository.*;
import com.gymplanner.service.WeeklyScheduleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Clasa pentru gestionarea operatiunilor legate de exercitii
 * @author Bratu Tudor-Ionut
 * @version 11 Ianuarie 2026
 */
@Controller
@RequestMapping("/workout")
public class LiveWorkoutController {

    @Autowired private WeeklyScheduleService scheduleService;
    @Autowired private WorkoutHistoryRepository historyRepository;
    @Autowired private UserRepository userRepository;

    // pregatire pagina de start antrenament
    @GetMapping("/start")
    public String startWorkout(@RequestParam String date, Model model, HttpSession session) {
        // verificare utilizator logat
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/";

        LocalDate localDate = LocalDate.parse(date);
        int dayOfWeek = localDate.getDayOfWeek().getValue();

        // obtinere plan pentru ziua curenta
        WeeklySchedule plan = scheduleService.getPlanForDay(currentUser.getId(), dayOfWeek, localDate);

        WorkoutHistory historyForm = new WorkoutHistory();
        historyForm.setWorkoutDate(localDate);

        if (plan != null) {
            List<WorkoutHistorySet> prepopulatedSets = new ArrayList<>();

            for (TemplateExercise te : plan.getExercises()) {
                int setsCount = (te.getPlannedSets() != null && te.getPlannedSets() > 0) ? te.getPlannedSets() : 3;

                for (int i = 1; i <= setsCount; i++) {
                    WorkoutHistorySet set = new WorkoutHistorySet();
                    set.setExerciseName(te.getExercise().getExerciseName());
                    set.setSetNumber(i);
                    int target = (te.getTargetReps() != null) ? te.getTargetReps() : 10;
                    set.setRepsTarget(target);

                    int restMin = (te.getRestTimeMinutes() != null) ? te.getRestTimeMinutes() : 2;
                    set.setRecommendedRestSeconds(restMin * 60);

                    prepopulatedSets.add(set);
                }
            }
            historyForm.setSets(prepopulatedSets);
        }

        model.addAttribute("historyForm", historyForm);
        model.addAttribute("planName", (plan != null) ? plan.getWorkoutName() : "Antrenament Liber");

        return "workout_live";
    }

    // salvare antrenament finalizat in baza de date
    @PostMapping("/save")
    public String saveWorkout(@ModelAttribute WorkoutHistory historyForm, HttpSession session) {
        // verificare utilizator logat
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) return "redirect:/";

        // setare utilizator pentru istoric
        historyForm.setUser(currentUser);

        if (historyForm.getSets() != null) {
            for (WorkoutHistorySet set : historyForm.getSets()) {
                set.setWorkoutHistory(historyForm);
            }
        }

        historyRepository.save(historyForm);

        return "redirect:/calendar";
    }
}