## 一、锁的分类

### 1、乐观锁和悲观锁

乐观锁就是乐观的认为不会发生冲突，用cas和版本号实现 悲观锁就是认为一定会发生冲突，对操作上锁

#### 1.悲观锁

悲观锁，总是假设最坏的情况，每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时候都会上锁，这样别人想拿这个数据就会阻塞直到它拿到锁。

```
传统的关系型数据库里边就用到了很多这种锁机制，比如行锁，表锁等，读锁，写锁等，都是在做操作之前先上锁。
再比如 Java 里面的同步原语 synchronized 关键字的实现也是悲观锁。
复制代码
```

适用场景:

比较适合写入操作比较频繁的场景，如果出现大量的读取操作，每次读取的时候都会进行加锁，这样会增加大量的锁的开销，降低了系统的吞吐量。

实现方式: `synchronized` 和`Lock`

#### 2.乐观锁

每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数据，可以使用版本号等机制

```
ABA问题(JDK1.5之后已有解决方案)：CAS需要在操作值的时候检查内存值是否发生变化，没有发生变化才会更新内存值。但是如果内存值原来是A，后来变成了B，然后又变成了A，那么CAS进行检查时会发现值没有发生变化，但是实际上是有变化的。ABA问题的解决思路就是在变量前面添加版本号，每次变量更新的时候都把版本号加一，这样变化过程就从“A－B－A”变成了“1A－2B－3A”。

循环时间长开销大：CAS操作如果长时间不成功，会导致其一直自旋，给CPU带来非常大的开销。

只能保证一个共享变量的原子操作(JDK1.5之后已有解决方案)：对一个共享变量执行操作时，CAS能够保证原子操作，但是对多个共享变量操作时，CAS是无法保证操作的原子性的。
复制代码
```

适用场景:

比较适合读取操作比较频繁的场景，如果出现大量的写入操作，数据发生冲突的可能性就会增大，为了保证数据的一致性，应用层需要不断的重新获取数据，这样会增加大量的查询操作，降低了系统的吞吐量。

实现方式:

1、使用版本标识来确定读到的数据与提交时的数据是否一致。提交后修改版本标识，不一致时可以采取丢弃和再次尝试的策略。

2、Java 中的 Compare and Swap 即 CAS ，当多个线程尝试使用 CAS 同时更新同一个变量时，只有其中一个线程能更新变量的值，而其它线程都失败，失败的线程并不会被挂起，而是被告知这次竞争中失败，并可以再次尝试。

3、在 Java 中 `java.util.concurrent.atomic` 包下面的原子变量类就是使用了乐观锁的一种实现方式 CAS 实现的。

### 2、公平锁/非公平锁

公平锁:

```
指多个线程按照申请锁的顺序来获取锁。
复制代码
```

非公平锁:

```
指多个线程获取锁的顺序并不是按照申请锁的顺序，有可能后申请的线程比先申请的线程优先获取锁。
有可能，会造成优先级反转或者饥饿现象。

复制代码
```

拓展`线程饥饿`:

```
一个或者多个线程因为种种原因无法获得所需要的资源，导致一直无法执行的状态
导致无法获取的原因:
线程优先级较低,没办法获取cpu时间
其他线程总是能在它之前持续地对该同步块进行访问。
线程在等待一个本身也处于永久等待完成的对象(比如调用这个对象的 wait 方法)，因为其他线程总是被持续地获得唤醒。

复制代码
```

实现方式: `ReenTrantLock`(公平/非公平)

对于`Java ReentrantLock`而言，通过构造函数指定该锁是否是公平锁，默认是非公平锁。非公平锁的优点在于吞吐量比公平锁大。

