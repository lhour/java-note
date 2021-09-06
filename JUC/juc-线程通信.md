## 虚假唤醒问题

 Demo1 ：

> 资源类中一般不处理异常，不能耽误其他线程的访问

```java
package com.hour.notify;

class Share {
    
    private int number = 0;

    public synchronized void recr() throws InterruptedException {
        if(number != 0){
            this.wait();
        }
        number ++;
        System.out.println(Thread.currentThread().getName() + " : " + number);
        this.notifyAll();
    }

    public synchronized void decr() throws InterruptedException {
        if(number != 1){
            this.wait();
        }
        number --;
        System.out.println(Thread.currentThread().getName() + " : " + number);
        this.notifyAll();
    }
}

public class Thread01 {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(
            () -> {
                while(true){
                    try {
                        share.recr();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        ).start();;

        new Thread(
            () -> {
                while(true){
                    try {
                        share.decr();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        ).start();

    }
}

```

wait() 在哪里睡就在哪里醒，会发生错误唤醒问题。

```java
Share share = new Share();
        new Thread(
            () -> {
                for(int i = 0; i < 10; i ++){
                    try {
                        share.recr();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        , "A").start();

        new Thread(
            () -> {
                for(int i = 0; i < 10; i ++){
                    try {
                        share.decr();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        , "B").start();
        
        new Thread(
            () -> {
                for(int i = 0; i < 10; i ++){
                    try {
                        share.recr();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        , "C").start();

        new Thread(
            () -> {
                for(int i = 0; i < 10; i ++){
                    try {
                        share.decr();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        , "D").start();
    }
```

虚假唤醒

A : +1

B : -1

C : +1

D : -1

导致出现2和-1的情况

所以需将下面代码优化

```java
	if(number != 0){
            this.wait();
        }
```

```java
	while(number != 0){
            this.wait();
        }
```





## Lock实现

```java
package com.hour.notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Share2 {

    private int number = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void recr() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + " : " + number);
            this.notifyAll();
        } finally {
            lock.unlock();
        }

    }

    public void decr() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + " : " + number);
            this.notifyAll();
        } finally {
            lock.unlock();
        }
    }

}

public class Lock01 {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.recr();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "B").start();

    }
}

```



### 定制

有A,B,C三个线程，A唤醒B,B唤醒C,C唤醒A



使用 condition.singal()

可以指定唤醒

```JAVA
package com.hour.notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Share3 {
    private int flag = 1;

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void method1() throws InterruptedException {
        lock.lock();
        try {
            while (flag != 1) {
                condition1.await();
            }
            for(int i = 0; i < 5; i ++){
                System.out.println(Thread.currentThread().getName() + " : " + flag);
            }
            flag = 2;
            condition2.signal();
        } finally {
            lock.unlock();
        }

    }

    public void method2() throws InterruptedException {
        lock.lock();
        try {
            while (flag != 2) {
                condition2.await();
            }
            for(int i = 0; i < 5; i ++){
                System.out.println(Thread.currentThread().getName() + " : " + flag);
            }
            flag = 3;
            condition3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void method3() throws InterruptedException {
        lock.lock();
        try {
            while (flag != 3) {
                condition3.await();
            }
            for(int i = 0; i < 5; i ++){
                System.out.println(Thread.currentThread().getName() + " : " + flag);
            }
            flag = 1;
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }

}

public class Lock02 {
    public static void main(String[] args) {
        Share3 share = new Share3();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    share.method1();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    share.method2();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    share.method3();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "C").start();

    }
}

```

