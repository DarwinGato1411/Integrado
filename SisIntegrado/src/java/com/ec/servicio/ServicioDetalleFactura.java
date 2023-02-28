/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.DetalleFactura;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioDetalleFactura {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(DetalleFactura detalleFactura) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(detalleFactura);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar detalleFactura " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(DetalleFactura detalleFactura) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(detalleFactura));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  detalleFactura " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(DetalleFactura detalleFactura) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(detalleFactura);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en modificar detalleFactura " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public DetalleFactura findAll() {

        List<DetalleFactura> listaDatos = new ArrayList<DetalleFactura>();
        DetalleFactura detalleFactura = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM DetalleFactura u ");
//            query.setParameter("opcDescripcion", "%" + valor + "%");
            listaDatos = (List<DetalleFactura>) query.getResultList();
            if (listaDatos.size() > 0) {
                detalleFactura = listaDatos.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta detalleFactura  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return detalleFactura;
    }

}
