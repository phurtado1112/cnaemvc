/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Actividaddet;
import org.hibernate.HibernateException;

/**
 *
 * @author PabloAntonio
 */
public interface ActividadDetDao {
    public void guardarActividaddet(Actividaddet ad);
    public void actualizarActividaddet(Actividaddet ad);
    public void eliminarActividaddet(Actividaddet ad);
    public Actividaddet obtenActividaddet(int idactividaddet) throws HibernateException;
    public List<Actividaddet> obtenListaActividaddets() throws HibernateException;
}
