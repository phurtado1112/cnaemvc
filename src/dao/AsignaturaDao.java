/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Asignatura;
import org.hibernate.HibernateException;

/**
 *
 * @author PabloAntonio
 */
public interface AsignaturaDao {
    public void guardarAsignatura(Asignatura a);
    public void actualizarAsignatura(Asignatura a);
    public void eliminarAsignatura(Asignatura a);
    public Asignatura obtenAsignatura(int idasignatura) throws HibernateException;
    public List<Asignatura> obtenListaAsignaturas() throws HibernateException;
    public Asignatura obtenerIdAsignatura (String nombre) throws HibernateException;
}
