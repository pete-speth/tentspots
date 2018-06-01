/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tentspots.data;

import com.tsguild.tentspots.model.Campsite;
import com.tsguild.tentspots.model.Location;
import com.tsguild.tentspots.model.Park;
import com.tsguild.tentspots.model.State;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author pspethmann
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CampsiteRepositoryTest {
    
    @Autowired
    CampsiteRepository campRepo;
    
    @Autowired
    LocationRepository locRepo;
    
    @Autowired
    ParkRepository parkRepo;
    
    private static Park p;
    private static Location l;
    private static Campsite c;
    
    @Test
    public void testFindAll(){
        
        List<Campsite> campsites = campRepo.findAll();
        assertNotNull(campsites);
        assertTrue(campsites.size() > 0);
    }
    
        
    @Test
    public void testSearch(){
        
        List<Campsite> results = campRepo.search(null, "BWCAW", "MN");
        assertNotNull(results);
        assertTrue(results.size() > 0);
        
    }
    
    @Test
    public void testSave(){
        
        State state = new State();
        state.setAbbr("MN");
        state.setName("Minnesota");
        
        p = new Park();
        p.setName("Awesome Park");
        
        int numParks = parkRepo.findAll().size();
        p = parkRepo.save(p);
        assertTrue(numParks < parkRepo.findAll().size());
        
        l = new Location();
        l.setLatitiude(BigDecimal.ZERO);
        l.setLongitude(BigDecimal.ZERO);
        l.setPark(p);
        l.setState(state);
        
        int numLocations = locRepo.findAll().size();
        l = locRepo.save(l);
        assertTrue(numLocations < locRepo.findAll().size());
        
        
        
        c = new Campsite();
        c.setName("Cool Campsite");
        c.setLocation(l);
        
        int numSites = campRepo.findAll().size();
        c = campRepo.save(c);
        
        assertTrue(numSites < campRepo.findAll().size());
    }
    
    @Test
    public void testDelete(){
        
        int numSites = campRepo.findAll().size();
        campRepo.delete(c);
        assertTrue(numSites > campRepo.findAll().size());
        
        int numLocations = locRepo.findAll().size();
        locRepo.delete(l);
        assertTrue(numLocations > locRepo.findAll().size());
        
        int numParks = parkRepo.findAll().size();
        parkRepo.delete(p);
        assertTrue(numParks > parkRepo.findAll().size());
                
    }
    
    
}
