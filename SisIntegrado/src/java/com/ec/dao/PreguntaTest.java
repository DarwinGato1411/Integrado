/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.dao;

import com.ec.entidad.Pregunta;
import com.ec.entidad.Test;

/**
 *
 * @author Darwin
 */
public class PreguntaTest {

    private Pregunta pregunta;
    private Test test;

    public PreguntaTest() {
    }

    public PreguntaTest(Pregunta pregunta, Test test) {
        this.pregunta = pregunta;
        this.test = test;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

}
