/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Respuesta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioRespuesta {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Respuesta respuesta) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(respuesta);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar respuesta " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Respuesta respuesta) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(respuesta));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  respuesta " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Respuesta respuesta) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(respuesta);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en modificar respuesta " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public Respuesta findAll() {

        List<Respuesta> listaDatos = new ArrayList<Respuesta>();
        Respuesta respuesta = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Respuesta u ");
//            query.setParameter("opcDescripcion", "%" + valor + "%");
            listaDatos = (List<Respuesta>) query.getResultList();
            if (listaDatos.size() > 0) {
                respuesta = listaDatos.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta respuesta  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return respuesta;
    }

}
