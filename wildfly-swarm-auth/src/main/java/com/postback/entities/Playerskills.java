/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postback.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Omer
 */
@Entity
@Table(name = "playerskills")
@NamedQueries({
    @NamedQuery(name = "Playerskills.findAll", query = "SELECT p FROM Playerskills p")})
public class Playerskills implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "passing")
    private double passing;
    @Basic(optional = false)
    @NotNull
    @Column(name = "teamwork")
    private double teamwork;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ballbontrol")
    private double ballbontrol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "throwIn")
    private double throwIn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dribbling")
    private double dribbling;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crossing")
    private double crossing;
    @Basic(optional = false)
    @NotNull
    @Column(name = "zonalmarking")
    private double zonalmarking;
    @Basic(optional = false)
    @NotNull
    @Column(name = "manmarking")
    private double manmarking;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rushingout")
    private double rushingout;
    @Basic(optional = false)
    @NotNull
    @Column(name = "handling")
    private double handling;
    @Basic(optional = false)
    @NotNull
    @Column(name = "shooting")
    private double shooting;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pace")
    private double pace;
    @Basic(optional = false)
    @NotNull
    @Column(name = "heading")
    private double heading;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rightfoot")
    private double rightfoot;
    @Basic(optional = false)
    @NotNull
    @Column(name = "leftfoot")
    private double leftfoot;
    @JoinColumn(name = "player", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Player player;

    public Playerskills() {
    }

    public Playerskills(Long id) {
        this.id = id;
    }

    public Playerskills(Long id, double passing, double teamwork, double ballbontrol, double throwIn, double dribbling, double crossing, double zonalmarking, double manmarking, double rushingout, double handling, double shooting, double pace, double heading, double rightfoot, double leftfoot) {
        this.id = id;
        this.passing = passing;
        this.teamwork = teamwork;
        this.ballbontrol = ballbontrol;
        this.throwIn = throwIn;
        this.dribbling = dribbling;
        this.crossing = crossing;
        this.zonalmarking = zonalmarking;
        this.manmarking = manmarking;
        this.rushingout = rushingout;
        this.handling = handling;
        this.shooting = shooting;
        this.pace = pace;
        this.heading = heading;
        this.rightfoot = rightfoot;
        this.leftfoot = leftfoot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPassing() {
        return passing;
    }

    public void setPassing(double passing) {
        this.passing = passing;
    }

    public double getTeamwork() {
        return teamwork;
    }

    public void setTeamwork(double teamwork) {
        this.teamwork = teamwork;
    }

    public double getBallbontrol() {
        return ballbontrol;
    }

    public void setBallbontrol(double ballbontrol) {
        this.ballbontrol = ballbontrol;
    }

    public double getThrowIn() {
        return throwIn;
    }

    public void setThrowIn(double throwIn) {
        this.throwIn = throwIn;
    }

    public double getDribbling() {
        return dribbling;
    }

    public void setDribbling(double dribbling) {
        this.dribbling = dribbling;
    }

    public double getCrossing() {
        return crossing;
    }

    public void setCrossing(double crossing) {
        this.crossing = crossing;
    }

    public double getZonalmarking() {
        return zonalmarking;
    }

    public void setZonalmarking(double zonalmarking) {
        this.zonalmarking = zonalmarking;
    }

    public double getManmarking() {
        return manmarking;
    }

    public void setManmarking(double manmarking) {
        this.manmarking = manmarking;
    }

    public double getRushingout() {
        return rushingout;
    }

    public void setRushingout(double rushingout) {
        this.rushingout = rushingout;
    }

    public double getHandling() {
        return handling;
    }

    public void setHandling(double handling) {
        this.handling = handling;
    }

    public double getShooting() {
        return shooting;
    }

    public void setShooting(double shooting) {
        this.shooting = shooting;
    }

    public double getPace() {
        return pace;
    }

    public void setPace(double pace) {
        this.pace = pace;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public double getRightfoot() {
        return rightfoot;
    }

    public void setRightfoot(double rightfoot) {
        this.rightfoot = rightfoot;
    }

    public double getLeftfoot() {
        return leftfoot;
    }

    public void setLeftfoot(double leftfoot) {
        this.leftfoot = leftfoot;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
        if (!(object instanceof Playerskills)) {
            return false;
        }
        Playerskills other = (Playerskills) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.postback.entities.Playerskills[ id=" + id + " ]";
    }
    
}
