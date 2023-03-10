/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "vacante")
@NamedQueries({
    @NamedQuery(name = "Vacante.findAll", query = "SELECT v FROM Vacante v")})
public class Vacante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vacante")
    private Integer idVacante;
    @Column(name = "vac_nombre")
    private String vacNombre;
    @Column(name = "vac_descripcion")
    private String vacDescripcion;
    @Column(name = "vac_fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date vacFechaInicio;
    @Column(name = "vac_fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date vacFechaFin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vac_sueldo")
    private BigDecimal vacSueldo;
    @Column(name = "vac_anio_experiencia")
    private Integer vacAnioExperiencia;
    @Column(name = "vac_hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date vacHoraInicio;
    @Column(name = "vac_hora_fin")
    @Temporal(TemporalType.TIME)
    private Date vacHoraFin;
    @Column(name = "vac_estado")
    private Boolean vacEstado;
    @Column(name = "vac_foto")
    private String vacFoto;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne
    private Empresa idEmpresa;
    @JoinColumn(name = "id_tipo_contratacion", referencedColumnName = "id_tipo_contratacion")
    @ManyToOne
    private TipoContratacion idTipoContratacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vacante")
    private Collection<CandidatoVacante> candidatoVacanteCollection;
    @Transient
    private AImage fotoGeneral = null;

    public Vacante() {
    }

    public Vacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public String getVacNombre() {
        return vacNombre;
    }

    public void setVacNombre(String vacNombre) {
        this.vacNombre = vacNombre;
    }

    public String getVacDescripcion() {
        return vacDescripcion;
    }

    public void setVacDescripcion(String vacDescripcion) {
        this.vacDescripcion = vacDescripcion;
    }

    public Date getVacFechaInicio() {
        return vacFechaInicio;
    }

    public void setVacFechaInicio(Date vacFechaInicio) {
        this.vacFechaInicio = vacFechaInicio;
    }

    public Date getVacFechaFin() {
        return vacFechaFin;
    }

    public void setVacFechaFin(Date vacFechaFin) {
        this.vacFechaFin = vacFechaFin;
    }

    public BigDecimal getVacSueldo() {
        return vacSueldo;
    }

    public void setVacSueldo(BigDecimal vacSueldo) {
        this.vacSueldo = vacSueldo;
    }

    public Integer getVacAnioExperiencia() {
        return vacAnioExperiencia;
    }

    public void setVacAnioExperiencia(Integer vacAnioExperiencia) {
        this.vacAnioExperiencia = vacAnioExperiencia;
    }

    public Date getVacHoraInicio() {
        return vacHoraInicio;
    }

    public void setVacHoraInicio(Date vacHoraInicio) {
        this.vacHoraInicio = vacHoraInicio;
    }

    public Date getVacHoraFin() {
        return vacHoraFin;
    }

    public void setVacHoraFin(Date vacHoraFin) {
        this.vacHoraFin = vacHoraFin;
    }

    public Boolean getVacEstado() {
        return vacEstado;
    }

    public void setVacEstado(Boolean vacEstado) {
        this.vacEstado = vacEstado;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public TipoContratacion getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(TipoContratacion idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public Collection<CandidatoVacante> getCandidatoVacanteCollection() {
        return candidatoVacanteCollection;
    }

    public void setCandidatoVacanteCollection(Collection<CandidatoVacante> candidatoVacanteCollection) {
        this.candidatoVacanteCollection = candidatoVacanteCollection;
    }

    public String getVacFoto() {
        return vacFoto;
    }

    public void setVacFoto(String vacFoto) {
        this.vacFoto = vacFoto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVacante != null ? idVacante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vacante)) {
            return false;
        }
        Vacante other = (Vacante) object;
        if ((this.idVacante == null && other.idVacante != null) || (this.idVacante != null && !this.idVacante.equals(other.idVacante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Vacante[ idVacante=" + idVacante + " ]";
    }

    public AImage getFotoGeneral() {
        try {
            fotoGeneral = new AImage("fotoPedido", Imagen_A_Bytes(vacFoto));
        } catch (Exception e) {
            System.out.println("ERRO al cargar fotografia vacante "+e.getMessage());
        }
        return fotoGeneral;
    }

    public byte[] Imagen_A_Bytes(String pathImagen) throws FileNotFoundException {
        String reportPath = "";
        reportPath = pathImagen;
        File file = new File(reportPath);

        FileInputStream fis = new FileInputStream(file);
        //create FileInputStream which obtains input bytes from a file in a file system
        //FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, consider using FileReader.

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                //Writes to this byte array output stream
                bos.write(buf, 0, readNum);
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
        }

        byte[] bytes = bos.toByteArray();
        return bytes;
    }
}
