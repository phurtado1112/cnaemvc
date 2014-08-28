package vista;

/**
 *
 * @author PabloAntonio
 */
public final class Cnae extends javax.swing.JFrame {
    
    public Cnae() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Escritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        MnuPrArchivo = new javax.swing.JMenu();
        MnuSalir = new javax.swing.JMenuItem();
        MnuCambioAsignatura = new javax.swing.JMenuItem();
        MnuPrOperaciones = new javax.swing.JMenu();
        MnuRegistroAsistencia = new javax.swing.JMenuItem();
        MnuEvaluacion = new javax.swing.JMenuItem();
        MnuPrCatalogo = new javax.swing.JMenu();
        MnuEstudiantes = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        MnuEstructuraEvaluacion = new javax.swing.JMenuItem();
        MnuCalendario = new javax.swing.JMenuItem();
        MnuActividadDet = new javax.swing.JMenuItem();
        MnuActividad = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MnuAsignatura = new javax.swing.JMenuItem();
        MnuCarreras = new javax.swing.JMenuItem();
        MnuFacultades = new javax.swing.JMenuItem();
        MnuUniversidades = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        MnuDocente = new javax.swing.JMenuItem();
        MnuPrReportes = new javax.swing.JMenu();
        MnuRepAsistencia = new javax.swing.JMenuItem();
        MnuRepNotas = new javax.swing.JMenuItem();
        mnuCatalogos = new javax.swing.JMenu();
        MnuRepEstudiantes = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        MnuRepEstructuraEvaluacion = new javax.swing.JMenuItem();
        MnuRepCalendario = new javax.swing.JMenuItem();
        MnuRepDetalleActividad = new javax.swing.JMenuItem();
        MnuRepActividad = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        mnuRepAsignatura = new javax.swing.JMenuItem();
        mnuRepUniversidad = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        MnuRepDocente = new javax.swing.JMenuItem();
        MnuPrUtilitarios = new javax.swing.JMenu();
        MnuCambioContrasena = new javax.swing.JMenuItem();
        MnuRespaldoDatos = new javax.swing.JMenuItem();
        MnuRecuperacionDatos = new javax.swing.JMenuItem();
        MnuPrAyuda = new javax.swing.JMenu();
        MnuHelp = new javax.swing.JMenuItem();
        MnuAcercaDe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MnuPrArchivo.setMnemonic('A');
        MnuPrArchivo.setText("Archivo");

        MnuSalir.setMnemonic('s');
        MnuSalir.setText("Salir");
        MnuPrArchivo.add(MnuSalir);

        MnuCambioAsignatura.setMnemonic('c');
        MnuCambioAsignatura.setText("Cambiar de Asignatura");
        MnuPrArchivo.add(MnuCambioAsignatura);

        jMenuBar1.add(MnuPrArchivo);

        MnuPrOperaciones.setMnemonic('o');
        MnuPrOperaciones.setText("Registro");

        MnuRegistroAsistencia.setMnemonic('r');
        MnuRegistroAsistencia.setText("Asistencia");
        MnuPrOperaciones.add(MnuRegistroAsistencia);

        MnuEvaluacion.setMnemonic('e');
        MnuEvaluacion.setText("Evaluación");
        MnuPrOperaciones.add(MnuEvaluacion);

        jMenuBar1.add(MnuPrOperaciones);

        MnuPrCatalogo.setMnemonic('c');
        MnuPrCatalogo.setText("Estructura");

        MnuEstudiantes.setMnemonic('e');
        MnuEstudiantes.setText("Estudiantes");
        MnuPrCatalogo.add(MnuEstudiantes);
        MnuPrCatalogo.add(jSeparator2);

        MnuEstructuraEvaluacion.setMnemonic('s');
        MnuEstructuraEvaluacion.setText("Estructura de Evaluación");
        MnuPrCatalogo.add(MnuEstructuraEvaluacion);

        MnuCalendario.setMnemonic('a');
        MnuCalendario.setText("Calendario");
        MnuCalendario.setToolTipText("");
        MnuCalendario.setName(""); // NOI18N
        MnuPrCatalogo.add(MnuCalendario);

        MnuActividadDet.setMnemonic('t');
        MnuActividadDet.setText("Detalle de Actividad");
        MnuPrCatalogo.add(MnuActividadDet);

        MnuActividad.setMnemonic('p');
        MnuActividad.setText("Actividades");
        MnuPrCatalogo.add(MnuActividad);
        MnuPrCatalogo.add(jSeparator1);

        MnuAsignatura.setMnemonic('g');
        MnuAsignatura.setText("Asignaturas");
        MnuPrCatalogo.add(MnuAsignatura);

        MnuCarreras.setMnemonic('r');
        MnuCarreras.setText("Carreras");
        MnuPrCatalogo.add(MnuCarreras);

        MnuFacultades.setMnemonic('f');
        MnuFacultades.setText("Facultades");
        MnuPrCatalogo.add(MnuFacultades);

        MnuUniversidades.setMnemonic('u');
        MnuUniversidades.setText("Universidades");
        MnuPrCatalogo.add(MnuUniversidades);
        MnuPrCatalogo.add(jSeparator3);

        MnuDocente.setMnemonic('d');
        MnuDocente.setText("Docente");
        MnuPrCatalogo.add(MnuDocente);

        jMenuBar1.add(MnuPrCatalogo);

        MnuPrReportes.setMnemonic('r');
        MnuPrReportes.setText("Reportes");

        MnuRepAsistencia.setMnemonic('r');
        MnuRepAsistencia.setText("Asistencia");
        MnuPrReportes.add(MnuRepAsistencia);

        MnuRepNotas.setMnemonic('n');
        MnuRepNotas.setText("Notas");
        MnuPrReportes.add(MnuRepNotas);

