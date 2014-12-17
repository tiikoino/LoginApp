/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.javaee.userapp.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sample.javaee.userapp.entity.Usr;

/**
 *
 * @author BBC300041
 */
@Stateless
public class UsrFacade extends AbstractFacade<Usr> {
    @PersistenceContext(unitName = "UserAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Usr findByName(String name) {
        Query query = getEntityManager().createNamedQuery("Usr.findByName");
        if(name == null || name.equals("")){
            query.setParameter("name", name);
            return (Usr)query.getSingleResult();
        }
        return null;
    }

    public UsrFacade() {
        super(Usr.class);
    }
    
}
