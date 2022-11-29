/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SE151515 Cao Trong Hieu
 */
@Entity
@Table(name = "Chatbot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chatbot.findAll", query = "SELECT c FROM Chatbot c")
    , @NamedQuery(name = "Chatbot.findByKeyword", query = "SELECT c FROM Chatbot c WHERE c.keyword = :keyword")
    , @NamedQuery(name = "Chatbot.findByContent", query = "SELECT c FROM Chatbot c WHERE c.content = :content")})
public class Chatbot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "keyword")
    private String keyword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "content")
    private String content;

    public Chatbot() {
    }

    public Chatbot(String keyword) {
        this.keyword = keyword;
    }

    public Chatbot(String keyword, String content) {
        this.keyword = keyword;
        this.content = content;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (keyword != null ? keyword.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chatbot)) {
            return false;
        }
        Chatbot other = (Chatbot) object;
        if ((this.keyword == null && other.keyword != null) || (this.keyword != null && !this.keyword.equals(other.keyword))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Chatbot[ keyword=" + keyword + " ]";
    }
    
}
