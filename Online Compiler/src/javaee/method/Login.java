package javaee.method;

import org.hibernate.query.Query;

public class Login extends Init {
    public boolean StartLogin(String username, String password) {
        if (!CheckUsername(username) || !CheckPassword(password)) return false;

        try {
            Query query = session.createQuery("select U.password from javaee.entity.UserEntity as U where U.username = '" + username + "'");
            return password.equals(query.list().get(0));
        } catch (Exception e) {
            errString = e.getMessage();
            return false;
        }
    }
}
