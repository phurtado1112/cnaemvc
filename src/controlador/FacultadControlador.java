/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import dao.UniversidadDaoImpl;
import javax.swing.table.DefaultTableModel;
import modelo.Universidad;
import util.Valida;
import vista.FacultadIF;
import vista.inicio.Cnae;

/**
 *
 * @author PabloAntonio
 */
public class FacultadControlador {
    FacultadIF frmFac ;
    UniversidadDaoImpl udi;
    Universidad univ = new Universidad();
    Valida va = new Valida();
    Cnae cnae = new Cnae();
    public DefaultTableModel modelo;
}
