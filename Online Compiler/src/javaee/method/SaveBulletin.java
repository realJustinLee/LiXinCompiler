package javaee.method;

import javaee.entity.BulletinEntity;
import javaee.entity.CodeEntity;
import javaee.entity.UserEntity;
import org.hibernate.Transaction;

import java.sql.Timestamp;

public class SaveBulletin extends Init {
    public boolean StartSave(String username, int code_id, String content) {
        if (!CheckUsername(username)) return false;

        try {
            Transaction transaction = session.beginTransaction();
            BulletinEntity bulletin = new BulletinEntity();
            bulletin.setUsername(session.load(UserEntity.class, username));
            bulletin.setCode_id(session.load(CodeEntity.class, code_id));
            bulletin.setContent(content);
            bulletin.setUploadtime(new Timestamp(System.currentTimeMillis()));
            session.save(bulletin);
            transaction.commit();
            return true;
        } catch (Exception e) {
            errString = e.getMessage();
            return false;
        }
    }
}
