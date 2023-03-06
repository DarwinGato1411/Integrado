/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Darwin
 */
@Embeddable
public class EvaluacionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_respuesta")
    private int idRespuesta;
    @Basic(optional = false)
    @Column(name = "id_pregunta")
    private int idPregunta;

    public EvaluacionPK() {
    }

    public EvaluacionPK(int idRespuesta, int idPregunta) {
        this.idRespuesta = idRespuesta;
        this.idPregunta = idPregunta;
    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRespuesta;
        hash += (int) idPregunta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionPK)) {
            return false;
        }
        EvaluacionPK other = (EvaluacionPK) object;
        if (this.idRespuesta != other.idRespuesta) {
            return false;
        }
        if (this.idPregunta != other.idPregunta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.EvaluacionPK[ idRespuesta=" + idRespuesta + ", idPregunta=" + idPregunta + " ]";
    }
    
}
