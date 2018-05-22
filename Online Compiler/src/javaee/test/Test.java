package javaee.test;

import javaee.method.Login;
import javaee.method.Register;
import javaee.method.SaveBulletin;
import javaee.method.SaveCode;

public class Test {
    public static void main(String[] args) {
        System.out.println("测试注册器类：");
        Register register = new Register();
        if (!register.StartRegister("testaccount1", "testaccount1")) {
            System.out.println("注册器类运行失败，原因：" + register.getErrString());
            return;
        }

        System.out.println("测试登入器类：");
        Login login = new Login();
        if (!login.StartLogin("testaccount1", "testaccount1")) {
            System.out.println("登入器类运行失败，原因：" + login.getErrString());
            return;
        }

        System.out.println("测试代码上传器类：");
        SaveCode saveCode = new SaveCode();
        if (!saveCode.StartSave("testaccount1", "This is my code.")) {
            System.out.println("代码上传器类运行失败，原因：" + saveCode.getErrString());
            return;
        }

        System.out.println("测试评论上传器类：");
        SaveBulletin saveBulletin = new SaveBulletin();
        if (!saveBulletin.StartSave("testaccount1", 1, "This is my comment.")) {
            System.out.println("评论上传器类运行失败，原因：" + saveBulletin.getErrString());
            return;
        }

        System.out.println("所有测试运行完成：");
    }
}
