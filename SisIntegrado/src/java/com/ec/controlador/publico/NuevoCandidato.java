/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador.publico;

import com.ec.dao.PreguntaRespuesta;
import com.ec.entidad.Candidato;
import com.ec.entidad.Usuario;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioCandidato;
import com.ec.servicio.ServicioUsuario;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class NuevoCandidato {
    
    @Wire
    Textbox ruc;
    
    @Wire
    Textbox nombre;
    @Wire
    Textbox account;
    @Wire
    Textbox password;
    @Wire
    Textbox passwordRepeat;
    
    ServicioUsuario servicioUsuario = new ServicioUsuario();
    private Usuario entidad = new Usuario();
    UserCredential credential = new UserCredential();
    ServicioCandidato servicioCandidato = new ServicioCandidato();
    private Candidato candidato = new Candidato();
    
    private String accion = "create";
    @Wire
    Window wVacante;
    
    public NuevoCandidato() {
        
    }
    
    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") PreguntaRespuesta valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        
    }
    
    @Command
    public void guardar() {
        if (ruc.getValue() == null
                    || nombre.getValue() == null
                    || account.getValue() == null
                    || password.getValue() == null) {
            Clients.showNotification("Verifique la informacion",
                        Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
            return;
        }
        
        Candidato can = servicioCandidato.findByRuc(ruc.getValue());
        if (can != null) {
            Clients.showNotification("El candidato ya se encuentra registrado",
                        Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
            return;
        }
        Candidato canLogin = servicioCandidato.findByLogin(account.getValue());
        if (canLogin != null) {
            Clients.showNotification("El nombre de la cuenta se encuentra registrado coloque un nuevo nombre",
                        Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
            return;
        }
        
        if (!password.getText().equals(passwordRepeat.getValue())) {
            
            Clients.showNotification("Las contraseñas no son iguales.",
                        Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
            return;
        }
        
        if (accion.equals("create")) {
            entidad.setUsuRuc(ruc.getValue());
            entidad.setUsuTipoUsuario("CANDIDATO");
            entidad.setUsuNivel(1);
            entidad.setUsuNombre(nombre.getValue());
            entidad.setUsuLogin(account.getValue());
            entidad.setUsuPassword(password.getValue());
            servicioUsuario.crear(entidad);
            candidato.setIdUsuario(entidad);            
            servicioCandidato.crear(candidato);
            
            wVacante.detach();
        } else {
            
        }
        
    }
    
    public Usuario getEntidad() {
        return entidad;
    }
    
    public void setEntidad(Usuario entidad) {
        this.entidad = entidad;
    }
    
    public String getAccion() {
        return accion;
    }
    
    public void setAccion(String accion) {
        this.accion = accion;
    }
    
}
