/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tentspots.controllers;

import com.tsguild.tentspots.model.Campsite;
import com.tsguild.tentspots.model.Feature;
import com.tsguild.tentspots.model.Park;
import com.tsguild.tentspots.model.State;
import com.tsguild.tentspots.model.Visit;
import com.tsguild.tentspots.service.CampsiteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author pspethmann
 */
@Controller
public class CampsiteController {
    
    @Autowired
    CampsiteService service;
    
    @GetMapping("/explore")
    public String explore(Model model){
        
        List<Campsite> campsites = service.getAllCampsites();
        model.addAttribute("campsites",campsites);
        
        
        List<State> states = service.getAllStates();
        model.addAttribute("states", states);
        
        return "explore";
    }
    
    @GetMapping("/explore/campsite/{id}")
    public String exploreCampsite(@PathVariable int id, Model model){
        
        Campsite campsite = service.getCampsite(id);
        model.addAttribute("campsite", campsite);
        
        return "explore-campsite";
    }
    
    @GetMapping("/explore/campsite/{id}/log")
    public String exploreCampsiteLog(@PathVariable int id, Model model){
        
        Campsite campsite = service.getCampsite(id);
        model.addAttribute("campsite", campsite);
        
        List<Visit> visits = service.getVisitsForCampsite(id);
        model.addAttribute("visits", visits);
        
        return "explore-campsite-log";
    }
    
    @GetMapping("/explore/campsite/{id}/log/add")
    public String exploreCampsiteAddLog(@PathVariable int id, Model model){
        
        Campsite campsite = service.getCampsite(id);
        model.addAttribute("campsite", campsite);
        
        return "explore-campsite-addlog";
    }
    
    @GetMapping("/update")
    public String update(Model model){
        
        List<State> states = service.getAllStates();
        model.addAttribute("states", states);
        
        return "update";
    }
    
    @GetMapping("/update/editor")
    public String addCampsite(Model model){
        
        List<Park> parks = service.getAllParks();
        model.addAttribute("parks", parks);
        
        List<State> states = service.getAllStates();
        model.addAttribute("states", states);
        
        List<Feature> features = service.getAllFeatures();
        model.addAttribute("features",features);
        
        return "update-editor";
    }
    
}
