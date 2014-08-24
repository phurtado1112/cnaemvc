package vista;

import clases.Universidad;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.Conecta;

/**
 *
 * @author PabloAntonio
 */
public class RepUniversidadesIF extends javax.swing.JInternalFrame {

    public Connection conn;
    JasperReport reporte;
    JasperPrint jasperprint;
    JasperViewer visor;
    Statement stm;
    Conecta cnx = new Conecta();
    DefaultComboBoxModel<String> modeloCombo;
    ResultSet rs;
    Universidad u = new Universidad();

    /**
     * Creates new form repUniversidadIF
     */
    public RepUniversidadesIF() {
        initComponents();
    }

    public void reporteU() {
        try {
            Class.forName("org.sqlite.JDBC"); //driver a utilizar                       
            conn = DriverManager.getConnection("jdbc:sqlite:cnae.sqlite");

            reporte = (JasperReport) JRLoader.loadObjectFromFile("src/reportes/repUniversidad.jasper");
            jasperprint = JasperFillManager.fillReport(reporte, null, conn);
            visor = new JasperViewer(jasperprint, false);
            visor.setTitle("Universidades - CNAE");
            visor.setVisible(true);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en Reporte Universidad: " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(RepUniversidadesIF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void reporteUDet() {
        try {
            Class.forName("org.sqlite.JDBC"); //driver a utilizar                       
            conn = DriverManager.getConnection("jdbc:sqlite:cnae.sqlite");

            int Univer = u.consultaIdU(cbUniversidad.getSelectedItem().toString());

            Map<String,Object> parametros = new HashMap<String,Object>();
            parametros.put("iduniv", Univer);
            JOptionPane.showMessageDialog(null, "El valor de iduniv: " + parametros);

            reporte = (JasperReport) JRLoader.loadObjectFromFile("src/reportes/repCarrera.jasper");
            jasperprint = JasperFillManager.fillReport(reporte, parametros, conn);
            visor = new JasperViewer(jasperprint, false);
            visor.setTitle("Universidades - CNAE");
            visor.setVisible(true);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en Reporte Universidad: " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(RepUniversidadesIF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public final void llenarCB() {
        cnx.Conecta();
        try {
            modeloCombo = new DefaultComboBoxModel<String>();
            String SQL = "select nombreU from universidad";
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                modeloCombo.addElement(rs.getString("nombreU"));
            }
            rs.close();
            cbUniversidad.setModel(modeloCombo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCB: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnEjecutar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cbUniversidad = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        rbDetalle = new javax.swing.JRadioButton();
        rbListado = new javax.swing.JRadioButton();

        setTitle("Reporte de Universidades");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Universidad"));

        cbUniversidad.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbUniversidad.setEnabled(false);

        jLabel1.setText("Universidad");

        buttonGroup1.add(rbDetalle);
        rbDetalle.setText("Detalle");
        rbDetalle.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbDetalleItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rbListado);
        rbListado.setText("Listado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbDetalle)
                    .addComponent(rbListado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cbUniversidad, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbDetalle)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbUniversidad, javax.swing.GroupLayout.Alignment.LEADING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, Short.MAX_VALUE)
                .addComponent(rbListado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEjecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(btnEjecutar))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Salir del Informe?", "Confirmar",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
        if (i == JOptionPane.OK_OPTION) {
            this.doDefaultCloseAction();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        if (rbDetalle.isSelected() == true) {
            this.reporteUDet();
        } else {
            this.reporteU();
        }
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void rbDetalleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbDetalleItemStateChanged
        if (rbDetalle.isSelected() == true) {
            cbUniversidad.setEnabled(true);
            llenarCB();
        } else {
            cbUniversidad.setEnabled(false);
        }
    }//GEN-LAST:event_rbDetalleItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEjecutar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbUniversidad;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbDetalle;
    private javax.swing.JRadioButton rbListado;
    // End of variables declaration//GEN-END:variables
}