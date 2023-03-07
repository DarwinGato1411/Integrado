/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Pais;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioPais {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Pais pais) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(pais);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar pais " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Pais pais) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(pais));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  pais " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Pais pais) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(pais);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en modificar pais " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Pais> findAll() {

        List<Pais> listaDatos = new ArrayList<Pais>();
        Pais pais = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Pais u ");
//            query.setParameter("opcDescripcion", "%" + valor + "%");
            listaDatos = (List<Pais>) query.getResultList();
            
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta pais  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDatos;
    }

}
