/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postback.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Omer
 */
@Entity
@Table(name = "user")
@NamedQueries(
{
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
})
public class User implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 100)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 100)
    @Column(name = "lastname")
    private String lastname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 50)
    @Column(name = "passwd")
    private String passwd;
    @Column(name = "status")
    private Integer status;
    @Column(name = "register_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
    @Column(name = "last_login_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;
    @Size(max = 255)
    @Column(name = "profile_url")
    private String profileUrl;
    @Lob
    @Column(name = "avatar")
    private byte[] avatar;
    @Column(name = "teamId")
    private Long teamId;

    public User()
    {
    }

    public User( Long id )
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname( String firstname )
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname( String lastname )
    {
        this.lastname = lastname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getPasswd()
    {
        return passwd;
    }

    public void setPasswd( String passwd )
    {
        this.passwd = passwd;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus( Integer status )
    {
        this.status = status;
    }

    public Date getRegisterDate()
    {
        return registerDate;
    }

    public void setRegisterDate( Date registerDate )
    {
        this.registerDate = registerDate;
    }

    public Date getLastLoginDate()
    {
        return lastLoginDate;
    }

    public void setLastLoginDate( Date lastLoginDate )
    {
        this.lastLoginDate = lastLoginDate;
    }

    public String getProfileUrl()
    {
        return profileUrl;
    }

    public void setProfileUrl( String profileUrl )
    {
        this.profileUrl = profileUrl;
    }

    public byte[] getAvatar()
    {
        return avatar;
    }

    public void setAvatar( byte[] avatar )
    {
        this.avatar = avatar;
    }

    public Long getTeamId()
    {
        return teamId;
    }

    public void setTeamId( Long teamId )
    {
        this.teamId = teamId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !(object instanceof User) )
        {
            return false;
        }
        User other = (User) object;
        if ( (this.id == null && other.id != null) || (this.id != null && !this.id.equals( other.id )) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.postback.entities.User[ id=" + id + " ]";
    }
    
}
