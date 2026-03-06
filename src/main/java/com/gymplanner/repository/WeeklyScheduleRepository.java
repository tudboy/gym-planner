package com.gymplanner.repository;

import com.gymplanner.model.WeeklySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

/** Clasa pentru gestionarea operatiunilor legate de exercitii
 * @author Bratu Tudor-Ionut
 * @version 11 Ianuarie 2026
 */
@Repository
public interface WeeklyScheduleRepository extends JpaRepository<WeeklySchedule, Integer> {

    // cautare plan activ pentru o data specifica
    @Query("SELECT w FROM WeeklySchedule w WHERE w.user.id = :userId AND w.dayOfWeek = :dayOfWeek AND w.activeFromDate <= :currentDate ORDER BY w.activeFromDate DESC")
    List<WeeklySchedule> findActivePlan(Integer userId, Integer dayOfWeek, LocalDate currentDate);
}