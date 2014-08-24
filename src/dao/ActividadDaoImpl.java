/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Actividad;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author PabloAntonio
 */
public class ActividadDaoImpl implements ActividadDao {
    private Session sesion;
    private Transaction tx;
    
    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en UniversidadDao", he);
    }

    @Override
    public void guardarActividad(Actividad a) {
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
    public void actualizarActividad(Actividad a) {
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
    public void eliminarActividad(Actividad a) {
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
    public Actividad obtenActividad(int idactividad) throws HibernateException {
        Actividad actividad = null;
        try {
            iniciaOperacion();
            actividad = (Actividad) sesion.get(Actividad.class, idactividad);
        } finally {
            sesion.close();
        }

        return actividad;
    }

    @Override
    public List<Actividad> obtenListaActividades() throws HibernateException {
        List<Actividad> listaActividades = null;

        try {
            iniciaOperacion();
            listaActividades = sesion.createQuery("from Actividad").list();
        } finally {
            sesion.close();
        }

        return listaActividades;
    }
    
}
