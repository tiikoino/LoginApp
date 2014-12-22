/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.javaee.userapp.ejb;

import java.util.List;
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

    // nameにてDB検索実施メソッド
    public List<Usr> findByName(String name) {
        // Usrエンティティクラスにて、宣言されている名前付きクエリの実行
        Query query = getEntityManager().createNamedQuery("Usr.findByName");
        // 検索条件のパラメータを設定
        query.setParameter("name", name);
        // 検索実行
        return query.getResultList();
    }

    public UsrFacade() {
        super(Usr.class);
    }
    
}
