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
@Table(name = "WorkoutHistory")
public class WorkoutHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HistoryID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @Column(name = "WorkoutDate")
    private LocalDate workoutDate;

    @Column(name = "DurationMinutes")
    private Integer durationMinutes;

    @Column(name = "Notes")
    private String notes;

    @OneToMany(mappedBy = "workoutHistory", cascade = CascadeType.ALL)
    private List<WorkoutHistorySet> sets = new ArrayList<>();

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public LocalDate getWorkoutDate() { return workoutDate; }
    public void setWorkoutDate(LocalDate workoutDate) { this.workoutDate = workoutDate; }
    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public List<WorkoutHistorySet> getSets() { return sets; }
    public void setSets(List<WorkoutHistorySet> sets) { this.sets = sets; }
}