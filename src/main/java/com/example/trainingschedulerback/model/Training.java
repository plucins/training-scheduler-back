package com.example.trainingschedulerback.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime trainingStartDate;
    private LocalDateTime trainingEndDate;
    @OneToMany
    private List<User> trainingUsers;
    @OneToOne()
    @JoinColumn(name = "place_id")
    private Place place;

    public Training() {
    }

    public Training(LocalDateTime trainingStartDate, LocalDateTime trainingEndDate, List<User> trainingParticipants, Place place) {
        this.trainingStartDate = trainingStartDate;
        this.trainingEndDate = trainingEndDate;
        this.trainingUsers = trainingParticipants;
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTrainingStartDate() {
        return trainingStartDate;
    }

    public void setTrainingStartDate(LocalDateTime trainingStartDate) {
        this.trainingStartDate = trainingStartDate;
    }

    public LocalDateTime getTrainingEndDate() {
        return trainingEndDate;
    }

    public void setTrainingEndDate(LocalDateTime trainingEndDate) {
        this.trainingEndDate = trainingEndDate;
    }

    public List<User> getTrainingUsers() {
        return trainingUsers;
    }

    public void setTrainingUsers(List<User> trainingUsers) {
        this.trainingUsers = trainingUsers;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
