/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tentspots.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author pspethmann
 */
@Entity
public class Location {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Min(-90)
    @Max(90)
    private BigDecimal latitude;
    
    @NotNull
    @Min(-180)
    @Max(180)
    private BigDecimal longitude;
    
    @ManyToOne
    @JoinColumn(name = "ParkId")
    private Park park;
    
    @ManyToOne
    @JoinColumn(name = "StateAbbr")
    private State state;

    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitiude) {
        this.latitude = latitiude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
   
    
}
