package top.flyfire.common.reflect;

import org.junit.Test;
import top.flyfire.common.Timed;
import top.flyfire.common.reflect.metainfo.ClassMetaInfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/6/21.
 */
public class ReflectUtilsTest {

    @Test
    public void testRawType(){
        Type type1 = new RawType<TestSameA<StringBuffer,Date>>(){}.getType();
        Type type2 = new RawType<TestSameA<StringBuffer,Date>>(){}.getType();
        System.out.println(type1==type2);
        System.out.println(type1.equals(type2));
    }

    @Test
    public void testVarType() throws Exception{
        Type type1 = TestSameName.class.getDeclaredField("age").getType();
        Type type2 = TestSameName.class.getDeclaredField("age").getType();
    }

    @Test
    public void testWildType() throws Exception{
        Type type1 = TestSameName.class.getDeclaredField("name").getGenericType();
        Type type2 = TestSameName.class.getDeclaredField("name").getGenericType();
        System.out.println(type1.equals(type2));
    }

    @Test
    public void testNew() throws Exception {
        int i = 1000000;
        Timed.test(i, new Timed.Case() {
            @Override
            public void exec() {
                try {
                    People.class.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        final Constructor constructor = People.class.getConstructor();
        Timed.test(i, new Timed.Case() {
            @Override
            public void exec() {
                try {
                    constructor.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Test
    public void testCast(){
        final double d = 10.0;
        Timed.test(1000000, new Timed.Case() {
            @Override
            public void exec() {
                double d1 = d;
            }
        });
        Timed.test(1000000, new Timed.Case() {
            @Override
            public void exec() {
                Integer integer = (int)d;
            }
        });

    }

    @Test
    public void testUnWrap() throws Exception {
//        MetaInfo metaInfo = ReflectUtils.unWrap(TestSameB.class);
//        Object object = ((ClassMetaInfo)metaInfo).newInstance();
//        ((ClassMetaInfo) metaInfo).getField("date").invokeSetter(object,new Date());
//        System.out.println(metaInfo);
        MetaInfo metaInfo = ReflectUtils.unWrap(new RawType<TestSameA<StringBuffer,Date>>(){}.getType());
//        Object object = ((ClassMetaInfo)metaInfo).newInstance();
//        ((ClassMetaInfo) metaInfo).getField("name").invokeSetter(object,new Date());
        System.out.println(metaInfo);
    }

    public static class TestSameName<A extends CharSequence>{
        private A name;

        public A getName() {
            return name;
        }

        public void setName(A name) {
            this.name = name;
        }

        private List<? extends Number>   age;

        public List<? extends Number> getAge() {
            return age;
        }

        public void setAge(List<? extends Number> age) {
            this.age = age;
        }

        private List<? extends A> names;

        public List<? extends A> getNames() {
            return names;
        }

        public void setNames(List<? extends A> names) {
            this.names = names;
        }
    }

    public static class TestSameA<A extends CharSequence,B> extends TestSameName<A>{
        private B date;

        public B getDate() {
            return date;
        }

        public void setDate(B date) {
            this.date = date;
        }
    }

    public static class TestSameB extends TestSameA<String,Date>{

        private TestSameA<StringBuffer,Integer> sb;

        public TestSameA<StringBuffer, Integer> getSb() {
            return sb;
        }

        public void setSb(TestSameA<StringBuffer, Integer> sb) {
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