package entities;

import entities.Account;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-11-29T15:23:10")
@StaticMetamodel(Staff.class)
public class Staff_ { 

    public static volatile SingularAttribute<Staff, Integer> id;
    public static volatile SingularAttribute<Staff, BigDecimal> salary;
    public static volatile SingularAttribute<Staff, Account> account;

}