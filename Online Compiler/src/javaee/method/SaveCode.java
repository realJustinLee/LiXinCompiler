package javaee.method;

import javaee.entity.CodeEntity;
import javaee.entity.UserEntity;
import org.hibernate.Transaction;

import java.sql.Timestamp;

public class SaveCode extends Init {
    public boolean StartSave(String username, String content) {
        if (!CheckUsername(username)) return false;

        try {
            Transaction transaction = session.beginTransaction();
            CodeEntity code = new CodeEntity();
            code.setUsername(session.load(UserEntity.class, username));
            code.setContent(content);
            code.setUploadtime(new Timestamp(System.currentTimeMillis()));
            session.save(code);
            transaction.commit();
            return true;
        } catch (Exception e) {
            errString = e.getMessage();
            return false;
        }
    }
}
