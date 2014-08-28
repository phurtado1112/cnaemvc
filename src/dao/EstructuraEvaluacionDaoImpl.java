/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Estructuraevaluacion;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author PabloAntonio
 */
public class EstructuraEvaluacionDaoImpl implements EstructuraEvaluacionDao{
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
    public void guardarEstructuraEvaluacion(Estructuraevaluacion ee) {
        try {
            iniciaOperacion();
            sesion.save(ee);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void actualizarEstructuraEvaluacion(Estructuraevaluacion ee) {
        try {
            iniciaOperacion();
            sesion.update(ee);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void eliminarEstructuraEvaluacion(Estructuraevaluacion ee) {
        try {
            iniciaOperacion();
            sesion.delete(ee);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public Estructuraevaluacion obtenEstructuraEvaluacion(int idestructuraevaluacion) throws HibernateException {
        Estructuraevaluacion estructuraevaluacion = null;
        try {
            iniciaOperacion();
            estructuraevaluacion = (Estructuraevaluacion) sesion.get(Estructuraevaluacion.class, idestructuraevaluacion);
        } finally {
            sesion.close();
        }
        return estructuraevaluacion;
    }

    @Override
    public List<Estructuraevaluacion> obtenListaEstructuraEvaluaciones() throws HibernateException {
        List<Estructuraevaluacion> listaEstructuraevaluacion = null;
        try {
            iniciaOperacion();
            listaEstructuraevaluacion = sesion.createQuery("from Estructuraevaluacion").list();
        } finally {
            sesion.close();
        }
        return listaEstructuraevaluacion;
    }
    
}
