package com.ec.entidad;

import com.ec.entidad.Ciudad;
import com.ec.entidad.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T09:39:40")
@StaticMetamodel(Canton.class)
public class Canton_ { 

    public static volatile SingularAttribute<Canton, String> canNombre;
    public static volatile SingularAttribute<Canton, Integer> idCanton;
    public static volatile SingularAttribute<Canton, Boolean> canEstado;
    public static volatile CollectionAttribute<Canton, Usuario> usuarioCollection;
    public static volatile SingularAttribute<Canton, Ciudad> idCiudad;

}