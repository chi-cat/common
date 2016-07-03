package top.flyfire.common;

import org.junit.Assert;
import top.flyfire.common.annotation.Failure;

import static org.junit.Assert.*;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/20.
 */
public class StringUtilsTest {

    @org.junit.Test
    public void testIsNull() throws Exception {
        System.out.println((char)('b'-32));
       assertTrue(StringUtils.isNull(null));
    }

    @org.junit.Test
    public void testIsNotNull() throws Exception {
        assertTrue(StringUtils.isNotNull(""));
    }

    @org.junit.Test
    public void testIsEmpty() throws Exception {
        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isEmpty(""));
    }

    @org.junit.Test
    public void testIsNotEmpty() throws Exception {
        assertTrue(StringUtils.isNotEmpty("1"));
    }

    @org.junit.Test
    public void testIsDigit() throws Exception {
        assertTrue(StringUtils.isDigit("123123"));
    }

    @org.junit.Test
    public void testIsNumric() throws Exception {
        assertTrue(StringUtils.isNumric("123123"));
        assertTrue(StringUtils.isNumric("123123.3"));
        assertTrue(StringUtils.isNumric("+123123.3"));
        assertTrue(StringUtils.isNumric("-123123.3"));
        assertTrue(StringUtils.isNumric("-123123.3234"));
    }

    @org.junit.Test
    public void testIsUrl() throws Exception {
        assertTrue(StringUtils.isUrl("http://123123"));
        assertTrue(StringUtils.isUrl("ftp://123123/23234/"));
        assertTrue(StringUtils.isUrl("ftp://123123/23234"));
    }

    @org.junit.Test
    public void testIsEmail() throws Exception {
        assertTrue(StringUtils.isEmail("123123@qq.com"));
        assertTrue(StringUtils.isEmail("123123@qq.com.cn"));
    }

    @org.junit.Test
    public void testIsDate() throws Exception {
        assertTrue(StringUtils.isDate("2101-12-12 12:12:12"));
    }

    @org.junit.Test
    public void testIsCode() throws Exception {
        assertTrue(StringUtils.isCode("asdfaf_123"));
    }
}