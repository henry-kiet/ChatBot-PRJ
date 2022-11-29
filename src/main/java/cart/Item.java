/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;


public class Item {
    private int id;
    private String description;
    private String name;
    private double price;
    private double discount;
    private int quantity;
    private String status;
    private String img;
    public Item() {
    }

    public Item(int id, String description, String name, double price, double discount, int quantity, String status, String img) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.status = status;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
 
    


    public String getStatus() {
        return status;
    }

    public void setStatus(String stataus) {
        this.status = stataus;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getNewPrice() {
        return price*(1-discount);
    }
    
    public double getCost() {
        return this.getNewPrice()*quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
