/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Empresa;
import com.ec.entidad.Test;
import com.ec.entidad.VersionTest;
import com.ec.entidad.Usuario;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioVersionTest {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear() {

        try {
            VersionTest test = new VersionTest();
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNativeQuery("select nextval('version_test')");
            Long key = ((BigInteger) query.getResultList()).longValue();
            test.setVerNombre("VERSION-" + key);
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

    public void eliminar(VersionTest test) {

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

    public void modificar(VersionTest test) {

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

    public List<Integer> findVersionTest(Test test) {

        List<Integer> listaDatos = new ArrayList<Integer>();

        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT max(u.versionTest) FROM Evaluacion u WHERE u.idPregunta.idTest=:idTest GROUP BY u.evaVersion ORDER BY u.evaVersion ASC ");
            query.setParameter("idTest", test);
            listaDatos = (List<Integer>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta test  findVersionTest  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDatos;
    }

    public List<VersionTest> findByUsuario(Usuario idUsuario) {

        List<VersionTest> listaDatos = new ArrayList<VersionTest>();
        VersionTest test = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM VersionTest u WHERE u.idEmpresa.idUsuario=:idUsuario ");
            query.setParameter("idUsuario", idUsuario);
            listaDatos = (List<VersionTest>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta test  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDatos;
    }

    public List<VersionTest> findByUsuarioVersionTestNombre(Usuario idUsuario, String valor) {

        List<VersionTest> listaDatos = new ArrayList<VersionTest>();
        VersionTest test = null;
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM VersionTest u WHERE u.idEmpresa.idUsuario=:idUsuario AND u.testNombre like :valor ");
            query.setParameter("idUsuario", idUsuario);
            query.setParameter("valor", "%" + valor + "%");
            listaDatos = (List<VersionTest>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Error en lsa consulta test  findAll  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDatos;
    }

}
