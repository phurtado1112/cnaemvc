/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DocenteDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import modelo.Docente;
import util.Globales;
import vista.inicio.IngresoInicialDocente;

/**
 *
 * @author PabloAntonio
 */
public class IngresoInicialDocenteControlador {

    IngresoInicialDocente frmIngIniDoc = new IngresoInicialDocente();
    DocenteDaoImpl ddi = new DocenteDaoImpl();
    Docente doc = new Docente();

    public IngresoInicialDocenteControlador() {
        inicio();
    }
    
    private void inicio(){
        Globales.Probar=0;
        frmIngIniDoc.setVisible(true);
        frmIngIniDoc.setLocationRelativeTo(null);
        frmIngIniDoc.btnCancelar.addActionListener(Evento);
        frmIngIniDoc.btnGuardar.addActionListener(Evento);
        frmIngIniDoc.btnSalir.addActionListener(Evento);
        frmIngIniDoc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private final ActionListener Evento = (ActionEvent ev) -> {
        if (ev.getSource() == frmIngIniDoc.btnSalir) {
            int i = JOptionPane.showConfirmDialog(null, "Desea Salir?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }
        
        if (ev.getSource() == frmIngIniDoc.btnCancelar) {
            Limpiar();
        }
        
        if(ev.getSource()== frmIngIniDoc.btnGuardar){
            GuardarDocente();
            SesionInicioControlador sic = new SesionInicioControlador();
            frmIngIniDoc.dispose();
        }
    };

    private void Limpiar() {
        frmIngIniDoc.txtNombre.setText("");
        frmIngIniDoc.txtApellido.setText("");
        frmIngIniDoc.txtUsuario.setText("");
        frmIngIniDoc.pwdContrasena.setText("");
    }
    
    private void GuardarDocente() {
        char[] arrayC = frmIngIniDoc.pwdContrasena.getPassword();
            String pass = new String(arrayC);
        if(frmIngIniDoc.txtNombre.getText().equals("") || frmIngIniDoc.txtApellido.getText().equals("")
                || frmIngIniDoc.txtUsuario.getText().equals("") || pass.equals("")){
            javax.swing.JOptionPane.showMessageDialog(null, "HAY VALORES SIN INGRESAR");
        } else{ 
            doc.setNombre(frmIngIniDoc.txtNombre.getText().trim());
            doc.setApellido(frmIngIniDoc.txtApellido.getText().trim());
            doc.setUsuario(frmIngIniDoc.txtUsuario.getText().trim());
            doc.setContrasena(pass);
            ddi.guardarDocente(doc);
            Limpiar();
        }
    }

}
