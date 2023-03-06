/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @EmbeddedId
    protected EvaluacionPK evaluacionPK;
    @Column(name = "eva_fecha")
    @Temporal(TemporalType.DATE)
    private Date evaFecha;
    @Column(name = "eva_afirmativa")
    private Boolean evaAfirmativa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "eva_valor")
    private BigDecimal evaValor;
    @Column(name = "eva_respuesta")
    private String evaRespuesta;
    @JoinColumn(name = "id_candidato", referencedColumnName = "id_candidato")
    @ManyToOne
    private Candidato idCandidato;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pregunta pregunta;
    @JoinColumn(name = "id_respuesta", referencedColumnName = "id_respuesta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Respuesta respuesta;

    public Evaluacion() {
    }

    public Evaluacion(EvaluacionPK evaluacionPK) {
        this.evaluacionPK = evaluacionPK;
    }

    public Evaluacion(int idRespuesta, int idPregunta) {
        this.evaluacionPK = new EvaluacionPK(idRespuesta, idPregunta);
    }

    public EvaluacionPK getEvaluacionPK() {
        return evaluacionPK;
    }

    public void setEvaluacionPK(EvaluacionPK evaluacionPK) {
        this.evaluacionPK = evaluacionPK;
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

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluacionPK != null ? evaluacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluacion)) {
            return false;
        }
        Evaluacion other = (Evaluacion) object;
        if ((this.evaluacionPK == null && other.evaluacionPK != null) || (this.evaluacionPK != null && !this.evaluacionPK.equals(other.evaluacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Evaluacion[ evaluacionPK=" + evaluacionPK + " ]";
    }
    
}
