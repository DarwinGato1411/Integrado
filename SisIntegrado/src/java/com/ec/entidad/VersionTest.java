/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "version_test")
@NamedQueries({
    @NamedQuery(name = "VersionTest.findAll", query = "SELECT v FROM VersionTest v")})
public class VersionTest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_version")
    private Integer idVersion;
    @Column(name = "ver_nombre")
    private String verNombre;

    public VersionTest() {
    }

    public VersionTest(Integer idVersion) {
        this.idVersion = idVersion;
    }

    public Integer getIdVersion() {
        return idVersion;
    }

    public void setIdVersion(Integer idVersion) {
        this.idVersion = idVersion;
    }

    public String getVerNombre() {
        return verNombre;
    }

    public void setVerNombre(String verNombre) {
        this.verNombre = verNombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVersion != null ? idVersion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VersionTest)) {
            return false;
        }
        VersionTest other = (VersionTest) object;
        if ((this.idVersion == null && other.idVersion != null) || (this.idVersion != null && !this.idVersion.equals(other.idVersion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.VersionTest[ idVersion=" + idVersion + " ]";
    }
    
}
