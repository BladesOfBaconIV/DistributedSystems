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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "JOBS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobs.findAll", query = "SELECT j FROM Jobs j")
    , @NamedQuery(name = "Jobs.findById", query = "SELECT j FROM Jobs j WHERE j.id = :id")
    , @NamedQuery(name = "Jobs.findByDesciption", query = "SELECT j FROM Jobs j WHERE j.desciption = :desciption")
    , @NamedQuery(name = "Jobs.findByProvider", query = "SELECT j FROM Jobs j WHERE j.creator = :provider_id")
})
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCIPTION")
    private String desciption;
    @JoinTable(name = "JOB_BIDS", joinColumns = {
        @JoinColumn(name = "JOB_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "FREELANCER", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Freelancer> freelancersCollection;
    @ManyToMany(mappedBy = "jobsCollection")
    private Collection<Keyword> keywordsCollection;
    @JoinColumn(name = "ASSIGNED_TO", referencedColumnName = "ID")
    @ManyToOne
    private Freelancer assignedTo;
    @JoinColumn(name = "CREATOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Provider creator;

    public Job() {
    }

    public Job(Integer id) {
        this.id = id;
    }

    public Job(Integer id, String desciption) {
        this.id = id;
        this.desciption = desciption;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    @XmlTransient
    public Collection<Freelancer> getFreelancersCollection() {
        return freelancersCollection;
    }

    public void setFreelancersCollection(Collection<Freelancer> freelancersCollection) {
        this.freelancersCollection = freelancersCollection;
    }

    @XmlTransient
    public Collection<Keyword> getKeywordsCollection() {
        return keywordsCollection;
    }

    public void setKeywordsCollection(Collection<Keyword> keywordsCollection) {
        this.keywordsCollection = keywordsCollection;
    }

    public Freelancer getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Freelancer assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Provider getCreator() {
        return creator;
    }

    public void setCreator(Provider creator) {
        this.creator = creator;
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
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Jobs[ id=" + id + " ]";
    }
    
}
