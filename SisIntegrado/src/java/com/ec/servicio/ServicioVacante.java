/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Usuario;
import com.ec.entidad.Vacante;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioVacante {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Vacante vacante) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(vacante);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar vacante " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Vacante vacante) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(vacante));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  vacante " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Vacante vacante) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(vacante);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en modificar vacante " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public Vacante findAll() {

        List<Vacante> listaDatos = new ArrayList<Vacante>();
        Vacante vacante = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Vacante u ");
//            query.setParameter("opcDescripcion", "%" + valor + "%");
            listaDatos = (List<Vacante>) query.getResultList();
            if (listaDatos.size() > 0) {
                vacante = listaDatos.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta vacante  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return vacante;
    }

    public List<Vacante> findByUsuarioLike(String buscar,Usuario idUsuario) {

        List<Vacante> listaDatos = new ArrayList<Vacante>();

        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Vacante u WHERE (u.vacNombre LIKE :buscar OR u.vacDescripcion LIKE :buscar ) and u.idEmpresa.idUsuario=:idUsuario");
            query.setParameter("buscar", "%" + buscar + "%");
            query.setParameter("idUsuario", idUsuario);
            listaDatos = (List<Vacante>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta vacante  findByUsuarioLike  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDatos;
    }

}
