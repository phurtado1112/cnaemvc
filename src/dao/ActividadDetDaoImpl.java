/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Actividaddet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author PabloAntonio
 */
public class ActividadDetDaoImpl implements ActividadDetDao{
    
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
    public void guardarActividaddet(Actividaddet ad) {
        try {
            iniciaOperacion();
            sesion.save(ad);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void actualizarActividaddet(Actividaddet ad) {
        try {
            iniciaOperacion();
            sesion.update(ad);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void eliminarActividaddet(Actividaddet ad) {
        try {
            iniciaOperacion();
            sesion.delete(ad);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public Actividaddet obtenActividaddet(int idactividaddet) throws HibernateException {
        Actividaddet actividaddet = null;
        try {
            iniciaOperacion();
            actividaddet = (Actividaddet) sesion.get(Actividaddet.class, idactividaddet);
        } finally {
            sesion.close();
        }

        return actividaddet;
    }

    @Override
    public List<Actividaddet> obtenListaActividaddets() throws HibernateException {
        List<Actividaddet> listaActividaddets = null;
        try {
            iniciaOperacion();
            listaActividaddets = sesion.createQuery("from Actividaddet").list();
        } finally {
            sesion.close();
        }

        return listaActividaddets;
    }
    
}
