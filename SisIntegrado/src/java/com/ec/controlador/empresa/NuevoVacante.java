/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador.empresa;

import com.ec.entidad.Empresa;
import com.ec.entidad.TipoContratacion;
import com.ec.entidad.Vacante;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioEmpresa;
import com.ec.servicio.ServicioTipoContratacion;
import com.ec.servicio.ServicioVacante;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class NuevoVacante {

    ServicioVacante servicioVacante = new ServicioVacante();
    ServicioTipoContratacion servicioTipoContratacion = new ServicioTipoContratacion();
    private List<TipoContratacion> listaTipoContratacion = new ArrayList<TipoContratacion>();
    private Vacante entidad = new Vacante();
    UserCredential credential = new UserCredential();
    ServicioEmpresa servicioEmpresa = new ServicioEmpresa();
    private Empresa empresa = new Empresa();

    private String accion = "create";
    @Wire
    Window wVacante;

    public NuevoVacante() {
        Session sess = Sessions.getCurrent();
        credential = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        empresa = servicioEmpresa.findByUsuario(credential.getUsuarioSistema());
    }

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") Vacante valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (valor != null) {
            this.entidad = valor;
            accion = "update";
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, +1);
            this.entidad = new Vacante();
            entidad.setVacFechaInicio(new Date());
            entidad.setVacFechaFin(calendar.getTime());
            accion = "create";

        }
        cargarTipoIdentificacion();

    }

    private void cargarTipoIdentificacion() {
        listaTipoContratacion = servicioTipoContratacion.findAll();
    }

    @Command
    public void guardar() {
        if (entidad.getVacNombre() != null
                    && entidad.getVacDescripcion() != null
                    && entidad.getIdTipoContratacion() != null) {

            entidad.setIdEmpresa(empresa);
            if (accion.equals("create")) {
                servicioVacante.crear(entidad);
                //  Messagebox.show("Guardado con exito");

                wVacante.detach();
            } else {
                servicioVacante.modificar(entidad);
                // Messagebox.show("Guardado con exito");

                wVacante.detach();
            }

        } else {
            Messagebox.show("Verifique la informacion requerida", "Atención", Messagebox.OK, Messagebox.ERROR);
        }
    }

    public List<TipoContratacion> getListaTipoContratacion() {
        return listaTipoContratacion;
    }

    public void setListaTipoContratacion(List<TipoContratacion> listaTipoContratacion) {
        this.listaTipoContratacion = listaTipoContratacion;
    }

    public Vacante getEntidad() {
        return entidad;
    }

    public void setEntidad(Vacante entidad) {
        this.entidad = entidad;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

}
