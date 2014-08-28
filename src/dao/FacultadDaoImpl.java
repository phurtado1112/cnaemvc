/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Facultad;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author PabloAntonio
 */
public class FacultadDaoImpl implements FacultadDao{
    
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
    public void guardarFacultad(Facultad f) {
        try {
            iniciaOperacion();
            sesion.save(f);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void actualizarFacultad(Facultad f) {
        try {
            iniciaOperacion();
            sesion.update(f);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void eliminarFacultad(Facultad f) {
        try {
            iniciaOperacion();
            sesion.delete(f);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public Facultad obtenFacultad(int idfacultad) throws HibernateException {
        Facultad facultad = null;
        try {
            iniciaOperacion();
            facultad = (Facultad) sesion.get(Facultad.class, idfacultad);
        } finally {
            sesion.close();
        }
        return facultad;
    }

    @Override
    public List<Facultad> obtenListaFacultad() throws HibernateException {
        List<Facultad> listaFacultades = null;

        try {
            iniciaOperacion();
            listaFacultades = sesion.createQuery("from Facultad").list();
        } finally {
            sesion.close();
        }

        return listaFacultades;
    }
    
}
