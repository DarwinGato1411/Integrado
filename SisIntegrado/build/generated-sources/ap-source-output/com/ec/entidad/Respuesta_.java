package com.ec.entidad;

import com.ec.entidad.Evaluacion;
import com.ec.entidad.Pregunta;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T15:01:49")
@StaticMetamodel(Respuesta.class)
public class Respuesta_ { 

    public static volatile SingularAttribute<Respuesta, Boolean> resEstado;
    public static volatile SingularAttribute<Respuesta, String> resDescripcion;
    public static volatile CollectionAttribute<Respuesta, Evaluacion> evaluacionCollection;
    public static volatile SingularAttribute<Respuesta, Integer> idRespuesta;
    public static volatile SingularAttribute<Respuesta, Pregunta> idPregunta;

}