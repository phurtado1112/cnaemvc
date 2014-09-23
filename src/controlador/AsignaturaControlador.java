/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import dao.AsignaturaDaoImpl;
import dao.CarreraDaoImpl;
import dao.FacultadDaoImpl;
import dao.UniversidadDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Asignatura;
import modelo.Carrera;
import modelo.Facultad;
import modelo.Universidad;
import util.Valida;
import vista.AsignaturaIF;

/**
 *
 * @author PabloAntonio
 */
public class AsignaturaControlador {
    AsignaturaIF frmAsi;
    AsignaturaDaoImpl adi;
    Asignatura asig = new Asignatura();
    Valida va = new Valida();
    DefaultTableModel modelo;
    DefaultComboBoxModel<String> modeloComboU, modeloComboF, modeloComboC;
    Universidad u = new Universidad();
    UniversidadDaoImpl udi;
    Facultad f = new Facultad();
    FacultadDaoImpl fdi;
    Carrera c = new Carrera();
    CarreraDaoImpl cdi;

    public AsignaturaControlador() {
        
    }

    public AsignaturaControlador(AsignaturaIF frmAsi, AsignaturaDaoImpl adi) {
        this.frmAsi = frmAsi;
        this.adi = adi;
        inicio();
    }
    
    private void inicio(){
        frmAsi.setVisible(true);
        BotonesInicio();
        Deshabilitar();
        defaultTableModel();
        LlenarTabla();

        frmAsi.btnActualizar.addActionListener(Evento);
        frmAsi.btnCancelar.addActionListener(Evento);
        frmAsi.btnEliminar.addActionListener(Evento);
        frmAsi.btnGuardar.addActionListener(Evento);
        frmAsi.btnNuevo.addActionListener(Evento);
        frmAsi.btnSalir.addActionListener(Evento);
        frmAsi.tblAsignatura.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAsignaturaMouseClicked(evt);
            }
        });
        
        frmAsi.cbxUniversidad.addItemListener((ItemEvent e) -> {
            frmAsi.cbxFacultad.setModel(llenarCBF());
        });
        
        frmAsi.cbxFacultad.addItemListener((ItemEvent e) -> {
            frmAsi.cbxCarrera.setModel(llenarCBC());
        });
    }
    
    private void limpiar() {
        frmAsi.txtAsignatura.setText("");
        frmAsi.txtAnio.setText("");
        frmAsi.txtCodigoGrupo.setText("");
        frmAsi.txtPeriodo.setText("");
        frmAsi.cbxCarrera.removeAllItems();
        frmAsi.cbxFacultad.removeAllItems();
        frmAsi.cbxUniversidad.removeAllItems();
    }

    private void Deshabilitar() {
        frmAsi.txtAsignatura.setEnabled(false);
        frmAsi.txtAnio.setEnabled(false);
        frmAsi.txtCodigoGrupo.setEnabled(false);
        frmAsi.txtPeriodo.setEnabled(false);
        frmAsi.cbxCarrera.setEnabled(false);
        frmAsi.cbxFacultad.setEnabled(false);
        frmAsi.cbxUniversidad.setEnabled(false);
    }

    private void Habilitar() {
        frmAsi.txtAsignatura.setEnabled(true);
        va.SoloLetras(frmAsi.txtAsignatura);
        va.SeleccionarTodo(frmAsi.txtAsignatura);
        frmAsi.txtAnio.setEnabled(true);
        va.SoloNumerosCelular(frmAsi.txtAnio);
        va.SeleccionarTodo(frmAsi.txtAnio);
        frmAsi.txtCodigoGrupo.setEnabled(true);
        va.LetrasNumeros(frmAsi.txtCodigoGrupo);
        va.SeleccionarTodo(frmAsi.txtCodigoGrupo);
        frmAsi.txtPeriodo.setEnabled(true);
        va.LetrasNumerosEspacio(frmAsi.txtPeriodo);
        va.SeleccionarTodo(frmAsi.txtPeriodo);
        frmAsi.txtAsignatura.requestFocus();
        frmAsi.cbxCarrera.setEnabled(true);
        frmAsi.cbxFacultad.setEnabled(true);
        frmAsi.cbxUniversidad.setEnabled(true);
    }

    private void BotonesInicio() {
        frmAsi.btnNuevo.setEnabled(true);
        frmAsi.btnActualizar.setEnabled(false);
        frmAsi.btnEliminar.setEnabled(false);
        frmAsi.btnGuardar.setEnabled(false);
        frmAsi.btnCancelar.setEnabled(false);
    }

    private void BotonesNuevo() {
        frmAsi.btnNuevo.setEnabled(false);
        frmAsi.btnActualizar.setEnabled(false);
        frmAsi.btnEliminar.setEnabled(false);
        frmAsi.btnGuardar.setEnabled(true);
        frmAsi.btnCancelar.setEnabled(true);
    }

    private void BotonesClick() {
        frmAsi.btnNuevo.setEnabled(false);
        frmAsi.btnGuardar.setEnabled(false);
        frmAsi.btnActualizar.setEnabled(true);
        frmAsi.btnCancelar.setEnabled(true);
        frmAsi.btnEliminar.setEnabled(true);
    }

    private boolean validar() {
        boolean val;
        if (frmAsi.txtAsignatura.getText().trim().length() == 0) { //Valida campo Nombre
            JOptionPane.showMessageDialog(null, "Asignatura está vacío,por favor llenarlo");
            val = false;
        } else if(frmAsi.txtAnio.getText().trim().length() == 0) { 
            JOptionPane.showMessageDialog(null, "El Año está vacío,por favor llenarlo");
            val = false;
        } else if(frmAsi.txtCodigoGrupo.getText().trim().length() == 0) { 
            JOptionPane.showMessageDialog(null, "El Código de grupo está vacío,por favor llenarlo");
            val = false;
        } else if(frmAsi.txtPeriodo.getText().trim().length() == 0) { 
            JOptionPane.showMessageDialog(null, "El Período está vacío,por favor llenarlo");
            val = false;
        } else {
            val = true;
        }
        return val;
    }

    private void defaultTableModel() {
        frmAsi.tblAsignatura.getColumnModel().getColumn(0).setPreferredWidth(15);
        frmAsi.tblAsignatura.getColumnModel().getColumn(1).setPreferredWidth(50);
        frmAsi.tblAsignatura.getColumnModel().getColumn(2).setPreferredWidth(50);
        frmAsi.tblAsignatura.getColumnModel().getColumn(3).setPreferredWidth(50);
        frmAsi.tblAsignatura.getColumnModel().getColumn(4).setPreferredWidth(50);
        frmAsi.tblAsignatura.getColumnModel().getColumn(5).setPreferredWidth(50);
        modelo = (DefaultTableModel) frmAsi.tblAsignatura.getModel();
        modelo.setNumRows(0);
    }

    private void LlenarTabla() {
        List<Asignatura> listaasig = adi.obtenListaAsignaturas();
        listaasig.stream().forEach((asign) -> {
            modelo.addRow(new Object[]{
                asign.getIdasignatura(), asign.getNombreA(), asign.getCarrera().getNombreC(), asign.getAnio(), asign.getGrupo(), asign.getPeriodo()
            });
        });
    }

    private DefaultComboBoxModel<String> llenarCBF() {
        modeloComboF = new DefaultComboBoxModel<>();
        List<Facultad> listafac = fdi.obtenListaFacultad();
        listafac.stream().forEach((asign) -> {
            modeloComboF.addElement(asign.getNombreF());
        });
        return modeloComboF;
    }
    
    private void llenarCBU() {
        modeloComboU = new DefaultComboBoxModel<>();
        List<Universidad> listauni = udi.obtenListaUniversidades();
        listauni.stream().forEach((asign) -> {
            modeloComboU.addElement(asign.getNombreU());
        });
    }
    
    private DefaultComboBoxModel<String> llenarCBC() {
        modeloComboC = new DefaultComboBoxModel<>();
        List<Carrera> listacar = cdi.obtenListaCarreras();
        listacar.stream().forEach((asign) -> {
            modeloComboC.addElement(asign.getNombreC());
        });
        return modeloComboC;
    }
    
    private final ActionListener Evento = (ActionEvent ev) -> {
        if (ev.getSource() == frmAsi.btnNuevo) {
            Habilitar();
            limpiar();
            BotonesNuevo();
            llenarCBC();
            llenarCBF();
            llenarCBU();
        }

        if (ev.getSource() == frmAsi.btnGuardar) {
            if (validar() == true) {
                int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea GUARDAR?", "Confirmar",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (i == JOptionPane.OK_OPTION) {
                    asig.setNombreA(frmAsi.txtAsignatura.getText().trim());
                    asig.setAnio(Integer.parseInt(frmAsi.txtAnio.getText().trim()));
                    asig.setGrupo(frmAsi.txtCodigoGrupo.getText().trim());
                    asig.setPeriodo(frmAsi.txtPeriodo.getText().trim());
                    asig.setCarrera((Carrera)frmAsi.cbxCarrera.getSelectedItem());
                    adi.guardarAsignatura(asig);
                    defaultTableModel();
                    LlenarTabla();
                    limpiar();
                    Deshabilitar();
                    BotonesInicio();
                }
            }
        }

        if (ev.getSource() == frmAsi.btnActualizar) {
            if (validar() == true) {
                int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea ACTUALIZAR?", "Confirmar",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (i == JOptionPane.OK_OPTION) {
                    int fila = frmAsi.tblAsignatura.getSelectedRow();
                    asig.setIdasignatura(Integer.parseInt(frmAsi.tblAsignatura.getValueAt(fila, 0).toString()));
                    asig.setNombreA(frmAsi.txtAsignatura.getText().trim());
                    asig.setAnio(Integer.parseInt(frmAsi.txtAnio.getText().trim()));
                    asig.setGrupo(frmAsi.txtCodigoGrupo.getText().trim());
                    asig.setPeriodo(frmAsi.txtPeriodo.getText().trim());
                    asig.setCarrera((Carrera)frmAsi.cbxCarrera.getSelectedItem());
                    adi.actualizarAsignatura(asig);
                    defaultTableModel();
                    LlenarTabla();
                    limpiar();
                    Deshabilitar();
                    BotonesInicio();
                }
            }
        }

        if (ev.getSource() == frmAsi.btnEliminar) {
            int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea ELIMINAR?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                int fila = frmAsi.tblAsignatura.getSelectedRow();
                Carrera carrer = cdi.obtenCarrera(Integer.parseInt(frmAsi.tblAsignatura.getValueAt(fila, 0).toString()));
                cdi.eliminarCarrera(carrer);
            }
            defaultTableModel();
            LlenarTabla();
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }

        if (ev.getSource() == frmAsi.btnCancelar) {
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }

        if (ev.getSource() == frmAsi.btnSalir) {
            System.out.println("boton Salir listo");
            int i = JOptionPane.showConfirmDialog(null, "Desea Salir?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                frmAsi.dispose();
            }
        }

    };

    private void tblAsignaturaMouseClicked(MouseEvent e) {
        if (e.getButton() == 1)//boton izquierdo
        {
            int fila = frmAsi.tblAsignatura.getSelectedRow();
            Habilitar();
            llenarCBC();
            llenarCBF();
            llenarCBU();
            BotonesClick();
            if (fila > -1) {
                this.frmAsi.txtAsignatura.setText(String.valueOf(this.frmAsi.tblAsignatura.getValueAt(fila, 1)));
                this.frmAsi.txtAnio.setText(String.valueOf(this.frmAsi.tblAsignatura.getValueAt(fila, 2)));
                this.frmAsi.txtCodigoGrupo.setText(String.valueOf(this.frmAsi.tblAsignatura.getValueAt(fila, 3)));
            }
        }
    }
}
