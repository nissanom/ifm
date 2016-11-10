package com.ifm.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author Omer
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

//    private Integer status;
//    private Date registerDate;
//    private Date lastLoginDate;
//    private BigInteger profileImageId;
//    private String profileUrl;
//    private String avatar;
    public UserDTO()
    {
    }

    public UserDTO( Long id )
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

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
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
        if ( !(object instanceof UserDTO) )
        {
            return false;
        }
        UserDTO other = (UserDTO) object;
        if ( (this.id == null && other.id != null) || (this.id != null && !this.id.equals( other.id )) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.ifm.dto.UserDTO[ id=" + id + " ]";
    }

}
