/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import entities.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionbean.ProductFacade;

public class Cart {

    ProductFacade pf = lookupProductFacadeBean();

    List<Item> list = null;

    public Cart() {
        list = new ArrayList();
    }

    public void add(int id, int quantity) {
        //Tim item trong cart


        Product product = pf.find(id);

        Item item = find(id);


        // Neu item ch co trong cart thi tao moi item va them vao cart
        if (item == null) {

            item = new Item(product.getId(),
                    product.getDescription(),
                    product.getName(),
                    product.getPrice(),
                    product.getDiscount(),
                    quantity,"new",product.getImg());
            list.add(item);
        } else { //Neu da co item trong gio hang thi cap nhap so luong
            item.setQuantity(item.getQuantity() + quantity);
        }
    }

    public void check(){
        for(int i =0;i<list.size()-1;i++){
            for(int j=i+1;j<list.size();j++)
            {
                if(list.get(i).getId()==list.get(j).getId()){
                    list.get(i).setStatus("old");
                }
            }
        }
    }
    
    
    public Item find(int id) {
        
       Product product = pf.find(id);
        
        for (Item item : list) {
            if (item.getId() == id && item.getPrice()==product.getPrice() && item.getDiscount() == product.getDiscount()) {
                return item;
            }
        }
        return null;
    }

    public void delete(int id, double price , double discount) {
        Item del= new Item();
        
        for (Item item : list) {
            if (item.getId() == id && item.getPrice()== price && item.getDiscount() == discount) {
                del=item;
            }
        }
        list.remove(del);
    }

    public void update(int id, int quantity , double price , double discount) {
        
        for (Item item : list) {
            if (item.getId() == id && item.getPrice()== price && item.getDiscount() == discount) {
                item.setQuantity(quantity);
            }
        }
        
        
    }

    public void empty() {
        list.clear();
    }

    public List<Item> getItems() {
        return list;
    }

    public List<Item> getAllItems() {
        List<Item> listAll = new ArrayList();
        for (Item item : list) {
            listAll.add(item);
        }
        return listAll;
    }

    public double getTotal() {
        double total = 0;
        for (Item item : list) {
            total += item.getCost();
        }
        return total;
    }

    public int getNumOfProducts() {
        return list.size();
    }

    private ProductFacade lookupProductFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ProductFacade) c.lookup("java:global/chatbot-test/ProductFacade!sessionbean.ProductFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
