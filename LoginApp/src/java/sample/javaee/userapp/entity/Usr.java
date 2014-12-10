/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.javaee.userapp.entity;

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
 * @author BBC300041
 */
@Entity
@Table(name = "USR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usr.findAll", query = "SELECT u FROM Usr u"),
    @NamedQuery(name = "Usr.findByName", query = "SELECT u FROM Usr u WHERE u.name = :name"),
    @NamedQuery(name = "Usr.findByMail", query = "SELECT u FROM Usr u WHERE u.mail = :mail")})
public class Usr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "NAME")
    private String name;
    @Size(max = 512)
    @Column(name = "MAIL")
    private String mail;

    public Usr() {
    }

    public Usr(String name) {
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usr)) {
            return false;
        }
        Usr other = (Usr) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sample.javaee.userapp.entity.Usr[ name=" + name + " ]";
    }
    
}
