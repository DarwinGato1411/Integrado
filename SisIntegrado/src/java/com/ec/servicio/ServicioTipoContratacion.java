/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.TipoContratacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioTipoContratacion {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(TipoContratacion tipoContratacion) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(tipoContratacion);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar tipoContratacion " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(TipoContratacion tipoContratacion) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(tipoContratacion));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  tipoContratacion " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(TipoContratacion tipoContratacion) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(tipoContratacion);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en modificar tipoContratacion " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public TipoContratacion findAll() {

        List<TipoContratacion> listaDatos = new ArrayList<TipoContratacion>();
        TipoContratacion tipoContratacion = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM TipoContratacion u ");
//            query.setParameter("opcDescripcion", "%" + valor + "%");
            listaDatos = (List<TipoContratacion>) query.getResultList();
            if (listaDatos.size() > 0) {
                tipoContratacion = listaDatos.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta tipoContratacion  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return tipoContratacion;
    }

}
