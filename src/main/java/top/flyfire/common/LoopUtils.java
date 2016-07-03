package top.flyfire.common;

import top.flyfire.common.ExceptionMsgPool;
import top.flyfire.common.MapUtils;
import top.flyfire.common.ObjectUtils;

import java.util.*;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/6/6.
 */
public class LoopUtils {

    public static <K,V> void forEach(Map<K,V> map,EntryProxy<K,V> entryProxy){
        if(MapUtils.isNotEmpty(map)&& ObjectUtils.isNotNull(entryProxy)){
            Set<Map.Entry<K,V>> entrySet = map.entrySet();
            for(Map.Entry<K,V> entry : entrySet){
                entryProxy.proxy(entry.getKey(),entry.getValue(),entry);
            }
        }
    }

    public interface  EntryProxy<K,V> {
        void proxy(K key, V value, Map.Entry<K,V> entry);
    }

    public static <T> void forEach(Collection<T> collection,ItemProxy<T> itemProxy){
        if(CollectionUtils.isNotEmpty(collection)&&ObjectUtils.isNotNull(itemProxy)){
            Iterator<T> iterator = collection.iterator();
            int i = 0;
            while (iterator.hasNext()){
                itemProxy.proxy(i,iterator.next(),collection);
            }
        }
    }

    public static <T> void forEach(List<T> list, ItemProxy<T> itemProxy){
        if(CollectionUtils.isNotEmpty(list)&&ObjectUtils.isNotNull(itemProxy)){
            for(int i = 0,len = list.size();i<len;i++){
                itemProxy.proxy(i,list.get(i),list);
            }
        }
    }

    public interface ItemProxy<T> {
        void proxy(int index,T value,Collection<T> collection);
    }

    public static <T> void forEach(T[] arr, ArrItemProxy itemProxy){
        if(ArrayUtils.isNotEmpty(arr)&&ObjectUtils.isNotNull(itemProxy)){
            for(int i =0,len = arr.length;i<len;i++){
                itemProxy.proxy(i,arr[i],arr);
            }
        }
    }

    public interface ArrItemProxy<T> {
        void proxy(int index,T value,T[] arr);
    }

    public static <T> T find(T[] arr, ArrItemFinder<T> finder){
        if(ArrayUtils.isNotEmpty(arr)&&ObjectUtils.isNotNull(finder)){
            for(int i =0,len = arr.length;i<len;i++){
                if(finder.find(arr[i])){
                    return arr[i];
                }
            }
        }
        return null;
    }

    public interface ArrItemFinder<T> {
        boolean find(T value);
    }

}
