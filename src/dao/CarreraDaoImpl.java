/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Calendario;
import modelo.Carrera;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author PabloAntonio
 */
public class CarreraDaoImpl implements CarreraDao{
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
    public void guardarCarrera(Carrera c) {
        try {
            iniciaOperacion();
            sesion.save(c);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void actualizarCarrera(Carrera c) {
        try {
            iniciaOperacion();
            sesion.update(c);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void eliminarCarrera(Carrera c) {
        try {
            iniciaOperacion();
            sesion.delete(c);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public Carrera obtenCarrera(int idcarrera) throws HibernateException {
        Carrera carrera = null;
        try {
            iniciaOperacion();
            carrera = (Carrera) sesion.get(Carrera.class, idcarrera);
        } finally {
            sesion.close();
        }
        return carrera;
    }

    @Override
    public List<Carrera> obtenListaCarreras() throws HibernateException {
        List<Carrera> listaCarreras = null;
        try {
            iniciaOperacion();
            listaCarreras = sesion.createQuery("from Carrera").list();
        } finally {
            sesion.close();
        }
        return listaCarreras;
    }
}
