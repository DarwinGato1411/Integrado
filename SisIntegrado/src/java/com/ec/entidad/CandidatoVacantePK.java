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
public class CandidatoVacantePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_vacante")
    private int idVacante;
    @Basic(optional = false)
    @Column(name = "id_candidato")
    private int idCandidato;

    public CandidatoVacantePK() {
    }

    public CandidatoVacantePK(int idVacante, int idCandidato) {
        this.idVacante = idVacante;
        this.idCandidato = idCandidato;
    }

    public int getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(int idVacante) {
        this.idVacante = idVacante;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idVacante;
        hash += (int) idCandidato;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CandidatoVacantePK)) {
            return false;
        }
        CandidatoVacantePK other = (CandidatoVacantePK) object;
        if (this.idVacante != other.idVacante) {
            return false;
        }
        if (this.idCandidato != other.idCandidato) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.CandidatoVacantePK[ idVacante=" + idVacante + ", idCandidato=" + idCandidato + " ]";
    }
    
}
