/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.EstudianteDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Estudiantes;
import modelo.Universidad;
import util.Globales;
import util.Valida;
import vista.EstudianteIF;

/**
 *
 * @author PabloAntonio
 */
public class EstudianteControlador {

    EstudianteIF frmEstu;
    EstudianteDaoImpl edi;
    Estudiantes estu = new Estudiantes();
    Valida va = new Valida();
    DefaultTableModel modelo;

    public EstudianteControlador() {

    }

    public EstudianteControlador(EstudianteIF frmEstu, EstudianteDaoImpl edi) {
        this.frmEstu = frmEstu;
        this.edi = edi;
        inicio();
    }

    private void inicio() {
        frmEstu.setVisible(true);
        BotonesInicio();
        Deshabilitar();
        defaultTableModel();
        LlenarTabla();
        llenarTXT();

        frmEstu.btnActualizar.addActionListener(Evento);
        frmEstu.btnCancelar.addActionListener(Evento);
        frmEstu.btnEliminar.addActionListener(Evento);
        frmEstu.btnGuardar.addActionListener(Evento);
        frmEstu.btnNuevo.addActionListener(Evento);
        frmEstu.btnSalir.addActionListener(Evento);
        frmEstu.tblEstudiantes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEstudianteMouseClicked(evt);
            }
        });
    }

    private void Habilitar() {
        frmEstu.txtNombres.setEnabled(true);
        va.SoloLetras(frmEstu.txtNombres);
        va.SeleccionarTodo(frmEstu.txtNombres);
        frmEstu.txtApellidos.setEnabled(true);
        va.LetrasSinEspacios(frmEstu.txtApellidos);
        va.SeleccionarTodo(frmEstu.txtApellidos);
        frmEstu.txtCarnet.setEnabled(true);
        va.LetrasNumerosUsuario(frmEstu.txtCarnet);
        va.SeleccionarTodo(frmEstu.txtCarnet);
        frmEstu.txtEmail.setEnabled(true);
        va.LetrasSinEspacios(frmEstu.txtEmail);
        va.SeleccionarTodo(frmEstu.txtEmail);
        frmEstu.txtCelular.setEnabled(true);
        va.LetrasNumeros(frmEstu.txtCelular);
        va.SeleccionarTodo(frmEstu.txtCelular);
        frmEstu.txtNombres.requestFocus();
    }

    private void Deshabilitar() {
        frmEstu.txtNombres.setEnabled(false);
        frmEstu.txtApellidos.setEnabled(false);
        frmEstu.txtCarnet.setEnabled(false);
        frmEstu.txtEmail.setEnabled(false);
        frmEstu.txtCelular.setEnabled(false);
    }

    private void limpiar() {
        frmEstu.txtNombres.setText("");
        frmEstu.txtApellidos.setText("");
        frmEstu.txtCarnet.setText("");
        frmEstu.txtEmail.setText("");
        frmEstu.txtCelular.setText("");
    }

    private void BotonesInicio() {
        frmEstu.btnNuevo.setEnabled(true);
        frmEstu.btnActualizar.setEnabled(false);
        frmEstu.btnEliminar.setEnabled(false);
        frmEstu.btnGuardar.setEnabled(false);
        frmEstu.btnCancelar.setEnabled(false);
    }

    private void BotonesNuevo() {
        frmEstu.btnNuevo.setEnabled(false);
        frmEstu.btnActualizar.setEnabled(false);
        frmEstu.btnEliminar.setEnabled(false);
        frmEstu.btnGuardar.setEnabled(true);
        frmEstu.btnCancelar.setEnabled(true);
    }

    private void BotonesClick() {
        frmEstu.btnNuevo.setEnabled(false);
        frmEstu.btnGuardar.setEnabled(false);
        frmEstu.btnActualizar.setEnabled(true);
        frmEstu.btnCancelar.setEnabled(true);
        frmEstu.btnEliminar.setEnabled(true);
    }

    private boolean validar() {
        boolean val;
        if (frmEstu.txtNombres.getText().trim().length() == 0) { //Valida campo Nombre
            JOptionPane.showMessageDialog(null, "El campo de texto Nombres está vacío,por favor llenarlo");
            val = false;
        } else if (frmEstu.txtApellidos.getText().trim().length() == 0) { //Valida campo Apellido
            JOptionPane.showMessageDialog(null, "El campo de texto Apellidos está vacío,por favor llenarlo");
            val = false;
        } else if (frmEstu.txtCarnet.getText().trim().length() == 0) { //Valida campo Apellido
            JOptionPane.showMessageDialog(null, "El campo de texto Carnet está vacío,por favor llenarlo");
            val = false;
        } else if (frmEstu.txtEmail.getText().trim().length() == 0) { //Valida campo Apellido
            JOptionPane.showMessageDialog(null, "El campo de texto E-mail está vacío,por favor llenarlo");
            val = false;
        } else if (frmEstu.txtCelular.getText().trim().length() == 0) { //Valida campo Apellido
            JOptionPane.showMessageDialog(null, "El campo de texto Celular está vacío,por favor llenarlo");
            val = false;
        } else {
            val = true;
        }
        return val;
    }

    private void defaultTableModel() {
        frmEstu.tblEstudiantes.getColumnModel().getColumn(0).setPreferredWidth(15);
        frmEstu.tblEstudiantes.getColumnModel().getColumn(1).setPreferredWidth(80);
        frmEstu.tblEstudiantes.getColumnModel().getColumn(2).setPreferredWidth(80);
        frmEstu.tblEstudiantes.getColumnModel().getColumn(3).setPreferredWidth(30);
        frmEstu.tblEstudiantes.getColumnModel().getColumn(4).setPreferredWidth(80);
        frmEstu.tblEstudiantes.getColumnModel().getColumn(5).setPreferredWidth(30);
        modelo = (DefaultTableModel) frmEstu.tblEstudiantes.getModel();
        modelo.setNumRows(0);
    }

    private void LlenarTabla() {
        List<Estudiantes> listaestu = edi.obtenListaEstudiante();
        listaestu.stream().forEach((asign) -> {
            modelo.addRow(new Object[]{
                asign.getIdestudiante(), asign.getNombreE(), asign.getApellidoE(),asign.getCarnet(),asign.getCelular()});
        });
    }
    
     private void llenarTXT() {
//        cnx.Conecta();
//         try {             
//            String SQL = "select nombreA from asignatura where idasignatura = " + Globales.id;
//            stm = cnx.conn.createStatement();            
//            rs = stm.executeQuery(SQL);
//            while (rs.next()) {
//                txtAsignatura.setText(rs.getString("nombreA"));
//            }
//            rs.close();            
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error LlenarTXT: " + ex.getMessage());
//        } finally {
//            cnx.Desconecta();
//         }
    }

    private final ActionListener Evento = (ActionEvent ev) -> {
        if (ev.getSource() == frmEstu.btnNuevo) {
            Habilitar();
            limpiar();
            BotonesNuevo();
        }

        if (ev.getSource() == frmEstu.btnGuardar) {
            if (validar() == true) {
                int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea GUARDAR?", "Confirmar",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (i == JOptionPane.OK_OPTION) {
                    estu.setNombreE(frmEstu.txtNombres.getText().trim());
                    estu.setApellidoE(frmEstu.txtApellidos.getText().trim());
                    estu.setCarnet(frmEstu.txtCarnet.getText().trim());
                    estu.setCelular(Integer.parseInt(frmEstu.txtCelular.getText().trim()));
                    estu.setEmail(frmEstu.txtEmail.getText().trim());
                    edi.guardarEstudiante(estu);
                    defaultTableModel();
                    LlenarTabla();
                    llenarTXT();
                    limpiar();
                    Deshabilitar();
                    BotonesInicio();
                }
            }
        }

        if (ev.getSource() == frmEstu.btnActualizar) {
            if (validar() == true) {
                int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea ACTUALIZAR?", "Confirmar",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (i == JOptionPane.OK_OPTION) {
                    int fila = frmEstu.tblEstudiantes.getSelectedRow();
                    estu.setIdestudiante(Integer.parseInt(frmEstu.tblEstudiantes.getValueAt(fila, 0).toString()));
                    estu.setNombreE(frmEstu.txtNombres.getText().trim());
                    estu.setApellidoE(frmEstu.txtApellidos.getText().trim());
                    estu.setCarnet(frmEstu.txtCarnet.getText().trim());
                    estu.setEmail(frmEstu.txtEmail.getText().trim());
                    estu.setCelular(Integer.parseInt(frmEstu.txtCelular.getText().trim()));
                    edi.actualizarEstudiante(estu);
                    defaultTableModel();
                    LlenarTabla();
                    llenarTXT();
                    limpiar();
                    Deshabilitar();
                    BotonesInicio();
                }
            }
        }

        if (ev.getSource() == frmEstu.btnEliminar) {
            int i = JOptionPane.showConfirmDialog(null, "¿Seguro desea ELIMINAR?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                int fila = frmEstu.tblEstudiantes.getSelectedRow();
                Estudiantes estudi = edi.obtenEstudiante(Integer.parseInt(frmEstu.tblEstudiantes.getValueAt(fila, 0).toString()));
                edi.eliminarEstudiante(estudi);
            }
            defaultTableModel();
            LlenarTabla();
            llenarTXT();
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }

        if (ev.getSource() == frmEstu.btnCancelar) {
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }

        if (ev.getSource() == frmEstu.btnSalir) {
            System.out.println("boton Salir listo");
            int i = JOptionPane.showConfirmDialog(null, "Desea Salir?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                frmEstu.dispose();
            }
        }
    };

    private void tblEstudianteMouseClicked(MouseEvent e) {
        if (e.getButton() == 1)//boton izquierdo
        {
            int fila = frmEstu.tblEstudiantes.getSelectedRow();
            Habilitar();
            if (fila > -1) {
                this.frmEstu.txtNombres.setText(String.valueOf(this.frmEstu.tblEstudiantes.getValueAt(fila, 1)));
                this.frmEstu.txtApellidos.setText(String.valueOf(this.frmEstu.tblEstudiantes.getValueAt(fila, 2)));
                this.frmEstu.txtCarnet.setText(String.valueOf(this.frmEstu.tblEstudiantes.getValueAt(fila, 3)));
                this.frmEstu.txtEmail.setText(String.valueOf(this.frmEstu.tblEstudiantes.getValueAt(fila, 4)));
                this.frmEstu.txtCelular.setText(String.valueOf(this.frmEstu.tblEstudiantes.getValueAt(fila, 5)));
            }
        }
        BotonesClick();
    }
}
