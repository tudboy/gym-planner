package com.gymplanner.service;

import com.gymplanner.model.WeeklySchedule;
import com.gymplanner.repository.WeeklyScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/** Clasa pentru gestionarea operatiunilor legate de exercitii
 * @author Bratu Tudor-Ionut
 * @version 11 Ianuarie 2026
 */
@Service
public class WeeklyScheduleService {

    private final WeeklyScheduleRepository weeklyScheduleRepository;

    // injectare dependinta repository
    @Autowired
    public WeeklyScheduleService(WeeklyScheduleRepository weeklyScheduleRepository) {
        this.weeklyScheduleRepository = weeklyScheduleRepository;
    }

    // returneaza planul activ pentru o zi specifica
    public WeeklySchedule getPlanForDay(Integer userId, Integer dayOfWeek, LocalDate date) {
        // obtine lista de planuri descrescator
        List<WeeklySchedule> versions = weeklyScheduleRepository.findActivePlan(userId, dayOfWeek, date);

        // returneaza primul plan gasit
        if (!versions.isEmpty()) {
            return versions.get(0);
        }

        return null;
    }
}