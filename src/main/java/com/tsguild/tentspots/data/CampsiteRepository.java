/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tentspots.data;

import com.tsguild.tentspots.model.Campsite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author pspethmann
 */
public interface CampsiteRepository extends JpaRepository<Campsite,Integer> {
    
    @Query(
            "select c from Campsite c "
                    + "inner join Location l on c.location = l.id "
                    + "inner join Park p on l.park = p.id "
                    + "inner join State s on l.state = s.id "
                    + "where "
                    + "((?1 is null or c.name = ?1) and "
                    + "(?2 is null or p.name = ?2) and "
                    + "(?3 is null or s.abbr = ?3))"
    
    )
    public List<Campsite> search(String campsiteName, String parkName, String stateAbbr);
}
