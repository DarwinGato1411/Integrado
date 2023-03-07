package com.ec.entidad;

import com.ec.entidad.Empresa;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T09:39:40")
@StaticMetamodel(GiroEmpresa.class)
public class GiroEmpresa_ { 

    public static volatile SingularAttribute<GiroEmpresa, Boolean> girEstado;
    public static volatile SingularAttribute<GiroEmpresa, Integer> idGiroEmpresa;
    public static volatile CollectionAttribute<GiroEmpresa, Empresa> empresaCollection;
    public static volatile SingularAttribute<GiroEmpresa, String> girNombre;

}