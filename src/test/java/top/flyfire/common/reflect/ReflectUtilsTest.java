package top.flyfire.common.reflect;

import org.junit.Test;
import top.flyfire.common.reflect.metainfo.ClassMetaInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/6/21.
 */
public class ReflectUtilsTest {

    @Test
    public void testUnWrap() throws Exception {
//        MetaInfo metaInfo = ReflectUtils.unWrap(TestSameB.class);
//        Object object = ((ClassMetaInfo)metaInfo).newInstance();
//        ((ClassMetaInfo) metaInfo).getField("date").invokeSetter(object,new Date());
//        System.out.println(metaInfo);
        MetaInfo metaInfo = ReflectUtils.unWrap(TestSameB.class);
        Object object = ((ClassMetaInfo)metaInfo).newInstance();
        ((ClassMetaInfo) metaInfo).getField("name").invokeSetter(object,new Date());
        System.out.println(metaInfo);
    }

    public static class TestSameName<A>{
        private A name;

        public A getName() {
            return name;
        }

        public void setName(A name) {
            this.name = name;
        }
    }

    public static class TestSameA<A,B> extends TestSameName<A>{
        private B date;

        public B getDate() {
            return date;
        }

        public void setDate(B date) {
            this.date = date;
        }
    }

    public static class TestSameB extends TestSameA<String,Date>{

        private TestSameA<Map<String,String>,Integer> sb;

        public TestSameA<Map<String, String>, Integer> getSb() {
            return sb;
        }

        public void setSb(TestSameA<Map<String, String>, Integer> sb) {
            this.sb = sb;
        }

        private List<List<Integer>> list;

        public List<List<Integer>> getList() {
            return list;
        }

        public void setList(List<List<Integer>> list) {
            this.list = list;
        }
    }

    public static class TestGA<A>{
        public A[] getArr() {
            return arr;
        }

        public void setArr(A[] arr) {
            this.arr = arr;
        }

        private A[] arr;
    }

    public static class TestGAM<K,V> extends TestGA<Map<K,V>>{

    }

    public static class TestGAM1 extends TestGAM<String,Date>{

    }

    public static class TestGAS extends TestGA<String>{

    }

    public static class TestA<T> {

        private List<T> ttList;

        public List<T> getTtList() {
            return ttList;
        }

        public void setTtList(List<T> ttList) {
            this.ttList = ttList;
        }

        private String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        private T t;

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }
    }

    public static class TestB<K> extends TestA<Date>{
        private K k;

        public K getK() {
            return k;
        }

        public void setK(K k) {
            this.k = k;
        }

        private String[] names;

        private K[] kkArr;

        public K[] getKkArr() {
            return kkArr;
        }

        public void setKkArr(K[] kkArr) {
            this.kkArr = kkArr;
        }

        public String[] getNames() {
            return names;
        }

        public void setNames(String[] names) {
            this.names = names;
        }
    }

    public static class TestC extends TestB<Integer>{
        private Map<String,String> map;

        public Map<String, String> getMap() {
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }
    }

}