/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Calendario;
import org.hibernate.HibernateException;

/**
 *
 * @author PabloAntonio
 */
public interface CalendarioDao {
    public void guardarCalendario(Calendario c);
    public void actualizarCalendario(Calendario c);
    public void eliminarCalendario(Calendario c);
    public Calendario obtenCalendario(int idcalendario) throws HibernateException;
    public List<Calendario> obtenListaCalendarios() throws HibernateException;
}
