package teclan.springboot.model;

/**
 * @ClassName: Login
 * @Description: TODO
 * @Author: Teclan
 * @Date: 2019/1/22 14:13
 **/
public class Login {
    private String accountId;
    private String password;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
