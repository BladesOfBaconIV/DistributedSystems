/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "FREELANCERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Freelancers.findAll", query = "SELECT f FROM Freelancers f")
    , @NamedQuery(name = "Freelancers.findById", query = "SELECT f FROM Freelancers f WHERE f.id = :id")
    , @NamedQuery(name = "Freelancers.findByDescription", query = "SELECT f FROM Freelancers f WHERE f.description = :description")
    , @NamedQuery(name = "Freelancers.findByTokens", query = "SELECT f FROM Freelancers f WHERE f.tokens = :tokens")})

public class Freelancer extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 500)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOKENS")
    private int tokens;
    @JoinTable(name = "SKILL_SETS", joinColumns = {
        @JoinColumn(name = "USER_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "SKILL_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Skill> skillsCollection;
    @ManyToMany(mappedBy = "freelancersCollection")
    private Collection<Job> jobsCollection;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User users;
    @OneToMany(mappedBy = "assignedTo")
    private Collection<Job> jobsCollection1;

    public Freelancer() {
    }

    public Freelancer(Integer id) {
        this.id = id;
    }

    public Freelancer(Integer id, int tokens) {
        this.id = id;
        this.tokens = tokens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    @XmlTransient
    public Collection<Skill> getSkillsCollection() {
        return skillsCollection;
    }

    public void setSkillsCollection(Collection<Skill> skillsCollection) {
        this.skillsCollection = skillsCollection;
    }

    @XmlTransient
    public Collection<Job> getJobsCollection() {
        return jobsCollection;
    }

    public void setJobsCollection(Collection<Job> jobsCollection) {
        this.jobsCollection = jobsCollection;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    @XmlTransient
    public Collection<Job> getJobsCollection1() {
        return jobsCollection1;
    }

    public void setJobsCollection1(Collection<Job> jobsCollection1) {
        this.jobsCollection1 = jobsCollection1;
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
        if (!(object instanceof Freelancer)) {
            return false;
        }
        Freelancer other = (Freelancer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Freelancers[ id=" + id + " ]";
    }
    
}
