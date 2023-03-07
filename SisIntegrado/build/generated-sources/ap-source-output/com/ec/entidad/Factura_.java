package com.ec.entidad;

import com.ec.entidad.DetalleFactura;
import com.ec.entidad.Usuario;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-07T09:39:40")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, BigDecimal> facSubtotal12;
    public static volatile CollectionAttribute<Factura, DetalleFactura> detalleFacturaCollection;
    public static volatile SingularAttribute<Factura, String> facTipoEntrega;
    public static volatile SingularAttribute<Factura, BigDecimal> facSubtotal0;
    public static volatile SingularAttribute<Factura, BigDecimal> facTotal;
    public static volatile SingularAttribute<Factura, Integer> idFactura;
    public static volatile SingularAttribute<Factura, Usuario> idUsuario;
    public static volatile SingularAttribute<Factura, Date> facFecha;
    public static volatile SingularAttribute<Factura, Date> fechaRetiro;
    public static volatile SingularAttribute<Factura, BigDecimal> facIva;

}