/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "candidato")
@NamedQueries({
    @NamedQuery(name = "Candidato.findAll", query = "SELECT c FROM Candidato c")})
public class Candidato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_candidato")
    private Integer idCandidato;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "can_aspiracion_salarial")
    private BigDecimal canAspiracionSalarial;
    @Column(name = "can_edad")
    private Integer canEdad;
    @Column(name = "can_hoja_vida")
    private String canHojaVida;
    @Column(name = "can_descripcion")
    private String canDescripcion;
    @Column(name = "can_telefono")
    private String canTelefono;
    @Column(name = "can_direccion")
    private String canDireccion;
    @OneToMany(mappedBy = "idCandidato")
    private Collection<Evaluacion> evaluacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidato")
    private Collection<CandidatoVacante> candidatoVacanteCollection;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public Candidato() {
    }

    public Candidato(Integer idCandidato) {
        this.idCandidato = idCandidato;
    }

    public Integer getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Integer idCandidato) {
        this.idCandidato = idCandidato;
    }

    public BigDecimal getCanAspiracionSalarial() {
        return canAspiracionSalarial;
    }

    public void setCanAspiracionSalarial(BigDecimal canAspiracionSalarial) {
        this.canAspiracionSalarial = canAspiracionSalarial;
    }

    public Integer getCanEdad() {
        return canEdad;
    }

    public void setCanEdad(Integer canEdad) {
        this.canEdad = canEdad;
    }

    public String getCanHojaVida() {
        return canHojaVida;
    }

    public void setCanHojaVida(String canHojaVida) {
        this.canHojaVida = canHojaVida;
    }

    public String getCanDescripcion() {
        return canDescripcion;
    }

    public void setCanDescripcion(String canDescripcion) {
        this.canDescripcion = canDescripcion;
    }

    public String getCanTelefono() {
        return canTelefono;
    }

    public void setCanTelefono(String canTelefono) {
        this.canTelefono = canTelefono;
    }

    public String getCanDireccion() {
        return canDireccion;
    }

    public void setCanDireccion(String canDireccion) {
        this.canDireccion = canDireccion;
    }

    public Collection<Evaluacion> getEvaluacionCollection() {
        return evaluacionCollection;
    }

    public void setEvaluacionCollection(Collection<Evaluacion> evaluacionCollection) {
        this.evaluacionCollection = evaluacionCollection;
    }

    public Collection<CandidatoVacante> getCandidatoVacanteCollection() {
        return candidatoVacanteCollection;
    }

    public void setCandidatoVacanteCollection(Collection<CandidatoVacante> candidatoVacanteCollection) {
        this.candidatoVacanteCollection = candidatoVacanteCollection;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCandidato != null ? idCandidato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candidato)) {
            return false;
        }
        Candidato other = (Candidato) object;
        if ((this.idCandidato == null && other.idCandidato != null) || (this.idCandidato != null && !this.idCandidato.equals(other.idCandidato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Candidato[ idCandidato=" + idCandidato + " ]";
    }
    
}
