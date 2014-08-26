/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Estudiantes;
import org.hibernate.HibernateException;

/**
 *
 * @author PabloAntonio
 */
public interface EstudianteDao {
    public void guardarEstudiante(Estudiantes e);
    public void actualizarEstudiante(Estudiantes e);
    public void eliminarEstudiante(Estudiantes e);
    public Estudiantes obtenEstudiante(int idestudiante) throws HibernateException;
    public List<Estudiantes> obtenListaEstudiante() throws HibernateException;
}
