package com.gymplanner.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Clasa pentru gestionarea operatiunilor legate de exercitii
 * @author Bratu Tudor-Ionut
 * @version 11 Ianuarie 2026
 */
@Entity
@Table(name = "WeeklySchedule")
public class WeeklySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ScheduleID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "DayOfWeek", nullable = false)
    private Integer dayOfWeek;

    @Column(name = "DayName")
    private String dayName;

    @Column(name = "ActiveFromDate", nullable = false)
    private LocalDate activeFromDate;

    @Column(name = "WorkoutName")
    private String workoutName;

    @Column(name = "IsRestDay")
    private Boolean isRestDay = false;

    @OneToMany(mappedBy = "weeklySchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateExercise> exercises = new ArrayList<>();

    public WeeklySchedule() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Integer getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(Integer dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public String getDayName() { return dayName; }
    public void setDayName(String dayName) { this.dayName = dayName; }

    public LocalDate getActiveFromDate() { return activeFromDate; }
    public void setActiveFromDate(LocalDate activeFromDate) { this.activeFromDate = activeFromDate; }

    public String getWorkoutName() { return workoutName; }
    public void setWorkoutName(String workoutName) { this.workoutName = workoutName; }

    public Boolean getRestDay() { return isRestDay; }
    public void setRestDay(Boolean restDay) { isRestDay = restDay; }

    public List<TemplateExercise> getExercises() { return exercises; }
    public void setExercises(List<TemplateExercise> exercises) { this.exercises = exercises; }
}