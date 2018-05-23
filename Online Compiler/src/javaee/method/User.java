package javaee.method;

import javaee.entity.UserEntity;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class User extends Init {
    public boolean Register(String username, String password) {
        if (IsInvalidUsername(username) || IsInvalidPassword(password)) return false;

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
            session.close();
            return false;
        }
    }

    public boolean Login(String username, String password) {
        if (IsInvalidUsername(username) || IsInvalidPassword(password)) return false;

        try {
            Query query = session.createQuery("select U.password from javaee.entity.UserEntity as U where U.username = '" + username + "'");
            return password.equals(query.list().get(0));
        } catch (Exception e) {
            errString = e.getMessage();
            session.close();
            return false;
        }
    }

    public boolean ChangePassword(String username, String password) {
        if (IsInvalidUsername(username) || IsInvalidPassword(password)) return false;

        try {
            Transaction transaction = session.beginTransaction();
            UserEntity user = session.load(UserEntity.class, username);
            user.setPassword(password);
            session.update(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            errString = e.getMessage();
            session.close();
            return false;
        }
    }
}
