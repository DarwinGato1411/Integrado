package com.ec.entidad;

import com.ec.entidad.Candidato;
import com.ec.entidad.EvaluacionPK;
import com.ec.entidad.Pregunta;
import com.ec.entidad.Respuesta;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T15:01:49")
@StaticMetamodel(Evaluacion.class)
public class Evaluacion_ { 

    public static volatile SingularAttribute<Evaluacion, BigDecimal> evaValor;
    public static volatile SingularAttribute<Evaluacion, String> evaRespuesta;
    public static volatile SingularAttribute<Evaluacion, Candidato> idCandidato;
    public static volatile SingularAttribute<Evaluacion, Boolean> evaAfirmativa;
    public static volatile SingularAttribute<Evaluacion, Date> evaFecha;
    public static volatile SingularAttribute<Evaluacion, Respuesta> respuesta;
    public static volatile SingularAttribute<Evaluacion, EvaluacionPK> evaluacionPK;
    public static volatile SingularAttribute<Evaluacion, Pregunta> pregunta;

}