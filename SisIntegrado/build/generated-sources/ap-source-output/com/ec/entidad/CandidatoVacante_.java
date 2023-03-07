package com.ec.entidad;

import com.ec.entidad.Candidato;
import com.ec.entidad.CandidatoVacantePK;
import com.ec.entidad.Vacante;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T15:01:49")
@StaticMetamodel(CandidatoVacante.class)
public class CandidatoVacante_ { 

    public static volatile SingularAttribute<CandidatoVacante, Boolean> canvNotifica;
    public static volatile SingularAttribute<CandidatoVacante, Vacante> vacante;
    public static volatile SingularAttribute<CandidatoVacante, Boolean> canvEstado;
    public static volatile SingularAttribute<CandidatoVacante, Date> canvFechaPostula;
    public static volatile SingularAttribute<CandidatoVacante, Candidato> candidato;
    public static volatile SingularAttribute<CandidatoVacante, String> canvDescripcion;
    public static volatile SingularAttribute<CandidatoVacante, CandidatoVacantePK> candidatoVacantePK;

}