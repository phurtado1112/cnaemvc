/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import dao.CarreraDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Carrera;
import modelo.Facultad;
import modelo.Universidad;
import util.Valida;
import vista.CarreraIF;

/**
 *
 * @author PabloAntonio
 */
public class CarreraControlador {
    CarreraIF frmCar;
    CarreraDaoImpl cdi;
    Carrera carr = new Carrera();
    Facultad facu = new Facultad();
    Universidad Univ = new Universidad();
    Valida va = new Valida();
    DefaultTableModel modelo;
    DefaultComboBoxModel<String> modeloComboUni, modeloComboFac;

    public CarreraControlador() {
        
    }

    public CarreraControlador(CarreraIF frmCar, CarreraDaoImpl cdi) {
        this.frmCar = frmCar;
        this.cdi = cdi;
        inicio();
    }
    
    private void inicio() {
        frmCar.setVisible(true);
        BotonesInicio();
        Deshabilitar();
        defaultTableModel();
        LlenarTabla();

        frmCar.btnActualizar.addActionListener(Evento);
        frmCar.btnCancelar.addActionListener(Evento);
        frmCar.btnEliminar.addActionListener(Evento);
        frmCar.btnGuardar.addActionListener(Evento);
        frmCar.btnNuevo.addActionListener(Evento);
        frmCar.btnSalir.addActionListener(Evento);
        frmCar.tblCarrera.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCarreraMouseClicked(evt);
            }
        });
    }

    private void limpiar() {
        frmCar.txtCarrera.setText("");
        frmCar.cbxFacultad.removeAllItems();
        frmCar.cbxUniversidad.removeAllItems();
    }

    private void Deshabilitar() {
        frmCar.txtCarrera.setEnabled(false);
        frmCar.cbxFacultad.setEnabled(false);
        frmCar.cbxUniversidad.setEnabled(false);
    }

    private void Habilitar() {
        frmCar.txtCarrera.setEnabled(true);
        va.SoloLetras(frmCar.txtCarrera);
        va.SeleccionarTodo(frmCar.txtCarrera);
        frmCar.txtCarrera.requestFocus();
        frmCar.cbxFacultad.setEnabled(true);
        frmCar.cbxUniversidad.setEnabled(true);
    }

    private void BotonesInicio() {
        frmCar.btnNuevo.setEnabled(true);
        frmCar.btnActualizar.setEnabled(false);
        frmCar.btnEliminar.setEnabled(false);
        frmCar.btnGuardar.setEnabled(false);
        frmCar.btnCancelar.setEnabled(false);
    }

    private void BotonesNuevo() {
        frmCar.btnNuevo.setEnabled(false);
        frmCar.btnActualizar.setEnabled(false);
        frmCar.btnEliminar.setEnabled(false);
        frmCar.btnGuardar.setEnabled(true);
        frmCar.btnCancelar.setEnabled(true);
    }

    private void BotonesClick() {
        frmCar.btnNuevo.setEnabled(false);
        frmCar.btnGuardar.setEnabled(false);
        frmCar.btnActualizar.setEnabled(true);
        frmCar.btnCancelar.setEnabled(true);
        frmCar.btnEliminar.setEnabled(true);
    }

    private boolean validar() {
        boolean val;
        if (frmCar.txtCarrera.getText().trim().length() == 0) { //Valida campo Nombre
            JOptionPane.showMessageDialog(null, "El campo de texto Carrera está vacío,por favor llenarlo");
            val = false;
        } else {
            val = true;
        }
        return val;
    }

    private void defaultTableModel() {
        frmCar.tblCarrera.getColumnModel().getColumn(0).setPreferredWidth(15);
        frmCar.tblCarrera.getColumnModel().getColumn(1).setPreferredWidth(100);
        frmCar.tblCarrera.getColumnModel().getColumn(2).setPreferredWidth(100);
        frmCar.tblCarrera.getColumnModel().getColumn(3).setPreferredWidth(100);
        modelo = (DefaultTableModel) frmCar.tblCarrera.getModel();
        modelo.setNumRows(0);
    }

    private void LlenarTabla() {
        List<Carrera> listacarr = cdi.obtenListaCarreras();
        listacarr.stream().forEach((asign) -> {
            modelo.addRow(new Object[]{
                asign.getIdcarrera(), asign.getNombreC(), asign.getFacultad().getNombreF(), asign.getFacultad().getUniversidad().getNombreU()
            });
        });
    }

    private void llenarCBF() {
        modeloComboFac = new DefaultComboBoxModel<>();
        List<Carrera> listacar = cdi.obtenListaCarreras();
        listacar.stream().forEach((asign) -> {
            modeloComboFac.addElement(asign.getFacultad().getNombreF());
        });
    }
    
    private void llenarCBU() {
        modeloComboFac = new DefaultComboBoxModel<>();
        List<Carrera> listacar = cdi.obtenListaCarreras();
        listacar.stream().forEach((asign) -> {
            modeloComboFac.addElement(asign.getFacultad().getUniversidad().getNombreU());
        });
    }
    
    private final ActionListener Evento = (ActionEvent ev) -> {
        if (ev.getSource() == frmCar.btnNuevo) {
            Habilitar();
            limpiar();
            BotonesNuevo();
            llenarCBF();
            llenarCBU();
        }

        if (ev.getSource() == frmCar.btnGuardar) {
            if (validar() == true) {
                int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea GUARDAR?", "Confirmar",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (i == JOptionPane.OK_OPTION) {
                    carr.setNombreC(frmCar.txtCarrera.getText().trim());
//                    carr.setFacultad((Facultad) frmCar.cbxFacultad.getSelectedItem());
                    cdi.guardarCarrera(carr);
                    defaultTableModel();
                    LlenarTabla();
                    limpiar();
                    Deshabilitar();
                    BotonesInicio();
                }
            }
        }

        if (ev.getSource() == frmCar.btnActualizar) {
            if (validar() == true) {
                int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea ACTUALIZAR?", "Confirmar",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (i == JOptionPane.OK_OPTION) {
                    int fila = frmCar.tblCarrera.getSelectedRow();
                    carr.setIdcarrera(Integer.parseInt(frmCar.tblCarrera.getValueAt(fila, 0).toString()));
                    carr.setNombreC(frmCar.txtCarrera.getText().trim());
//                    carr.setFacultad((Facultad) frmCar.cbxFacultad.getSelectedItem());
                    cdi.actualizarCarrera(carr);
                    defaultTableModel();
                    LlenarTabla();
                    limpiar();
                    Deshabilitar();
                    BotonesInicio();
                }
            }
        }

        if (ev.getSource() == frmCar.btnEliminar) {
            int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea ELIMINAR?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                int fila = frmCar.tblCarrera.getSelectedRow();
                Carrera carrer = cdi.obtenCarrera(Integer.parseInt(frmCar.tblCarrera.getValueAt(fila, 0).toString()));
                cdi.eliminarCarrera(carrer);
            }
            defaultTableModel();
            LlenarTabla();
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }

        if (ev.getSource() == frmCar.btnCancelar) {
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }

        if (ev.getSource() == frmCar.btnSalir) {
            System.out.println("boton Salir listo");
            int i = JOptionPane.showConfirmDialog(null, "Desea Salir?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                frmCar.dispose();
            }
        }

    };

    private void tblCarreraMouseClicked(MouseEvent e) {
        if (e.getButton() == 1)//boton izquierdo
        {
            int fila = frmCar.tblCarrera.getSelectedRow();
            Habilitar();
            llenarCBF();
            llenarCBU();
            if (fila > -1) {
                this.frmCar.txtCarrera.setText(String.valueOf(this.frmCar.tblCarrera.getValueAt(fila, 1)));
            }
        }
        BotonesClick();
    }
}
