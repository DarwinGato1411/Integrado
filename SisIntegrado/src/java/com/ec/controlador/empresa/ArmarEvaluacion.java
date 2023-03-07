/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador.empresa;

import com.ec.dao.PreguntaRespuesta;
import com.ec.dao.PreguntaTest;
import com.ec.entidad.Pregunta;
import com.ec.entidad.Respuesta;
import com.ec.entidad.Test;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioPregunta;
import com.ec.servicio.ServicioRespuesta;
import com.ec.servicio.ServicioTest;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;

/**
 *
 * @author gato
 */
public class ArmarEvaluacion {

    ServicioTest servicioTest = new ServicioTest();
    ServicioPregunta servicioPregunta = new ServicioPregunta();
    ServicioRespuesta servicioRespuesta = new ServicioRespuesta();
    private List<Test> listaTest = new ArrayList<Test>();
    private Test testSelected = null;
    private Pregunta preguntaSelected = null;
    private List<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
    private List<Respuesta> listaRespuestas = new ArrayList<Respuesta>();

    //reporte
    AMedia fileContent = null;
    Connection con = null;
    UserCredential credential = new UserCredential();

    public ArmarEvaluacion() {
        Session sess = Sessions.getCurrent();
        credential = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        buscarTest();

    }

    private void buscarTest() {
        listaTest = servicioTest.findByUsuario(credential.getUsuarioSistema());
    }

    private void buscarPregunta() {
        listaPreguntas = servicioPregunta.findByTest(testSelected);
    }

    private void buscarRespuesta() {
        listaRespuestas = servicioRespuesta.findByPregunta(preguntaSelected);
    }

    @Command
    @NotifyChange({"listaPreguntas", "buscar", "testSelected", "listaRespuestas"})
    public void buscarPreguntas(@BindingParam("valor") Test valor) {
        testSelected = valor;
        preguntaSelected = null;
        buscarPregunta();
        buscarRespuesta();
    }

    @Command
    @NotifyChange({"listaRespuestas", "buscar", "preguntaSelected"})
    public void busacarRespuestas(@BindingParam("valor") Pregunta valor) {
        preguntaSelected = valor;
        buscarRespuesta();
    }

    @Command
    @NotifyChange({"listaTest", "buscar"})
    public void nuevoTest() {
        try {

            org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                        "/empresa/nuevo/test.zul", null, null);
            window.doModal();
            buscarTest();
        } catch (Exception e) {
            Clients.showNotification("Ocurrio un error " + e.getMessage(),
                        Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
        }
    }

    @Command
    @NotifyChange({"listaVacantes", "buscar"})
    public void modificarTest(@BindingParam("valor") Test valor) {
        try {
//            if (Messagebox.show("¿Desea modificar el registro, recuerde que debe crear las reteniones nuevamente?", "Atención", Messagebox.YES | Messagebox.NO, Messagebox.INFORMATION) == Messagebox.YES) {
            final HashMap<String, Test> map = new HashMap<String, Test>();

            map.put("valor", valor);
            org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                        "/empresa/nuevo/test.zul", null, map);
            window.doModal();
        } catch (Exception e) {
            Clients.showNotification("Ocurrio un error " + e.getMessage(),
                        Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
        }
    }

    @Command
    @NotifyChange({"listaPreguntas", "buscar"})
    public void nuevoPregunta() {
        try {
            if (testSelected != null) {
                final HashMap<String, PreguntaTest> map = new HashMap<String, PreguntaTest>();
                PreguntaTest preguntaTest = new PreguntaTest(null, testSelected);
                map.put("valor", preguntaTest);
                org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                            "/empresa/nuevo/pregunta.zul", null, map);
                window.doModal();
                buscarPregunta();
            } else {
                Clients.showNotification("Seleccione un test ",
                            Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
            }
        } catch (Exception e) {
            Clients.showNotification("Ocurrio un error " + e.getMessage(),
                        Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
        }
    }

    @Command
    @NotifyChange({"listaPreguntas", "buscar"})
    public void modificarPregunta(@BindingParam("valor") Pregunta valor) {
        try {
            if (testSelected != null) {
                final HashMap<String, PreguntaTest> map = new HashMap<String, PreguntaTest>();
                PreguntaTest preguntaTest = new PreguntaTest(valor, testSelected);
                map.put("valor", preguntaTest);
                org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                            "/empresa/nuevo/pregunta.zul", null, map);
                window.doModal();
            } else {
                Clients.showNotification("Seleccione un test ",
                            Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
            }
        } catch (Exception e) {
            Clients.showNotification("Ocurrio un error " + e.getMessage(),
                        Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
        }

    }

    @Command
    @NotifyChange({"listaRespuestas", "buscar"})
    public void nuevoRespuesta() {
        try {
            if (preguntaSelected != null) {
                final HashMap<String, PreguntaRespuesta> map = new HashMap<String, PreguntaRespuesta>();
                PreguntaRespuesta parametro = new PreguntaRespuesta(preguntaSelected, null);
                map.put("valor", parametro);
                org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                            "/empresa/nuevo/respuesta.zul", null, map);
                window.doModal();
                busacarRespuestas(preguntaSelected);
            } else {
                Clients.showNotification("Seleccione un test ",
                            Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
            }
        } catch (Exception e) {
            Clients.showNotification("Ocurrio un error " + e.getMessage(),
                        Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
        }
    }

    @Command
    @NotifyChange({"listaRespuestas", "buscar"})
    public void modificarRespuesta(@BindingParam("valor") Respuesta valor) {
        try {
            if (preguntaSelected != null) {
                final HashMap<String, PreguntaRespuesta> map = new HashMap<String, PreguntaRespuesta>();
                PreguntaRespuesta parametro = new PreguntaRespuesta(preguntaSelected, valor);
                map.put("valor", parametro);
                org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                            "/empresa/nuevo/respuesta.zul", null, map);
                window.doModal();
            } else {
                Clients.showNotification("Seleccione un test ",
                            Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
            }
        } catch (Exception e) {
            Clients.showNotification("Ocurrio un error " + e.getMessage(),
                        Clients.NOTIFICATION_TYPE_ERROR, null, "middle_center", 2000, true);
        }

    }

    public List<Test> getListaTest() {
        return listaTest;
    }

    public void setListaTest(List<Test> listaTest) {
        this.listaTest = listaTest;
    }

    public Test getTestSelected() {
        return testSelected;
    }

    public void setTestSelected(Test testSelected) {
        this.testSelected = testSelected;
    }

    public Pregunta getPreguntaSelected() {
        return preguntaSelected;
    }

    public void setPreguntaSelected(Pregunta preguntaSelected) {
        this.preguntaSelected = preguntaSelected;
    }

    public List<Pregunta> getListaPreguntas() {
        return listaPreguntas;
    }

    public void setListaPreguntas(List<Pregunta> listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }

    public List<Respuesta> getListaRespuestas() {
        return listaRespuestas;
    }

    public void setListaRespuestas(List<Respuesta> listaRespuestas) {
        this.listaRespuestas = listaRespuestas;
    }

}
