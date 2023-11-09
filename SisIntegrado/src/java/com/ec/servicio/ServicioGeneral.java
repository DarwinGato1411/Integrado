/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Usuario;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author gato
 */
public class ServicioGeneral {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void generarEvaluacion(Integer evaluacion, List<Integer> listaCandidatos,Integer version) {
        try {
            
            Integer[] arregloCandidato = listaCandidatos.toArray(new Integer[0]);
            em = HelperPersistencia.getEMF();

            em.getTransaction().begin();
         
            StoredProcedureQuery queryStore = em.createStoredProcedureQuery("generarevaluaciones");
            queryStore.registerStoredProcedureParameter("version_test", Integer.class, ParameterMode.IN);
            queryStore.registerStoredProcedureParameter("evalparam", Integer.class, ParameterMode.IN);
            queryStore.registerStoredProcedureParameter("candidatoparam", Array.class, ParameterMode.IN);
            Array array = em.unwrap(Connection.class).createArrayOf("int", (Integer[])arregloCandidato);
            queryStore.setParameter("version_test", version);
            queryStore.setParameter("evalparam", evaluacion);
            queryStore.setParameter("candidatoparam", array);
            queryStore.executeUpdate();
            em.getTransaction().commit();
        } catch (SQLException e) {
            System.out.println("Error generarEvaluacion " + e.getMessage());
        } finally {
            em.close();
        }

    }

}
