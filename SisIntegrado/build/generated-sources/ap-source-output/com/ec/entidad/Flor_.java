package com.ec.entidad;

import com.ec.entidad.DetalleFactura;
import com.ec.entidad.Empresa;
import com.ec.entidad.Variedad;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T15:01:49")
@StaticMetamodel(Flor.class)
public class Flor_ { 

    public static volatile CollectionAttribute<Flor, DetalleFactura> detalleFacturaCollection;
    public static volatile SingularAttribute<Flor, Integer> florCantidad;
    public static volatile SingularAttribute<Flor, Variedad> idVariedad;
    public static volatile SingularAttribute<Flor, String> florDireccion;
    public static volatile SingularAttribute<Flor, Empresa> idEmpresa;
    public static volatile SingularAttribute<Flor, Integer> idFlor;
    public static volatile SingularAttribute<Flor, BigDecimal> florPrecioUnidad;

}