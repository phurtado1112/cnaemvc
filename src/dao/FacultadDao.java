/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Facultad;
import org.hibernate.HibernateException;

/**
 *
 * @author PabloAntonio
 */
public interface FacultadDao {
    public void guardarFacultad(Facultad f);
    public void actualizarFacultad(Facultad f);
    public void eliminarFacultad(Facultad f);
    public Facultad obtenFacultad(int idfacultad) throws HibernateException;
    public List<Facultad> obtenListaFacultad() throws HibernateException;
}
