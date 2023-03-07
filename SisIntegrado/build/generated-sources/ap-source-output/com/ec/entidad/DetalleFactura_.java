package com.ec.entidad;

import com.ec.entidad.Factura;
import com.ec.entidad.Flor;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T15:01:49")
@StaticMetamodel(DetalleFactura.class)
public class DetalleFactura_ { 

    public static volatile SingularAttribute<DetalleFactura, String> detDescripcion;
    public static volatile SingularAttribute<DetalleFactura, BigDecimal> detTotal;
    public static volatile SingularAttribute<DetalleFactura, Factura> idFactura;
    public static volatile SingularAttribute<DetalleFactura, BigDecimal> detSubtotal;
    public static volatile SingularAttribute<DetalleFactura, Flor> idFlor;
    public static volatile SingularAttribute<DetalleFactura, Integer> idDetalleFactura;
    public static volatile SingularAttribute<DetalleFactura, BigDecimal> delIva;

}