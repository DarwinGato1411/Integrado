/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "pregunta")
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p")})
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pregunta")
    private Integer idPregunta;
    @Column(name = "pre_nombre")
    private String preNombre;
    @Column(name = "pre_descripcion")
    private String preDescripcion;
    @Column(name = "pre_estado")
    private Boolean preEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pregunta")
    private Collection<Evaluacion> evaluacionCollection;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne
    private Empresa idEmpresa;

    public Pregunta() {
    }

    public Pregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getPreNombre() {
        return preNombre;
    }

    public void setPreNombre(String preNombre) {
        this.preNombre = preNombre;
    }

    public String getPreDescripcion() {
        return preDescripcion;
    }

    public void setPreDescripcion(String preDescripcion) {
        this.preDescripcion = preDescripcion;
    }

    public Boolean getPreEstado() {
        return preEstado;
    }

    public void setPreEstado(Boolean preEstado) {
        this.preEstado = preEstado;
    }

    public Collection<Evaluacion> getEvaluacionCollection() {
        return evaluacionCollection;
    }

    public void setEvaluacionCollection(Collection<Evaluacion> evaluacionCollection) {
        this.evaluacionCollection = evaluacionCollection;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPregunta != null ? idPregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.idPregunta == null && other.idPregunta != null) || (this.idPregunta != null && !this.idPregunta.equals(other.idPregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Pregunta[ idPregunta=" + idPregunta + " ]";
    }
    
}
