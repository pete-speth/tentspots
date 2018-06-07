/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tentspots.data;

import com.tsguild.tentspots.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pspethmann
 */
@Repository
public interface LoginRepository extends JpaRepository<Login, String> {
    
}
