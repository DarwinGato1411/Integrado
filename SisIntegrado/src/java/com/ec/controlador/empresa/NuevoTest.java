/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador.empresa;

import com.ec.entidad.Empresa;
import com.ec.entidad.Test;
import com.ec.entidad.TipoContratacion;
import com.ec.entidad.Vacante;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioEmpresa;
import com.ec.servicio.ServicioTest;
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
public class NuevoTest {

    ServicioTest servicioTest = new ServicioTest();
    private Test entidad = new Test();
    UserCredential credential = new UserCredential();
    ServicioEmpresa servicioEmpresa = new ServicioEmpresa();
    private Empresa empresa = new Empresa();

    private String accion = "create";
    @Wire
    Window wVacante;

    public NuevoTest() {
        Session sess = Sessions.getCurrent();
        credential = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        empresa = servicioEmpresa.findByUsuario(credential.getUsuarioSistema());
    }

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") Test valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (valor != null) {
            this.entidad = valor;
            accion = "update";
        } else {          
            this.entidad = new Test();
            accion = "create";

        }
    

    }

   

    @Command
    public void guardar() {
        if (entidad.getTestNombre()!= null) {

            entidad.setIdEmpresa(empresa);
            if (accion.equals("create")) {
                servicioTest.crear(entidad);
                //  Messagebox.show("Guardado con exito");

                wVacante.detach();
            } else {
                servicioTest.modificar(entidad);
                // Messagebox.show("Guardado con exito");

                wVacante.detach();
            }

        } else {
            Messagebox.show("Verifique la informacion requerida", "Atención", Messagebox.OK, Messagebox.ERROR);
        }
    }

    public Test getEntidad() {
        return entidad;
    }

    public void setEntidad(Test entidad) {
        this.entidad = entidad;
    }

    

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

}
