package com.ec.entidad;

import com.ec.entidad.CandidatoVacante;
import com.ec.entidad.Empresa;
import com.ec.entidad.TipoContratacion;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T15:01:49")
@StaticMetamodel(Vacante.class)
public class Vacante_ { 

    public static volatile SingularAttribute<Vacante, BigDecimal> vacSueldo;
    public static volatile SingularAttribute<Vacante, Date> vacHoraFin;
    public static volatile CollectionAttribute<Vacante, CandidatoVacante> candidatoVacanteCollection;
    public static volatile SingularAttribute<Vacante, String> vacNombre;
    public static volatile SingularAttribute<Vacante, Integer> vacAnioExperiencia;
    public static volatile SingularAttribute<Vacante, Boolean> vacEstado;
    public static volatile SingularAttribute<Vacante, TipoContratacion> idTipoContratacion;
    public static volatile SingularAttribute<Vacante, Date> vacFechaFin;
    public static volatile SingularAttribute<Vacante, Empresa> idEmpresa;
    public static volatile SingularAttribute<Vacante, Integer> idVacante;
    public static volatile SingularAttribute<Vacante, Date> vacFechaInicio;
    public static volatile SingularAttribute<Vacante, Date> vacHoraInicio;
    public static volatile SingularAttribute<Vacante, String> vacDescripcion;

}