/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tentspots.controllers;

import com.tsguild.tentspots.model.Campsite;
import com.tsguild.tentspots.model.Feature;
import com.tsguild.tentspots.model.Location;
import com.tsguild.tentspots.model.Park;
import com.tsguild.tentspots.model.State;
import com.tsguild.tentspots.model.Visit;
import com.tsguild.tentspots.service.CampsiteService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author pspethmann
 */
@Controller
public class FormsController {

    @Autowired
    CampsiteService service;

    @GetMapping("/explore/campsite/{cId}/log/editor/{vId}")
    public String exploreCampsiteAddLog(@PathVariable int cId, @PathVariable int vId, Model model) {

        Campsite campsite = service.getCampsite(cId);
        model.addAttribute("campsite", campsite);

        Visit visit = service.getVisit(vId);
        if (visit == null) {
            visit = new Visit();
        }
        
        model.addAttribute("visit", visit);

        return "explore-campsite-addlog";
    }

    @PostMapping("/explore/campsite/{cId}/log/editor/{vId}")
    public String postVisitLog(@Valid Visit visit, BindingResult result,
            @PathVariable int cId, @PathVariable int vId, Model model) {

        visit.setId(vId);
        
        if (result.hasErrors() || service.save(visit) == null) {

            Campsite campsite = service.getCampsite(cId);
            
            model.addAttribute("visit", visit);
            model.addAttribute("campsite", campsite);

            return "explore-campsite-addlog";
        }
        

        return "redirect:/explore/campsite/" + cId +"/log";
    }

    
    @GetMapping("/update/editor/{id}")
    public String editCampsite(Model model, @PathVariable int id) {

        Campsite campsite = service.getCampsite(id);
        if (campsite == null){
            campsite = new Campsite();
            
            Location location = new Location();
            model.addAttribute("location", location);
            campsite.setLocation(location);
        }
        
        model.addAttribute("campsite", campsite);
        model.addAttribute("location", campsite.getLocation());

        List<Park> parks = service.getAllParks();
        model.addAttribute("parks", parks);
        
        model.addAttribute("p", new Park());

        List<State> states = service.getAllStates();
        model.addAttribute("states", states);

        List<Feature> features = service.getAllFeatures();
        model.addAttribute("features", features);

        return "update-editor";
    }

    @PostMapping("/update/editor/{id}")
    public String postCampsite(@Valid Campsite campsite, BindingResult resultC,
            @Valid Location location, BindingResult resultL, String parkName,
            String governingOrg, String[] featureIds, boolean isNewPark, 
            @PathVariable int id, Model model) {

        if (id > 0) {           
            Campsite old = service.getCampsite(id);
            location.setId(old.getLocation().getId());
            
            campsite.setId(id);
        }
        
        
        List<Feature> features = new ArrayList<>();
        if (featureIds != null) {
            for (String f : featureIds) {

                int fid = Integer.parseInt(f);
                features.add(service.getFeature(fid));
            }
        }
        
        boolean validPark = true;
        Park p = new Park();
        
        if (isNewPark) {

            validPark = false;
                      
            p.setName(parkName);
            p.setGoverningOrg(governingOrg);
            
            List<Park> parks = service.getAllParks();
            for (Park oldPark: parks) {
                if (oldPark.getName().equalsIgnoreCase(p.getName())){
                    p = oldPark;
                    validPark = true;
                    break;
                }
            }
            
            if (!validPark && service.save(p) != null){    
                validPark = true;
            }
            
            location.setPark(p);
        }

        campsite.setLocation(location);
        campsite.setFeatures(features);

        if (resultC.hasErrors() || resultL.hasErrors() || !validPark
                || service.save(location) == null
                || service.save(campsite) == null) {

            model.addAttribute("campsite", campsite);
            model.addAttribute("location", location);

            List<Park> parks = service.getAllParks();
            model.addAttribute("parks", parks);
            
            model.addAttribute("p", p);
            
            model.addAttribute("parkName", parkName);
            model.addAttribute("governingOrg", governingOrg);

            List<State> states = service.getAllStates();
            model.addAttribute("states", states);

            List<Feature> allFeatures = service.getAllFeatures();
            model.addAttribute("features", allFeatures);
            
            return "update-editor";
        }

        return "redirect:/update";
    }
}
