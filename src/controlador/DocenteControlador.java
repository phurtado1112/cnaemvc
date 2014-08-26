/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import dao.DocenteDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Docente;
import util.Valida;
import vista.DocenteIF;

/**
 *
 * @author PabloAntonio
 */
public class DocenteControlador {
    DocenteIF frmDoce ;
    DocenteDaoImpl ddi;
    Docente doce = new Docente();
    Valida va = new Valida();
    DefaultTableModel modelo;

    public DocenteControlador() {
    }

    public DocenteControlador(DocenteIF frmDoce, DocenteDaoImpl ddi) {
        this.frmDoce = frmDoce;
        this.ddi = ddi;
        inicio();
    }
    
    private void inicio() {
        frmDoce.setVisible(true);
        BotonesInicio();
        Deshabilitar();
        defaultTableModel();
        LlenarTabla();
        
        frmDoce.btnActualizar.addActionListener(Evento);
        frmDoce.btnCancelar.addActionListener(Evento);
        frmDoce.btnEliminar.addActionListener(Evento);
        frmDoce.btnGuardar.addActionListener(Evento);
        frmDoce.btnNuevo.addActionListener(Evento);
        frmDoce.btnSalir.addActionListener(Evento);
        frmDoce.tblDocente.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDocenteMouseClicked(evt);
            }
        });
    }
    
    private void Habilitar() {
        frmDoce.txtNombre.setEnabled(true);
        va.SoloLetras(frmDoce.txtNombre);
        va.SeleccionarTodo(frmDoce.txtNombre);
        frmDoce.txtApellido.setEnabled(true);
        va.LetrasSinEspacios(frmDoce.txtApellido);
        va.SeleccionarTodo(frmDoce.txtApellido);
        frmDoce.txtNombre.requestFocus();
        frmDoce.txtUsuario.setEnabled(true);
        va.LetrasSinEspacios(frmDoce.txtUsuario);
        va.SeleccionarTodo(frmDoce.txtUsuario);
        frmDoce.pswContrasena.setEnabled(true);
    }

    private void Deshabilitar() {
        frmDoce.txtNombre.setEnabled(false);
        frmDoce.txtApellido.setEnabled(false);
        frmDoce.txtUsuario.setEnabled(false);
    }

    private void limpiar() {
        frmDoce.txtNombre.setText("");
        frmDoce.txtApellido.setText("");
        frmDoce.txtUsuario.setText("");
    }

    private void BotonesInicio() {
        frmDoce.btnNuevo.setEnabled(true);
        frmDoce.btnActualizar.setEnabled(false);
        frmDoce.btnEliminar.setEnabled(false);
        frmDoce.btnGuardar.setEnabled(false);
        frmDoce.btnCancelar.setEnabled(false);
    }

    private void BotonesNuevo() {
        frmDoce.btnNuevo.setEnabled(false);
        frmDoce.btnActualizar.setEnabled(false);
        frmDoce.btnEliminar.setEnabled(false);
        frmDoce.btnGuardar.setEnabled(true);
        frmDoce.btnCancelar.setEnabled(true);
    }

    private void BotonesClick() {
        frmDoce.btnNuevo.setEnabled(false);
        frmDoce.btnGuardar.setEnabled(false);
        frmDoce.btnActualizar.setEnabled(true);
        frmDoce.btnCancelar.setEnabled(true);
        frmDoce.btnEliminar.setEnabled(true);
    }

    private boolean validar() {
        boolean val;
        if(frmDoce.txtNombre.getText().trim().length()==0){ //Valida campo Nombre
            JOptionPane.showMessageDialog(null, "El campo de texto Nombre está vacío,por favor llenarlo");
            val = false;
        } else if(frmDoce.txtApellido.getText().trim().length()==0){ //Valida campo Apellido
            JOptionPane.showMessageDialog(null, "El campo de texto Apellido está vacío,por favor llenarlo");
            val = false;
        } else if(frmDoce.txtUsuario.getText().trim().length()==0){ //Valida campo Apellido
            JOptionPane.showMessageDialog(null, "El campo de texto Usuario está vacío,por favor llenarlo");
            val = false;
        } else if(frmDoce.pswContrasena.getText().trim().length()==0){ //Valida campo Apellido
            JOptionPane.showMessageDialog(null, "El campo de texto Contraseña está vacío,por favor llenarlo");
            val = false;
        } else {
            val=true;
        }       
        return val;
    }

    private void defaultTableModel() {
        frmDoce.tblDocente.getColumnModel().getColumn(0).setPreferredWidth(15);
        frmDoce.tblDocente.getColumnModel().getColumn(1).setPreferredWidth(80);
        frmDoce.tblDocente.getColumnModel().getColumn(2).setPreferredWidth(80);
        frmDoce.tblDocente.getColumnModel().getColumn(3).setPreferredWidth(80);
        frmDoce.tblDocente.getColumnModel().getColumn(4).setPreferredWidth(80);
        modelo = (DefaultTableModel) frmDoce.tblDocente.getModel();
        modelo.setNumRows(0);
    }

    private void LlenarTabla() {
        List<Docente> listadoce = ddi.obtenListaDocentes();
        listadoce.stream().forEach((asign) -> {
            modelo.addRow(new Object[]{
                asign.getIddocente(), asign.getNombre(), asign.getApellido(), asign.getUsuario(),asign.getContrasena()
            });
        });
    }
    
    private final ActionListener Evento = (ActionEvent ev) -> {
        if (ev.getSource() == frmDoce.btnNuevo) {
            Habilitar();
            limpiar();
            BotonesNuevo();
        }

        if (ev.getSource() == frmDoce.btnGuardar) {
            char[] arrayC = frmDoce.pswContrasena.getPassword();
            String pass = new String(arrayC);
            if (validar() == true) {
                int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea GUARDAR?", "Confirmar",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (i == JOptionPane.OK_OPTION) {
                    doce.setNombre(frmDoce.txtNombre.getText().trim());
                    doce.setApellido(frmDoce.txtApellido.getText().trim());
                    doce.setUsuario(frmDoce.txtUsuario.getText().trim());
                    doce.setContrasena(pass);
                    ddi.guardarDocente(doce);
                    defaultTableModel();
                    LlenarTabla();
                    limpiar();
                    Deshabilitar();
                    BotonesInicio();
                }
            }
        }
        
        if (ev.getSource() == frmDoce.btnActualizar) {
            if (validar() == true) {
                int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea ACTUALIZAR?", "Confirmar",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (i == JOptionPane.OK_OPTION) {
                    int fila = frmDoce.tblDocente.getSelectedRow();
                    doce.setIddocente(Integer.parseInt(frmDoce.tblDocente.getValueAt(fila, 0).toString()));
                    doce.setNombre(frmDoce.txtNombre.getText().trim());
                    doce.setApellido(frmDoce.txtApellido.getText().trim());
                    doce.setUsuario(frmDoce.txtUsuario.getText().trim());
                    doce.setContrasena(frmDoce.pswContrasena.getText().trim());
                    ddi.actualizarDocente(doce);
                    defaultTableModel();
                    LlenarTabla();
                    limpiar();
                    Deshabilitar();
                    BotonesInicio();
                }
            }
        }
        
        if (ev.getSource() == frmDoce.btnEliminar) {
            int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea ELIMINAR?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                int fila = frmDoce.tblDocente.getSelectedRow();
                Docente docent = ddi.obtenDocente(Integer.parseInt(frmDoce.tblDocente.getValueAt(fila, 0).toString()));
                ddi.eliminarDocente(docent);
            }
            defaultTableModel();
            LlenarTabla();
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }
        
        if (ev.getSource() == frmDoce.btnCancelar) {
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }

        if (ev.getSource() == frmDoce.btnSalir) {
            System.out.println("boton Salir listo");
            int i = JOptionPane.showConfirmDialog(null, "Desea Salir?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                frmDoce.dispose();
            }
        }
    };
    
    private void tblDocenteMouseClicked(MouseEvent e) {
        if( e.getButton()== 1)//boton izquierdo
        {
             int fila = frmDoce.tblDocente.getSelectedRow();
             Habilitar();
             if (fila > -1){           
                this.frmDoce.txtNombre.setText(String.valueOf(this.frmDoce.tblDocente.getValueAt(fila, 1) ));
                this.frmDoce.txtApellido.setText(String.valueOf(this.frmDoce.tblDocente.getValueAt(fila, 2) ));
                this.frmDoce.txtUsuario.setText(String.valueOf(this.frmDoce.tblDocente.getValueAt(fila, 3) ));
                this.frmDoce.pswContrasena.setText(String.valueOf(this.frmDoce.tblDocente.getValueAt(fila, 4) ));
             }
        }
        BotonesClick();
    }
}
