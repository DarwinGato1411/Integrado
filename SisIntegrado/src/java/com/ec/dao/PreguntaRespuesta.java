/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.dao;

import com.ec.entidad.Pregunta;
import com.ec.entidad.Respuesta;

/**
 *
 * @author Darwin
 */
public class PreguntaRespuesta {

    private Pregunta pregunta;
    private Respuesta respuesta;

    public PreguntaRespuesta() {
    }

    public PreguntaRespuesta(Pregunta pregunta, Respuesta respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

}
