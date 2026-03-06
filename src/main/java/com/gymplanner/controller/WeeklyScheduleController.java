package com.gymplanner.controller;

import com.gymplanner.model.User;
import com.gymplanner.model.WeeklySchedule;
import com.gymplanner.service.WeeklyScheduleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedHashMap;
import java.util.Map;

/** Clasa pentru gestionarea operatiunilor legate de exercitii
 * @author Bratu Tudor-Ionut
 * @version 11 Ianuarie 2026
 */
@Controller
@RequestMapping("/calendar")
public class WeeklyScheduleController {

    private final WeeklyScheduleService scheduleService;

    @Autowired
    public WeeklyScheduleController(WeeklyScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // afisare calendar cu planificarea saptamanala
    @GetMapping
    public String viewCalendar(@RequestParam(required = false) LocalDate date,
                               Model model,
                               HttpSession session) {

        // verificare utilizator logat
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/";
        }

        // stabilire data curenta
        if (date == null) {
            date = LocalDate.now();
        }

        LocalDate startOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        Map<LocalDate, WeeklySchedule> weekPlan = new LinkedHashMap<>();

        // iterare prin zilele saptamanii pentru construire plan
        for (int i = 0; i < 7; i++) {
            LocalDate currentDay = startOfWeek.plusDays(i);
            int dayOfWeekIndex = currentDay.getDayOfWeek().getValue();

            WeeklySchedule plan = scheduleService.getPlanForDay(currentUser.getId(), dayOfWeekIndex, currentDay);

            weekPlan.put(currentDay, plan);
        }

        // trimitere date catre interfata
        model.addAttribute("weekPlan", weekPlan);
        model.addAttribute("currentDate", date);
        model.addAttribute("nextWeek", date.plusWeeks(1));
        model.addAttribute("prevWeek", date.minusWeeks(1));
        model.addAttribute("username", currentUser.getUsername());

        return "calendar";
    }
}