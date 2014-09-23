/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import dao.EstructuraEvaluacionDaoImpl;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import modelo.Estructuraevaluacion;
import util.Valida;
import vista.EstructuraEvaluacionIF;

/**
 *
 * @author PabloAntonio
 */
public class EstructuraEvaluacionControlador {
    EstructuraEvaluacionIF frmese;
    EstructuraEvaluacionDaoImpl eedi;
    Estructuraevaluacion esev = new Estructuraevaluacion();
    Valida va = new Valida();
    DefaultTableModel modelo;
    DefaultComboBoxModel<String> modeloCombo;

    public EstructuraEvaluacionControlador() {
        
    }

    public EstructuraEvaluacionControlador(EstructuraEvaluacionIF frmese, EstructuraEvaluacionDaoImpl eedi) {
        this.frmese = frmese;
        this.eedi = eedi;
    }
    
    
}
