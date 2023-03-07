package com.ec.entidad;

import com.ec.entidad.Canton;
import com.ec.entidad.Pais;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T09:39:40")
@StaticMetamodel(Ciudad.class)
public class Ciudad_ { 

    public static volatile SingularAttribute<Ciudad, Boolean> ciuEstado;
    public static volatile SingularAttribute<Ciudad, Pais> idPais;
    public static volatile CollectionAttribute<Ciudad, Canton> cantonCollection;
    public static volatile SingularAttribute<Ciudad, String> ciuNombre;
    public static volatile SingularAttribute<Ciudad, Integer> idCiudad;

}