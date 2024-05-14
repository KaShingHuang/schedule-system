package test;

import org.junit.Test;
import util.MD5Util;

public class TestMD5Util {
    @Test
    public void testencrypt(){
        String encrypt = MD5Util.encrypt("123456");
        System.out.println(encrypt);
    }
}
