package com.gymplanner.repository;

import com.gymplanner.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/** Clasa pentru gestionarea operatiunilor legate de exercitii
 * @author Bratu Tudor-Ionut
 * @version 11 Ianuarie 2026
 */
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    // interogare pentru exercitii globale si personale
    @Query("SELECT e FROM Exercise e WHERE e.user.id = :userId OR e.user IS NULL")
    List<Exercise> findAllForUser(Integer userId);
}