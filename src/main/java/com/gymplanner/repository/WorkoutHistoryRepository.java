package com.gymplanner.repository;

import com.gymplanner.model.WorkoutHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/** Clasa pentru gestionarea operatiunilor legate de exercitii
 * @author Bratu Tudor-Ionut
 * @version 11 Ianuarie 2026
 */
@Repository
public interface WorkoutHistoryRepository extends JpaRepository<WorkoutHistory, Integer> {
    List<WorkoutHistory> findByUserIdOrderByWorkoutDateDesc(Integer userId);
}