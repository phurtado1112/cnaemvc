/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import dao.ActividadDetDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Actividad;
import modelo.Actividaddet;
import util.Valida;
import vista.ActividadDetIF;

/**
 *
 * @author PabloAntonio
 */
public class ActividadDetControlador {
    ActividadDetIF frmActiDet;
    ActividadDetDaoImpl addi;
    Actividaddet actd = new Actividaddet();
    Valida va = new Valida();
    DefaultTableModel modelo;
    DefaultComboBoxModel<String> modeloCombo;
    int id = 1;

    public ActividadDetControlador() {
        
    }

    public ActividadDetControlador(ActividadDetIF frmActiDet, ActividadDetDaoImpl addi) {
        this.frmActiDet = frmActiDet;
        this.addi = addi;
        inicio();
    }
    
    private void inicio(){
        frmActiDet.setVisible(true);
        BotonesInicio();
        Deshabilitar();
        defaultTableModel();
        LlenarTabla();
        
        frmActiDet.btnActualizar.addActionListener(Evento);
        frmActiDet.btnCancelar.addActionListener(Evento);
        frmActiDet.btnEliminar.addActionListener(Evento);
        frmActiDet.btnGuardar.addActionListener(Evento);
        frmActiDet.btnNuevo.addActionListener(Evento);
        frmActiDet.btnSalir.addActionListener(Evento);
        frmActiDet.tblActividadDet.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblActividadDetMouseClicked(evt);
            }
        });
    }
    
    private void limpiar() {
        frmActiDet.txtActividadDet.setText("");
    }

    private void Deshabilitar() {
        frmActiDet.txtActividadDet.setEnabled(false);
        frmActiDet.cbxActividad.removeAllItems();
    }

    private void Habilitar() {
        frmActiDet.txtActividadDet.setEnabled(true);
        va.SoloLetras(frmActiDet.txtActividadDet);
        va.SeleccionarTodo(frmActiDet.txtActividadDet);
        frmActiDet.txtActividadDet.requestFocus();
    }

    private void BotonesInicio() {
        frmActiDet.btnNuevo.setEnabled(true);
        frmActiDet.btnActualizar.setEnabled(false);
        frmActiDet.btnEliminar.setEnabled(false);
        frmActiDet.btnGuardar.setEnabled(false);
        frmActiDet.btnCancelar.setEnabled(false);
    }

    private void BotonesNuevo() {
        frmActiDet.btnNuevo.setEnabled(false);
        frmActiDet.btnActualizar.setEnabled(false);
        frmActiDet.btnEliminar.setEnabled(false);
        frmActiDet.btnGuardar.setEnabled(true);
        frmActiDet.btnCancelar.setEnabled(true);
    }

    private void BotonesClick() {
        frmActiDet.btnNuevo.setEnabled(false);
        frmActiDet.btnGuardar.setEnabled(false);
        frmActiDet.btnActualizar.setEnabled(true);
        frmActiDet.btnCancelar.setEnabled(true);
        frmActiDet.btnEliminar.setEnabled(true);
    }
    
    private boolean validar() {
        boolean val;
        if(frmActiDet.txtActividadDet.getText().trim().length()==0){ //Valida campo Nombre
            JOptionPane.showMessageDialog(null, "El campo de texto Actividad está vacío,por favor llenarlo");
            val = false;
        } else {
            val=true;
        }       
        return val;
    }
    
    private void defaultTableModel() {
        frmActiDet.tblActividadDet.getColumnModel().getColumn(0).setPreferredWidth(15);
        frmActiDet.tblActividadDet.getColumnModel().getColumn(1).setPreferredWidth(130);
        frmActiDet.tblActividadDet.getColumnModel().getColumn(2).setPreferredWidth(130);
        modelo = (DefaultTableModel) frmActiDet.tblActividadDet.getModel();
        modelo.setNumRows(0);
    }

    private void LlenarTabla() {
        List<Actividaddet> listaactdet = addi.obtenListaActividaddets();
        listaactdet.stream().forEach((asign) -> {
            modelo.addRow(new Object[]{
                asign.getIdactividaddet(), asign.getActividaddet(), asign.getActividad()
            });
        });
    }
    
     public final void llenarCB() {
         modeloCombo = new DefaultComboBoxModel<>();
         List<Actividaddet> listaactdet = addi.obtenListaActividaddets();
         listaactdet.stream().forEach((asign) -> {
             modeloCombo.addElement(asign.getActividad().getActividad());
         });
    }
     
    private final ActionListener Evento = (ActionEvent ev) -> {
        if (ev.getSource() == frmActiDet.btnNuevo) {
            Habilitar();
            limpiar();
            BotonesNuevo();
            llenarCB();
        }
        
        if (ev.getSource() == frmActiDet.btnGuardar) {
            if (validar() == true) {
                int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea GUARDAR?", "Confirmar",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (i == JOptionPane.OK_OPTION) {
                    actd.setActividaddet(frmActiDet.txtActividadDet.getText().trim());
                    actd.setActividad((Actividad) frmActiDet.cbxActividad.getSelectedItem());
                    addi.guardarActividaddet(actd);
                    defaultTableModel();
                    LlenarTabla();
                    limpiar();
                    Deshabilitar();
                    BotonesInicio();
                }
            }
        }
        
        if (ev.getSource() == frmActiDet.btnActualizar) {
            if (validar() == true) {
                int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea ACTUALIZAR?", "Confirmar",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (i == JOptionPane.OK_OPTION) {
                    int fila = frmActiDet.tblActividadDet.getSelectedRow();
                    actd.setIdactividaddet(Integer.parseInt(frmActiDet.tblActividadDet.getValueAt(fila, 0).toString()));
                    actd.setActividaddet(frmActiDet.txtActividadDet.getText().trim());
                    actd.setActividad((Actividad)frmActiDet.cbxActividad.getSelectedItem());
                    addi.actualizarActividaddet(actd);
                    defaultTableModel();
                    LlenarTabla();
                    limpiar();
                    Deshabilitar();
                    BotonesInicio();
                }
            }
        }
        
        if (ev.getSource() == frmActiDet.btnEliminar) {
            int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea ELIMINAR?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                int fila = frmActiDet.tblActividadDet.getSelectedRow();
                Actividaddet actdet = addi.obtenActividaddet(Integer.parseInt(frmActiDet.tblActividadDet.getValueAt(fila, 0).toString()));
                addi.eliminarActividaddet(actdet);
            }
            defaultTableModel();
            LlenarTabla();
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }
        
        if (ev.getSource() == frmActiDet.btnCancelar) {
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }

        if (ev.getSource() == frmActiDet.btnSalir) {
            System.out.println("boton Salir listo");
            int i = JOptionPane.showConfirmDialog(null, "Desea Salir?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                frmActiDet.dispose();
            }
        }
        
    };
    
    private void tblActividadDetMouseClicked(MouseEvent e) {
        if( e.getButton()== 1)//boton izquierdo
        {
             int fila = frmActiDet.tblActividadDet.getSelectedRow();
             Habilitar();
             if (fila > -1){           
                this.frmActiDet.txtActividadDet.setText(String.valueOf(this.frmActiDet.tblActividadDet.getValueAt(fila, 1) ));
             }
        }
        BotonesClick();
    }
}
