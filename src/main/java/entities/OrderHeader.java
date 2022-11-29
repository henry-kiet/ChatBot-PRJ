/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SE151515 Cao Trong Hieu
 */
@Entity
@Table(name = "OrderHeader")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderHeader.findAll", query = "SELECT o FROM OrderHeader o")
    , @NamedQuery(name = "OrderHeader.findByOrderId", query = "SELECT o FROM OrderHeader o WHERE o.orderId = :orderId")
    , @NamedQuery(name = "OrderHeader.findByDate", query = "SELECT o FROM OrderHeader o WHERE o.date = :date")
    , @NamedQuery(name = "OrderHeader.findByStatus", query = "SELECT o FROM OrderHeader o WHERE o.status = :status")
    , @NamedQuery(name = "OrderHeader.findByCustomerId", query = "SELECT o FROM OrderHeader o WHERE o.customerId = :customerId")
    , @NamedQuery(name = "OrderHeader.findByStaffId", query = "SELECT o FROM OrderHeader o WHERE o.staffId = :staffId")
    , @NamedQuery(name = "OrderHeader.findByShipToAddress", query = "SELECT o FROM OrderHeader o WHERE o.shipToAddress = :shipToAddress")})
public class OrderHeader implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_id")
    private Integer orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Size(max = 30)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_id")
    private int customerId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "staff_id")
    private int staffId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "shipToAddress")
    private String shipToAddress;

    public OrderHeader() {
    }

    public OrderHeader(Integer orderId) {
        this.orderId = orderId;
    }

    public OrderHeader(Integer orderId, Date date, String status, int customerId, int staffId, String shipToAddress) {
        this.orderId = orderId;
        this.date = date;
        this.status = status;
        this.customerId = customerId;
        this.staffId = staffId;
        this.shipToAddress = shipToAddress;
    }



    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getShipToAddress() {
        return shipToAddress;
    }

    public void setShipToAddress(String shipToAddress) {
        this.shipToAddress = shipToAddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderHeader)) {
            return false;
        }
        OrderHeader other = (OrderHeader) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OrderHeader[ orderId=" + orderId + " ]";
    }
    
}
