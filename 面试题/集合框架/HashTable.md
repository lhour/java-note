首先从最上方的注释中可以看到Hashtable自JDK1.0版本就有了，而HashMap是JDK1.2才加入的。观察一下类的声明，我们可以看到他们继承的类也是不同的，Hashtable继承的是Dictionary, **Dictionary这个类从注释上写着已经是obsolete被废弃了，所以连带Hashtable也基本不用了**。Hashtable 也有元素个数，数组大小，负载因子这些属性，不用元素个数用的是count不是 size。也是使用链表法来解决冲突。    

```
public class Hashtable<K,V>
    extends Dictionary<K,V>
    implements Map<K,V>, Cloneable, java.io.Serializable
public class HashMap<K,V> extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable复制代码
```

 构造函数可以看出**默认大小是****11，同时初始大小给定多少初始数组就多大，不会做扩展到2的指数次幂这样的操作。** threshold=initialCapacity*loadFactor这点和HashMap相同。

```
    public Hashtable(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal Load: "+loadFactor);

        if (initialCapacity==0)
            initialCapacity = 1;
        this.loadFactor = loadFactor;
        table = new Entry<?,?>[initialCapacity];
        threshold = (int)Math.min(initialCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
    }

    public Hashtable() {
        this(11, 0.75f);
    }复制代码
```

 contains这个方法是**从表尾开始向前搜索的，同时也没有使用** ==**来比较**

```
    public synchronized boolean contains(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        Entry<?,?> tab[] = table;
        for (int i = tab.length ; i-- > 0 ;) {
            for (Entry<?,?> e = tab[i] ; e != null ; e = e.next) {
                if (e.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }复制代码
```

 从containsKey 可以看出，**Hashtable的index计算逻辑是使用key.hashCode()的后31位然后除以tab.length****取余数**。HashMap的那种按位与的操作仅当操作数低位全是 1时才等价为取余操作，也就是2 的指数次幂-1才可成立，这样做计算速度比除法快很多，不过冲突数量会增加，所以加入了一些打散的设计比如hashCode高位与低位异或。

```
    public synchronized boolean containsKey(Object key) {
        Entry<?,?> tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (Entry<?,?> e = tab[index] ; e != null ; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                return true;
            }
        }
        return false;
    }复制代码
```

  扩展方法rehash的**扩大方式是旧数组大小\*2+1** ，而HashMap是*2，要重新计算每一个的index所以效率低，同时冲突时将**后面的元素插入到前面元素的前一位**，所以会改变顺序 

```
    protected void rehash() {
        int oldCapacity = table.length;
        Entry<?,?>[] oldMap = table;

        // overflow-conscious code
        int newCapacity = (oldCapacity << 1) + 1;//新大小=旧大小*2+1
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            if (oldCapacity == MAX_ARRAY_SIZE)
                // Keep running with MAX_ARRAY_SIZE buckets
                return;
            newCapacity = MAX_ARRAY_SIZE;
        }
        Entry<?,?>[] newMap = new Entry<?,?>[newCapacity];//创建一个新的数组

        modCount++;
        threshold = (int)Math.min(newCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
        table = newMap;

        for (int i = oldCapacity ; i-- > 0 ;) {
            for (Entry<K,V> old = (Entry<K,V>)oldMap[i] ; old != null ; ) {
                Entry<K,V> e = old;
                old = old.next;

                int index = (e.hash & 0x7FFFFFFF) % newCapacity;//重新计算每一个元素的index
                e.next = (Entry<K,V>)newMap[index];//前后元素有冲突时，后面的元素插入到前面元素的前面
                newMap[index] = e;
            }
        }
    }复制代码
```

 对于插入结点同样要先检查是否存在key值相同的点，存在则不插入，然后检查是否需要扩展数组，插入时如果发生冲突，也是将要**插入的元素放在链表的首位** ，而putVal方法是放入尾部的。同时，可以看到Hashtable是**不支持null作为key或value值的**

```
    public synchronized V put(K key, V value) {
        // Make sure the value is not null
        if (value == null) {//value为null直接报错
            throw new NullPointerException();
        }

        // Makes sure the key is not already in the hashtable.
        Entry<?,?> tab[] = table;
        int hash = key.hashCode();//若key为null这里会报错
        int index = (hash & 0x7FFFFFFF) % tab.length;
        @SuppressWarnings("unchecked")
        Entry<K,V> entry = (Entry<K,V>)tab[index];
        for(; entry != null ; entry = entry.next) {
            if ((entry.hash == hash) && entry.key.equals(key)) {
                V old = entry.value;
                entry.value = value;
                return old;
            }
        }

        addEntry(hash, key, value, index);
        return null;
    }
    private void addEntry(int hash, K key, V value, int index) {
        modCount++;

        Entry<?,?> tab[] = table;
        if (count >= threshold) {
            // Rehash the table if the threshold is exceeded
            rehash();

            tab = table;
            hash = key.hashCode();
            index = (hash & 0x7FFFFFFF) % tab.length;
        }

        // Creates the new entry.
        @SuppressWarnings("unchecked")
        Entry<K,V> e = (Entry<K,V>) tab[index];
        tab[index] = new Entry<>(hash, key, value, e);
        count++;
    }复制代码
```

Hashtable的**hashcode方法计算所有entry的hash值总和**

```
public synchronized int hashCode() {
        int h = 0;
        if (count == 0 || loadFactor < 0)
            return h;  // Returns zero

        loadFactor = -loadFactor;  // Mark hashCode computation in progress
        Entry<?,?>[] tab = table;
        for (Entry<?,?> entry : tab) {
            while (entry != null) {
                h += entry.hashCode();
                entry = entry.next;
            }
        }

        loadFactor = -loadFactor;  // Mark hashCode computation complete

        return h;
    }复制代码
```

 elements这个方法是Hashtable多出来的，**返回所有value值的枚举**

```
    public synchronized Enumeration<V> elements() {
        return this.<V>getEnumeration(VALUES);
    }复制代码
```

我们可以注意到，Hashtable的**方法都加上了synchronized**，他们是线程安全的，但是对于本身是线程安全的情况就会大幅度影响性能，JDK8开始引入modCount来作为fast-fail机制，防止其他线程的非synchronzied方法对Hashtable进行修改。


作者：Mr_zebra
链接：https://juejin.cn/post/6844903701660827661
来源：稀土掘金
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。