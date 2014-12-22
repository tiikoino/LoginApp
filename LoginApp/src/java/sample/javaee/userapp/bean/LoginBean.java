package sample.javaee.userapp.bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import sample.javaee.userapp.ejb.UsrFacade;
import sample.javaee.userapp.entity.Usr;

@Named(value = "loginBean")// ManagedBean宣言
@SessionScoped  // スコープ宣言
public class LoginBean implements Serializable {

    // EL式でアクセスされるフィールド
    private String name;
    private String mail;

    // CDIにてインジェクとされるクラス
    @Inject
    private UsrFacade usrFacade;

    // デフォルトコンストラクタ
    public LoginBean() {
    }

    // xhtmlから呼び出されるメソッド
    public String loginCheck() {
        List<Usr> usrList = null;

        // EJBを利用して、nameにてDB検索
        usrList = usrFacade.findByName(name);

        // 検索結果が空出なければ、値を取得
        if (!usrList.isEmpty()) {
            name = usrList.get(0).getName();
            mail = usrList.get(0).getMail();
            // OK画面へ遷移
            return "OK";
        }
        // それ以外は、NG画面へ遷移
        return "NG";
    }

    // 以降、アクセサメソッド
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
