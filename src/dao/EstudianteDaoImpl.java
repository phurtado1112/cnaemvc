/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Estudiantes;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author PabloAntonio
 */
public class EstudianteDaoImpl implements EstudianteDao{
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
    public void guardarEstudiante(Estudiantes e) {
        try {
            iniciaOperacion();
            sesion.save(e);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void actualizarEstudiante(Estudiantes e) {
        try {
            iniciaOperacion();
            sesion.update(e);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void eliminarEstudiante(Estudiantes e) {
        try {
            iniciaOperacion();
            sesion.delete(e);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public Estudiantes obtenEstudiante(int idestudiante) throws HibernateException {        
        Estudiantes estudiantes = null;
        try {
            iniciaOperacion();
            estudiantes = (Estudiantes) sesion.get(Estudiantes.class, idestudiante);
        } finally {
            sesion.close();
        }

        return estudiantes;
    }

    @Override
    public List<Estudiantes> obtenListaEstudiante() throws HibernateException {
        List<Estudiantes> listaEstudiantes = null;

        try {
            iniciaOperacion();
            listaEstudiantes = sesion.createQuery("from Estudiantes").list();
        } finally {
            sesion.close();
        }

        return listaEstudiantes;
    }
}
