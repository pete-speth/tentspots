/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tentspots.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author pspethmann
 */
@Entity
public class State {
    
    @Id
    private String abbr;
    private String name;

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
