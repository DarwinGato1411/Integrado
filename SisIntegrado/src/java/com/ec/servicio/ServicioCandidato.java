/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Candidato;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioCandidato {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Candidato candidato) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(candidato);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar candidato " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Candidato candidato) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(candidato));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  candidato " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Candidato candidato) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(candidato);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en modificar candidato " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public Candidato findAll() {

        List<Candidato> listaDatos = new ArrayList<Candidato>();
        Candidato candidato = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Candidato u ");
//            query.setParameter("opcDescripcion", "%" + valor + "%");
            listaDatos = (List<Candidato>) query.getResultList();
            if (listaDatos.size() > 0) {
                candidato = listaDatos.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta candidato  findActivo  " + e.getMessage());
        } finally {
            em.close();
        }

        return candidato;
    }

}
