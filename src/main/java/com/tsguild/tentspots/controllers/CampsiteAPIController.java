/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tentspots.controllers;

import com.tsguild.tentspots.model.Campsite;
import com.tsguild.tentspots.model.Feature;
import com.tsguild.tentspots.model.Location;
import com.tsguild.tentspots.model.Visit;
import com.tsguild.tentspots.service.CampsiteService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pspethmann
 */
@RestController
@RequestMapping("/api")
public class CampsiteAPIController {
    
    @Autowired
    CampsiteService service;
    
    @GetMapping("/campsite/{id}/recentVisits")
    public List<Visit> getRecentVisits(@PathVariable int id) {
        
        List<Visit> visits = service.getVisitsForCampsite(id);
        
        visits = visits.stream()
                .filter(v -> v.getNotes() != null && !v.getNotes().isEmpty())
                .limit(3)
                .collect(Collectors.toList());
        
        return visits;
    }
    
    @GetMapping("/campsite/{id}/location")
    public Location getLocation(@PathVariable int id){
        
        Campsite c = service.getCampsite(id);
        return c.getLocation();
    }
    
    @GetMapping("/campsite/top3")
    public List<Campsite> getTopCampsites(){
        
        List<Campsite> campsites = service.getAllCampsites();
        
        if (campsites.size()>3) {
            campsites = campsites.stream()
                            .sorted((c1,c2) -> -Integer.
                                    compare(c1.getNumVisits(), 
                                            c2.getNumVisits()))
                            .limit(3)
                            .collect(Collectors.toList());
        }
        
        return campsites;
    }
    
    @GetMapping("/campsite/{id}/features")
    public List<Feature> getCampsiteFeatures(@PathVariable int id){
        
        Campsite c = service.getCampsite(id);
        return c.getFeatures();
    }
    
    @DeleteMapping("/delete/campsite/{id}")
    public void deleteCampsite(@PathVariable int id) {
        
        Campsite c = service.getCampsite(id);
        List<Visit> visits = service.getVisitsForCampsite(id);
        
        for (Visit v: visits){
            service.delete(v);
        }
        
        service.delete(c);
    }
    
    @DeleteMapping("/delete/visit/{id}")
    public void deleteVisit(@PathVariable int id) {
        
        Visit v = service.getVisit(id);
        
        service.delete(v);
    }
}
