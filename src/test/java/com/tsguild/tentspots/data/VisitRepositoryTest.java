/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tentspots.data;

import com.tsguild.tentspots.model.Visit;
import java.time.LocalDate;
import java.util.List;
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
public class VisitRepositoryTest {
    
    @Autowired
    VisitRepository visitRepo;
    
    @Autowired
    CampsiteRepository campRepo;
    
    private static Visit v;
    
    @Test
    public void testFindAll(){
        
        List<Visit> visits = visitRepo.findAll();
        assertTrue(visits.size() > 0);
    }
    
    @Test
    public void testSave(){
        
        v = new Visit();
        v.setGroupLeader("Peter Spethmann");
        v.setStartDate(LocalDate.of(2000, 1, 1));
        v.setEndDate(LocalDate.of(2001, 1, 1));
        v.setGroupSize(4);
        v.setCampsite(campRepo.getOne(1));
        
        int numVisits = visitRepo.findAll().size();
        visitRepo.save(v);
        assertTrue(numVisits < visitRepo.findAll().size());
    }
    
    @Test
    public void testDelete(){
        
        int numVisits = visitRepo.findAll().size();
        visitRepo.delete(v);
        assertTrue(numVisits > visitRepo.findAll().size());
    }
    
}
