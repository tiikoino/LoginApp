package sample.javaee.userapp.bean;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import sample.javaee.userapp.entity.Usr;

@Stateless
public class UserMain {
    @PersistenceContext(unitName = "UserAppPU")
    private static EntityManager em;

    public static void main(String[] args){

        Usr isa = new Usr();
        isa.setName("isa");
        isa.setMail("isa.yukari@qualysite.co.jp");
        
// ① EntityManagerを用いた検索
        //insert
        em.persist(isa);
        //select
        Usr selectUsr = em.find(Usr.class, "isa");
        //update
        isa.setName("isa yukari");
        Usr updateUsr = em.merge(isa);
        //delete
        em.remove(isa);

// ② JPQLを用いた検索
        // 直接 JPQL を記載
        List<Usr> resultList = em.createQuery("SELECT u FROM Usr u").getResultList();

        // パラメータがある検索：パラメータを「:」にて表現
        Query query = em.createQuery("SELECT u FROM Usr u WHERE u.name = :name");
        query.setParameter("name", "isa");
        List<Usr> resultList2 = query.getResultList();
        
        //名前付きクエリ検索："Usr.findAll"はEntityクラスにて@NamedQueryで宣言されている
        List<Usr> resultList3 = em.createNamedQuery("Usr.findAll").getResultList();

// ③ Criteria APIを用いた検索
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Usr.class));
        List<Usr> resultList4 = em.createQuery(cq).getResultList();

// ④ ネイティブクエリ
        List<Usr> resultList5 = em.createNativeQuery("SELECT * FROM USR").getResultList();
        
    }
}
