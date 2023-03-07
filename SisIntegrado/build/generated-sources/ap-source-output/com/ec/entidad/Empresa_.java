package com.ec.entidad;

import com.ec.entidad.Flor;
import com.ec.entidad.GiroEmpresa;
import com.ec.entidad.Test;
import com.ec.entidad.Usuario;
import com.ec.entidad.Vacante;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T15:01:49")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile SingularAttribute<Empresa, String> empNombre;
    public static volatile CollectionAttribute<Empresa, Vacante> vacanteCollection;
    public static volatile CollectionAttribute<Empresa, Test> testCollection;
    public static volatile SingularAttribute<Empresa, GiroEmpresa> idGiroEmpresa;
    public static volatile SingularAttribute<Empresa, Usuario> idUsuario;
    public static volatile SingularAttribute<Empresa, Integer> idEmpresa;
    public static volatile SingularAttribute<Empresa, String> empDireccion;
    public static volatile SingularAttribute<Empresa, Boolean> empEstado;
    public static volatile CollectionAttribute<Empresa, Flor> florCollection;
    public static volatile SingularAttribute<Empresa, String> empCorreo;
    public static volatile SingularAttribute<Empresa, String> empTelefono;

}