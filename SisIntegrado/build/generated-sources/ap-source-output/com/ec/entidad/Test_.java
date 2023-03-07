package com.ec.entidad;

import com.ec.entidad.Empresa;
import com.ec.entidad.Pregunta;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T15:01:49")
@StaticMetamodel(Test.class)
public class Test_ { 

    public static volatile SingularAttribute<Test, String> testNombre;
    public static volatile SingularAttribute<Test, Integer> idTest;
    public static volatile SingularAttribute<Test, Empresa> idEmpresa;
    public static volatile CollectionAttribute<Test, Pregunta> preguntaCollection;

}