/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import dao.ActividadDaoImpl;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.Actividad;
import modelo.Universidad;
import util.Valida;
import vista.ActividadIF;

/**
 *
 * @author PabloAntonio
 */
public class ActividadControlador {
    ActividadIF frmActi;
    ActividadDaoImpl adi;
    Actividad act;
    Valida va = new Valida();
    DefaultTableModel modelo;

    public ActividadControlador() {
    }

    public ActividadControlador(ActividadIF frmActi, ActividadDaoImpl adi) {
        this.frmActi = frmActi;
        this.adi = adi;
        inicio();
    }
    
    private void inicio(){
        
    }
    
    public void limpiar() {
        frmActi.txtActividad.setText("");
    }

    private void Deshabilitar() {
        frmActi.txtActividad.setEnabled(false);
    }

    public void Habilitar() {
        frmActi.txtActividad.setEnabled(true);
        va.SoloLetras(frmActi.txtActividad);
        va.SeleccionarTodo(frmActi.txtActividad);
        frmActi.txtActividad.requestFocus();
    }

    private void BotonesInicio() {
        frmActi.btnNuevo.setEnabled(true);
        frmActi.btnActualizar.setEnabled(false);
        frmActi.btnEliminar.setEnabled(false);
        frmActi.btnGuardar.setEnabled(false);
        frmActi.btnCancelar.setEnabled(false);
    }

    private void BotonesNuevo() {
        frmActi.btnNuevo.setEnabled(false);
        frmActi.btnActualizar.setEnabled(false);
        frmActi.btnEliminar.setEnabled(false);
        frmActi.btnGuardar.setEnabled(true);
        frmActi.btnCancelar.setEnabled(true);
    }

    private void BotonesClick() {
        frmActi.btnNuevo.setEnabled(false);
        frmActi.btnGuardar.setEnabled(false);
        frmActi.btnActualizar.setEnabled(true);
        frmActi.btnCancelar.setEnabled(true);
        frmActi.btnEliminar.setEnabled(true);
    }
    
    public void defaultTableModel() {
        frmActi.tblActividad.getColumnModel().getColumn(0).setPreferredWidth(15);
        frmActi.tblActividad.getColumnModel().getColumn(1).setPreferredWidth(260);
        frmActi.tblActividad.getColumnModel().getColumn(2).setPreferredWidth(30);
        modelo = (DefaultTableModel) frmActi.tblActividad.getModel();
        modelo.setNumRows(0);
    }

    private void LlenarTabla() {
        List<Universidad> listauniv = adi.obtenListaUniversidades();
        listauniv.stream().forEach((asign) -> {
            modelo.addRow(new Object[]{
                asign.getIduniversidad(), asign.getNombreU(), asign.getSiglas(),
            });
        });
    }
}
