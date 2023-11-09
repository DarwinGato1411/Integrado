/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "evaluacion")
@NamedQueries({
    @NamedQuery(name = "Evaluacion.findAll", query = "SELECT e FROM Evaluacion e")})
public class Evaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_evaluacion")
    private Integer idEvaluacion;
    @Column(name = "eva_fecha")
    @Temporal(TemporalType.DATE)
    private Date evaFecha;
    @Column(name = "eva_caduca")
    @Temporal(TemporalType.DATE)
    private Date evaCaduca;
    @Column(name = "eva_afirmativa")
    private Boolean evaAfirmativa;
    @Column(name = "eva_respuesta_bool")
    private Boolean evaRespuestaBool;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "eva_valor")
    private BigDecimal evaValor;
    @Column(name = "eva_respuesta")
    private String evaRespuesta;
    @Column(name = "eva_version")
    private String evaVersion;
    @Column(name = "version_test")
    private Integer versionTest;
    @JoinColumn(name = "id_candidato", referencedColumnName = "id_candidato")
    @ManyToOne
    private Candidato idCandidato;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")
    @ManyToOne(optional = false)
    private Pregunta idPregunta;
    @JoinColumn(name = "id_respuesta", referencedColumnName = "id_respuesta")
    @ManyToOne(optional = false)
    private Respuesta idRespuesta;

    public Evaluacion() {
    }

    public Evaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Integer getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Date getEvaFecha() {
        return evaFecha;
    }

    public void setEvaFecha(Date evaFecha) {
        this.evaFecha = evaFecha;
    }

    public Boolean getEvaAfirmativa() {
        return evaAfirmativa;
    }

    public void setEvaAfirmativa(Boolean evaAfirmativa) {
        this.evaAfirmativa = evaAfirmativa;
    }

    public BigDecimal getEvaValor() {
        return evaValor;
    }

    public void setEvaValor(BigDecimal evaValor) {
        this.evaValor = evaValor;
    }

    public String getEvaRespuesta() {
        return evaRespuesta;
    }

    public void setEvaRespuesta(String evaRespuesta) {
        this.evaRespuesta = evaRespuesta;
    }

    public Candidato getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Candidato idCandidato) {
        this.idCandidato = idCandidato;
    }

    public Pregunta getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Pregunta idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Respuesta getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(Respuesta idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public Date getEvaCaduca() {
        return evaCaduca;
    }

    public void setEvaCaduca(Date evaCaduca) {
        this.evaCaduca = evaCaduca;
    }

    public Boolean getEvaRespuestaBool() {
        return evaRespuestaBool;
    }

    public void setEvaRespuestaBool(Boolean evaRespuestaBool) {
        this.evaRespuestaBool = evaRespuestaBool;
    }

    public String getEvaVersion() {
        return evaVersion;
    }

    public void setEvaVersion(String evaVersion) {
        this.evaVersion = evaVersion;
    }

    public Integer getVersionTest() {
        return versionTest;
    }

    public void setVersionTest(Integer versionTest) {
        this.versionTest = versionTest;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvaluacion != null ? idEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluacion)) {
            return false;
        }
        Evaluacion other = (Evaluacion) object;
        if ((this.idEvaluacion == null && other.idEvaluacion != null) || (this.idEvaluacion != null && !this.idEvaluacion.equals(other.idEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Evaluacion[ idEvaluacion=" + idEvaluacion + " ]";
    }
    
}
