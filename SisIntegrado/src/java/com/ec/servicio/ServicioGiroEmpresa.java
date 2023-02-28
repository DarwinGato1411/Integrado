/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.GiroEmpresa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioGiroEmpresa {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(GiroEmpresa giroEmpresa) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(giroEmpresa);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar giroEmpresa " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(GiroEmpresa giroEmpresa) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(giroEmpresa));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  giroEmpresa " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(GiroEmpresa giroEmpresa) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(giroEmpresa);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en modificar giroEmpresa " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public GiroEmpresa findAll() {

        List<GiroEmpresa> listaDatos = new ArrayList<GiroEmpresa>();
        GiroEmpresa giroEmpresa = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM GiroEmpresa u ");
//            query.setParameter("opcDescripcion", "%" + valor + "%");
            listaDatos = (List<GiroEmpresa>) query.getResultList();
            if (listaDatos.size() > 0) {
                giroEmpresa = listaDatos.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta giroEmpresa  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return giroEmpresa;
    }

}
