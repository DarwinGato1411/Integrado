package com.ec.entidad;

import com.ec.entidad.CandidatoVacante;
import com.ec.entidad.Evaluacion;
import com.ec.entidad.Usuario;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T15:01:49")
@StaticMetamodel(Candidato.class)
public class Candidato_ { 

    public static volatile SingularAttribute<Candidato, BigDecimal> canAspiracionSalarial;
    public static volatile SingularAttribute<Candidato, Integer> idCandidato;
    public static volatile SingularAttribute<Candidato, String> canDireccion;
    public static volatile SingularAttribute<Candidato, Usuario> idUsuario;
    public static volatile SingularAttribute<Candidato, String> canHojaVida;
    public static volatile SingularAttribute<Candidato, String> canDescripcion;
    public static volatile SingularAttribute<Candidato, String> canTelefono;
    public static volatile CollectionAttribute<Candidato, CandidatoVacante> candidatoVacanteCollection;
    public static volatile CollectionAttribute<Candidato, Evaluacion> evaluacionCollection;
    public static volatile SingularAttribute<Candidato, Integer> canEdad;

}