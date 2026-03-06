package com.gymplanner.controller;

import com.gymplanner.model.*;
import com.gymplanner.repository.*;
import com.gymplanner.service.WeeklyScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Clasa pentru gestionarea operatiunilor legate de exercitii
 * @author Bratu Tudor-Ionut
 * @version 11 Ianuarie 2026
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleManagerController {

    @Autowired private WeeklyScheduleService scheduleService;
    @Autowired private WeeklyScheduleRepository scheduleRepository;
    @Autowired private ExerciseRepository exerciseRepository;
    @Autowired private TemplateExerciseRepository templateRepository;
    @Autowired private UserRepository userRepository;

    // afisare pagina de gestionare a unei zile
    @GetMapping("/manage")
    public String manageDay(@RequestParam String date, Model model) {
        LocalDate localDate = LocalDate.parse(date);
        int dayOfWeek = localDate.getDayOfWeek().getValue();

        // cautare plan existent pentru ziua respectiva
        WeeklySchedule schedule = scheduleRepository.findActivePlan(1, dayOfWeek, localDate)
                .stream()
                .findFirst()
                .orElse(null);

        // creare versiune noua de plan daca este necesar
        if (schedule == null || !schedule.getActiveFromDate().isEqual(localDate)) {
            WeeklySchedule newVersion = new WeeklySchedule();
            newVersion.setUser(userRepository.findById(1).orElse(null));
            newVersion.setDayOfWeek(dayOfWeek);
            newVersion.setActiveFromDate(localDate);
            newVersion.setDayName(localDate.getDayOfWeek().name());

            if (schedule != null) {
                newVersion.setWorkoutName(schedule.getWorkoutName());
            }

            schedule = scheduleRepository.save(newVersion);
        }

        model.addAttribute("schedule", schedule);
        model.addAttribute("allExercises", exerciseRepository.findAll());
        model.addAttribute("templateExercises", templateRepository.findByWeeklyScheduleId(schedule.getId()));

        return "schedule_manage";
    }

    // actualizare detalii principale ale zilei
    @PostMapping("/updateHeader")
    public String updateHeader(@ModelAttribute WeeklySchedule scheduleFormData) {
        WeeklySchedule existing = scheduleRepository.findById(scheduleFormData.getId()).get();
        existing.setWorkoutName(scheduleFormData.getWorkoutName());
        existing.setRestDay(scheduleFormData.getRestDay());
        scheduleRepository.save(existing);

        return "redirect:/schedule/manage?date=" + existing.getActiveFromDate();
    }

    // adaugare exercitiu in planul zilei
    @PostMapping("/addExercise")
    public String addExerciseToPlan(@RequestParam Integer scheduleId,
                                    @RequestParam Integer exerciseId,
                                    @RequestParam Integer sets,
                                    @RequestParam Integer reps,
                                    @RequestParam Integer restTime) {

        TemplateExercise te = new TemplateExercise();
        te.setWeeklySchedule(scheduleRepository.findById(scheduleId).get());
        te.setExercise(exerciseRepository.findById(exerciseId).get());
        te.setPlannedSets(sets);
        te.setTargetReps(reps);
        te.setRestTimeMinutes(restTime);

        templateRepository.save(te);

        WeeklySchedule ws = scheduleRepository.findById(scheduleId).get();
        return "redirect:/schedule/manage?date=" + ws.getActiveFromDate();
    }

    // stergere exercitiu din plan
    @GetMapping("/deleteExercise/{id}")
    public String deleteTemplateExercise(@PathVariable Integer id) {
        TemplateExercise te = templateRepository.findById(id).get();
        String date = te.getWeeklySchedule().getActiveFromDate().toString();
        templateRepository.delete(te);
        return "redirect:/schedule/manage?date=" + date;
    }
}