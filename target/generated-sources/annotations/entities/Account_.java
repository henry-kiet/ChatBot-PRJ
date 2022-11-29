package entities;

import entities.Customer;
import entities.Staff;
import entities.UserDetail;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-11-29T15:23:10")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, String> address;
    public static volatile SingularAttribute<Account, String> role;
    public static volatile SingularAttribute<Account, String> gender;
    public static volatile ListAttribute<Account, UserDetail> userDetailList;
    public static volatile SingularAttribute<Account, Staff> staff;
    public static volatile SingularAttribute<Account, String> userName;
    public static volatile SingularAttribute<Account, Boolean> enabled;
    public static volatile SingularAttribute<Account, String> password;
    public static volatile SingularAttribute<Account, String> phone;
    public static volatile SingularAttribute<Account, String> name;
    public static volatile SingularAttribute<Account, Integer> id;
    public static volatile SingularAttribute<Account, String> email;
    public static volatile SingularAttribute<Account, Customer> customer;

}