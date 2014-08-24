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
 * Facultad generated by hbm2java
 */
@Entity
@Table(name="facultad"
    ,catalog="cnae"
)
public class Facultad  implements java.io.Serializable {


     private Integer idfacultad;
     private Universidad universidad;
     private String nombreF;
     private Set carreras = new HashSet(0);

    public Facultad() {
    }

	
    public Facultad(Universidad universidad, String nombreF) {
        this.universidad = universidad;
        this.nombreF = nombreF;
    }
    public Facultad(Universidad universidad, String nombreF, Set carreras) {
       this.universidad = universidad;
       this.nombreF = nombreF;
       this.carreras = carreras;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idfacultad", unique=true, nullable=false)
    public Integer getIdfacultad() {
        return this.idfacultad;
    }
    
    public void setIdfacultad(Integer idfacultad) {
        this.idfacultad = idfacultad;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="iduniversidad", nullable=false)
    public Universidad getUniversidad() {
        return this.universidad;
    }
    
    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    
    @Column(name="nombreF", nullable=false, length=45)
    public String getNombreF() {
        return this.nombreF;
    }
    
    public void setNombreF(String nombreF) {
        this.nombreF = nombreF;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="facultad")
    public Set getCarreras() {
        return this.carreras;
    }
    
    public void setCarreras(Set carreras) {
        this.carreras = carreras;
    }




}

