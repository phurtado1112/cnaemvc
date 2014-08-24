/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Docente;
import org.hibernate.HibernateException;

/**
 *
 * @author PabloAntonio
 */
public interface DocenteDao {
    public void guardarDocente(Docente d);
    public void actualizarDocente(Docente d);
    public void eliminarDocente(Docente d);
    public Docente obtenDocente(int iddocente) throws HibernateException;
    public List<Docente> obtenListaDocentes() throws HibernateException;
    public String obtenerContraseniaDocente (String usuario) throws HibernateException;
    public Docente verificarDatos(Docente d) throws HibernateException;
}
