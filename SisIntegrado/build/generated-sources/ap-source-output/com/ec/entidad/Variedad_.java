package com.ec.entidad;

import com.ec.entidad.Flor;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T09:39:40")
@StaticMetamodel(Variedad.class)
public class Variedad_ { 

    public static volatile SingularAttribute<Variedad, Integer> idVariedad;
    public static volatile SingularAttribute<Variedad, String> varDescripcion;
    public static volatile SingularAttribute<Variedad, String> varTipo;
    public static volatile SingularAttribute<Variedad, String> varNombre;
    public static volatile SingularAttribute<Variedad, Integer> varTamano;
    public static volatile CollectionAttribute<Variedad, Flor> florCollection;

}