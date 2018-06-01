/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tentspots.service;

import com.tsguild.tentspots.data.CampsiteRepository;
import com.tsguild.tentspots.data.VisitRepository;
import com.tsguild.tentspots.model.Campsite;
import com.tsguild.tentspots.model.Location;
import com.tsguild.tentspots.model.Park;
import com.tsguild.tentspots.model.State;
import com.tsguild.tentspots.model.Visit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author pspethmann
 */
public class CampsiteServiceTest {

    CampsiteRepository campRepo = mock(CampsiteRepository.class);
    VisitRepository visitRepo = mock(VisitRepository.class);
    CampsiteService service = new CampsiteService(campRepo, visitRepo);
    
    private static List<Campsite> campsites;
    private static List<Visit> visits;
    
    @BeforeClass
    public static void setUpClass() {
        
        campsites = new ArrayList<>();
        visits = new ArrayList<>();

        Campsite c1 = new Campsite();
        c1.setName("Camp 1");
        Campsite c2 = new Campsite();
        c2.setName("Camp 2");
        
        campsites.add(c1);
        campsites.add(c2);
        
        Visit v1 = new Visit();
        Visit v2 = new Visit();
        
        visits.add(v1);
        visits.add(v2);
    }

    @Test
    public void testGetCampsite() {
        
        when(campRepo.findById(1)).thenReturn(Optional.of(campsites.get(0)));
        Campsite c = service.getCampsite(1);

        assertTrue(c != null);
        assertTrue(c.getName().equals("Camp 1"));
    }

    @Test
    public void testGetAllCampsites() {
        
        when(campRepo.findAll()).thenReturn(campsites);
        List<Campsite> campsites = service.getAllCampsites();

        assertNotNull(campsites);
        assertTrue(campsites.size() == 2);

    }

    @Test
    public void testGetCampsitesFromSearch() {

        when(campRepo.search(null, null, null)).thenReturn(campsites);
        
        Campsite c = new Campsite();
        Location l = new Location();
        l.setPark(new Park());
        l.setState(new State());
        
        c.setLocation(l);
        
        List<Campsite> results = service.getCampsitesFromSearch(c);

        assertNotNull(results);
        assertTrue(results.size() > 0);

    }

    @Test
    public void testGetVisitsForCampsite() {

        when(visitRepo.findByCampsiteId(1)).thenReturn(visits);
        
        List<Visit> visits = service.getVisitsForCampsite(1);

        assertNotNull(visits);
        assertTrue(visits.size() > 0);
    }

    @Test
    public void testSave_Campsite() {
        
        Campsite toAdd = new Campsite();
        when(campRepo.save(toAdd)).thenReturn(toAdd);
        
        Campsite added = service.save(toAdd);
        
        assertNotNull(added);
        assertEquals(added, toAdd);
    }

    @Test
    public void testSave_Visit() {
        
        Visit toAdd = new Visit();
        when(visitRepo.save(toAdd)).thenReturn(toAdd);
        
        Visit added = service.save(toAdd);
        
        assertNotNull(added);
        assertEquals(added, toAdd);
    }

    @Test
    public void testDelete_Campsite() {
        
    }

    @Test
    public void testDelete_Visit() {
    }

}
