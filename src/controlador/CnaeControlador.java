/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ActividadDaoImpl;
import dao.ActividadDetDaoImpl;
import dao.DocenteDaoImpl;
import dao.EstudianteDaoImpl;
import dao.UniversidadDaoImpl;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import vista.ActividadDetIF;
import vista.ActividadIF;
import vista.AsignaturaIF;
import vista.AsistenciaIF;
import vista.CalendarioIF;
import vista.CambioContrasena;
import vista.CarreraIF;
import vista.DocenteIF;
import vista.EstructuraEvaluacionIF;
import vista.EstudianteIF;
import vista.FacultadIF;
import vista.NotasIF;
import vista.Reestaurar;
import vista.RepActividadDetalleIF;
import vista.RepActividadIF;
import vista.RepAsignaturasIF;
import vista.RepCalendarioIF;
import vista.RepDocentesIF;
import vista.RepEstructuraEvaluacionIF;
import vista.RepEstudiantesIF;
import vista.RepUniversidadesIF;
import vista.Respaldar;
import vista.UniversidadIF;
import vista.inicio.Cnae;
import vista.inicio.SeleccionarAsignatura;

/**
 *
 * @author PabloAntonio
 */
public class CnaeControlador {

    Cnae frmMenu = new Cnae();

    public CnaeControlador() {
        inicio();
    }

    private void inicio() {
        frmMenu.setVisible(true);
        frmMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frmMenu.setContentPane(frmMenu.Escritorio);
        frmMenu.Escritorio.setBackground(new java.awt.Color(0, 128, 192));
        frmMenu.MnuUniversidades.addActionListener(Evento);
        frmMenu.MnuSalir.addActionListener(Evento);
    }

    public void centerJIF(JInternalFrame jif) {
        Dimension desktopSize = frmMenu.Escritorio.getSize();
        Dimension jInternalFrameSize = jif.getSize();
        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }

    public boolean validar(JInternalFrame jif) {
        boolean estado;
        estado = jif.isClosed();
        return estado;
    }

