/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.javaee.userapp.bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;
import sample.javaee.userapp.ejb.UsrFacade;
import sample.javaee.userapp.entity.Usr;

/**
 *
 * @author BBC300041
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String name;
    private String mail;
    @Inject
    private UsrFacade usrFacade;

    public LoginBean() {
    }

    public String loginCheck() {
        Usr findUsr = usrFacade.findByName(name);

        if (findUsr != null) {
            if (name.equals(findUsr.getName())) {
                mail = findUsr.getMail();
                return "OK";
            }
        }
        return "NG";
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
}
