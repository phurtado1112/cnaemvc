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
 * Carrera generated by hbm2java
 */
@Entity
@Table(name="carrera"
    ,catalog="cnae"
)
public class Carrera  implements java.io.Serializable {


     private Integer idcarrera;
     private Facultad facultad;
     private String nombreC;
     private Set asignaturas = new HashSet(0);

    public Carrera() {
    }

	
    public Carrera(Facultad facultad, String nombreC) {
        this.facultad = facultad;
        this.nombreC = nombreC;
    }
    public Carrera(Facultad facultad, String nombreC, Set asignaturas) {
       this.facultad = facultad;
       this.nombreC = nombreC;
       this.asignaturas = asignaturas;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idcarrera", unique=true, nullable=false)
    public Integer getIdcarrera() {
        return this.idcarrera;
    }
    
    public void setIdcarrera(Integer idcarrera) {
        this.idcarrera = idcarrera;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idfacultad", nullable=false)
    public Facultad getFacultad() {
        return this.facultad;
    }
    
    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    
    @Column(name="nombreC", nullable=false, length=45)
    public String getNombreC() {
        return this.nombreC;
    }
    
    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="carrera")
    public Set getAsignaturas() {
        return this.asignaturas;
    }
    
    public void setAsignaturas(Set asignaturas) {
        this.asignaturas = asignaturas;
    }




}


