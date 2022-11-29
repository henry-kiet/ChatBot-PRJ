package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-11-29T15:23:10")
@StaticMetamodel(OrderDetail.class)
public class OrderDetail_ { 

    public static volatile SingularAttribute<OrderDetail, Integer> quantity;
    public static volatile SingularAttribute<OrderDetail, Integer> productId;
    public static volatile SingularAttribute<OrderDetail, Integer> orderId;
    public static volatile SingularAttribute<OrderDetail, Double> price;
    public static volatile SingularAttribute<OrderDetail, Double> discount;
    public static volatile SingularAttribute<OrderDetail, Integer> id;

}