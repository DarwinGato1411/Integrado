/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Ciudad;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioCiudad {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Ciudad ciudad) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(ciudad);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar ciudad " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Ciudad ciudad) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(ciudad));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  ciudad " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Ciudad ciudad) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(ciudad);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en modificar ciudad " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public Ciudad findAll() {

        List<Ciudad> listaDatos = new ArrayList<Ciudad>();
        Ciudad ciudad = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Ciudad u ");
//            query.setParameter("opcDescripcion", "%" + valor + "%");
            listaDatos = (List<Ciudad>) query.getResultList();
            if (listaDatos.size() > 0) {
                ciudad = listaDatos.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta ciudad  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return ciudad;
    }

}
