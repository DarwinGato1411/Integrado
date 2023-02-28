/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Canton;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioCanton {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Canton canton) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(canton);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar canton " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Canton canton) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(canton));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  canton " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Canton canton) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(canton);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en modificar canton " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public Canton findAll() {

        List<Canton> listaDatos = new ArrayList<Canton>();
        Canton canton = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Canton u ");
//            query.setParameter("opcDescripcion", "%" + valor + "%");
            listaDatos = (List<Canton>) query.getResultList();
            if (listaDatos.size() > 0) {
                canton = listaDatos.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta canton  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return canton;
    }

}
