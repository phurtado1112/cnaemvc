/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Actividad;
import org.hibernate.HibernateException;

/**
 *
 * @author PabloAntonio
 */
public interface ActividadDao {
    public void guardarActividad(Actividad a);
    public void actualizarActividad(Actividad a);
    public void eliminarActividad(Actividad a);
    public Actividad obtenActividad(int idactividad) throws HibernateException;
    public List<Actividad> obtenListaActividades() throws HibernateException;
}
