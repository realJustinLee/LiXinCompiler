package javaee.test;

import javaee.method.*;

public class Test {
    public static void main(String[] args) {
        Code code = new Code();
        System.out.println(code.GetByUsername("testaccount1")[0].getContent());
        System.out.println(code.GetById(2).getTitle());

        Bulletin bulletin = new Bulletin();
        System.out.println(bulletin.GetById(4).getContent());
        System.out.println(bulletin.GetByCodeId(1)[0].getContent());
        System.out.println(bulletin.GetByUsername("testaccount1")[0].getContent());
    }
}
