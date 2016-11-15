/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postback.dto;

import java.io.Serializable;

/**
 *
 * @author Omer
 */

public class TeamDTO implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Long id;
   
    private String name;
    private byte[] logo;
    
  

    public TeamDTO() {
    }

    public TeamDTO(Long id) {
        this.id = id;
    }

    public TeamDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getLogo()
    {
        return logo;
    }

    public void setLogo( byte[] logo )
    {
        this.logo = logo;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TeamDTO)) {
            return false;
        }
        TeamDTO other = (TeamDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ifm.dto.TeamDTO[ id=" + id + " ]";
    }
    
}
