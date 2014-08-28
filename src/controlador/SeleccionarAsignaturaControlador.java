/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.AsignaturaDaoImpl;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Asignatura;
import vista.SeleccionarAsignatura;

/**
 *
 * @author PabloAntonio
 */
public class SeleccionarAsignaturaControlador {

    SeleccionarAsignatura frmSelectAsig = new SeleccionarAsignatura();
    Asignatura asig = new Asignatura();
    AsignaturaDaoImpl adi = new AsignaturaDaoImpl();

    public SeleccionarAsignaturaControlador() {
        inicio();
    }

    private void inicio() {
        defaultTableModel();
        llenarTabla();
    }

    private void ocultaFormulario() {
        frmSelectAsig.cbxCarrera.setVisible(false);
        frmSelectAsig.cbxFacultad.setVisible(false);
        frmSelectAsig.cbxUniversidad.setVisible(false);
        frmSelectAsig.txtAsignatura.setVisible(false);
        frmSelectAsig.txtAnio.setVisible(false);
        frmSelectAsig.txtGrupo.setVisible(false);
        frmSelectAsig.txtPeriodo.setVisible(false);
        frmSelectAsig.lblCarrera.setVisible(false);
        frmSelectAsig.lblFacultad.setVisible(false);
        frmSelectAsig.lblUniversidad.setVisible(false);
        frmSelectAsig.lblAsignatura.setVisible(false);
        frmSelectAsig.lblAnio.setVisible(false);
        frmSelectAsig.lblGrupo.setVisible(false);
        frmSelectAsig.lblPeriodo.setVisible(false);
        frmSelectAsig.pnlAsignatura.setVisible(false);
        frmSelectAsig.btnGuardarAsignatura.setVisible(false);
        frmSelectAsig.tblAsignatura.setEnabled(true);
    }

    private void muestraFormulario() {
        frmSelectAsig.tblAsignatura.setVisible(false);
        frmSelectAsig.jScrollPane1.setVisible(false);
        frmSelectAsig.pnlAsignatura.setVisible(true);
        frmSelectAsig.cbxUniversidad.setVisible(true);
//        frmSelectAsig.cbxUniversidad.setModel(llenarCBUni());
        frmSelectAsig.cbxFacultad.setVisible(true);
        frmSelectAsig.cbxFacultad.setModel(llenarCBFac());
        frmSelectAsig.cbxCarrera.setVisible(true);
        frmSelectAsig.cbxCarrera.setModel(llenarCBCar());
        frmSelectAsig.txtAsignatura.setVisible(true);
        frmSelectAsig.txtAnio.setVisible(true);
        frmSelectAsig.txtGrupo.setVisible(true);
        frmSelectAsig.txtPeriodo.setVisible(true);
        frmSelectAsig.lblCarrera.setVisible(true);
        frmSelectAsig.lblFacultad.setVisible(true);
        frmSelectAsig.lblUniversidad.setVisible(true);
        frmSelectAsig.lblAsignatura.setVisible(true);
        frmSelectAsig.lblAnio.setVisible(true);
        frmSelectAsig.lblGrupo.setVisible(true);
        frmSelectAsig.lblPeriodo.setVisible(true);
        frmSelectAsig.btnGuardarAsignatura.setVisible(true);
        frmSelectAsig.btnAceptar.setVisible(false);
    }

    public void defaultTableModel() {
        frmSelectAsig.tblAsignatura.getColumnModel().getColumn(0).setPreferredWidth(20);
        frmSelectAsig.tblAsignatura.getColumnModel().getColumn(1).setPreferredWidth(80);
        frmSelectAsig.tblAsignatura.getColumnModel().getColumn(2).setPreferredWidth(80);
        frmSelectAsig.tblAsignatura.getColumnModel().getColumn(3).setPreferredWidth(80);
        frmSelectAsig.tblAsignatura.getColumnModel().getColumn(4).setPreferredWidth(80);
        frmSelectAsig.modelo = (DefaultTableModel) frmSelectAsig.tblAsignatura.getModel();
        frmSelectAsig.modelo.setNumRows(0);
    }

