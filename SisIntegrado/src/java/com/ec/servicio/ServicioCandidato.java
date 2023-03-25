/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Candidato;
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

    public List<Candidato> findAll(String buscar) {

        List<Candidato> listaDatos = new ArrayList<Candidato>();

        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Candidato u  WHERE (u.canDescripcion LIKE :canDescripcion  OR u.idUsuario.usuNombre LIKE :usuNombre)");
            query.setParameter("canDescripcion", "%" + buscar + "%");
            query.setParameter("usuNombre", "%" + buscar + "%");
            listaDatos = (List<Candidato>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta candidato  findActivo  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDatos;
    }

    public Candidato findByUsuario(Usuario buscar) {

        List<Candidato> listaDatos = new ArrayList<Candidato>();

        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Candidato u  WHERE u.idUsuario =:idUsuario");
            query.setParameter("idUsuario", buscar);
            listaDatos = (List<Candidato>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta candidato  findActivo  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDatos.isEmpty() ? null : listaDatos.get(0);
    }

    public Candidato findByCandidatoCedula(String cedula) {

        List<Candidato> listaDatos = new ArrayList<Candidato>();

        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Candidato u  WHERE u.idUsuario.usuRuc =:usuRuc");
            query.setParameter("usuRuc", cedula);
            listaDatos = (List<Candidato>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta candidato  findActivo  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDatos.isEmpty() ? null : listaDatos.get(0);
    }

    public Candidato findByRuc(String buscar) {

        List<Candidato> listaDatos = new ArrayList<Candidato>();

        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Candidato u  WHERE u.idUsuario.usuRuc =:usuRuc");
            query.setParameter("usuRuc", buscar);
            listaDatos = (List<Candidato>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta candidato  findActivo  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDatos.isEmpty() ? null : listaDatos.get(0);
    }

    public Candidato findByLogin(String usuLogin) {

        List<Candidato> listaDatos = new ArrayList<Candidato>();

        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Candidato u  WHERE u.idUsuario.usuLogin=:usuLogin ");
            query.setParameter("usuLogin", usuLogin);
            listaDatos = (List<Candidato>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta candidato  findActivo  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDatos.isEmpty() ? null : listaDatos.get(0);
    }

    public List<Candidato> findEmpresa(Empresa buscar) {

        List<Candidato> listaDatos = new ArrayList<Candidato>();

        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Candidato u  WHERE (u.canDescripcion LIKE :canDescripcion  OR u.idUsuario.usuNombre LIKE :usuNombre)");
            query.setParameter("canDescripcion", "%" + buscar + "%");
            query.setParameter("usuNombre", "%" + buscar + "%");
            listaDatos = (List<Candidato>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta candidato  findActivo  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDatos;
    }

//     public List<Candidato> findCandidatoEmpresa(String busqueda,Empresa buscar) {
//
//        List<Candidato> listaDatos = new ArrayList<Candidato>();
//
//        try {
//            //Connection connection = em.unwrap(Connection.class);
//
//            em = HelperPersistencia.getEMF();
//            em.getTransaction().begin();
//            Query query = em.createQuery("SELECT u FROM Candidato u  WHERE (u.idUsuario.usuRuc LIKE :busqueda  OR u.idUsuario.usuNombre LIKE :busqueda) AND u.");
//            query.setParameter("canDescripcion", "%" + buscar + "%");
//            query.setParameter("usuNombre", "%" + buscar + "%");
//            listaDatos = (List<Candidato>) query.getResultList();
//
//            em.getTransaction().commit();
//        } catch (Exception e) {
//
//            System.out.println("Error en lsa consulta candidato  findActivo  " + e.getMessage());
//        } finally {
//            em.close();
//        }
//
//        return listaDatos;
//    }
}
