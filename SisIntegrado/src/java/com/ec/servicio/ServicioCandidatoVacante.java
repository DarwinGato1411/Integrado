/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.CandidatoVacante;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioCandidatoVacante {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(CandidatoVacante candidatoVacante) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(candidatoVacante);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar candidatoVacante " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(CandidatoVacante candidatoVacante) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(candidatoVacante));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  candidatoVacante " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(CandidatoVacante candidatoVacante) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(candidatoVacante);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en modificar candidatoVacante " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public CandidatoVacante findAll() {

        List<CandidatoVacante> listaDatos = new ArrayList<CandidatoVacante>();
        CandidatoVacante candidatoVacante = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM CandidatoVacante u ");
//            query.setParameter("opcDescripcion", "%" + valor + "%");
            listaDatos = (List<CandidatoVacante>) query.getResultList();
            if (listaDatos.size() > 0) {
                candidatoVacante = listaDatos.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta candidatoVacante  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return candidatoVacante;
    }

}
