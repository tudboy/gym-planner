package com.gymplanner.model;

import jakarta.persistence.*;

/** Clasa pentru gestionarea operatiunilor legate de exercitii
 * @author Bratu Tudor-Ionut
 * @version 11 Ianuarie 2026
 */
@Entity
@Table(name = "WorkoutHistorySets")
public class WorkoutHistorySet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SetID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "HistoryID")
    private WorkoutHistory workoutHistory;

    @Column(name = "ExerciseName")
    private String exerciseName;

    @Column(name = "SetNumber")
    private Integer setNumber;

    @Column(name = "WeightKG")
    private Double weightKG;

    @Column(name = "RepsPerformed")
    private Integer repsPerformed;

    @Column(name = "RepsTarget")
    private Integer repsTarget;

    @Column(name = "RecommendedRestSeconds")
    private Integer recommendedRestSeconds;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public WorkoutHistory getWorkoutHistory() { return workoutHistory; }
    public void setWorkoutHistory(WorkoutHistory workoutHistory) { this.workoutHistory = workoutHistory; }
    public String getExerciseName() { return exerciseName; }
    public void setExerciseName(String exerciseName) { this.exerciseName = exerciseName; }
    public Integer getSetNumber() { return setNumber; }
    public void setSetNumber(Integer setNumber) { this.setNumber = setNumber; }
    public Double getWeightKG() { return weightKG; }
    public void setWeightKG(Double weightKG) { this.weightKG = weightKG; }
    public Integer getRepsPerformed() { return repsPerformed; }
    public void setRepsPerformed(Integer repsPerformed) { this.repsPerformed = repsPerformed; }
    public Integer getRepsTarget() { return repsTarget; }
    public void setRepsTarget(Integer repsTarget) { this.repsTarget = repsTarget; }
    public Integer getRecommendedRestSeconds() { return recommendedRestSeconds; }
    public void setRecommendedRestSeconds(Integer recommendedRestSeconds) { this.recommendedRestSeconds = recommendedRestSeconds; }
}