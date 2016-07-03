package top.flyfire.common.multhread;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shyy_work on 2016/5/30.
 */
public abstract class ThreadProxy<K,V> {

    class ValueProxy {
        private V value;

        public ValueProxy(K key) {
            this.value = ThreadProxy.this.proxy(key);
        }

        ValueProxy(){}
    }

    transient final Object lock;

    transient final Map<K,ValueProxy> proxyC;

    public ThreadProxy() {
        this(new Object());
    }

    public ThreadProxy(Object lock) {
        this.lock = lock;
        this.proxyC = new HashMap<K,ValueProxy>();
        init();
    }

    public final V get(K key){
        ValueProxy valueProxy;
        if(null==(valueProxy = proxyC.get(key))){
            synchronized (lock){
                if(null==(valueProxy = proxyC.get(key))){
                    valueProxy = new ValueProxy(key);
                    proxyC.put(key,valueProxy);
                }
            }
        }
        return valueProxy.value;
    }

    protected abstract V proxy(K key);

    protected void init(){
        //// TODO: 2016/6/21 init
    }

}