对于`Synchronized`而言，也是一种非公平锁。由于其并不像`ReentrantLock`是通过AQS(`AbstractQueuedSynchronizer)`的来实现线程调度，所以并没有任何办法使其变成公平锁。

### 3、可重入锁

如果一个线程获得过该锁，可以再次获得，主要是用途就是在递归方面，还有就是防止死锁，比如在一个同步方法块中调用了另一个相同锁对象的同步方法块

实现方式: `synchronized`、`ReentrantLock`

### 4、独享锁/共享锁

```
独享锁是指该锁一次只能被一个线程所持有。
共享锁是指该锁可被多个线程所持有。
复制代码
```

实现方式: 独享锁: `ReentrantLock` 和 `synchronized` 贡献锁: `ReadWriteLock`

拓展:

互斥锁/读写锁 就是对上面的一种具体实现:

```
互斥锁:在Java中的具体实现就是ReentrantLock,synchronized
读写锁:在Java中的具体实现就是ReadWriteLock
复制代码
```

对于Java ReentrantLock而言，其是独享锁。但是对于Lock的另一个实现类ReadWriteLock，其读锁是共享锁，其写锁是独享锁。读锁的共享锁可保证并发读是非常高效的，读写，写读 ，写写的过程是互斥的。独享锁与共享锁也是通过AQS来实现的，通过实现不同的方法，来实现独享或者共享。对于Synchronized而言，当然是独享锁

### 5、偏向锁/轻量级锁/重量级锁

```
基于 jdk 1.6 以上
```

`偏向锁`指的是当前只有这个线程获得，没有发生争抢，此时将方法头的markword设置成0，然后每次过来都cas一下就好，不用重复的获取锁.指一段同步代码一直被一个线程所访问，那么该线程会自动获取锁。降低获取锁的代价

`轻量级锁`：在偏向锁的基础上，有线程来争抢，此时膨胀为轻量级锁，多个线程获取锁时用cas自旋获取，而不是阻塞状态

`重量级锁`：轻量级锁自旋一定次数后，膨胀为重量级锁，其他线程阻塞，当获取锁线程释放锁后唤醒其他线程。（线程阻塞和唤醒比上下文切换的时间影响大的多，涉及到用户态和内核态的切换）

实现方式: `synchronized`

### 6、分段锁

在1.7的concurrenthashmap中有分段锁的实现，具体为默认16个的segement数组，其中segement继承自reentranklock，每个线程过来获取一个锁，然后操作这个锁下连着的map。

实现方式:

```
我们以ConcurrentHashMap来说一下分段锁的含义以及设计思想，ConcurrentHashMap中的分段锁称为Segment，
它即类似于HashMap（JDK7与JDK8中HashMap的实现）的结构，即内部拥有一个Entry数组，数组中的每个元素又是一个链表；
同时又是一个ReentrantLock（Segment继承了ReentrantLock)。

当需要put元素的时候，并不是对整个hashmap进行加锁，而是先通过hashcode来知道他要放在那一个分段中，
然后对这个分段进行加锁，所以当多线程put的时候，只要不是放在一个分段中，就实现了真正的并行的插入。

但是，在统计size的时候，可就是获取hashmap全局信息的时候，就需要获取所有的分段锁才能统计。

分段锁的设计目的是细化锁的粒度，当操作不需要更新整个数组的时候，就仅仅针对数组中的一项进行加锁操作。
复制代码
```

## 二、锁的底层实现

### 1、Synchronized

synchronized 关键字通过一对字节码指令 monitorenter/monitorexit 实现

前置知识:

```
对象头:
Hotspot 虚拟机的对象头主要包括两部分数据：Mark Word（标记字段）、Klass Pointer（类型指针）。其中：

Klass Point 是是对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是哪个类的实例。

Mark Word 用于存储对象自身的运行时数据，它是实现轻量级锁和偏向锁的关键，所以下面将重点阐述 Mark Word 。

Monitor:
每一个 Java 对象都有成为Monitor 的潜质，因为在 Java 的设计中 ，每一个 Java 对象自打娘胎里出来就带了一把看不见的锁，它叫做内部锁或者 Monitor 锁

复制代码
```

对象头结构:



![img](java-锁.assets/170bcd199df90c76tplv-t2oaga2asx-watermark.awebp)



Monitor数据结构:

```
ObjectMonitor() {
    _header       = NULL;
    _count        = 0; //记录个数
    _waiters      = 0,
    _recursions   = 0;
    _object       = NULL;
    _owner        = NULL;
    _WaitSet      = NULL; //处于wait状态的线程，会被加入到_WaitSet
    _WaitSetLock  = 0 ;
    _Responsible  = NULL ;
    _succ         = NULL ;
    _cxq          = NULL ;
    FreeNext      = NULL ;
    _EntryList    = NULL ; //处于等待锁block状态的线程，会被加入到该列表
    _SpinFreq     = 0 ;
    _SpinClock    = 0 ;
    OwnerIsThread = 0 ;
  }
参考: https://blog.csdn.net/javazejian/article/details/72828483
复制代码
```

ObjectMonitor中有两个队列，`_WaitSet` 和 `_EntryList`，用来保存`ObjectWaiter`对象列表( 每个等待锁的线程都会被封装成ObjectWaiter对象)，`_owner`指向持有`ObjectMonitor`对象的线程，当多个线程同时访问一段同步代码时，首先会进入 `_EntryList` 集合，当线程获取到对象的`monitor` 后进入 `_Owner` 区域并把`monitor`中的`owner`变量设置为当前线程同时monitor中的`计数器count加1`.

若线程调用 wait() 方法，将释放当前持有的monitor，owner变量恢复为null，count自减1，同时该线程进入 WaitSe t集合中等待被唤醒。若当前线程执行完毕也将释放monitor(锁)并复位变量的值，以便其他线程进入获取monitor(锁)



#### 1.1、字节码实现

##### 同步代码块:

```
public class SynchronizedTest {

    public void test2() {
        synchronized(this) {
        }
    }
}
复制代码
```







`synchronized`关键字基于上述两个指令实现了锁的获取和释放过程:

`monitorenter`指令插入到同步代码块的开始位置，

`monitorexit` 指令插入到同步代码块的结束位置.

线程执行到 monitorenter 指令时，将会尝试获取对象所对应的 Monitor 所有权，即尝试获取对象的锁。

```
当执行monitorenter指令时，当前线程将试图获取 objectref(即对象锁) 所对应的 monitor 的持有权，当 objectref 的 monitor 的进入计数器为 0，那线程可以成功取得 monitor，并将计数器值设置为 1，取锁成功。如果当前线程已经拥有 objectref 的 monitor 的持有权，那它可以重入这个 monitor (关于重入性稍后会分析)，重入时计数器的值也会加 1。倘若其他线程已经拥有 objectref 的 monitor 的所有权，那当前线程将被阻塞，直到正在执行线程执行完毕，即monitorexit指令被执行，执行线程将释放 monitor(锁)并设置计数器值为0 ，其他线程将有机会持有 monitor 。

复制代码
```

##### 同步方法:

```
synchronized 方法则会被翻译成普通的方法调用和返回指令如：
invokevirtual、areturn 指令，在 JVM 字节码层面并没有任何特别的指令来实现被synchronized 修饰的方法，
而是在 Class 文件的方法表中将该方法的 access_flags 字段中的 synchronized 标志位置设置为 1，
表示该方法是同步方法，并使用调用该方法的对象或该方法所属的 Class 
在 JVM 的内部对象表示 Klass 作为锁对象
复制代码
 //省略没必要的字节码
  //==================syncTask方法======================
  public synchronized void syncTask();
    descriptor: ()V
    //方法标识ACC_PUBLIC代表public修饰，ACC_SYNCHRONIZED指明该方法为同步方法
    flags: ACC_PUBLIC, ACC_SYNCHRONIZED
    Code:
      stack=3, locals=1, args_size=1
         0: aload_0
         1: dup
         2: getfield      #2                  // Field i:I
         5: iconst_1
         6: iadd
         7: putfield      #2                  // Field i:I
        10: return
      LineNumberTable:
        line 12: 0
        line 13: 10
}
SourceFile: "SyncMethod.java"
复制代码
```

以下部分参考: [JVM源码分析之synchronized实现](https://link.juejin.cn?target=https%3A%2F%2Fwww.jianshu.com%2Fp%2Fc5058b6fe8e5)

#### 1.2、偏向锁获取

```
1、获取对象头的Mark Word；
2、判断mark是否为可偏向状态，即mark的偏向锁标志位为 1，锁标志位为 01；
3、判断mark中JavaThread的状态：如果为空，则进入步骤（4）；如果指向当前线程，
则执行同步代码块；如果指向其它线程，进入步骤（5）；
4、通过CAS原子指令设置mark中JavaThread为当前线程ID，
如果执行CAS成功，则执行同步代码块，否则进入步骤（5）；
5、如果执行CAS失败，表示当前存在多个线程竞争锁，当达到全局安全点（safepoint），
获得偏向锁的线程被挂起，撤销偏向锁，并升级为轻量级，升级完成后被阻塞在安全点的线程继续执行同步代码块；
复制代码
```

在大多数情况下，锁不仅不存在多线程竞争，而且总是由同一线程多次获得，因此为了减少同一线程获取锁(会涉及到一些CAS操作,耗时)的代价而引入偏向锁。偏向锁的核心思想是，如果一个线程获得了锁，那么锁就进入偏向模式，此时Mark Word 的结构也变为偏向锁结构，当这个线程再次请求锁时，无需再做任何同步操作，即获取锁的过程，这样就省去了大量有关锁申请的操作，从而也就提供程序的性能。所以，对于没有锁竞争的场合，偏向锁有很好的优化效果，毕竟极有可能连续多次是同一个线程申请相同的锁。

注意 JVM 提供了关闭偏向锁的机制， JVM 启动命令指定如下参数即可

```
-XX:-UseBiasedLocking
复制代码
```







偏向锁的撤销:

```
偏向锁的 撤销（revoke） 是一个很特殊的操作， 为了执行撤销操作， 需要等待全局安全点（Safe Point）， 
此时间点所有的工作线程都停止了字节码的执行。

偏向锁这个机制很特殊， 别的锁在执行完同步代码块后， 都会有释放锁的操作， 而偏向锁并没有直观意义上的“释放锁”操作。

引入一个概念 epoch, 其本质是一个时间戳 ， 代表了偏向锁的有效性
复制代码
```

#### 1.3、轻量级锁

在多线程交替执行同步块的情况下，尽量避免重量级锁引起的性能消耗，但是如果多个线程在同一时刻进入临界区，会导致轻量级锁膨胀升级重量级锁，所以轻量级锁的出现并非是要替代重量级锁。

```
1、获取对象的markOop数据mark；
2、判断mark是否为无锁状态：mark的偏向锁标志位为 0，锁标志位为 01；
3、如果mark处于无锁状态，则进入步骤（4），否则执行步骤（6）；
4、把mark保存到BasicLock对象的_displaced_header字段；
5、通过CAS尝试将Mark Word更新为指向BasicLock对象的指针，如果更新成功，表示竞争到锁，则执行同步代码，否则执行步骤（6）；
6、如果当前mark处于加锁状态，且mark中的ptr指针指向当前线程的栈帧，则执行同步代码，否则说明有多个线程竞争轻量级锁，轻量级锁需要膨胀升级为重量级锁；

复制代码
```

#### 1.4、重量级锁

重量级锁通过对象内部的监视器（monitor）实现，其中monitor的本质是依赖于底层操作系统的Mutex Lock实现，操作系统实现线程之间的切换需要从用户态到内核态的切换，切换成本非常高。

锁膨胀过程:

```
1、整个膨胀过程在自旋下完成；
2、mark->has_monitor()方法判断当前是否为重量级锁，即Mark Word的锁标识位为 10，如果当前状态为重量级锁，执行步骤（3），否则执行步骤（4）；
3、mark->monitor()方法获取指向ObjectMonitor的指针，并返回，说明膨胀过程已经完成；
4、如果当前锁处于膨胀中，说明该锁正在被其它线程执行膨胀操作，则当前线程就进行自旋等待锁膨胀完成，这里需要注意一点，
虽然是自旋操作，但不会一直占用cpu资源，每隔一段时间会通过os::NakedYield方法放弃cpu资源，或通过park方法挂起；
如果其他线程完成锁的膨胀操作，则退出自旋并返回；
5、如果当前是轻量级锁状态，即锁标识位为 00

复制代码
```

Monitor 竞争:

```
1、通过CAS尝试把monitor的_owner字段设置为当前线程；
2、如果设置之前的_owner指向当前线程，说明当前线程再次进入monitor，即重入锁，执行_recursions ++ ，记录重入的次数；
3、如果之前的_owner指向的地址在当前线程中，这种描述有点拗口，换一种说法：之前_owner指向的BasicLock在当前线程栈上，
说明当前线程是第一次进入该monitor，设置_recursions为1，_owner为当前线程，该线程成功获得锁并返回；
4、如果获取锁失败，则等待锁的释放；

复制代码
```

其本质就是通过CAS设置monitor的_owner字段为当前线程，如果CAS成功，则表示该线程获取了锁，跳出自旋操作，执行同步代码，否则继续被挂起；

Monitor 释放:

当某个持有锁的线程执行完同步代码块时，会进行锁的释放，给其它线程机会执行同步代码，在HotSpot中，通过退出monitor的方式实现锁的释放，并通知被阻塞的线程.

#### 1.5、锁优化内容

锁消除:

```
消除锁是虚拟机另外一种锁的优化，这种优化更彻底，
Java虚拟机在JIT编译时(可以简单理解为当某段代码即将第一次被执行时进行编译，又称即时编译)，
通过对运行上下文的扫描，去除不可能存在共享资源竞争的锁，
通过这种方式消除没有必要的锁，可以节省毫无意义的请求锁时间
复制代码
```

锁粗化:

```
将多个连续的加锁、解锁操作连接在一起，扩展成一个范围更大的锁。
复制代码
```

自旋锁:

```
线程的阻塞和唤醒，需要 CPU 从用户态转为核心态。频繁的阻塞和唤醒对 CPU 来说是一件负担很重的工作，势必会给系统的并发性能带来很大的压力。
同时，我们发现在许多应用上面，对象锁的锁状态只会持续很短一段时间。为了这一段很短的时间，频繁地阻塞和唤醒线程是非常不值得的

适应性自旋锁:
自适应就意味着自旋的次数不再是固定的，它是由前一次在同一个锁上的自旋时间及锁的拥有者的状态来决定
复制代码
```



### 2、ReetrantLock

#### 2.1、Lock



```
   //加锁
    void lock();

    //解锁
    void unlock();

    //可中断获取锁，与lock()不同之处在于可响应中断操作，即在获
    //取锁的过程中可中断，注意synchronized在获取锁时是不可中断的
    void lockInterruptibly() throws InterruptedException;

    //尝试非阻塞获取锁，调用该方法后立即返回结果，如果能够获取则返回true，否则返回false
    boolean tryLock();

    //根据传入的时间段获取锁，在指定时间内没有获取锁则返回false，如果在指定时间内当前线程未被中并断获取到锁则返回true
    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;

    //获取等待通知组件，该组件与当前锁绑定，当前线程只有获得了锁
    //才能调用该组件的wait()方法，而调用后，当前线程将释放锁。
    Condition newCondition();

复制代码
```

在Java 1.5中，官方在concurrent并发包`(J.U.C)`中加入了Lock接口，该接口中提供了lock()方法和unLock()方法对显式加锁和显式释放锁操作进行支持.

Lock 锁提供的优势:

```
可以使锁更公平。
可以使线程在等待锁的时候响应中断。
可以让线程尝试获取锁，并在无法获取锁的时候立即返回或者等待一段时间。
可以在不同的范围，以不同的顺序获取和释放锁。
复制代码
```

#### 2.2、AQS (AbstractQueuedSynchronizer)

AQS 即队列同步器。它是构建锁或者其他同步组件的基础框架（如 ReentrantLock、ReentrantReadWriteLock、Semaphore 等），J.U.C 并发包的作者（Doug Lea）期望它能够成为实现大部分同步需求的基础。

数据结构:

```
    //同步队列头节点
    private transient volatile Node head;

    //同步队列尾节点
    private transient volatile Node tail;

    //同步状态
    private volatile int state;
复制代码
```

AQS 使用一个 int 类型的成员变量 state 来表示同步状态：

- 当 `state > 0` 时，表示已经获取了锁。
- 当 `state = 0` 时，表示释放了锁。

Node构成FIFO的同步队列来完成线程获取锁的排队工作

- 如果当前线程获取同步状态失败（锁）时，AQS 则会将当前线程以及等待状态等信息构造成一个节点（Node）并将其加入同步队列，同时会阻塞当前线程
- 当同步状态释放时，则会把节点中的线程唤醒，使其再次尝试获取同步状态

参考: [深入剖析基于并发AQS的(独占锁)重入锁(ReetrantLock)及其Condition实现原理](https://link.juejin.cn?target=https%3A%2F%2Fblog.csdn.net%2Fjavazejian%2Farticle%2Fdetails%2F75043422)



![img](java-锁.assets/170bcd4c8957604atplv-t2oaga2asx-watermark.awebp)



#### 2.3、Sync

`Sync`：抽象类，是ReentrantLock的内部类，继承自AbstractQueuedSynchronizer，实现了释放锁的操作(tryRelease()方法)，并提供了lock抽象方法，由其子类实现。

`NonfairSync`：是ReentrantLock的内部类，继承自Sync，非公平锁的实现类。

`FairSync`：是ReentrantLock的内部类，继承自Sync，公平锁的实现类。

AQS、Sync 和 ReentrantLock 的具体关系图:



#### 2.4、ReentrantLock 实现原理

构造函数:

```
public ReentrantLock() {
    sync = new NonfairSync();
}

public ReentrantLock(boolean fair) {
    sync = fair ? new FairSync() : new NonfairSync();
}
复制代码
```

ReentrantLock 提供两种实现方式,公平锁/非公平锁. 通过构造函数进行初始化 `sync` 进行判断当前锁得类型.

##### 2.4.1、非公平锁(NonfairSync)

```
    final void lock() {
    //cas 获取锁
        if (compareAndSetState(0, 1))
        //如果成功设置当前线程Id
            setExclusiveOwnerThread(Thread.currentThread());
        else
        //否则再次请求同步状态
            acquire(1);
    }
复制代码
```

先对同步状态执行CAS操作，尝试把state的状态从0设置为1， 如果返回true则代表获取同步状态成功，也就是当前线程获取锁成，可操作临界资源，如果返回false，则表示已有线程持有该同步状态(其值为1) 获取锁失败，注意这里存在并发的情景，也就是可能同时存在多个线程设置state变量，因此是CAS操作保证了state变量操作的原子性。返回false后，执行`acquire(1)`方法

`#acquire(int arg)`方法，为 AQS 提供的模板方法。该方法为独占式获取同步状态，但是该方法对中断不敏感。也就是说，由于线程获取同步状态失败而加入到 CLH 同步队列中，后续对该线程进行中断操作时，线程不会从 CLH 同步队列中移除。

`acquire` 代码:

```
   public final void acquire(int arg) {
   //尝试获取同步状态
       if (!tryAcquire(arg) &&
           //自旋直到获得同步状态成功,添加节点到队列    
           acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
           selfInterrupt();
   }
复制代码
```

1、`tryAcquire` 尝试获取同步状态

```
    final boolean nonfairTryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getState();
            //锁闲置
            if (c == 0) {
            //CAS占用
                if (compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            //如果锁state=1 && 线程为当前线程 重入锁的逻辑
            else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                if (nextc < 0) // overflow
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);
                return true;
            }
            return false;
        }
复制代码
```

2、`acquireQueued` 加入队列中,自旋获取锁

```
private Node addWaiter(Node mode) {
   //将请求同步状态失败的线程封装成结点
   Node node = new Node(Thread.currentThread(), mode);

   Node pred = tail;
   //如果是第一个结点加入肯定为空，跳过。
   //如果非第一个结点则直接执行CAS入队操作，尝试在尾部快速添加
   if (pred != null) {
       node.prev = pred;
       //使用CAS执行尾部结点替换，尝试在尾部快速添加
       if (compareAndSetTail(pred, node)) {
           pred.next = node;
           return node;
       }
   }
   //如果第一次加入或者CAS操作没有成功执行enq入队操作
   enq(node);
   return node;
}

   final boolean acquireQueued(final Node node, int arg) {
       boolean failed = true;
       try {
           boolean interrupted = false;
           for (;;) {
           //获取前驱节点
               final Node p = node.predecessor();
               //如果前驱节点试头节点, 尝试获取同步状态
               if (p == head && tryAcquire(arg)) {
                   setHead(node);
                   p.next = null; // help GC
                   failed = false;
                   return interrupted;
               }
               // 获取失败，线程等待
               if (shouldParkAfterFailedAcquire(p, node) &&
                   parkAndCheckInterrupt())
                   interrupted = true;
           }
       } finally {
           if (failed)
               cancelAcquire(node);
       }
   }

复制代码
```

流程图:



![img](java-锁.assets/170bcd577fb6094dtplv-t2oaga2asx-watermark.awebp)



##### 2.4.2、公平锁(FairSync)

与非公平锁不同的是，在获取锁的时，公平锁的获取顺序是完全遵循时间上的FIFO规则，也就是说先请求的线程一定会先获取锁，后来的线程肯定需要排队，这点与前面我们分析非公平锁的nonfairTryAcquire(int acquires)方法实现有锁不同，下面是公平锁中tryAcquire()方法的实现

```
    protected final boolean tryAcquire(int acquires) {
        final Thread current = Thread.currentThread();
        int c = getState();
        if (c == 0) {
        //判断队列中是否又线程在等待
            if (!hasQueuedPredecessors() &&
                compareAndSetState(0, acquires)) {
                setExclusiveOwnerThread(current);
                return true;
            }
        }
        //重入锁逻辑
        else if (current == getExclusiveOwnerThread()) {
            int nextc = c + acquires;
            if (nextc < 0)
                throw new Error("Maximum lock count exceeded");
            setState(nextc);
            return true;
        }
        return false;
    }
复制代码
```

#### 2.4.3、解锁

```
//ReentrantLock类的unlock
public void unlock() {
    sync.release(1);
}

//AQS类的release()方法
public final boolean release(int arg) {
    //尝试释放锁
    if (tryRelease(arg)) {

        Node h = head;
        if (h != null && h.waitStatus != 0)
            //唤醒后继结点的线程
            unparkSuccessor(h);
        return true;
    }
    return false;
}

//ReentrantLock类中的内部类Sync实现的tryRelease(int releases) 
protected final boolean tryRelease(int releases) {

      int c = getState() - releases;
      if (Thread.currentThread() != getExclusiveOwnerThread())
          throw new IllegalMonitorStateException();
      boolean free = false;
      //判断状态是否为0，如果是则说明已释放同步状态
      if (c == 0) {
          free = true;
          //设置Owner为null
          setExclusiveOwnerThread(null);
      }
      //设置更新同步状态
      setState(c);
      return free;
  }
复制代码
```

### 3、ReentrantReadWriteLock

构造函数:

```
Lock readLock();

Lock writeLock();

/** 使用默认（非公平）的排序属性创建一个新的 ReentrantReadWriteLock */
public ReentrantReadWriteLock() {
    this(false);
}

/** 使用给定的公平策略创建一个新的 ReentrantReadWriteLock */
public ReentrantReadWriteLock(boolean fair) {
    sync = fair ? new FairSync() : new NonfairSync();
    readerLock = new ReadLock(this);
    writerLock = new WriteLock(this);
}

复制代码
```

`java.util.concurrent.locks.ReentrantReadWriteLock`，实现 `ReadWriteLock` 接口，可重入的读写锁实现类。在它内部，维护了一对相关的锁，一个用于只读操作，另一个用于写入操作。只要没有 `Writer` 线程，读取锁可以由多个 `Reader` 线程同时保持。也就说说，写锁是独占的，读锁是共享的。

在 ReentrantLock 中，使用 Sync ( 实际是 AQS )的 int 类型的 state 来表示同步状态，表示锁被一个线程重复获取的次数。但是，读写锁 ReentrantReadWriteLock 内部维护着一对读写锁，如果要用一个变量维护多种状态，需要采用“按位切割使用”的方式来维护这个变量，将其切分为两部分：高16为表示读，低16为表示写。

分割之后，读写锁是如何迅速确定读锁和写锁的状态呢？通过位运算。假如当前同步状态为S，那么：

- 写状态，等于 S & 0x0000FFFF（将高 16 位全部抹去）
- 读状态，等于 S >>> 16 (无符号补 0 右移 16 位)。



![img](java-锁.assets/170bcd5cdd8ae54dtplv-t2oaga2asx-watermark.awebp)



1、readLock

```
    public final void acquireShared(int arg) {
        if (tryAcquireShared(arg) < 0)
            doAcquireShared(arg);
    }
    
    protected final int tryAcquireShared(int unused) {
    //当前线程
    Thread current = Thread.currentThread();
    int c = getState();
    //exclusiveCount(c)计算写锁
    //如果存在写锁，且锁的持有者不是当前线程，直接返回-1
    //存在锁降级问题，后续阐述
    if (exclusiveCount(c) != 0 &&
            getExclusiveOwnerThread() != current)
        return -1;
    //读锁
    int r = sharedCount(c);

    /*
     * readerShouldBlock():读锁是否需要等待（公平锁原则）
     * r < MAX_COUNT：持有线程小于最大数（65535）
     * compareAndSetState(c, c + SHARED_UNIT)：设置读取锁状态
     */
    if (!readerShouldBlock() &&
            r < MAX_COUNT &&
            compareAndSetState(c, c + SHARED_UNIT)) { //修改高16位的状态，所以要加上2^16
        /*
         * holdCount部分后面讲解
         */
        if (r == 0) {
            firstReader = current;
            firstReaderHoldCount = 1;
        } else if (firstReader == current) {
            firstReaderHoldCount++;
        } else {
            HoldCounter rh = cachedHoldCounter;
            if (rh == null || rh.tid != getThreadId(current))
                cachedHoldCounter = rh = readHolds.get();
            else if (rh.count == 0)
                readHolds.set(rh);
            rh.count++;
        }
        return 1;
    }
    return fullTryAcquireShared(current);
}
    
    
复制代码
```

### 4、synchronized 和 ReentrantLock 异同？

相同点

```
都实现了多线程同步和内存可见性语义。
都是可重入锁。
复制代码
```

不同点

```
同步实现机制不同
synchronized 通过 Java 对象头锁标记和 Monitor 对象实现同步。
ReentrantLock 通过CAS、AQS（AbstractQueuedSynchronizer）和 LockSupport（用于阻塞和解除阻塞）实现同步。


可见性实现机制不同
synchronized 依赖 JVM 内存模型保证包含共享变量的多线程内存可见性。
ReentrantLock 通过 ASQ 的 volatile state 保证包含共享变量的多线程内存可见性。

使用方式不同
synchronized 可以修饰实例方法（锁住实例对象）、静态方法（锁住类对象）、代码块（显示指定锁对象）。
ReentrantLock 显示调用 tryLock 和 lock 方法，需要在 finally 块中释放锁。

功能丰富程度不同
synchronized 不可设置等待时间、不可被中断（interrupted）。
ReentrantLock 提供有限时间等候锁（设置过期时间）、可中断锁（lockInterruptibly）、condition（提供 await、condition（提供 await、signal 等方法）等丰富功能

锁类型不同
synchronized 只支持非公平锁。
ReentrantLock 提供公平锁和非公平锁实现。当然，在大部分情况下，非公平锁是高效的选择。
```


作者：九灵
链接：https://juejin.cn/post/6844904084911161357
来源：掘金
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。