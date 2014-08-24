/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Universidad;
import org.hibernate.HibernateException;

/**
 *
 * @author PabloAntonio
 */
public interface UniversidadDao {
    public void guardarUniversidad(Universidad u);
    public void actualizarUniversidad(Universidad u);
    public void eliminarUniversidad(Universidad u);
    public Universidad obtenUniversidad(int iduniversidad) throws HibernateException;
    public List<Universidad> obtenListaUniversidades() throws HibernateException;
}
