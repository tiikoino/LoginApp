package sample.javaee.userapp.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sample.javaee.userapp.entity.Usr;

@Stateless
public class UsrFacade extends AbstractFacade<Usr> {
    @PersistenceContext(unitName = "UserAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsrFacade() {
        super(Usr.class);
    }
    
}
