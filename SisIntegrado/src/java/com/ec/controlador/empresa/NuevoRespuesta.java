/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador.empresa;

import com.ec.dao.PreguntaRespuesta;
import com.ec.dao.PreguntaTest;
import com.ec.entidad.Empresa;
import com.ec.entidad.Pregunta;
import com.ec.entidad.Respuesta;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioEmpresa;
import com.ec.servicio.ServicioPregunta;
import com.ec.servicio.ServicioRespuesta;
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
public class NuevoRespuesta {

    ServicioRespuesta servicioRespuesta = new ServicioRespuesta();
    private Respuesta entidad = new Respuesta();
    UserCredential credential = new UserCredential();
    ServicioEmpresa servicioEmpresa = new ServicioEmpresa();
    private Empresa empresa = new Empresa();

    private String accion = "create";
    @Wire
    Window wPregunta;

    public NuevoRespuesta() {
        Session sess = Sessions.getCurrent();
        credential = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        empresa = servicioEmpresa.findByUsuario(credential.getUsuarioSistema());
    }

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") PreguntaRespuesta valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (valor.getRespuesta() != null) {
            this.entidad = valor.getRespuesta();
            entidad.setIdPregunta(valor.getPregunta());
            accion = "update";
        } else {
            this.entidad = new Respuesta();
            entidad.setIdPregunta(valor.getPregunta());
            accion = "create";

        }

    }

    @Command
    public void guardar() {
        if (entidad.getResDescripcion() != null) {

            if (accion.equals("create")) {
                servicioRespuesta.crear(entidad);
                //  Messagebox.show("Guardado con exito");

                wPregunta.detach();
            } else {
                servicioRespuesta.modificar(entidad);
                // Messagebox.show("Guardado con exito");

                wPregunta.detach();
            }

        } else {
            Messagebox.show("Verifique la informacion requerida", "Atención", Messagebox.OK, Messagebox.ERROR);
        }
    }

    public Respuesta getEntidad() {
        return entidad;
    }

    public void setEntidad(Respuesta entidad) {
        this.entidad = entidad;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

}
