/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tentspots.service;

import com.tsguild.tentspots.data.CampsiteRepository;
import com.tsguild.tentspots.data.FeatureRepository;
import com.tsguild.tentspots.data.ParkRepository;
import com.tsguild.tentspots.data.StateRepository;
import com.tsguild.tentspots.data.VisitRepository;
import com.tsguild.tentspots.model.Campsite;
import com.tsguild.tentspots.model.Feature;
import com.tsguild.tentspots.model.Park;
import com.tsguild.tentspots.model.State;
import com.tsguild.tentspots.model.Visit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author pspethmann
 */
@Component
public class CampsiteService {

    @Autowired
    CampsiteRepository campRepo;
    @Autowired
    VisitRepository visitRepo;
    @Autowired
    ParkRepository parkRepo;
    @Autowired
    StateRepository stateRepo;
    @Autowired
    FeatureRepository featureRepo;

    public CampsiteService(CampsiteRepository campRepo, VisitRepository visitRepo) {
        this.campRepo = campRepo;
        this.visitRepo = visitRepo;
    }

    public Campsite getCampsite(int id) {

        Optional<Campsite> c = campRepo.findById(id);
        
        if (c.isPresent()) {
            Campsite campsite = c.get();
            int numVists = visitRepo.findByCampsiteId(campsite.getId()).size();
            campsite.setNumVisits(numVists);
            
            return campsite;
        }
        
        return null;
    }

    public List<Campsite> getAllCampsites() {

        List<Campsite> campsites =  campRepo.findAll();
        
        for (Campsite c: campsites){
            int numVists = visitRepo.findByCampsiteId(c.getId()).size();
            c.setNumVisits(numVists);
        }
        
        return campsites;
    }

    public List<Campsite> getCampsitesFromSearch(Campsite campsite) {

        String campsiteName = campsite.getName();
        String parkName = campsite.getLocation().getPark().getName();
        String stateAbbr = campsite.getLocation().getState().getAbbr();

        List<Campsite> campsites =  campRepo.search(campsiteName, parkName, stateAbbr);
        
        for (Campsite c: campsites){
            int numVists = visitRepo.findByCampsiteId(c.getId()).size();
            c.setNumVisits(numVists);
        }
        
        return campsites;
    }

    public List<Visit> getVisitsForCampsite(int campsiteId) {

        List<Visit> visits = visitRepo.findByCampsiteId(campsiteId);
        
        visits = visits.stream()
                .sorted((v1,v2) -> v2.getStartDate()
                        .compareTo(v1.getStartDate()))
                .collect(Collectors.toList());
        
        return visits;
    }

    public List<Park> getAllParks() {

        return parkRepo.findAll();
    }

    public List<State> getAllStates() {

        return stateRepo.findAll();
    }

    public List<Feature> getAllFeatures() {
        return featureRepo.findAll();
    }

    public Campsite save(Campsite c) {

        return campRepo.save(c);
    }

    public Visit save(Visit v) {

        return visitRepo.save(v);
    }

    public void delete(Campsite c) {

        campRepo.delete(c);
    }

    public void delete(Visit v) {

        visitRepo.delete(v);
    }

    private boolean validate(Campsite c) {

        return false;
    }

    private boolean validate(Visit v) {

        return false;
    }

}
