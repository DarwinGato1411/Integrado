/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Flor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioFlor {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Flor flor) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(flor);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar flor " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Flor flor) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(flor));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  flor " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Flor flor) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(flor);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en modificar flor " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public Flor findAll() {

        List<Flor> listaDatos = new ArrayList<Flor>();
        Flor flor = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Flor u ");
//            query.setParameter("opcDescripcion", "%" + valor + "%");
            listaDatos = (List<Flor>) query.getResultList();
            if (listaDatos.size() > 0) {
                flor = listaDatos.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta flor  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return flor;
    }

}
