/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador.nuevo;

import com.ec.entidad.GiroEmpresa;
import com.ec.servicio.ServicioGiroEmpresa;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class NuevoGiroEmpresa {

    @Wire
    Window wGiroEmpresa;
    private GiroEmpresa entidad = new GiroEmpresa();
    ServicioGiroEmpresa servicio = new ServicioGiroEmpresa();
    private String accion = "create";
   
    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") GiroEmpresa valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (valor != null) {
            this.entidad = valor;
            accion = "update";

        } else {
            this.entidad = new GiroEmpresa();
            accion = "create";

        }

    }

    @Command
    public void guardar() {
        if (entidad.getGirNombre()!= null) {           
            if (accion.equals("create")) {
                servicio.crear(entidad);
                wGiroEmpresa.detach();
            } else {
                servicio.modificar(entidad);
                wGiroEmpresa.detach();
            }
            Clients.showNotification("Registro correcto",
                        Clients.NOTIFICATION_TYPE_INFO, null, "middle_center", 2000, true);
        } else {
            Clients.showNotification("Verifique la información ingresada",
                        Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
        }
    }

    public GiroEmpresa getEntidad() {
        return entidad;
    }

    public void setEntidad(GiroEmpresa entidad) {
        this.entidad = entidad;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

  
}
