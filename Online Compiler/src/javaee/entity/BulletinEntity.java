package javaee.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bulletin", schema = "javaee")
public class BulletinEntity {
    private int id;
    private Timestamp uploadtime;
    private String content;
    private UserEntity username;
    private CodeEntity code_id;

    public CodeEntity getCode_id() {
        return code_id;
    }

    public void setCode_id(CodeEntity code_id) {
        this.code_id = code_id;
    }

    public UserEntity getUsername() {
        return username;
    }

    public void setUsername(UserEntity username) {
        this.username = username;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uploadtime", nullable = false)
    public Timestamp getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Timestamp uploadtime) {
        this.uploadtime = uploadtime;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 1000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BulletinEntity that = (BulletinEntity) o;
        return id == that.id &&
                Objects.equals(uploadtime, that.uploadtime) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, uploadtime, content);
    }
}
