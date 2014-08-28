/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Asignatura;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author PabloAntonio
 */
public class AsignaturaDaoImpl implements AsignaturaDao {
    private Session sesion;
    private Transaction tx;
    
    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en DocenteDao", he);
    }

    @Override
    public void guardarAsignatura(Asignatura a) {
        try {
            iniciaOperacion();
            sesion.save(a);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void actualizarAsignatura(Asignatura a) {
        try {
            iniciaOperacion();
            sesion.update(a);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void eliminarAsignatura(Asignatura a) {
        try {
            iniciaOperacion();
            sesion.delete(a);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public Asignatura obtenAsignatura(int idasignatura) throws HibernateException {
        Asignatura asignatura = null;
        try {
            iniciaOperacion();
            asignatura = (Asignatura) sesion.get(Asignatura.class, idasignatura);
        } finally {
            sesion.close();
        }
        return asignatura;
    }

    @Override
    public List<Asignatura> obtenListaAsignaturas() throws HibernateException {
        List<Asignatura> listaAsignaturas = null;
        try {
            iniciaOperacion();
            listaAsignaturas = sesion.createQuery("from Asignaturas").list();
        } finally {
            sesion.close();
        }
        return listaAsignaturas;
    }

    @Override
    public Asignatura obtenerIdAsignatura(String nombre) throws HibernateException {
        Asignatura asignatura = null;
        try {
            iniciaOperacion();
            asignatura = (Asignatura) sesion.get(Asignatura.class, nombre);
        } finally {
            sesion.close();
        }
        return asignatura;
    }    
}
