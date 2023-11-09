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
@Table(name = "empresa")
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private Integer idEmpresa;
    @Column(name = "emp_nombre")
    private String empNombre;
    @Column(name = "emp_direccion")
    private String empDireccion;
    @Column(name = "emp_telefono")
    private String empTelefono;
    @Column(name = "emp_correo")
    private String empCorreo;
    @Column(name = "emp_estado")
    private Boolean empEstado;
    @OneToMany(mappedBy = "idEmpresa")
    private Collection<Test> testCollection;
    @OneToMany(mappedBy = "idEmpresa")
    private Collection<Vacante> vacanteCollection;
    @OneToMany(mappedBy = "idEmpresa")
    private Collection<Flor> florCollection;
    @JoinColumn(name = "id_giro_empresa", referencedColumnName = "id_giro_empresa")
    @ManyToOne
    private GiroEmpresa idGiroEmpresa;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public Empresa() {
    }

    public Empresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getEmpNombre() {
        return empNombre;
    }

    public void setEmpNombre(String empNombre) {
        this.empNombre = empNombre;
    }

    public String getEmpDireccion() {
        return empDireccion;
    }

    public void setEmpDireccion(String empDireccion) {
        this.empDireccion = empDireccion;
    }

    public String getEmpTelefono() {
        return empTelefono;
    }

    public void setEmpTelefono(String empTelefono) {
        this.empTelefono = empTelefono;
    }

    public String getEmpCorreo() {
        return empCorreo;
    }

    public void setEmpCorreo(String empCorreo) {
        this.empCorreo = empCorreo;
    }

    public Boolean getEmpEstado() {
        return empEstado;
    }

    public void setEmpEstado(Boolean empEstado) {
        this.empEstado = empEstado;
    }

    public Collection<Test> getTestCollection() {
        return testCollection;
    }

    public void setTestCollection(Collection<Test> testCollection) {
        this.testCollection = testCollection;
    }

    public Collection<Vacante> getVacanteCollection() {
        return vacanteCollection;
    }

    public void setVacanteCollection(Collection<Vacante> vacanteCollection) {
        this.vacanteCollection = vacanteCollection;
    }

    public Collection<Flor> getFlorCollection() {
        return florCollection;
    }

    public void setFlorCollection(Collection<Flor> florCollection) {
        this.florCollection = florCollection;
    }

    public GiroEmpresa getIdGiroEmpresa() {
        return idGiroEmpresa;
    }

    public void setIdGiroEmpresa(GiroEmpresa idGiroEmpresa) {
        this.idGiroEmpresa = idGiroEmpresa;
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
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Empresa[ idEmpresa=" + idEmpresa + " ]";
    }
    
}
