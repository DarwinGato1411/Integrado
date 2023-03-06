/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Evaluacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioEvaluacion {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Evaluacion evaluacion) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(evaluacion);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar evaluacion " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Evaluacion evaluacion) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(evaluacion));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  evaluacion " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Evaluacion evaluacion) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(evaluacion);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en modificar evaluacion " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public Evaluacion findAll() {

        List<Evaluacion> listaDatos = new ArrayList<Evaluacion>();
        Evaluacion evaluacion = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Evaluacion u ");
//            query.setParameter("opcDescripcion", "%" + valor + "%");
            listaDatos = (List<Evaluacion>) query.getResultList();
            if (listaDatos.size() > 0) {
                evaluacion = listaDatos.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta evaluacion  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return evaluacion;
    }

}
