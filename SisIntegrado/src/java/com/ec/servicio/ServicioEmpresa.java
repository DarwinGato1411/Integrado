/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Empresa;
import com.ec.entidad.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioEmpresa {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Empresa empresa) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(empresa);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar empresa " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Empresa empresa) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(empresa));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  empresa " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Empresa empresa) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(empresa);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en modificar empresa " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Empresa> findAll() {

        List<Empresa> listaDatos = new ArrayList<Empresa>();

        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Empresa u ");
//            query.setParameter("opcDescripcion", "%" + valor + "%");
            listaDatos = (List<Empresa>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta empresa  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDatos;
    }

    public Empresa findByUsuario(Usuario idUsuario) {

        List<Empresa> listaDatos = new ArrayList<Empresa>();
        Empresa empresa = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Empresa u WHERE u.idUsuario=:idUsuario ");
            query.setParameter("idUsuario", idUsuario);
            listaDatos = (List<Empresa>) query.getResultList();
            if (listaDatos.size() > 0) {
                empresa = listaDatos.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta empresa  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return empresa;
    }

}
