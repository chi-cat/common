package top.flyfire.common;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/6/3.
 */
public class CharUtilsTest {

    @Test
    public void testIsDigit() throws Exception {
        Assert.assertTrue(CharUtils.isDigit('0'));
        Assert.assertTrue(CharUtils.isDigit('3'));
        Assert.assertTrue(CharUtils.isDigit('9'));
        Assert.assertFalse(CharUtils.isDigit('z'));
    }

    @Test
    public void testIsUpper() throws Exception {
        Assert.assertTrue(CharUtils.isUpper('A'));
        Assert.assertTrue(CharUtils.isUpper('U'));
        Assert.assertTrue(CharUtils.isUpper('Z'));
        Assert.assertFalse(CharUtils.isUpper('z'));
    }

    @Test
    public void testIsLower() throws Exception {
        Assert.assertTrue(CharUtils.isLower('a'));
        Assert.assertTrue(CharUtils.isLower('d'));
        Assert.assertTrue(CharUtils.isLower('z'));
        Assert.assertFalse(CharUtils.isLower('U'));
    }

    @Test
    public void testIsLetter() throws Exception {
        Assert.assertTrue(CharUtils.isLetter('a'));
        Assert.assertTrue(CharUtils.isLetter('d'));
        Assert.assertTrue(CharUtils.isLetter('z'));
        Assert.assertTrue(CharUtils.isLetter('U'));
    }

    @Test
    public void testToLower() throws Exception {
        Assert.assertTrue(CharUtils.toLower('U')==CharPool.U_);
        Assert.assertTrue(CharUtils.toLower('u')==CharPool.U_);
    }

    @Test
    public void testToUpper() throws Exception {
        Assert.assertTrue(CharUtils.toUpper('U')==CharPool.U);
        Assert.assertTrue(CharUtils.toUpper('u')==CharPool.U);
    }
}