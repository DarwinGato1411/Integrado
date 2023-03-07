package com.ec.entidad;

import com.ec.entidad.Candidato;
import com.ec.entidad.Canton;
import com.ec.entidad.Empresa;
import com.ec.entidad.Factura;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T09:39:40")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> usuCorreo;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile CollectionAttribute<Usuario, Factura> facturaCollection;
    public static volatile SingularAttribute<Usuario, String> usuNombre;
    public static volatile SingularAttribute<Usuario, String> usuLogin;
    public static volatile SingularAttribute<Usuario, Integer> usuNivel;
    public static volatile CollectionAttribute<Usuario, Candidato> candidatoCollection;
    public static volatile SingularAttribute<Usuario, String> usuFoto;
    public static volatile SingularAttribute<Usuario, String> usuTipoUsuario;
    public static volatile SingularAttribute<Usuario, Date> usuFechaRegistro;
    public static volatile SingularAttribute<Usuario, Canton> idCanton;
    public static volatile CollectionAttribute<Usuario, Empresa> empresaCollection;
    public static volatile SingularAttribute<Usuario, String> usuRuc;
    public static volatile SingularAttribute<Usuario, String> usuPassword;
    public static volatile SingularAttribute<Usuario, Boolean> usuActivo;

}