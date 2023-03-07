/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Pregunta;
import com.ec.entidad.Test;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioPregunta {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Pregunta pregunta) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(pregunta);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar pregunta " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Pregunta pregunta) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(pregunta));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  pregunta " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Pregunta pregunta) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(pregunta);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en modificar pregunta " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public Pregunta findAll() {

        List<Pregunta> listaDatos = new ArrayList<Pregunta>();
        Pregunta pregunta = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Pregunta u ");
//            query.setParameter("opcDescripcion", "%" + valor + "%");
            listaDatos = (List<Pregunta>) query.getResultList();
            if (listaDatos.size() > 0) {
                pregunta = listaDatos.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta pregunta  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return pregunta;
    }

    public List<Pregunta> findByTest(Test idTest) {

        List<Pregunta> listaDatos = new ArrayList<Pregunta>();
        Pregunta pregunta = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Pregunta u WHERE u.idTest=:idTest ");
            query.setParameter("idTest", idTest);
            listaDatos = (List<Pregunta>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta pregunta  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDatos;
    }

}
