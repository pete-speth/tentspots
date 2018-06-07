/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tentspots.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author pspethmann
 */
@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull(message = "Group Leader is required.")
    @Size(min=1, message = "Group Leader is required.")
    @Size(max=60, message = "Group Leader: 60 characters max.")
    private String groupLeader;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @Transient
    private String startDateStr;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @Transient
    private String endDateStr;
    
    @NotNull
    @Min(1)
    private int groupSize;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "CampsiteId")
    private Campsite campsite;
    
    @Transient
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    
    public String getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(String groupLeader) {
        this.groupLeader = groupLeader;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    
    public String getStartDateStr() {
        startDateStr = fmt.format(startDate);
        return startDateStr;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    
     public String getEndDateStr(){
        endDateStr =  fmt.format(endDate);
        return endDateStr;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public Campsite getCampsite() {
        return campsite;
    }

    public void setCampsite(Campsite campsite) {
        this.campsite = campsite;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
