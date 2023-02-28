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
@Table(name = "variedad")
@NamedQueries({
    @NamedQuery(name = "Variedad.findAll", query = "SELECT v FROM Variedad v")})
public class Variedad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_variedad")
    private Integer idVariedad;
    @Column(name = "var_nombre")
    private String varNombre;
    @Column(name = "var_descripcion")
    private String varDescripcion;
    @Column(name = "var_tamano")
    private Integer varTamano;
    @Column(name = "var_tipo")
    private String varTipo;
    @OneToMany(mappedBy = "idVariedad")
    private Collection<Flor> florCollection;

    public Variedad() {
    }

    public Variedad(Integer idVariedad) {
        this.idVariedad = idVariedad;
    }

    public Integer getIdVariedad() {
        return idVariedad;
    }

    public void setIdVariedad(Integer idVariedad) {
        this.idVariedad = idVariedad;
    }

    public String getVarNombre() {
        return varNombre;
    }

    public void setVarNombre(String varNombre) {
        this.varNombre = varNombre;
    }

    public String getVarDescripcion() {
        return varDescripcion;
    }

    public void setVarDescripcion(String varDescripcion) {
        this.varDescripcion = varDescripcion;
    }

    public Integer getVarTamano() {
        return varTamano;
    }

    public void setVarTamano(Integer varTamano) {
        this.varTamano = varTamano;
    }

    public String getVarTipo() {
        return varTipo;
    }

    public void setVarTipo(String varTipo) {
        this.varTipo = varTipo;
    }

    public Collection<Flor> getFlorCollection() {
        return florCollection;
    }

    public void setFlorCollection(Collection<Flor> florCollection) {
        this.florCollection = florCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVariedad != null ? idVariedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Variedad)) {
            return false;
        }
        Variedad other = (Variedad) object;
        if ((this.idVariedad == null && other.idVariedad != null) || (this.idVariedad != null && !this.idVariedad.equals(other.idVariedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Variedad[ idVariedad=" + idVariedad + " ]";
    }
    
}
