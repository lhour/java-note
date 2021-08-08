## ArrayList 和 LinkedList

[toc]
### 区别

1. arraylist底层是数组，尾部插入和查询都非常快。linked是链表，头插非常快
2. array是定长，达到一定数据量后需要扩容，就会导致插入变慢，猜想可能数据量大到一定程度后array的尾插会比linked慢。
3. 百万数量级的array头部插入时结果不能秒出，感觉大于了一分钟

```java
    
    //头部插入
    List<Integer> array = new ArrayList<>();
    long Start =System.currentTimeMillis();
    for(int i = 0; i < 100000; i ++){
        array.add(0,(int)Math.random()*10000);
    }
    long End = System.currentTimeMillis();
    System.out.println(End-Start);      //498


    List<Integer> linked = new LinkedList<>();
    Start =System.currentTimeMillis();
    for(int i = 0; i < 100000; i ++){
        linked.add(0,(int)Math.random()*10000);
    }
    End = System.currentTimeMillis();
    System.out.println(End-Start);      //136
```

### ArrayList源码学习

#### arraylist有三种构造方法


   默认为空的两个变量
```java 
    /**
     * Shared empty array instance used for empty instances.
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};
    /**
     * Shared empty array instance used for default sized empty instances. We
     * distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
     * first element is added.
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
```
   
三种方法
```java
    //初始化容量
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
    }

//.......................................................................
    //无参
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

//.......................................................................
    //将一个Collection，转化为arraylist
    public ArrayList(Collection<? extends E> c) {
        Object[] a = c.toArray();
        if ((size = a.length) != 0) {
            if (c.getClass() == ArrayList.class) {
                elementData = a;
            } else {
                elementData = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            // replace with empty array.
            elementData = EMPTY_ELEMENTDATA;
        }
    }

```
1. 无参构造很简单
2. 初始化容量initialCapacity
   1. 首先如果大于0就返回一个数组
   2. 等于0则返回已经设好的 EMPTY_ELEMENTDATA
   3. 小于0会返回异常
3. 第三种是将一个实现collection类的对象转变为数组形式
   1. 同时做了一个判断，如果传进来的类型就是ArrayList，那就直接画等号
   2. 否则还要进行一次浅拷贝
   
##### toArray
arraylist中的toArray方法
```java
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }
```
##### 继续深入
toArray又抛给了copyof

```java
    //fileName:Array.class
    @IntrinsicCandidate
    public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
        @SuppressWarnings("unchecked")
        T[] copy = ((Object)newType == (Object)Object[].class)
            ? (T[]) new Object[newLength]
            : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, 0, copy, 0,
                         Math.min(original.length, newLength));
        return copy;
    }
```
这依然不是最底层

arraycopy是一个System类的一个方法
```java
    //fileName:System.class
    /*
     * @param      src      the source array.
     * @param      srcPos   starting position in the source array.
     * @param      dest     the destination array.
     * @param      destPos  starting position in the destination data.
     * @param      length   the number of array elements to be copied.
     * @throws     IndexOutOfBoundsException  if copying would cause
     *             access of data outside array bounds.
     * @throws     ArrayStoreException  if an element in the {@code src}
     *             array could not be stored into the {@code dest} array
     *             because of a type mismatch.
     * @throws     NullPointerException if either {@code src} or
     *             {@code dest} is {@code null}.
     */
    @IntrinsicCandidate
    public static native void arraycopy(Object src,  int  srcPos,
                                        Object dest, int destPos,
                                        int length);
```
未给出详细源代码。但是注意这种方式是浅拷贝。

#### get方法

```java
    public E get(int index) {
        Objects.checkIndex(index, size);
        return elementData(index);
    }
//--------------------------------------------------------

    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }
```

可以发现实际上就是数组，只不过先进行了一下check，就是越界检查。
最终会到达这行代码

```java
throw outOfBoundsCheckIndex(oobef, index, length);
```

#### set方法

```java
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }
```
发现替换时，会返回原来的值

#### add方法

```java
    private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }
//-----------------------------------------------------------------
    public boolean add(E e) {
        modCount++;
        add(e, elementData, size);
        return true;
    }
//-----------------------------------------------------------------
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        modCount++;
        final int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elementData).length)
            elementData = grow();
        System.arraycopy(elementData, index,
                         elementData, index + 1,
                         s - index);
        elementData[index] = element;
        size = s + 1;
    }
```
1. 如果直接add，会调用第一个，默认放在最尾部
2. 如果插入到中间，要进行一次浅拷贝，速度会慢很多
3. 同时要考虑是否扩容
   
#### 扩容

```java
    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = ArraysSupport.newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private Object[] grow() {
        return grow(size + 1);
    }
//------------------------------------------------------------------------------------------
    public static int newLength(int oldLength, int minGrowth, int prefGrowth) {
        // assert oldLength >= 0
        // assert minGrowth > 0

        int newLength = Math.max(minGrowth, prefGrowth) + oldLength;
        if (newLength - MAX_ARRAY_LENGTH <= 0) {
            return newLength;
        }
        return hugeLength(oldLength, minGrowth);
    }
```
这个地方会有两种方式，一种是增加到最小，一种是进行一次位运算。一半，向下取整。
