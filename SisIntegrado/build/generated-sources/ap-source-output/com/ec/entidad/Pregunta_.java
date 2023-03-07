package com.ec.entidad;

import com.ec.entidad.Empresa;
import com.ec.entidad.Evaluacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T09:39:40")
@StaticMetamodel(Pregunta.class)
public class Pregunta_ { 

    public static volatile SingularAttribute<Pregunta, String> preDescripcion;
    public static volatile SingularAttribute<Pregunta, String> preNombre;
    public static volatile SingularAttribute<Pregunta, Empresa> idEmpresa;
    public static volatile SingularAttribute<Pregunta, Boolean> preEstado;
    public static volatile CollectionAttribute<Pregunta, Evaluacion> evaluacionCollection;
    public static volatile SingularAttribute<Pregunta, Integer> idPregunta;

}