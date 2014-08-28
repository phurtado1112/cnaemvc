/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Docente;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author PabloAntonio
 */
public class DocenteDaoImpl implements DocenteDao {
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
    public void guardarDocente(Docente d) {
        try {
            iniciaOperacion();
            sesion.save(d);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void actualizarDocente(Docente d) {
        try {
            iniciaOperacion();
            sesion.update(d);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void eliminarDocente(Docente d) {
        try {
            iniciaOperacion();
            sesion.delete(d);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public Docente obtenDocente(int iddocente) throws HibernateException {
        Docente docente = null;
        try {
            iniciaOperacion();
            docente = (Docente) sesion.get(Docente.class, iddocente);
        } finally {
            sesion.close();
        }
        return docente;
    }

    @Override
    public List<Docente> obtenListaDocentes() throws HibernateException {
        List<Docente> listaDocentes = null;
        try {
            iniciaOperacion();
            listaDocentes = sesion.createQuery("from Docente").list();
        } finally {
            sesion.close();
        }
        return listaDocentes;
    }
    
    @Override
    public Docente verificarDatos(Docente d) throws HibernateException {
        Docente doc = null;
        try {
            iniciaOperacion();
            String hql = "FROM Docente WHERE usuario = '" + d.getUsuario()
                    + "' and contrasena = '" + d.getContrasena() + "'";
            Query query = sesion.createQuery(hql);

            if (!query.list().isEmpty()) {
                doc = (Docente) query.list().get(0);
            }
        } catch (HibernateException e) {
            throw e;
        }
        return doc;
    }

    @Override
    public String obtenerContraseniaDocente(String usuario) throws HibernateException {
        String contrase;
        try {
            iniciaOperacion();
            contrase = (String) sesion.createQuery("select d.contrasena from Docente d where d.usuario = :usuario").setString("usuario", usuario).uniqueResult();
        } finally {
            sesion.close();
        }
        return contrase;
    }
}
