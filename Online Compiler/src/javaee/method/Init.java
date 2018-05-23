package javaee.method;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

abstract class Init {
    private static final SessionFactory ourSessionFactory;
    Session session = getSession();
    String errString = "";

    static {
        try {
            ourSessionFactory = new Configuration().
                    configure("hibernate.cfg.xml").
                    buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public String getErrString() {
        return errString;
    }

    boolean IsInvalidUsername(String username) {
        if (username.length() > 20) {
            errString = "用户名不可超过20个字符";
            return true;
        }
        if (!username.matches(".*[a-zA-Z0-9]+.*")) {
            errString = "用户名必须由字母和数字组成";
            return true;
        }
        return false;
    }

    boolean IsInvalidPassword(String password) {
        if (!password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$")) {
            errString = "密码必须由字母和数字组成，且长度必须在8-20之间";
            return true;
        }
        return false;
    }
}
