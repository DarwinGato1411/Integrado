/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador.empresa;

import com.ec.dao.PreguntaTest;
import com.ec.entidad.Empresa;
import com.ec.entidad.Pregunta;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioEmpresa;
import com.ec.servicio.ServicioPregunta;
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
public class NuevoPregunta {

    ServicioPregunta servicioTest = new ServicioPregunta();
    private Pregunta entidad = new Pregunta();
    UserCredential credential = new UserCredential();
    ServicioEmpresa servicioEmpresa = new ServicioEmpresa();
    private Empresa empresa = new Empresa();

    private String accion = "create";
    @Wire
    Window wPregunta;

    public NuevoPregunta() {
        Session sess = Sessions.getCurrent();
        credential = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        empresa = servicioEmpresa.findByUsuario(credential.getUsuarioSistema());
    }

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") PreguntaTest valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (valor.getPregunta() != null) {
            this.entidad = valor.getPregunta();
            entidad.setIdTest(valor.getTest());
            accion = "update";
        } else {
            this.entidad = new Pregunta();
            entidad.setIdTest(valor.getTest());
            accion = "create";

        }

    }

    @Command
    public void guardar() {
        if (entidad.getPreNombre() != null
                    && entidad.getPreDescripcion() != null) {

            if (accion.equals("create")) {
                servicioTest.crear(entidad);
                //  Messagebox.show("Guardado con exito");

                wPregunta.detach();
            } else {
                servicioTest.modificar(entidad);
                // Messagebox.show("Guardado con exito");

                wPregunta.detach();
            }

        } else {
            Messagebox.show("Verifique la informacion requerida", "Atención", Messagebox.OK, Messagebox.ERROR);
        }
    }

    public Pregunta getEntidad() {
        return entidad;
    }

    public void setEntidad(Pregunta entidad) {
        this.entidad = entidad;
    }


    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

}
