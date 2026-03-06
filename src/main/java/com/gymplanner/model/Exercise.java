package com.gymplanner.model;

import jakarta.persistence.*;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

/** Clasa pentru gestionarea operatiunilor legate de exercitii
 * @author Bratu Tudor-Ionut
 * @version 11 Ianuarie 2026
 */
@Entity
@Table(name = "Exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExerciseID")
    private Integer id;

    @Column(name = "ExerciseName", nullable = false)
    private String exerciseName;

    @Column(name = "TargetMuscleGroup", nullable = false)
    private String targetMuscleGroup;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    public Exercise() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getExerciseName() { return exerciseName; }
    public void setExerciseName(String exerciseName) { this.exerciseName = exerciseName; }

    public String getTargetMuscleGroup() { return targetMuscleGroup; }
    public void setTargetMuscleGroup(String targetMuscleGroup) { this.targetMuscleGroup = targetMuscleGroup; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}