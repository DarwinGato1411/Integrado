/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador.empresa;

import com.ec.entidad.Canton;
import com.ec.entidad.Ciudad;
import com.ec.entidad.Empresa;
import com.ec.entidad.GiroEmpresa;
import com.ec.entidad.Pais;
import com.ec.entidad.Usuario;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioCanton;
import com.ec.servicio.ServicioCiudad;
import com.ec.servicio.ServicioEmpresa;
import com.ec.servicio.ServicioGiroEmpresa;
import com.ec.servicio.ServicioPais;
import com.ec.servicio.ServicioUsuario;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;

/**
 *
 * @author Darwin
 */
public class EmpresaCtr {

    private Empresa empresa;
    private Usuario usuario;
    ServicioUsuario servicioUsuario = new ServicioUsuario();
    ServicioEmpresa servicioEmpresa = new ServicioEmpresa();
    UserCredential credential = new UserCredential();

    ServicioGiroEmpresa servicioGiroEmpresa = new ServicioGiroEmpresa();
    private List<GiroEmpresa> listaGiro = new ArrayList<GiroEmpresa>();

    ServicioPais servicioPais = new ServicioPais();
    private List<Pais> listaPais = new ArrayList<Pais>();
    private Pais paisSelected = new Pais();
    ServicioCiudad servicioCiudad = new ServicioCiudad();
    private List<Ciudad> listaCiudad = new ArrayList<Ciudad>();
    private Ciudad ciudadSeleted = new Ciudad();
    ServicioCanton servicioCanton = new ServicioCanton();
    private List<Canton> listaCanton = new ArrayList<Canton>();

    public EmpresaCtr() {
        Session sess = Sessions.getCurrent();
        credential = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        empresa = servicioEmpresa.findByUsuario(credential.getUsuarioSistema());
        if (empresa.getIdUsuario().getIdCanton() != null) {
            paisSelected = empresa.getIdUsuario().getIdCanton().getIdCiudad().getIdPais();
            ciudadSeleted = empresa.getIdUsuario().getIdCanton().getIdCiudad();
//            paisSelected=empresa.getIdUsuario().getIdCanton();
            buscarCanton();
        }
        cargarDatos();

    }

    @Command
    public void guardar() {
//        empresa.setIdGiroEmpresa(giroEmpresaSeleted);
        servicioEmpresa.modificar(empresa);
        usuario = empresa.getIdUsuario();
        servicioUsuario.modificar(usuario);
        Clients.showNotification("Guardado correctamente... ",
                    Clients.NOTIFICATION_TYPE_INFO, null, "end_center", 3000, true);
    }

    private void cargarDatos() {
        listaPais = servicioPais.findAll();
        paisSelected = listaPais.get(0);
        listaCiudad = servicioCiudad.findByPais(paisSelected);
//        listaCanton = servicioCanton.findAll();
    }

    @Command
    @NotifyChange({"listaCiudad"})
    public void buscarCiudad() {
        listaCiudad = servicioCiudad.findByPais(paisSelected);
//        ciudadSeleted=null;
    }

    @Command
    @NotifyChange({"listaCanton"})
    public void buscarCanton() {
        listaCanton = servicioCanton.findByCiudad(ciudadSeleted);
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<GiroEmpresa> getListaGiro() {
        return listaGiro;
    }

    public void setListaGiro(List<GiroEmpresa> listaGiro) {
        this.listaGiro = listaGiro;
    }

    public List<Pais> getListaPais() {
        return listaPais;
    }

    public void setListaPais(List<Pais> listaPais) {
        this.listaPais = listaPais;
    }

    public Pais getPaisSelected() {
        return paisSelected;
    }

    public void setPaisSelected(Pais paisSelected) {
        this.paisSelected = paisSelected;
    }

    public List<Ciudad> getListaCiudad() {
        return listaCiudad;
    }

    public void setListaCiudad(List<Ciudad> listaCiudad) {
        this.listaCiudad = listaCiudad;
    }

    public Ciudad getCiudadSeleted() {
        return ciudadSeleted;
    }

    public void setCiudadSeleted(Ciudad ciudadSeleted) {
        this.ciudadSeleted = ciudadSeleted;
    }

    public List<Canton> getListaCanton() {
        return listaCanton;
    }

    public void setListaCanton(List<Canton> listaCanton) {
        this.listaCanton = listaCanton;
    }

}
