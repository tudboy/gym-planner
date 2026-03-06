package com.gymplanner.service;

import com.gymplanner.model.Exercise;
import com.gymplanner.model.User;
import com.gymplanner.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** Clasa pentru gestionarea operatiunilor legate de exercitii
 * @author Bratu Tudor-Ionut
 * @version 11 Ianuarie 2026
 */
@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    // injectare dependinta repository
    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    // returneaza toate exercitiile pentru un utilizator
    public List<Exercise> getAllExercisesForUser(Integer userId) {
        return exerciseRepository.findAllForUser(userId);
    }

    // adauga un exercitiu nou si seteaza utilizatorul
    public void addExercise(Exercise exercise, User user) {
        exercise.setUser(user);
        exerciseRepository.save(exercise);
    }

    // gaseste un exercitiu dupa id
    public Exercise getExerciseById(Integer id) {
        return exerciseRepository.findById(id).orElse(null);
    }

    // sterge un exercitiu dupa id
    public void deleteExercise(Integer id) {
        exerciseRepository.deleteById(id);
    }
}