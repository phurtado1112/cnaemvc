/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DocenteDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import vista.SesionInicio;

/**
 *
 * @author PabloAntonio
 */
public class SesionInicioControlador {

    SesionInicio frmInicio = new SesionInicio();
    DocenteDaoImpl ddi = new DocenteDaoImpl();

    public SesionInicioControlador() {
        inicio();
    }

    private void inicio() {
        frmInicio.setVisible(true);
        frmInicio.setLocationRelativeTo(null);
        if (comprobarDocente() == false) {
            frmInicio.btnRegistro.setVisible(false);
            frmInicio.btnInicioSesion.addActionListener(Evento);
            frmInicio.btnSalir.addActionListener(Evento);
        } else {            
            frmInicio.btnInicioSesion.setVisible(false);
            frmInicio.btnRegistro.addActionListener(Evento);
            frmInicio.btnSalir.addActionListener(Evento);
        }
        
    }
    
    private final ActionListener Evento = (ActionEvent ev) -> {
        if (ev.getSource() == frmInicio.btnSalir) {
            int i = JOptionPane.showConfirmDialog(null, "Desea Salir?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }
        
        if (ev.getSource() == frmInicio.btnInicioSesion) {
            try {
                validarDocente();
            } catch (Exception ex) {
                Logger.getLogger(SesionInicioControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (ev.getSource() == frmInicio.btnRegistro) {
            IngresoInicialDocenteControlador iidc = new IngresoInicialDocenteControlador();
            frmInicio.dispose();
        }
    };

    private void validarDocente() throws Exception {
        String usuario = frmInicio.txtUsuario.getText().trim();
        char[] contra = frmInicio.pwdContrasena.getPassword();
        String contrasenia = String.valueOf(contra);
        if (usuario.equals("") || contrasenia.equals("")) {
            JOptionPane.showMessageDialog(null, "No ha ingresado el usuario o la contraseña");
        } else {
            try {
                if (contrasenia.equals(ddi.obtenerContraseniaDocente(usuario))) {
                    CnaeControlador cc = new CnaeControlador();
                    frmInicio.dispose();
                }
            } catch (HibernateException e) {
                throw e;
            }
        }
    }

    private boolean comprobarDocente() {
        boolean prueba = false;
        try {
            List doc = ddi.obtenListaDocentes();
            if (doc == null) {
                prueba = true;
            }
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Error Comprobar docente: " + e.getMessage());
        }
        return prueba;
    }
}
