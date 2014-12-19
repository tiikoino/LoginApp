/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.javaee.userapp.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BBC300041
 */
@Entity
@Table(name = "USR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usr.findAll", query = "SELECT u FROM Usr u"),
    @NamedQuery(name = "Usr.findById", query = "SELECT u FROM Usr u WHERE u.id = :id"),
    @NamedQuery(name = "Usr.findByName", query = "SELECT u FROM Usr u WHERE u.name = :name"),
    @NamedQuery(name = "Usr.findByMail", query = "SELECT u FROM Usr u WHERE u.mail = :mail")})
public class Usr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 512)
    @Column(name = "NAME")
    private String name;
    @Size(max = 512)
    @Column(name = "MAIL")
    private String mail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Message> messageCollection;

    public Usr() {
    }

    public Usr(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usr)) {
            return false;
        }
        Usr other = (Usr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usr{" + "id=" + id + ", name=" + name + ", mail=" + mail + ", messageCollection=" + messageCollection + '}';
    }

}
