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
@Table(name = "KEYWORDS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Keywords.findAll", query = "SELECT k FROM Keywords k")
    , @NamedQuery(name = "Keywords.findById", query = "SELECT k FROM Keywords k WHERE k.id = :id")
    , @NamedQuery(name = "Keywords.findByKeyword", query = "SELECT k FROM Keywords k WHERE k.keyword = :keyword")})
public class Keyword implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "KEYWORD")
    private String keyword;
    @JoinTable(name = "JOB_KEYWORDS", joinColumns = {
        @JoinColumn(name = "KEY_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "JOB_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Job> jobsCollection;

    public Keyword() {
    }

    public Keyword(Integer id) {
        this.id = id;
    }

    public Keyword(Integer id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @XmlTransient
    public Collection<Job> getJobsCollection() {
        return jobsCollection;
    }

    public void setJobsCollection(Collection<Job> jobsCollection) {
        this.jobsCollection = jobsCollection;
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
        if (!(object instanceof Keyword)) {
            return false;
        }
        Keyword other = (Keyword) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Keywords[ id=" + id + " ]";
    }
    
}
