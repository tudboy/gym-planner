package com.gymplanner.model;

import jakarta.persistence.*;

/** Clasa pentru gestionarea operatiunilor legate de exercitii
 * @author Bratu Tudor-Ionut
 * @version 11 Ianuarie 2026
 */
@Entity
@Table(name = "TemplateExercises")
public class TemplateExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TemplateID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ScheduleID", nullable = false)
    private WeeklySchedule weeklySchedule;

    @ManyToOne
    @JoinColumn(name = "ExerciseID", nullable = false)
    private Exercise exercise;

    @Column(name = "PlannedSets")
    private Integer plannedSets = 4;

    @Column(name = "TargetReps")
    private Integer targetReps = 10;

    @Column(name = "RestTimeMinutes")
    private Integer restTimeMinutes = 2;

    @Column(name = "OrderIndex")
    private Integer orderIndex;

    public TemplateExercise() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public WeeklySchedule getWeeklySchedule() { return weeklySchedule; }
    public void setWeeklySchedule(WeeklySchedule weeklySchedule) { this.weeklySchedule = weeklySchedule; }

    public Exercise getExercise() { return exercise; }
    public void setExercise(Exercise exercise) { this.exercise = exercise; }

    public Integer getPlannedSets() { return plannedSets; }
    public void setPlannedSets(Integer plannedSets) { this.plannedSets = plannedSets; }

    public Integer getTargetReps() { return targetReps; }
    public void setTargetReps(Integer targetReps) { this.targetReps = targetReps; }

    public Integer getRestTimeMinutes() { return restTimeMinutes; }
    public void setRestTimeMinutes(Integer restTimeMinutes) { this.restTimeMinutes = restTimeMinutes; }

    public Integer getOrderIndex() { return orderIndex; }
    public void setOrderIndex(Integer orderIndex) { this.orderIndex = orderIndex; }
}