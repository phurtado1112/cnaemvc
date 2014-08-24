package vista;

import clases.Asignatura;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import util.Conecta;
import clases.Calendario;
import clases.Asistencia;
import clases.Estudiantes;
import java.awt.HeadlessException;
import util.Globales;
import util.Valida;
/**
 *
 * @author Pablo
 */
public class AsistenciaIF extends javax.swing.JInternalFrame {
    
    DefaultTableModel model;
    DefaultComboBoxModel<String> modeloCombo;
    Asignatura a = new Asignatura();
    Calendario Ca = new Calendario();
    Asistencia asis = new Asistencia();
    Estudiantes E = new Estudiantes();
    Valida va=new Valida();
    Conecta cnx = new Conecta();
    ResultSet rs;
    Statement stm;

    public AsistenciaIF() {
        initComponents();
        cnx.Conecta();
        limpiar();
        BotonesInicio();
        LlenarTablaIngreso();        
        llenarTXT();      
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
     
    public void Asistencia(JTable Tabla, TableColumn columna ){
        String Asis[] = {"Presente","Ausente"};
        JComboBox<String> Combo = new JComboBox<String>(Asis);
        columna.setCellEditor(new DefaultCellEditor(Combo));        
    }

    private void limpiar(){
        cbxFecha.removeAllItems();
    }
  
    private void Habilitar(){
        cbxFecha.setEnabled(true);
    }
    
    private void Deshabilitar() {        
        cbxFecha.setEnabled(false);
        LlenarTablaIngreso();
        tblAsistencia.setEnabled(false);
    }
    
    private void BotonesInicio(){
        jcbIngresar.setEnabled(true);
        jcbIngresar.setSelected(false);
        btnGuardar.setEnabled(false);
        jcbModificar.setEnabled(true);
        jcbModificar.setSelected(false);
        btnModificar.setEnabled(false);
        btnCancelar.setEnabled(false);        
    }
    
    private void BotonesIngresar(){
        btnGuardar.setEnabled(true);
        jcbModificar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnCancelar.setEnabled(true);        
    }
    
    private void BotonesModificar(){
        jcbIngresar.setEnabled(false);    
        btnModificar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }

    private void LlenarTablaIngreso() {
       int[] anchos = {30, 100, 100};
       
       cnx.Conecta();
        try{           
            String [] titulos ={"ID","Nombre","Asistencia"};
            
            String SQL = "Select * from estudiantea_view where idasignatura =" + Globales.id;
            model = new DefaultTableModel(null, titulos);
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            String [] fila = new String[2];
            while(rs.next()){
                fila[0] = rs.getString("idestudiante");
                fila[1] = rs.getString("nombre");             
                model.addRow(fila);
            }
            tblAsistencia.setModel(model);
            
            for(int i = 0; i < tblAsistencia.getColumnCount(); i++) {
                tblAsistencia.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
             Asistencia(tblAsistencia, tblAsistencia.getColumnModel().getColumn(2));   
             
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error LlenarTabla Asistencia: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
    
    private void LlenarTablaModificar() {
       int[] anchos = {30, 100, 100};       
       cnx.Conecta();
        try{                         
              String [] titulos ={"ID","Nombre","Asistencia"};              
              String SQL = "Select * from asistencia_view where fecha = '" + cbxFecha.getSelectedItem().toString() + "'";

              model = new DefaultTableModel(null, titulos);
              stm = cnx.conn.createStatement();
              rs = stm.executeQuery(SQL);
              String [] fila = new String[3];
              while(rs.next())
              {
                  fila[0] = rs.getString("idasistencia");
                  fila[1] = rs.getString("nombre");
                  fila[2] = rs.getString("asistencia");            
                  model.addRow(fila);
              }
              tblAsistencia.setModel(model);

              for(int i = 0; i < tblAsistencia.getColumnCount(); i++) {
                    tblAsistencia.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                }
              Asistencia(tblAsistencia, tblAsistencia.getColumnModel().getColumn(2));            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error LlenarTabla Asistencia para edición: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
        
    private DefaultComboBoxModel<String> llenarCB() {        
        cnx.Conecta();
        try {            
            modeloCombo = new DefaultComboBoxModel<String>();
            String SQL = "select fecha from calendario";
            stm = cnx.conn.createStatement();            
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                modeloCombo.addElement(rs.getString("fecha"));
            }
            cbxFecha.setModel(modeloCombo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCB: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
        cbxFecha.setSelectedIndex(-1);
        return modeloCombo;        
    }
    
    private DefaultComboBoxModel<String> llenarCBModificar() {        
        cnx.Conecta();
        try {            
            modeloCombo = new DefaultComboBoxModel<String>();
            String SQL = "select distinct c.fecha from calendario as c inner join asistencia as a on "
                          + "(c.idcalendario=a.idcalendario)";
            stm = cnx.conn.createStatement();            
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                modeloCombo.addElement(rs.getString("fecha"));
            }
            cbxFecha.setModel(modeloCombo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCB para Modificación: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
        cbxFecha.setSelectedIndex(-1);
        return modeloCombo;        
    }
    
    private void llenarTXT() {
        cnx.Conecta();
         try {             
            String SQL = "select nombreA from asignatura where idasignatura = " + Globales.id;
            stm = cnx.conn.createStatement();            
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                txtAsignatura.setText(rs.getString("nombreA"));
            }
            rs.close();            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarTXT: " + ex.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbxFecha = new javax.swing.JComboBox<String>();
        jLabel3 = new javax.swing.JLabel();
        txtAsignatura = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAsistencia = new javax.swing.JTable();
        jcbModificar = new javax.swing.JCheckBox();
        jcbIngresar = new javax.swing.JCheckBox();
        btnModificar = new javax.swing.JButton();

        setTitle("Lista de Asistencia");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de Asistencia"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Fecha");

        cbxFecha.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxFecha.setEnabled(false);
        cbxFecha.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxFechaItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Asignatura");

        txtAsignatura.setEditable(false);
        txtAsignatura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtAsignatura.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtAsignatura.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        tblAsistencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAsistencia.setColumnSelectionAllowed(true);
        tblAsistencia.setEnabled(false);
        tblAsistencia.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblAsistencia);
        tblAsistencia.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblAsistencia.getColumnModel().getColumnCount() > 0) {
            tblAsistencia.getColumnModel().getColumn(0).setResizable(false);
            tblAsistencia.getColumnModel().getColumn(1).setResizable(false);
        }

        jcbModificar.setText("Modificar");
        jcbModificar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbModificarItemStateChanged(evt);
            }
        });

        jcbIngresar.setText("Ingresar");
        jcbIngresar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbIngresarItemStateChanged(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jcbIngresar)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(jcbModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addGap(18, 30, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalir)
                    .addComponent(btnGuardar)
                    .addComponent(jcbIngresar)
                    .addComponent(jcbModificar)
                    .addComponent(btnModificar))
                .addGap(23, 23, 23))
        );

        jcbModificar.getAccessibleContext().setAccessibleDescription("");

        getAccessibleContext().setAccessibleParent(jScrollPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try
        {
            if (cbxFecha.getSelectedIndex()==-1)
            {
                JOptionPane.showMessageDialog(null,"El campo fecha esta vacío");
            }
            else if(asis.validarFecha(Ca.ConsultarIDCal(cbxFecha.getSelectedItem().toString()))>0)
            {
                JOptionPane.showMessageDialog(null, "Ya existe un registro de asistencia con esa Fecha \n "+
                        "Si desea cambiar el valor de las asistencias utilice la función Modificar.");
            }
            else
            {
                DefaultTableModel modelo = (DefaultTableModel)tblAsistencia.getModel();
                int filas = modelo.getRowCount();      
                for (int i = 0; i < filas; i++) 
                {  
                    if (tblAsistencia.getValueAt(i, 2)==null)
                    {
                        asis.setAsistencia("Ausente");
                    }
                    else
                    {
                        asis.setAsistencia(modelo.getValueAt(i, 2).toString());                     
                    }
                    asis.setIdcalendario(Ca.ConsultarIDCal(cbxFecha.getSelectedItem().toString()));                               
                    asis.setIdestudiante(Integer.parseInt(modelo.getValueAt(i, 0).toString()));
                    asis.setIdasignatura(a.consultaIdA(txtAsignatura.getText()));                
                    asis.GuardarAsistencia();                           
                }
                JOptionPane.showMessageDialog(null, "Datos Guardados Exitosamente");
                limpiar();
                Deshabilitar();
                BotonesInicio();
            }
        }            
        catch(HeadlessException | NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Error guardar Asistencia: " + e.getMessage());
        }       
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
                limpiar();
                Deshabilitar();
                LlenarTablaIngreso();
                BotonesInicio();                                
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Salir?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            this.doDefaultCloseAction();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void cbxFechaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxFechaItemStateChanged
        if (cbxFecha.getSelectedIndex()==-1)     
        {
            
        }else if (jcbIngresar.isSelected()==true && cbxFecha.getSelectedIndex()!=-1)
        {
            if(asis.validarFecha(Ca.ConsultarIDCal(cbxFecha.getSelectedItem().toString()))>0)
            {
                String fecha=cbxFecha.getSelectedItem().toString();
                int i = JOptionPane.showConfirmDialog(null, "Ya existe un registro de asistencia con la fecha: \n"
                        + fecha + " \nDesea Modificarlo ?", "Confirmar",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (i == JOptionPane.OK_OPTION) 
                {
                    limpiar();                    
                    BotonesInicio();
                    jcbModificar.setSelected(true);
                    cbxFecha.setSelectedItem(fecha);
                    LlenarTablaModificar();
                } else
                {
                    limpiar();
                    Deshabilitar();
                    LlenarTablaIngreso();
                    BotonesInicio();
                }
            }
        } else
        {
            LlenarTablaModificar();
        }
    }//GEN-LAST:event_cbxFechaItemStateChanged

    private void jcbModificarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbModificarItemStateChanged
        if (jcbModificar.isSelected())
        {
            Habilitar();
            BotonesModificar();
            llenarCBModificar();
            tblAsistencia.setEnabled(true);
        }
        else
        {
            limpiar();
            Deshabilitar();
            BotonesInicio();            
        }
    }//GEN-LAST:event_jcbModificarItemStateChanged

    private void jcbIngresarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbIngresarItemStateChanged
        if (jcbIngresar.isSelected())
        {
            Habilitar();
            BotonesIngresar();
            llenarCB();
            tblAsistencia.setEnabled(true);
        }
        else
        {
            limpiar();
            Deshabilitar();
            BotonesInicio();            
        }
    }//GEN-LAST:event_jcbIngresarItemStateChanged

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try
        {
            if (cbxFecha.getSelectedIndex()==-1)
            {
                JOptionPane.showMessageDialog(null,"El campo fecha esta vacío");
            }
            else 
            {
                DefaultTableModel modelo = (DefaultTableModel)tblAsistencia.getModel();
                int filas = modelo.getRowCount();      
                for (int i = 0; i < filas; i++) 
                {
                    asis.setIdasistencia(Integer.parseInt(modelo.getValueAt(i,0).toString()));
                    asis.setAsistencia(modelo.getValueAt(i, 2).toString());              
                    asis.ActualizarAsistencia();                                               
                }          
            JOptionPane.showMessageDialog(null, "Datos Actualizados Exitosamente");
            limpiar();
            Deshabilitar();
            BotonesInicio();
            }

        }            
        catch(HeadlessException | NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Error actualizar Asistencia: " + e.getMessage());
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbxFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox jcbIngresar;
    private javax.swing.JCheckBox jcbModificar;
    private javax.swing.JTable tblAsistencia;
    private javax.swing.JTextField txtAsignatura;
    // End of variables declaration//GEN-END:variables
}