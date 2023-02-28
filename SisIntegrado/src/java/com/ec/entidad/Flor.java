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
@Table(name = "flor")
@NamedQueries({
    @NamedQuery(name = "Flor.findAll", query = "SELECT f FROM Flor f")})
public class Flor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_flor")
    private Integer idFlor;
    @Column(name = "flor_cantidad")
    private Integer florCantidad;
    @Column(name = "flor_direccion")
    private String florDireccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "flor_precio_unidad")
    private BigDecimal florPrecioUnidad;
    @OneToMany(mappedBy = "idFlor")
    private Collection<DetalleFactura> detalleFacturaCollection;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne
    private Empresa idEmpresa;
    @JoinColumn(name = "id_variedad", referencedColumnName = "id_variedad")
    @ManyToOne
    private Variedad idVariedad;

    public Flor() {
    }

    public Flor(Integer idFlor) {
        this.idFlor = idFlor;
    }

    public Integer getIdFlor() {
        return idFlor;
    }

    public void setIdFlor(Integer idFlor) {
        this.idFlor = idFlor;
    }

    public Integer getFlorCantidad() {
        return florCantidad;
    }

    public void setFlorCantidad(Integer florCantidad) {
        this.florCantidad = florCantidad;
    }

    public String getFlorDireccion() {
        return florDireccion;
    }

    public void setFlorDireccion(String florDireccion) {
        this.florDireccion = florDireccion;
    }

    public BigDecimal getFlorPrecioUnidad() {
        return florPrecioUnidad;
    }

    public void setFlorPrecioUnidad(BigDecimal florPrecioUnidad) {
        this.florPrecioUnidad = florPrecioUnidad;
    }

    public Collection<DetalleFactura> getDetalleFacturaCollection() {
        return detalleFacturaCollection;
    }

    public void setDetalleFacturaCollection(Collection<DetalleFactura> detalleFacturaCollection) {
        this.detalleFacturaCollection = detalleFacturaCollection;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Variedad getIdVariedad() {
        return idVariedad;
    }

    public void setIdVariedad(Variedad idVariedad) {
        this.idVariedad = idVariedad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFlor != null ? idFlor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flor)) {
            return false;
        }
        Flor other = (Flor) object;
        if ((this.idFlor == null && other.idFlor != null) || (this.idFlor != null && !this.idFlor.equals(other.idFlor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Flor[ idFlor=" + idFlor + " ]";
    }
    
}
