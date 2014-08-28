/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Estructuraevaluacion;
import org.hibernate.HibernateException;

/**
 *
 * @author PabloAntonio
 */
public interface EstructuraEvaluacionDao {
    public void guardarEstructuraEvaluacion(Estructuraevaluacion ee);
    public void actualizarEstructuraEvaluacion(Estructuraevaluacion ee);
    public void eliminarEstructuraEvaluacion(Estructuraevaluacion ee);
    public Estructuraevaluacion obtenEstructuraEvaluacion(int idestructuraevaluacion) throws HibernateException;
    public List<Estructuraevaluacion> obtenListaEstructuraEvaluaciones() throws HibernateException;
}
