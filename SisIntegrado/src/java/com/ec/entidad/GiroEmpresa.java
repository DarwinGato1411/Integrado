/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "giro_empresa")
@NamedQueries({
    @NamedQuery(name = "GiroEmpresa.findAll", query = "SELECT g FROM GiroEmpresa g")})
public class GiroEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_giro_empresa")
    private Integer idGiroEmpresa;
    @Column(name = "gir_nombre")
    private String girNombre;
    @Column(name = "gir_estado")
    private Boolean girEstado;
    @OneToMany(mappedBy = "idGiroEmpresa")
    private Collection<Empresa> empresaCollection;

    public GiroEmpresa() {
    }

    public GiroEmpresa(Integer idGiroEmpresa) {
        this.idGiroEmpresa = idGiroEmpresa;
    }

    public Integer getIdGiroEmpresa() {
        return idGiroEmpresa;
    }

    public void setIdGiroEmpresa(Integer idGiroEmpresa) {
        this.idGiroEmpresa = idGiroEmpresa;
    }

    public String getGirNombre() {
        return girNombre;
    }

    public void setGirNombre(String girNombre) {
        this.girNombre = girNombre;
    }

    public Boolean getGirEstado() {
        return girEstado;
    }

    public void setGirEstado(Boolean girEstado) {
        this.girEstado = girEstado;
    }

    public Collection<Empresa> getEmpresaCollection() {
        return empresaCollection;
    }

    public void setEmpresaCollection(Collection<Empresa> empresaCollection) {
        this.empresaCollection = empresaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGiroEmpresa != null ? idGiroEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GiroEmpresa)) {
            return false;
        }
        GiroEmpresa other = (GiroEmpresa) object;
        if ((this.idGiroEmpresa == null && other.idGiroEmpresa != null) || (this.idGiroEmpresa != null && !this.idGiroEmpresa.equals(other.idGiroEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.GiroEmpresa[ idGiroEmpresa=" + idGiroEmpresa + " ]";
    }
    
}
