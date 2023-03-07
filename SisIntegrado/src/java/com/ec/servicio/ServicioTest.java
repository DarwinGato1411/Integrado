/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Empresa;
import com.ec.entidad.Test;
import com.ec.entidad.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioTest {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Test test) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(test);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar test " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Test test) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(test));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  test " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Test test) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(test);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en modificar test " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Test> findAll() {

        List<Test> listaDatos = new ArrayList<Test>();
        Test test = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Test u ");
//            query.setParameter("opcDescripcion", "%" + valor + "%");
            listaDatos = (List<Test>) query.getResultList();
            
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta test  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDatos;
    }
    public List<Test> findByUsuario(Usuario idUsuario) {

        List<Test> listaDatos = new ArrayList<Test>();
        Test test = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Test u WHERE u.idEmpresa.idUsuario=:idUsuario ");
            query.setParameter("idUsuario",idUsuario);
            listaDatos = (List<Test>) query.getResultList();
            
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta test  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDatos;
    }

}
