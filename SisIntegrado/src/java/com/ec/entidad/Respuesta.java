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
@Table(name = "respuesta")
@NamedQueries({
    @NamedQuery(name = "Respuesta.findAll", query = "SELECT r FROM Respuesta r")})
public class Respuesta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_respuesta")
    private Integer idRespuesta;
    @Column(name = "res_descripcion")
    private String resDescripcion;
    @Column(name = "res_estado")
    private Boolean resEstado;
    @Column(name = "res_correcta")
    private Boolean resCorrecta;
    @Column(name = "res_opcion_multiple")
    private Boolean resOpcionMultiple;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRespuesta")
    private Collection<Evaluacion> evaluacionCollection;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")
    @ManyToOne
    private Pregunta idPregunta;

    public Respuesta() {
    }

    public Respuesta(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public Integer getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public String getResDescripcion() {
        return resDescripcion;
    }

    public void setResDescripcion(String resDescripcion) {
        this.resDescripcion = resDescripcion;
    }

    public Boolean getResEstado() {
        return resEstado;
    }

    public void setResEstado(Boolean resEstado) {
        this.resEstado = resEstado;
    }

    public Boolean getResCorrecta() {
        return resCorrecta;
    }

    public void setResCorrecta(Boolean resCorrecta) {
        this.resCorrecta = resCorrecta;
    }

    public Boolean getResOpcionMultiple() {
        return resOpcionMultiple;
    }

    public void setResOpcionMultiple(Boolean resOpcionMultiple) {
        this.resOpcionMultiple = resOpcionMultiple;
    }

    public Collection<Evaluacion> getEvaluacionCollection() {
        return evaluacionCollection;
    }

    public void setEvaluacionCollection(Collection<Evaluacion> evaluacionCollection) {
        this.evaluacionCollection = evaluacionCollection;
    }

    public Pregunta getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Pregunta idPregunta) {
        this.idPregunta = idPregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuesta != null ? idRespuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuesta)) {
            return false;
        }
        Respuesta other = (Respuesta) object;
        if ((this.idRespuesta == null && other.idRespuesta != null) || (this.idRespuesta != null && !this.idRespuesta.equals(other.idRespuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Respuesta[ idRespuesta=" + idRespuesta + " ]";
    }
    
}
