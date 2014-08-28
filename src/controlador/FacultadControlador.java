/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.FacultadDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Actividad;
import modelo.Actividaddet;
import modelo.Facultad;
import modelo.Universidad;
import util.Valida;
import vista.FacultadIF;

/**
 *
 * @author PabloAntonio
 */
public class FacultadControlador {

    FacultadIF frmFac;
    FacultadDaoImpl fdi;
    Facultad facu = new Facultad();
    Valida va = new Valida();
    DefaultTableModel modelo;
    DefaultComboBoxModel<String> modeloCombo;

    public FacultadControlador() {

    }

    public FacultadControlador(FacultadIF frmFac, FacultadDaoImpl fdi) {
        this.frmFac = frmFac;
        this.fdi = fdi;
        inicio();
    }

    private void inicio() {
        frmFac.setVisible(true);
        BotonesInicio();
        Deshabilitar();
        defaultTableModel();
        LlenarTabla();

        frmFac.btnActualizar.addActionListener(Evento);
        frmFac.btnCancelar.addActionListener(Evento);
        frmFac.btnEliminar.addActionListener(Evento);
        frmFac.btnGuardar.addActionListener(Evento);
        frmFac.btnNuevo.addActionListener(Evento);
        frmFac.btnSalir.addActionListener(Evento);
        frmFac.tblFacultad.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFacultadMouseClicked(evt);
            }
        });
    }

    private void limpiar() {
        frmFac.txtFacultad.setText("");
        frmFac.cbxUniversidad.removeAllItems();
    }

    private void Deshabilitar() {
        frmFac.txtFacultad.setEnabled(false);
        frmFac.cbxUniversidad.setEnabled(false);
    }

    private void Habilitar() {
        frmFac.txtFacultad.setEnabled(true);
        va.SoloLetras(frmFac.txtFacultad);
        va.SeleccionarTodo(frmFac.txtFacultad);
        frmFac.txtFacultad.requestFocus();
    }

    private void BotonesInicio() {
        frmFac.btnNuevo.setEnabled(true);
        frmFac.btnActualizar.setEnabled(false);
        frmFac.btnEliminar.setEnabled(false);
        frmFac.btnGuardar.setEnabled(false);
        frmFac.btnCancelar.setEnabled(false);
    }

    private void BotonesNuevo() {
        frmFac.btnNuevo.setEnabled(false);
        frmFac.btnActualizar.setEnabled(false);
        frmFac.btnEliminar.setEnabled(false);
        frmFac.btnGuardar.setEnabled(true);
        frmFac.btnCancelar.setEnabled(true);
    }

    private void BotonesClick() {
        frmFac.btnNuevo.setEnabled(false);
        frmFac.btnGuardar.setEnabled(false);
        frmFac.btnActualizar.setEnabled(true);
        frmFac.btnCancelar.setEnabled(true);
        frmFac.btnEliminar.setEnabled(true);
    }

    private boolean validar() {
        boolean val;
        if (frmFac.txtFacultad.getText().trim().length() == 0) { //Valida campo Nombre
            JOptionPane.showMessageDialog(null, "El campo de texto Facultad está vacío,por favor llenarlo");
            val = false;
        } else {
            val = true;
        }
        return val;
    }

    private void defaultTableModel() {
        frmFac.tblFacultad.getColumnModel().getColumn(0).setPreferredWidth(15);
        frmFac.tblFacultad.getColumnModel().getColumn(1).setPreferredWidth(130);
        frmFac.tblFacultad.getColumnModel().getColumn(2).setPreferredWidth(130);
        modelo = (DefaultTableModel) frmFac.tblFacultad.getModel();
        modelo.setNumRows(0);
    }

    private void LlenarTabla() {
        List<Facultad> listaactdet = fdi.obtenListaFacultad();
        listaactdet.stream().forEach((asign) -> {
            modelo.addRow(new Object[]{
                asign.getIdfacultad(), asign.getIdfacultad(), asign.getUniversidad().getNombreU()
            });
        });
    }

    public final void llenarCB() {
        modeloCombo = new DefaultComboBoxModel<>();
        List<Facultad> listafac = fdi.obtenListaFacultad();
        listafac.stream().forEach((asign) -> {
            modeloCombo.addElement(asign.getUniversidad().getNombreU());
        });
    }

    private final ActionListener Evento = (ActionEvent ev) -> {
        if (ev.getSource() == frmFac.btnNuevo) {
            Habilitar();
            limpiar();
            BotonesNuevo();
            llenarCB();
        }

        if (ev.getSource() == frmFac.btnGuardar) {
            if (validar() == true) {
                int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea GUARDAR?", "Confirmar",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (i == JOptionPane.OK_OPTION) {
                    facu.setNombreF(frmFac.txtFacultad.getText().trim());
                    facu.setUniversidad((Universidad) frmFac.cbxUniversidad.getSelectedItem());
                    fdi.guardarFacultad(facu);
                    defaultTableModel();
                    LlenarTabla();
                    limpiar();
                    Deshabilitar();
                    BotonesInicio();
                }
            }
        }

        if (ev.getSource() == frmFac.btnActualizar) {
            if (validar() == true) {
                int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea ACTUALIZAR?", "Confirmar",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (i == JOptionPane.OK_OPTION) {
                    int fila = frmFac.tblFacultad.getSelectedRow();
                    facu.setIdfacultad(Integer.parseInt(frmFac.tblFacultad.getValueAt(fila, 0).toString()));
                    facu.setNombreF(frmFac.txtFacultad.getText().trim());
                    facu.setUniversidad((Universidad) frmFac.cbxUniversidad.getSelectedItem());
                    fdi.actualizarFacultad(facu);
                    defaultTableModel();
                    LlenarTabla();
                    limpiar();
                    Deshabilitar();
                    BotonesInicio();
                }
            }
        }

        if (ev.getSource() == frmFac.btnEliminar) {
            int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea ELIMINAR?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                int fila = frmFac.tblFacultad.getSelectedRow();
                Facultad facult = fdi.obtenFacultad(Integer.parseInt(frmFac.tblFacultad.getValueAt(fila, 0).toString()));
                fdi.eliminarFacultad(facult);
            }
            defaultTableModel();
            LlenarTabla();
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }

        if (ev.getSource() == frmFac.btnCancelar) {
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }

        if (ev.getSource() == frmFac.btnSalir) {
            System.out.println("boton Salir listo");
            int i = JOptionPane.showConfirmDialog(null, "Desea Salir?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                frmFac.dispose();
            }
        }

    };

    private void tblFacultadMouseClicked(MouseEvent e) {
        if (e.getButton() == 1)//boton izquierdo
        {
            int fila = frmFac.tblFacultad.getSelectedRow();
            Habilitar();
            if (fila > -1) {
                this.frmFac.txtFacultad.setText(String.valueOf(this.frmFac.tblFacultad.getValueAt(fila, 1)));
            }
        }
        BotonesClick();
    }
}