    private void llenarTabla() {
        List<Asignatura> listasig = adi.obtenListaAsignaturas();
        listasig.stream().forEach((asign) -> {
            frmSelectAsig.modelo.addRow(new Object[]{
                asign.getIdasignatura(), asign.getNombreA(), asign.getGrupo(), asign.getPeriodo(), asign.getAnio()
            });
        });
    }

    private void llenarCBUni() {
//        List<Universidad> listUniv = adi.obtenListaAsignaturas();
//        cnx.Conecta();
//        try {            
//            modelocbxU = new DefaultComboBoxModel<String>();            
//            String SQL = "select nombreU from universidad";
//            st = cnx.conn.createStatement();            
//            rs = st.executeQuery(SQL);
//            while (rs.next()) {
//                modelocbxU.addElement(rs.getString("nombreU"));
//            }
//            cbxUniversidad.setModel(modelocbxU);
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error LlenarCB Universidad: " + ex.getMessage());
//        } finally {
//            cnx.Desconecta();
//        }
//        return modelocbxU;

    }

    private DefaultComboBoxModel<String> llenarCBFac() {
//        cnx.Conecta();
//        try {            
//            modelocbxF = new DefaultComboBoxModel<String>();            
//            String SQL = "select nombreF from facultad where iduniversidad = " + u.consultaIdU((String)cbxUniversidad.getSelectedItem());
//            st = cnx.conn.createStatement();            
//            rs = st.executeQuery(SQL);
//            while (rs.next()) {
//                modelocbxF.addElement(rs.getString("nombreF"));
//            }
//            cbxFacultad.setModel(modelocbxF);
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error LlenarCB Facultad: " + ex.getMessage());
//        } finally {
//            cnx.Desconecta();
//        }
//        return modelocbxF;
        return null;
    }

    private DefaultComboBoxModel<String> llenarCBCar() {
//        cnx.Conecta();
//        try {            
//            modelocbxC = new DefaultComboBoxModel<String>();            
//            String SQL = "select nombreC from carrera where idfacultad = " + f.consultaId((String)cbxFacultad.getSelectedItem());
//            st = cnx.conn.createStatement();            
//            rs = st.executeQuery(SQL);
//            while (rs.next()) {
//                modelocbxC.addElement(rs.getString("nombreC"));
//            }
//            cbxCarrera.setModel(modelocbxC);
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error LlenarCB Carrera: " + ex.getMessage());
//        } finally {
//            cnx.Desconecta();
//        }
//        return modelocbxC;
        return null;
    }

    private boolean validar() {
        boolean val;
        if (frmSelectAsig.txtAsignatura.getText().trim().length() == 0) { //Valida campo Nombre
            JOptionPane.showMessageDialog(null, "El campo de texto Asignatura está vacío,por favor llenarlo");
            val = false;
        } else if (frmSelectAsig.txtGrupo.getText().trim().length() == 0) { //Valida campo Apellido
            JOptionPane.showMessageDialog(null, "El campo de texto Código de Grupo está vacío,por favor llenarlo");
            val = false;
        } else if (frmSelectAsig.txtAnio.getText().trim().length() == 0) { //Valida campo Apellido
            JOptionPane.showMessageDialog(null, "El campo de texto Año está vacío,por favor llenarlo");
            val = false;
        } else if (frmSelectAsig.txtAnio.getText().trim().length() != 4) { //Valida campo Apellido
            JOptionPane.showMessageDialog(null, "El campo de texto Año debe tener 4 Digitos");
            val = false;
        } else if (frmSelectAsig.txtPeriodo.getText().trim().length() == 0) { //Valida campo Apellido
            JOptionPane.showMessageDialog(null, "El campo de texto Período está vacío,por favor llenarlo");
            val = false;
        } else {
            val = true;
        }
        return val;
    }
}
