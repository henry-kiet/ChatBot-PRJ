/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;
import order.order;

/**
 *
 * @author Admin
 */
public class Chat {
    private String uInput;
    private String botMsg;
    private List<order> order;
    public Chat(){
        uInput="";
        botMsg="";
        order = new ArrayList();
    }
    
    public Chat(String uInput, String botMsg){
        this.uInput = uInput;
        this.botMsg = botMsg;
    }

    public Chat(String uInput, String botMsg, List<order> order) {
        this.uInput = uInput;
        this.botMsg = botMsg;
        this.order = order;
    }
       
    public String getuInput() {
        return uInput;
    }

    public void setuInput(String uInput) {
        this.uInput = uInput;
    }

    public String getBotMsg() {
        return botMsg;
    }

    public void setBotMsg(String botMsg) {
        this.botMsg = botMsg;
    }

    public void setOrder(List<order> order) {
        this.order = order;
    }

    public List<order> getOrder() {
        return order;
    }   
}
