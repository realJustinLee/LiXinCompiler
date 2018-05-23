package javaee.method;

import javaee.entity.BulletinEntity;
import javaee.entity.CodeEntity;
import javaee.entity.UserEntity;
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.util.List;

public class Bulletin extends Init {
    public boolean Save(String username, int code_id, String content) {
        if (IsInvalidUsername(username)) return false;

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
            session.close();
            return false;
        }
    }

    public BulletinEntity GetById(int id) {
        try {
            return session.load(BulletinEntity.class, id);
        } catch (Exception e) {
            errString = e.getMessage();
            session.close();
            return null;
        }
    }

    public BulletinEntity[] GetByUsername(String username) {
        if (IsInvalidUsername(username)) return null;

        try {
            List list = session.createQuery("from BulletinEntity where username = '" + username + "'").list();
            if (list.size() == 0) return null;
            BulletinEntity[] bulletins = new BulletinEntity[list.size()];
            for (int i = 0; i < list.size(); i++)
                bulletins[i] = (BulletinEntity) list.get(i);
            return bulletins;
        } catch (Exception e) {
            errString = e.getMessage();
            session.close();
            return null;
        }
    }

    public BulletinEntity[] GetByCodeId(int code_id) {
        try {
            List list = session.createQuery("from BulletinEntity where code_id = '" + code_id + "'").list();
            if (list.size() == 0) return null;
            BulletinEntity[] bulletins = new BulletinEntity[list.size()];
            for (int i = 0; i < list.size(); i++)
                bulletins[i] = (BulletinEntity) list.get(i);
            return bulletins;
        } catch (Exception e) {
            errString = e.getMessage();
            session.close();
            return null;
        }
    }

    public BulletinEntity[] GetAll() {
        try {
            List list = session.createQuery("from BulletinEntity").list();
            if (list.size() == 0) return null;
            BulletinEntity[] bulletins = new BulletinEntity[list.size()];
            for (int i = 0; i < list.size(); i++)
                bulletins[i] = (BulletinEntity) list.get(i);
            return bulletins;
        } catch (Exception e) {
            errString = e.getMessage();
            session.close();
            return null;
        }
    }
}
