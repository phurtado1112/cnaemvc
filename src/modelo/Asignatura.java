package modelo;
// Generated 08-01-2014 03:42:54 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Asignatura generated by hbm2java
 */
@Entity
@Table(name="asignatura"
    ,catalog="cnae"
)
public class Asignatura  implements java.io.Serializable {


     private Integer idasignatura;
     private Carrera carrera;
     private String nombreA;
     private String grupo;
     private Integer anio;
     private String periodo;
     private Set estructuraevaluacions = new HashSet(0);
     private Set calendarios = new HashSet(0);
     private Set asistencias = new HashSet(0);
     private Set notases = new HashSet(0);
     private Set estudianteses = new HashSet(0);

    public Asignatura() {
    }

	
    public Asignatura(Carrera carrera, String nombreA, String grupo) {
        this.carrera = carrera;
        this.nombreA = nombreA;
        this.grupo = grupo;
    }
    public Asignatura(Carrera carrera, String nombreA, String grupo, Integer anio, String periodo, Set estructuraevaluacions, Set calendarios, Set asistencias, Set notases, Set estudianteses) {
       this.carrera = carrera;
       this.nombreA = nombreA;
       this.grupo = grupo;
       this.anio = anio;
       this.periodo = periodo;
       this.estructuraevaluacions = estructuraevaluacions;
       this.calendarios = calendarios;
       this.asistencias = asistencias;
       this.notases = notases;
       this.estudianteses = estudianteses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idasignatura", unique=true, nullable=false)
    public Integer getIdasignatura() {
        return this.idasignatura;
    }
    
    public void setIdasignatura(Integer idasignatura) {
        this.idasignatura = idasignatura;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idcarrera", nullable=false)
    public Carrera getCarrera() {
        return this.carrera;
    }
    
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    
    @Column(name="nombreA", nullable=false, length=45)
    public String getNombreA() {
        return this.nombreA;
    }
    
    public void setNombreA(String nombreA) {
        this.nombreA = nombreA;
    }

    
    @Column(name="grupo", nullable=false, length=45)
    public String getGrupo() {
        return this.grupo;
    }
    
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    
    @Column(name="anio")
    public Integer getAnio() {
        return this.anio;
    }
    
    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    
    @Column(name="periodo", length=45)
    public String getPeriodo() {
        return this.periodo;
    }
    
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="asignatura")
    public Set getEstructuraevaluacions() {
        return this.estructuraevaluacions;
    }
    
    public void setEstructuraevaluacions(Set estructuraevaluacions) {
        this.estructuraevaluacions = estructuraevaluacions;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="asignatura")
    public Set getCalendarios() {
        return this.calendarios;
    }
    
    public void setCalendarios(Set calendarios) {
        this.calendarios = calendarios;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="asignatura")
    public Set getAsistencias() {
        return this.asistencias;
    }
    
    public void setAsistencias(Set asistencias) {
        this.asistencias = asistencias;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="asignatura")
    public Set getNotases() {
        return this.notases;
    }
    
    public void setNotases(Set notases) {
        this.notases = notases;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="asignatura")
    public Set getEstudianteses() {
        return this.estudianteses;
    }
    
    public void setEstudianteses(Set estudianteses) {
        this.estudianteses = estudianteses;
    }




}