    private final ActionListener Evento = (ActionEvent ev) -> {
        if (ev.getSource() == frmMenu.MnuSalir) {
            int i = JOptionPane.showConfirmDialog(null, "Desea Salir?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }

        if (ev.getSource() == frmMenu.MnuDocente) {
            DocenteDaoImpl ddi = new DocenteDaoImpl();
            DocenteIF docenteVent = new DocenteIF();
            DocenteControlador docctl = new DocenteControlador(docenteVent, ddi);
            centerJIF(docenteVent);
            frmMenu.Escritorio.add(docenteVent);
            docenteVent.toFront();
            try {
                docenteVent.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.MnuAcercaDe) {

        }

        if (ev.getSource() == frmMenu.MnuAsignatura) {
            AsignaturaIF asignaturaVent = new AsignaturaIF();
            centerJIF(asignaturaVent);
            frmMenu.Escritorio.add(asignaturaVent);
            asignaturaVent.toFront();
            try {
                asignaturaVent.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.MnuCalendario) {
            CalendarioIF calendarioVent = new CalendarioIF();
            centerJIF(calendarioVent);
            frmMenu.Escritorio.add(calendarioVent);
            calendarioVent.toFront();
            try {
                calendarioVent.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.MnuCambioAsignatura) {
            SeleccionarAsignatura saVent = new SeleccionarAsignatura();
            saVent.setVisible(true);
            frmMenu.dispose();
        }

        if (ev.getSource() == frmMenu.MnuCambioContrasena) {
            CambioContrasena cambio = new CambioContrasena();
            centerJIF(cambio);
            frmMenu.Escritorio.add(cambio);
            cambio.toFront();
            try {
                cambio.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.MnuCarreras) {
            CarreraIF carreraVent = new CarreraIF();
            centerJIF(carreraVent);
            frmMenu.Escritorio.add(carreraVent);
            carreraVent.toFront();
            try {
                carreraVent.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.MnuEstructuraEvaluacion) {
            EstructuraEvaluacionIF estructuraVent = new EstructuraEvaluacionIF();
            centerJIF(estructuraVent);
            frmMenu.Escritorio.add(estructuraVent);
            estructuraVent.toFront();
            try {
                estructuraVent.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.MnuEstudiantes) {
            JOptionPane.showConfirmDialog(null, "Estoy en el menú E");            
            EstudianteDaoImpl edi = new EstudianteDaoImpl();
            EstudianteIF estudianteVent = new EstudianteIF();
            EstudianteControlador estctl = new EstudianteControlador(estudianteVent, edi);
            centerJIF(estudianteVent);
            frmMenu.Escritorio.add(estudianteVent);
            estudianteVent.toFront();
            try {
                estudianteVent.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (ev.getSource() == frmMenu.MnuActividad) {
//            JOptionPane.showConfirmDialog(null, "Estoy en el menú actividad");
            ActividadDaoImpl adi = new ActividadDaoImpl();
            ActividadIF actividadVent = new ActividadIF();
            ActividadControlador actctl = new ActividadControlador(actividadVent,adi);
            centerJIF(actividadVent);
            frmMenu.Escritorio.add(actividadVent);
            actividadVent.toFront();
            try {
                actividadVent.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (ev.getSource() == frmMenu.MnuActividadDet) {
            ActividadDetDaoImpl addi = new ActividadDetDaoImpl();
            ActividadDetIF actividadDetVent = new ActividadDetIF();
            ActividadDetControlador actdetctl = new ActividadDetControlador(actividadDetVent, addi);
            centerJIF(actividadDetVent);
            frmMenu.Escritorio.add(actividadDetVent);
            actividadDetVent.toFront();
            try {
                actividadDetVent.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.MnuEvaluacion) {
            NotasIF evaluacionVent = new NotasIF();
            centerJIF(evaluacionVent);
            frmMenu.Escritorio.add(evaluacionVent);
            evaluacionVent.toFront();
            try {
                evaluacionVent.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.MnuFacultades) {
            FacultadIF facultadVent = new FacultadIF();
            centerJIF(facultadVent);
            frmMenu.Escritorio.add(facultadVent);
            facultadVent.toFront();
            try {
                facultadVent.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.MnuUniversidades) {
//            JOptionPane.showConfirmDialog(null, "Estoy en el menú Universidad");
            UniversidadDaoImpl udi = new UniversidadDaoImpl();
            UniversidadIF universidadVent = new UniversidadIF();
            UniversidadControlador unictl = new UniversidadControlador(universidadVent, udi);
            centerJIF(universidadVent);
            frmMenu.Escritorio.add(universidadVent);
            universidadVent.toFront();
            try {
                universidadVent.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (ev.getSource() == frmMenu.MnuHelp) {

        }

        if (ev.getSource() == frmMenu.MnuNotas) {

        }

        if (ev.getSource() == frmMenu.MnuPrArchivo) {

        }

        if (ev.getSource() == frmMenu.MnuPrAyuda) {

        }

        if (ev.getSource() == frmMenu.MnuPrCatalogo) {

        }

        if (ev.getSource() == frmMenu.MnuPrOperaciones) {

        }

        if (ev.getSource() == frmMenu.MnuPrReportes) {

        }

        if (ev.getSource() == frmMenu.MnuPrUtilitarios) {

        }

        if (ev.getSource() == frmMenu.MnuRecuperacionDatos) {
            Reestaurar recup = new Reestaurar();
            centerJIF(recup);
            frmMenu.Escritorio.add(recup);
            recup.toFront();
            try {
                recup.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.MnuRegistroAsistencia) {
            AsistenciaIF asistenciaVent = new AsistenciaIF();
            centerJIF(asistenciaVent);
            frmMenu.Escritorio.add(asistenciaVent);
            asistenciaVent.toFront();
            try {
                asistenciaVent.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.MnuRepActividad) {
            RepActividadIF actividadRep = new RepActividadIF();
            centerJIF(actividadRep);
            frmMenu.Escritorio.add(actividadRep);
            actividadRep.toFront();
            try {
                actividadRep.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.MnuRepAsistencia) {

        }

        if (ev.getSource() == frmMenu.MnuRepCalendario) {
            RepCalendarioIF calendarioRep = new RepCalendarioIF();
            centerJIF(calendarioRep);
            frmMenu.Escritorio.add(calendarioRep);
            calendarioRep.toFront();
            try {
                calendarioRep.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.MnuRepDetalleActividad) {
            RepActividadDetalleIF actividadDetalleRep = new RepActividadDetalleIF();
            centerJIF(actividadDetalleRep);
            frmMenu.Escritorio.add(actividadDetalleRep);
            actividadDetalleRep.toFront();
            try {
                actividadDetalleRep.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.MnuRepDocente) {
            RepDocentesIF docenteRep = new RepDocentesIF();
            centerJIF(docenteRep);
            frmMenu.Escritorio.add(docenteRep);
            docenteRep.toFront();
            try {
                docenteRep.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.MnuRepEstructuraEvaluacion) {
            RepEstructuraEvaluacionIF estructuraEvaluacionRep = new RepEstructuraEvaluacionIF();
            centerJIF(estructuraEvaluacionRep);
            frmMenu.Escritorio.add(estructuraEvaluacionRep);
            estructuraEvaluacionRep.toFront();
            try {
                estructuraEvaluacionRep.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.MnuRepEstudiantes) {
            RepEstudiantesIF estudianteRep = new RepEstudiantesIF();
            centerJIF(estudianteRep);
            frmMenu.Escritorio.add(estudianteRep);
            estudianteRep.toFront();
            try {
                estudianteRep.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.MnuRespaldoDatos) {
            Respaldar respal = new Respaldar();
            centerJIF(respal);
            frmMenu.Escritorio.add(respal);
            respal.toFront();
            try {
                respal.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.mnuRepAsignatura) {
            RepAsignaturasIF asignaturaRep = new RepAsignaturasIF();
            centerJIF(asignaturaRep);
            frmMenu.Escritorio.add(asignaturaRep);
            asignaturaRep.toFront();
            try {
                asignaturaRep.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ev.getSource() == frmMenu.mnuCatalogos) {

        }

        if (ev.getSource() == frmMenu.mnuRepUniversidad) {
            RepUniversidadesIF universidadRep = new RepUniversidadesIF();
            centerJIF(universidadRep);
            frmMenu.Escritorio.add(universidadRep);
            universidadRep.toFront();
            try {
                universidadRep.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Cnae.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

}
