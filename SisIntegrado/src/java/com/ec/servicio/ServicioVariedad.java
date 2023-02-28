/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Variedad;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioVariedad {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Variedad variedad) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(variedad);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar variedad " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Variedad variedad) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(variedad));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  variedad " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Variedad variedad) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(variedad);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en modificar variedad " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public Variedad findAll() {

        List<Variedad> listaDatos = new ArrayList<Variedad>();
        Variedad variedad = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Variedad u ");
//            query.setParameter("opcDescripcion", "%" + valor + "%");
            listaDatos = (List<Variedad>) query.getResultList();
            if (listaDatos.size() > 0) {
                variedad = listaDatos.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta variedad  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return variedad;
    }

}
