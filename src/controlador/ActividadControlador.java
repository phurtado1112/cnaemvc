/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import dao.ActividadDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Actividad;
import util.Valida;
import vista.ActividadIF;

/**
 *
 * @author PabloAntonio
 */
public class ActividadControlador {
    ActividadIF frmActi;
    ActividadDaoImpl adi;
    Actividad act = new Actividad();
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
        frmActi.setVisible(true);
        BotonesInicio();
        Deshabilitar();
        defaultTableModel();
        LlenarTabla();
        
        frmActi.btnActualizar.addActionListener(Evento);
        frmActi.btnCancelar.addActionListener(Evento);
        frmActi.btnEliminar.addActionListener(Evento);
        frmActi.btnGuardar.addActionListener(Evento);
        frmActi.btnNuevo.addActionListener(Evento);
        frmActi.btnSalir.addActionListener(Evento);
        frmActi.tblActividad.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblActividadMouseClicked(evt);
            }
        });
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
    
    private boolean validar() {
        boolean val;
        if(frmActi.txtActividad.getText().trim().length()==0){ //Valida campo Nombre
            JOptionPane.showMessageDialog(null, "El campo de texto Actividad está vacío,por favor llenarlo");
            val = false;
        } else {
            val=true;
        }       
        return val;
    }
    
    public void defaultTableModel() {
        frmActi.tblActividad.getColumnModel().getColumn(0).setPreferredWidth(15);
        frmActi.tblActividad.getColumnModel().getColumn(1).setPreferredWidth(260);
        modelo = (DefaultTableModel) frmActi.tblActividad.getModel();
        modelo.setNumRows(0);
    }

    private void LlenarTabla() {
        List<Actividad> listauniv = adi.obtenListaActividades();
        listauniv.stream().forEach((asign) -> {
            modelo.addRow(new Object[]{
                asign.getIdactividad(), asign.getActividad(),
            });
        });
    }
    
    private final ActionListener Evento = (ActionEvent ev) -> {
        if (ev.getSource() == frmActi.btnNuevo) {
            Habilitar();
            limpiar();
            BotonesNuevo();
        }
        
        if (ev.getSource() == frmActi.btnGuardar) {
            if (validar() == true) {
                int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea GUARDAR?", "Confirmar",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (i == JOptionPane.OK_OPTION) {
                    act.setActividad(frmActi.txtActividad.getText().trim());
                    adi.guardarActividad(act);
                    defaultTableModel();
                    LlenarTabla();
                    limpiar();
                    Deshabilitar();
                    BotonesInicio();
                }
            }
        }
        
        if (ev.getSource() == frmActi.btnActualizar) {
            if (validar() == true) {
                int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea ACTUALIZAR?", "Confirmar",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (i == JOptionPane.OK_OPTION) {
                    int fila = frmActi.tblActividad.getSelectedRow();
                    act.setIdactividad(Integer.parseInt(frmActi.tblActividad.getValueAt(fila, 0).toString()));
                    act.setActividad(frmActi.txtActividad.getText().trim());
                    adi.actualizarActividad(act);
                    defaultTableModel();
                    LlenarTabla();
                    limpiar();
                    Deshabilitar();
                    BotonesInicio();
                }
            }
        }
        
        if (ev.getSource() == frmActi.btnEliminar) {
            int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea ELIMINAR?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                int fila = frmActi.tblActividad.getSelectedRow();
                Actividad activ = adi.obtenActividad(Integer.parseInt(frmActi.tblActividad.getValueAt(fila, 0).toString()));
                adi.eliminarActividad(activ);
            }
            defaultTableModel();
            LlenarTabla();
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }
        
        if (ev.getSource() == frmActi.btnCancelar) {
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }

        if (ev.getSource() == frmActi.btnSalir) {
            System.out.println("boton Salir listo");
            int i = JOptionPane.showConfirmDialog(null, "Desea Salir?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                frmActi.dispose();
            }
        }
        
    };
    
    public void tblActividadMouseClicked(MouseEvent e) {
        if( e.getButton()== 1)//boton izquierdo
        {
             int fila = frmActi.tblActividad.getSelectedRow();
             Habilitar();
             if (fila > -1){           
                this.frmActi.txtActividad.setText(String.valueOf(this.frmActi.tblActividad.getValueAt(fila, 1) ));
             }
        }
        BotonesClick();
    }
}
