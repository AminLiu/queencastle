package com.queencastle.service.test;

import org.junit.Test;

public class StringTester {

    @Test
    public void test() {
        String str = "qrscene_b8qp";
        String pid = str.substring("qrscene_".length());
        System.out.println(pid);
    }
}
