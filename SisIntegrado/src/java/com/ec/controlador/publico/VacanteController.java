/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador.publico;

import com.ec.entidad.Candidato;
import com.ec.entidad.Vacante;
import com.ec.servicio.ServicioCandidato;
import com.ec.servicio.ServicioEmpresa;
import com.ec.servicio.ServicioVacante;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

/**
 *
 * @author Darwin
 */
public class VacanteController {

    ServicioVacante servicioVacante = new ServicioVacante();
    private List<Vacante> listaVacantes = new ArrayList<Vacante>();
    private String buscar = "";

    ServicioCandidato servicioCandidato = new ServicioCandidato();
    private List<Candidato> listaCandidato = new ArrayList<Candidato>();
    private String buscarCan = "";
    private Integer totalVacantes;
    private Integer totalCandidatos;
    private Integer totalEmpresas;
    ServicioEmpresa servicioEmpresa = new ServicioEmpresa();

    public VacanteController() {
        buscarvacantes();
        buscarCandidato();
        totalEmpresas = servicioEmpresa.findAll().size();
    }

    private void buscarvacantes() {
        listaVacantes = servicioVacante.findAll(buscar);
        totalVacantes = listaVacantes.size();
    }

    private void buscarCandidato() {
        listaCandidato = servicioCandidato.findAll(buscarCan);
        totalCandidatos = listaCandidato.size();
    }

    @Command
    @NotifyChange({"listaVacantes", "buscar"})
    public void buscarLike() {
        buscarvacantes();
    }

    @Command
    @NotifyChange({"listaCandidato", "buscarCan"})
    public void buscarCandidatoLike() {
        buscarCandidato();
    }

    public List<Vacante> getListaVacantes() {
        return listaVacantes;
    }

    public void setListaVacantes(List<Vacante> listaVacantes) {
        this.listaVacantes = listaVacantes;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public String getBuscarCan() {
        return buscarCan;
    }

    public void setBuscarCan(String buscarCan) {
        this.buscarCan = buscarCan;
    }

    public List<Candidato> getListaCandidato() {
        return listaCandidato;
    }

    public void setListaCandidato(List<Candidato> listaCandidato) {
        this.listaCandidato = listaCandidato;
    }

    public Integer getTotalVacantes() {
        return totalVacantes;
    }

    public void setTotalVacantes(Integer totalVacantes) {
        this.totalVacantes = totalVacantes;
    }

    public Integer getTotalCandidatos() {
        return totalCandidatos;
    }

    public void setTotalCandidatos(Integer totalCandidatos) {
        this.totalCandidatos = totalCandidatos;
    }

    public Integer getTotalEmpresas() {
        return totalEmpresas;
    }

    public void setTotalEmpresas(Integer totalEmpresas) {
        this.totalEmpresas = totalEmpresas;
    }

}
