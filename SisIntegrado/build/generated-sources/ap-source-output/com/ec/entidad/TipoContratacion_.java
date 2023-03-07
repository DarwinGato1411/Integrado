package com.ec.entidad;

import com.ec.entidad.Vacante;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T15:01:49")
@StaticMetamodel(TipoContratacion.class)
public class TipoContratacion_ { 

    public static volatile SingularAttribute<TipoContratacion, Boolean> tipcEstado;
    public static volatile SingularAttribute<TipoContratacion, Integer> idTipoContratacion;
    public static volatile CollectionAttribute<TipoContratacion, Vacante> vacanteCollection;
    public static volatile SingularAttribute<TipoContratacion, String> tipcNombre;

}