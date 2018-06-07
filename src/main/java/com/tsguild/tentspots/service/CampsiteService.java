/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tentspots.service;

import com.tsguild.tentspots.data.CampsiteRepository;
import com.tsguild.tentspots.data.FeatureRepository;
import com.tsguild.tentspots.data.LocationRepository;
import com.tsguild.tentspots.data.ParkRepository;
import com.tsguild.tentspots.data.StateRepository;
import com.tsguild.tentspots.data.VisitRepository;
import com.tsguild.tentspots.model.Campsite;
import com.tsguild.tentspots.model.Feature;
import com.tsguild.tentspots.model.Location;
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
    LocationRepository locationRepo;
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

        List<Campsite> campsites = campRepo.findAll();

        for (Campsite c : campsites) {
            int numVists = visitRepo.findByCampsiteId(c.getId()).size();
            c.setNumVisits(numVists);
        }
        
        campsites = campsites.stream()
                .sorted((c1,c2) -> -Integer.compare(c1.getId(), c2.getId()))
                .collect(Collectors.toList());

        return campsites;
    }

    public List<Campsite> getCampsitesFromSearch(String campsiteName, String parkName, String stateAbbr) {

        List<Campsite> campsites = campRepo.search(campsiteName, parkName, stateAbbr);

        for (Campsite c : campsites) {
            int numVists = visitRepo.findByCampsiteId(c.getId()).size();
            c.setNumVisits(numVists);
        }

        return campsites;
    }

    public List<Visit> getVisitsForCampsite(int campsiteId) {

        List<Visit> visits = visitRepo.findByCampsiteId(campsiteId);

        if (visits.size() > 1) {
            visits = visits.stream()
                    .sorted((v1, v2) -> v2.getStartDate()
                    .compareTo(v1.getStartDate()))
                    .collect(Collectors.toList());
        }

        return visits;
    }
    
    public Visit getVisit(int visitId) {
        
        Optional<Visit> o = visitRepo.findById(visitId);
        
        if (o.isPresent()){
            return o.get();
        }
        
        return null;
    }
    
    public List<Location> getAllLocations() {
        return locationRepo.findAll();
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
    
    public Feature getFeature(int id){
        
        Optional<Feature> o = featureRepo.findById(id);
        
        if (o.isPresent()){
            return o.get();
        }
        
        return null;
    }

    public Campsite save(Campsite c) {

        return campRepo.save(c);
    }

    public Visit save(Visit v) {

        return visitRepo.save(v);
    }
    
    public Location save(Location l){
        
        return locationRepo.save(l);
    }
    
    public Park save(Park p){
        return parkRepo.save(p);
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
