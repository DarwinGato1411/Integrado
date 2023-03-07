package com.ec.entidad;

import com.ec.entidad.Ciudad;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T09:39:40")
@StaticMetamodel(Pais.class)
public class Pais_ { 

    public static volatile SingularAttribute<Pais, Integer> paisCodigo;
    public static volatile SingularAttribute<Pais, String> paisNombre;
    public static volatile SingularAttribute<Pais, Integer> idPais;
    public static volatile CollectionAttribute<Pais, Ciudad> ciudadCollection;

}