        mnuCatalogos.setMnemonic('c');
        mnuCatalogos.setText("Catálogos");

        MnuRepEstudiantes.setMnemonic('e');
        MnuRepEstudiantes.setText("Estudiantes");
        mnuCatalogos.add(MnuRepEstudiantes);
        mnuCatalogos.add(jSeparator5);

        MnuRepEstructuraEvaluacion.setMnemonic('v');
        MnuRepEstructuraEvaluacion.setText("Estructura de Evaluación");
        mnuCatalogos.add(MnuRepEstructuraEvaluacion);

        MnuRepCalendario.setMnemonic('l');
        MnuRepCalendario.setText("Calendario");
        mnuCatalogos.add(MnuRepCalendario);

        MnuRepDetalleActividad.setMnemonic('t');
        MnuRepDetalleActividad.setText("Detalle Actividad");
        mnuCatalogos.add(MnuRepDetalleActividad);

        MnuRepActividad.setMnemonic('i');
        MnuRepActividad.setText("Actividad");
        mnuCatalogos.add(MnuRepActividad);
        mnuCatalogos.add(jSeparator6);

        mnuRepAsignatura.setMnemonic('g');
        mnuRepAsignatura.setText("Asignaturas");
        mnuCatalogos.add(mnuRepAsignatura);

        mnuRepUniversidad.setMnemonic('u');
        mnuRepUniversidad.setText("Universidades");
        mnuCatalogos.add(mnuRepUniversidad);
        mnuCatalogos.add(jSeparator7);

        MnuRepDocente.setMnemonic('d');
        MnuRepDocente.setText("Docente");
        mnuCatalogos.add(MnuRepDocente);

        MnuPrReportes.add(mnuCatalogos);

        jMenuBar1.add(MnuPrReportes);

        MnuPrUtilitarios.setMnemonic('u');
        MnuPrUtilitarios.setText("Utilitarios");

        MnuCambioContrasena.setMnemonic('c');
        MnuCambioContrasena.setText("Cambio Contraseña");
        MnuPrUtilitarios.add(MnuCambioContrasena);

        MnuRespaldoDatos.setMnemonic('r');
        MnuRespaldoDatos.setText("Respaldo de Datos");
        MnuPrUtilitarios.add(MnuRespaldoDatos);

        MnuRecuperacionDatos.setMnemonic('d');
        MnuRecuperacionDatos.setText("Reestablecer Datos");
        MnuPrUtilitarios.add(MnuRecuperacionDatos);

        jMenuBar1.add(MnuPrUtilitarios);

        MnuPrAyuda.setMnemonic('y');
        MnuPrAyuda.setText("Ayuda");

        MnuHelp.setMnemonic('a');
        MnuHelp.setText("Ayuda");
        MnuPrAyuda.add(MnuHelp);

        MnuAcercaDe.setMnemonic('c');
        MnuAcercaDe.setText("Acerca de");
        MnuPrAyuda.add(MnuAcercaDe);

        jMenuBar1.add(MnuPrAyuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JDesktopPane Escritorio;
    public javax.swing.JMenuItem MnuAcercaDe;
    public javax.swing.JMenuItem MnuActividad;
    public javax.swing.JMenuItem MnuActividadDet;
    public javax.swing.JMenuItem MnuAsignatura;
    public javax.swing.JMenuItem MnuCalendario;
    public javax.swing.JMenuItem MnuCambioAsignatura;
    public javax.swing.JMenuItem MnuCambioContrasena;
    public javax.swing.JMenuItem MnuCarreras;
    public javax.swing.JMenuItem MnuDocente;
    public javax.swing.JMenuItem MnuEstructuraEvaluacion;
    public javax.swing.JMenuItem MnuEstudiantes;
    public javax.swing.JMenuItem MnuEvaluacion;
    public javax.swing.JMenuItem MnuFacultades;
    public javax.swing.JMenuItem MnuHelp;
    public javax.swing.JMenu MnuPrArchivo;
    public javax.swing.JMenu MnuPrAyuda;
    public javax.swing.JMenu MnuPrCatalogo;
    public javax.swing.JMenu MnuPrOperaciones;
    public javax.swing.JMenu MnuPrReportes;
    public javax.swing.JMenu MnuPrUtilitarios;
    public javax.swing.JMenuItem MnuRecuperacionDatos;
    public javax.swing.JMenuItem MnuRegistroAsistencia;
    public javax.swing.JMenuItem MnuRepActividad;
    public javax.swing.JMenuItem MnuRepAsistencia;
    public javax.swing.JMenuItem MnuRepCalendario;
    public javax.swing.JMenuItem MnuRepDetalleActividad;
    public javax.swing.JMenuItem MnuRepDocente;
    public javax.swing.JMenuItem MnuRepEstructuraEvaluacion;
    public javax.swing.JMenuItem MnuRepEstudiantes;
    public javax.swing.JMenuItem MnuRepNotas;
    public javax.swing.JMenuItem MnuRespaldoDatos;
    public javax.swing.JMenuItem MnuSalir;
    public javax.swing.JMenuItem MnuUniversidades;
    public javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JPopupMenu.Separator jSeparator1;
    public javax.swing.JPopupMenu.Separator jSeparator2;
    public javax.swing.JPopupMenu.Separator jSeparator3;
    public javax.swing.JPopupMenu.Separator jSeparator5;
    public javax.swing.JPopupMenu.Separator jSeparator6;
    public javax.swing.JPopupMenu.Separator jSeparator7;
    public javax.swing.JMenu mnuCatalogos;
    public javax.swing.JMenuItem mnuRepAsignatura;
    public javax.swing.JMenuItem mnuRepUniversidad;
    // End of variables declaration//GEN-END:variables
}