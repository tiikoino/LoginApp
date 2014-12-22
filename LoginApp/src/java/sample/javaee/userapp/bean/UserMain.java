package sample.javaee.userapp.bean;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import sample.javaee.userapp.entity.Usr;

@Named(value = "userMain")// ManagedBean宣言
@Dependent// スコープの宣言
@Stateless// EBJ 宣言
public class UserMain implements Serializable{
    
    // EntityManager宣言
    @PersistenceContext(unitName = "UserAppPU")
    private  EntityManager em;

    // EL式でアクセスされるフィールド
    private Usr isa;
    
    // xhtmlから呼び出されるメソッド
    public void sample(){
        
        //User オブジェクトの生成
        createUsr();
        
// ① EntityManagerを用いた検索
        insertWithEntityManager();
        selectWithEntityManager();
        updateWithEntityManager();
        deleteWithEntityManager();
        
// ② JPQLを用いた検索
        selectWithJPQL();
        
// ③ Criteria APIを用いた検索
       selectWithCriteriaAPI();

// ④ ネイティブクエリ
       selectWithNativeQuery();
    }
    
// オブジェクトの生成
    public void createUsr(){
        isa = new Usr();
        isa.setName("isa");
        isa.setMail("isa.yukari@qualysite.co.jp");
    }
    
// ① EntityManagerを用いた検索 -------------------------------------------------
    @Transactional(Transactional.TxType.REQUIRED)
    public void insertWithEntityManager(){
        //INSERT処理
        em.persist(isa);
        // insert処理をDBへ反映
        em.flush();
        System.out.println("EntityManager INSERT:" + em.createNamedQuery("Usr.findAll").getResultList());
    }        

    @Transactional(Transactional.TxType.SUPPORTS)
    public void selectWithEntityManager(){
        //SELECT処理　find(検索したエンティティのクラスObj,主キーの値)
        Usr selectUsr = em.find(Usr.class, 4);
        System.out.println("EntityManager SELECT:" + selectUsr);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void updateWithEntityManager(){
        //UPDATE処理
        isa.setName("isa yukari");
        Usr updateUsr = em.merge(isa);
        System.out.println("EntityManager UPDATE:" + updateUsr);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public void deleteWithEntityManager(){
        //DELETE処理
        em.remove(isa);
        System.out.println("EntityManager DELETE:" + em.createNamedQuery("Usr.findAll").getResultList());
    }
// ② JPQLを用いた検索 ----------------------------------------------------------
    @Transactional(Transactional.TxType.SUPPORTS)
    public void selectWithJPQL(){
        // 直接 JPQL を記載
        List<Usr> resultList = em.createQuery("SELECT u FROM Usr u").getResultList();
        System.out.println("JPQL SELECT:" + resultList);

        // パラメータがある検索：パラメータを「:」にて表現
        Query query = em.createQuery("SELECT u FROM Usr u WHERE u.name = :name");
        query.setParameter("name", "isa");
        List<Usr> resultList2 = query.getResultList();
        System.out.println("JPQL param SELECT:" + resultList2);
        
        //名前付きクエリ検索："Usr.findAll"はEntityクラスにて@NamedQueryで宣言されている
        List<Usr> resultList3 = em.createNamedQuery("Usr.findAll").getResultList();
        System.out.println("JPQL named SELECT:" + resultList3);
    }
    
// ③ Criteria APIを用いた検索 --------------------------------------------------
    @Transactional(Transactional.TxType.SUPPORTS)
    public void selectWithCriteriaAPI(){
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Usr.class));
        List<Usr> resultList4 = em.createQuery(cq).getResultList();

        System.out.println("Criteria API SELECT:" + resultList4);
    }

// ④ ネイティブクエリ ----------------------------------------------------------
    @Transactional(Transactional.TxType.SUPPORTS)
    public void selectWithNativeQuery(){
        Query query = em.createNativeQuery("SELECT * FROM USR");
        List<Usr> resultList5 = query.getResultList();
        System.out.println("NativeQuery SELECT:" + resultList5);
        
    }
}
