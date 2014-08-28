/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Calendario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author PabloAntonio
 */
public class CalendarioDaoImpl implements CalendarioDao {
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
    public void guardarCalendario(Calendario c) {
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
    public void actualizarCalendario(Calendario c) {
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
    public void eliminarCalendario(Calendario c) {
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
    public Calendario obtenCalendario(int idcalendario) throws HibernateException {
        Calendario calendario = null;
        try {
            iniciaOperacion();
            calendario = (Calendario) sesion.get(Calendario.class, idcalendario);
        } finally {
            sesion.close();
        }
        return calendario;
    }

    @Override
    public List<Calendario> obtenListaCalendarios() throws HibernateException {
        List<Calendario> listaCalendarios = null;
        try {
            iniciaOperacion();
            listaCalendarios = sesion.createQuery("from Calendario").list();
        } finally {
            sesion.close();
        }
        return listaCalendarios;
    }
}
