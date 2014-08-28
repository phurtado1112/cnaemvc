/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Universidad;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author PabloAntonio
 */
public class UniversidadDaoImpl implements UniversidadDao {

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
    public void guardarUniversidad(Universidad u) {
        try {
            iniciaOperacion();
            sesion.save(u);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void actualizarUniversidad(Universidad u) {
        try {
            iniciaOperacion();
            sesion.update(u);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void eliminarUniversidad(Universidad u) {
        try {
            iniciaOperacion();
            sesion.delete(u);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public Universidad obtenUniversidad(int iduniversidad) throws HibernateException {
        Universidad universidad = null;
        try {
            iniciaOperacion();
            universidad = (Universidad) sesion.get(Universidad.class, iduniversidad);
        } finally {
            sesion.close();
        }
        return universidad;
    }

    @Override
    public List<Universidad> obtenListaUniversidades() throws HibernateException {
        List<Universidad> listaUniversidades = null;

        try {
            iniciaOperacion();
            listaUniversidades = sesion.createQuery("from Universidad").list();
        } finally {
            sesion.close();
        }
        return listaUniversidades;
    }
}
