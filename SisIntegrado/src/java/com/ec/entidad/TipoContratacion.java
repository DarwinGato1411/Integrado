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
@Table(name = "tipo_contratacion")
@NamedQueries({
    @NamedQuery(name = "TipoContratacion.findAll", query = "SELECT t FROM TipoContratacion t")})
public class TipoContratacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_contratacion")
    private Integer idTipoContratacion;
    @Column(name = "tipc_nombre")
    private String tipcNombre;
    @Column(name = "tipc_estado")
    private Boolean tipcEstado;
    @OneToMany(mappedBy = "idTipoContratacion")
    private Collection<Vacante> vacanteCollection;

    public TipoContratacion() {
    }

    public TipoContratacion(Integer idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public Integer getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(Integer idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public String getTipcNombre() {
        return tipcNombre;
    }

    public void setTipcNombre(String tipcNombre) {
        this.tipcNombre = tipcNombre;
    }

    public Boolean getTipcEstado() {
        return tipcEstado;
    }

    public void setTipcEstado(Boolean tipcEstado) {
        this.tipcEstado = tipcEstado;
    }

    public Collection<Vacante> getVacanteCollection() {
        return vacanteCollection;
    }

    public void setVacanteCollection(Collection<Vacante> vacanteCollection) {
        this.vacanteCollection = vacanteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoContratacion != null ? idTipoContratacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoContratacion)) {
            return false;
        }
        TipoContratacion other = (TipoContratacion) object;
        if ((this.idTipoContratacion == null && other.idTipoContratacion != null) || (this.idTipoContratacion != null && !this.idTipoContratacion.equals(other.idTipoContratacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.TipoContratacion[ idTipoContratacion=" + idTipoContratacion + " ]";
    }
    
}
