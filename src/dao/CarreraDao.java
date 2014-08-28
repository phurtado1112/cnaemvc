/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Carrera;
import org.hibernate.HibernateException;

/**
 *
 * @author PabloAntonio
 */
public interface CarreraDao {
    public void guardarCarrera(Carrera c);
    public void actualizarCarrera(Carrera c);
    public void eliminarCarrera(Carrera c);
    public Carrera obtenCarrera(int idcarrera) throws HibernateException;
    public List<Carrera> obtenListaCarreras() throws HibernateException;
}
