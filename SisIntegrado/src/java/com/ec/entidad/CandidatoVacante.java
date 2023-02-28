/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
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
@Table(name = "candidato_vacante")
@NamedQueries({
    @NamedQuery(name = "CandidatoVacante.findAll", query = "SELECT c FROM CandidatoVacante c")})
public class CandidatoVacante implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CandidatoVacantePK candidatoVacantePK;
    @Column(name = "canv_fecha_postula")
    @Temporal(TemporalType.DATE)
    private Date canvFechaPostula;
    @Column(name = "canv_descripcion")
    private String canvDescripcion;
    @Column(name = "canv_notifica")
    private Boolean canvNotifica;
    @Column(name = "canv_estado")
    private Boolean canvEstado;
    @JoinColumn(name = "id_candidato", referencedColumnName = "id_candidato", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Candidato candidato;
    @JoinColumn(name = "id_vacante", referencedColumnName = "id_vacante", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vacante vacante;

    public CandidatoVacante() {
    }

    public CandidatoVacante(CandidatoVacantePK candidatoVacantePK) {
        this.candidatoVacantePK = candidatoVacantePK;
    }

    public CandidatoVacante(int idVacante, int idCandidato) {
        this.candidatoVacantePK = new CandidatoVacantePK(idVacante, idCandidato);
    }

    public CandidatoVacantePK getCandidatoVacantePK() {
        return candidatoVacantePK;
    }

    public void setCandidatoVacantePK(CandidatoVacantePK candidatoVacantePK) {
        this.candidatoVacantePK = candidatoVacantePK;
    }

    public Date getCanvFechaPostula() {
        return canvFechaPostula;
    }

    public void setCanvFechaPostula(Date canvFechaPostula) {
        this.canvFechaPostula = canvFechaPostula;
    }

    public String getCanvDescripcion() {
        return canvDescripcion;
    }

    public void setCanvDescripcion(String canvDescripcion) {
        this.canvDescripcion = canvDescripcion;
    }

    public Boolean getCanvNotifica() {
        return canvNotifica;
    }

    public void setCanvNotifica(Boolean canvNotifica) {
        this.canvNotifica = canvNotifica;
    }

    public Boolean getCanvEstado() {
        return canvEstado;
    }

    public void setCanvEstado(Boolean canvEstado) {
        this.canvEstado = canvEstado;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (candidatoVacantePK != null ? candidatoVacantePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CandidatoVacante)) {
            return false;
        }
        CandidatoVacante other = (CandidatoVacante) object;
        if ((this.candidatoVacantePK == null && other.candidatoVacantePK != null) || (this.candidatoVacantePK != null && !this.candidatoVacantePK.equals(other.candidatoVacantePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.CandidatoVacante[ candidatoVacantePK=" + candidatoVacantePK + " ]";
    }
    
}
