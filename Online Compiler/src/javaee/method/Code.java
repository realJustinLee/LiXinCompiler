package javaee.method;

import javaee.entity.CodeEntity;
import javaee.entity.UserEntity;
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.util.List;

public class Code extends Init {
    public boolean Save(String username, String title, String content) {
        if (IsInvalidUsername(username)) return false;

        try {
            Transaction transaction = session.beginTransaction();
            CodeEntity code = new CodeEntity();
            code.setUsername(session.load(UserEntity.class, username));
            code.setContent(content);
            code.setTitle(title);
            code.setUploadtime(new Timestamp(System.currentTimeMillis()));
            session.save(code);
            transaction.commit();
            return true;
        } catch (Exception e) {
            errString = e.getMessage();
            session.close();
            return false;
        }
    }

    public CodeEntity GetById(int id) {
        try {
            return session.load(CodeEntity.class, id);
        } catch (Exception e) {
            errString = e.getMessage();
            session.close();
            return null;
        }
    }


    public CodeEntity[] GetByUsername(String username) {
        if (IsInvalidUsername(username)) return null;

        try {
            List list = session.createQuery("from CodeEntity where username = '" + username + "'").list();
            if (list.size() == 0) return null;
            CodeEntity[] codes = new CodeEntity[list.size()];
            for (int i = 0; i < list.size(); i++)
                codes[i] = (CodeEntity) list.get(i);
            return codes;
        } catch (Exception e) {
            errString = e.getMessage();
            session.close();
            return null;
        }
    }

    public CodeEntity[] GetAll() {
        try {
            List list = session.createQuery("from CodeEntity").list();
            if (list.size() == 0) return null;
            CodeEntity[] codes = new CodeEntity[list.size()];
            for (int i = 0; i < list.size(); i++)
                codes[i] = (CodeEntity) list.get(i);
            return codes;
        } catch (Exception e) {
            errString = e.getMessage();
            session.close();
            return null;
        }
    }
}
