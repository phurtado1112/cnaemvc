/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UniversidadDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Universidad;
import util.Valida;
import vista.UniversidadIF;
//import vista.inicio.Cnae;

/**
 *
 * @author PabloAntonio
 */
public class UniversidadControlador {

    UniversidadIF frmUniv ;
    UniversidadDaoImpl udi;
    Universidad univ = new Universidad();
    Valida va = new Valida();
//    Cnae cnae = new Cnae();
    DefaultTableModel modelo;

    public UniversidadControlador() {
        
    }

    public UniversidadControlador(UniversidadIF frmUniv, UniversidadDaoImpl udi) {
        this.frmUniv = frmUniv;
        this.udi = udi;
        inicio();
    }
    

    private void inicio() {
        frmUniv.setVisible(true);
        BotonesInicio();
        Deshabilitar();
        defaultTableModel();
        LlenarTabla();
        
        frmUniv.btnActualizar.addActionListener(Evento);
        frmUniv.btnCancelar.addActionListener(Evento);
        frmUniv.btnEliminar.addActionListener(Evento);
        frmUniv.btnGuardar.addActionListener(Evento);
        frmUniv.btnNuevo.addActionListener(Evento);
        frmUniv.btnSalir.addActionListener(Evento);
        frmUniv.tblUniversidad.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUniversidadMouseClicked(evt);
            }
        });
    }

    private void Habilitar() {
        frmUniv.txtUniversidad.setEnabled(true);
        va.SoloLetras(frmUniv.txtUniversidad);
        va.SeleccionarTodo(frmUniv.txtUniversidad);
        frmUniv.txtSiglas.setEnabled(true);
        va.LetrasSinEspacios(frmUniv.txtSiglas);
        va.SeleccionarTodo(frmUniv.txtSiglas);
        frmUniv.txtUniversidad.requestFocus();
        frmUniv.BtnBuscarLogo.setEnabled(true);
    }

    private void Deshabilitar() {
        frmUniv.txtUniversidad.setEnabled(false);
        frmUniv.txtSiglas.setEnabled(false);
        frmUniv.BtnBuscarLogo.setEnabled(false);
    }

    private void limpiar() {
        frmUniv.txtUniversidad.setText("");
        frmUniv.txtSiglas.setText("");
        frmUniv.LblLogo.setIcon(null);
    }

    private void BotonesInicio() {
        frmUniv.btnNuevo.setEnabled(true);
        frmUniv.btnActualizar.setEnabled(false);
        frmUniv.btnEliminar.setEnabled(false);
        frmUniv.btnGuardar.setEnabled(false);
        frmUniv.btnCancelar.setEnabled(false);
    }

    private void BotonesNuevo() {
        frmUniv.btnNuevo.setEnabled(false);
        frmUniv.btnActualizar.setEnabled(false);
        frmUniv.btnEliminar.setEnabled(false);
        frmUniv.btnGuardar.setEnabled(true);
        frmUniv.btnCancelar.setEnabled(true);
    }

    private void BotonesClick() {
        frmUniv.btnNuevo.setEnabled(false);
        frmUniv.btnGuardar.setEnabled(false);
        frmUniv.btnActualizar.setEnabled(true);
        frmUniv.btnCancelar.setEnabled(true);
        frmUniv.btnEliminar.setEnabled(true);
    }

    private boolean validar() {
        boolean val;
        if(frmUniv.txtUniversidad.getText().trim().length()==0){ //Valida campo Nombre
            JOptionPane.showMessageDialog(null, "El campo de texto Universidad está vacío,por favor llenarlo");
            val = false;
        } else if(frmUniv.txtSiglas.getText().trim().length()==0){ //Valida campo Apellido
            JOptionPane.showMessageDialog(null, "El campo de texto Siglas está vacío,por favor llenarlo");
            val = false;
        } else {
            val=true;
        }       
        return val;
    }

    public void defaultTableModel() {
        frmUniv.tblUniversidad.getColumnModel().getColumn(0).setPreferredWidth(15);
        frmUniv.tblUniversidad.getColumnModel().getColumn(1).setPreferredWidth(260);
        frmUniv.tblUniversidad.getColumnModel().getColumn(2).setPreferredWidth(30);
        modelo = (DefaultTableModel) frmUniv.tblUniversidad.getModel();
        modelo.setNumRows(0);
    }

    private void LlenarTabla() {
        List<Universidad> listauniv = udi.obtenListaUniversidades();
        listauniv.stream().forEach((asign) -> {
            modelo.addRow(new Object[]{
                asign.getIduniversidad(), asign.getNombreU(), asign.getSiglas(),
            });
        });
    }

    private final ActionListener Evento = (ActionEvent ev) -> {
        if (ev.getSource() == frmUniv.btnNuevo) {
            Habilitar();
            limpiar();
            BotonesNuevo();
        }

        if (ev.getSource() == frmUniv.btnGuardar) {
            if (validar() == true) {
                int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea GUARDAR?", "Confirmar",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (i == JOptionPane.OK_OPTION) {
                    univ.setNombreU(frmUniv.txtUniversidad.getText().trim());
                    univ.setSiglas(frmUniv.txtSiglas.getText().trim());
                    udi.guardarUniversidad(univ);
                    defaultTableModel();
                    LlenarTabla();
                    limpiar();
                    Deshabilitar();
                    BotonesInicio();
                }
            }
        }
        
        if (ev.getSource() == frmUniv.btnActualizar) {
            if (validar() == true) {
                int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea ACTUALIZAR?", "Confirmar",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (i == JOptionPane.OK_OPTION) {
                    int fila = frmUniv.tblUniversidad.getSelectedRow();
                    univ.setIduniversidad(Integer.parseInt(frmUniv.tblUniversidad.getValueAt(fila, 0).toString()));
                    univ.setNombreU(frmUniv.txtUniversidad.getText().trim());
                    univ.setSiglas(frmUniv.txtSiglas.getText().trim());
                    udi.actualizarUniversidad(univ);
                    defaultTableModel();
                    LlenarTabla();
                    limpiar();
                    Deshabilitar();
                    BotonesInicio();
                }
            }
        }
        
        if (ev.getSource() == frmUniv.btnEliminar) {
            int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea ELIMINAR?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                int fila = frmUniv.tblUniversidad.getSelectedRow();
                Universidad univer = udi.obtenUniversidad(Integer.parseInt(frmUniv.tblUniversidad.getValueAt(fila, 0).toString()));
                udi.eliminarUniversidad(univer);
            }
            defaultTableModel();
            LlenarTabla();
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }
        
        if (ev.getSource() == frmUniv.btnCancelar) {
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }

        if (ev.getSource() == frmUniv.btnSalir) {
            System.out.println("boton Salir listo");
            int i = JOptionPane.showConfirmDialog(null, "Desea Salir?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                frmUniv.dispose();
            }
        }
    };
    
    public void tblUniversidadMouseClicked(MouseEvent e) {
        if( e.getButton()== 1)//boton izquierdo
        {
             int fila = frmUniv.tblUniversidad.getSelectedRow();
             Habilitar();
             if (fila > -1){           
                this.frmUniv.txtUniversidad.setText(String.valueOf(this.frmUniv.tblUniversidad.getValueAt(fila, 1) ));
                this.frmUniv.txtSiglas.setText(String.valueOf(this.frmUniv.tblUniversidad.getValueAt(fila, 2) ));
             }
        }
        BotonesClick();
    }

}
