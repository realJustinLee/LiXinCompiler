package javaee.method;

import javaee.entity.UserEntity;
import org.hibernate.Transaction;

public class Register extends Init {
    public boolean StartRegister(String username, String password) {
        if (!CheckUsername(username) || !CheckPassword(password)) return false;

        try {
            Transaction transaction = session.beginTransaction();
            UserEntity user = new UserEntity();
            user.setUsername(username);
            user.setPassword(password);
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            errString = e.getMessage();
            return false;
        }
    }

}
