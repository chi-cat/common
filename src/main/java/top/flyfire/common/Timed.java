package top.flyfire.common;

/**
 * Created by shyy_work on 2016/6/20.
 */
public class Timed {

    public final static int SS = 1;

    public final static int ss = SS*1000;

    public final static int mm = ss*60;

    public final static int HH = mm*60;


    public interface Case {
        void exec();
    }

    public final static void test(int count, Case cs){
        long time,start = time = 0;
        for(int i = 0;i<count;i++){
            start = System.currentTimeMillis();
            cs.exec();
            time += (System.currentTimeMillis() - start);
        }
        System.out.printf("%d小时%d分钟%d秒%d毫秒%n",time/HH,(time%=HH)/mm,(time%=mm)/ss,(time%=ss)/SS);
    }

    public final static void test(final int count, int threadCount, final Case cs){
        Thread[] thread = new Thread[threadCount];
        for(int i = 0;i<threadCount;i++){
            thread[i] = new Thread(new Runnable() {
                public void run() {
                    Timed.test(count,cs);
                }
            });
        }
        for(int i =0;i<threadCount;i++){
            thread[i].start();
        }
    }

}
