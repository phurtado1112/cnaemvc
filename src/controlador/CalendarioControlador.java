/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import dao.CalendarioDaoImpl;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import modelo.Calendario;
import util.Valida;
import vista.CalendarioIF;

/**
 *
 * @author PabloAntonio
 */
public class CalendarioControlador {
    CalendarioIF frmCal;
    CalendarioDaoImpl cdi;
    Calendario cale = new Calendario();
    Valida va = new Valida();
    DefaultTableModel modelo;
    DefaultComboBoxModel<String> modeloCombo;

    public CalendarioControlador() {
        
    }

    public CalendarioControlador(CalendarioIF frmCal, CalendarioDaoImpl cdi) {
        this.frmCal = frmCal;
        this.cdi = cdi;
    }
    
    
}
