## java.util.concurrent.locks 接口 Lock

- **所有已知实现类：**

  [ReentrantLock](https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/locks/ReentrantLock.html), [ReentrantReadWriteLock.ReadLock](https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/locks/ReentrantReadWriteLock.ReadLock.html), [ReentrantReadWriteLock.WriteLock](https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/locks/ReentrantReadWriteLock.WriteLock.html)

------

- `public interface **Lock**`

`Lock` 实现提供了比使用 `synchronized` 方法和语句可获得的更广泛的锁定操作。此实现允许更灵活的结构，可以具有差别很大的属性，可以支持多个相关的 [`Condition`](https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/locks/Condition.html) 对象。

锁是控制多个线程对共享资源进行访问的工具。通常，锁提供了对共享资源的独占访问。一次只能有一个线程获得锁，对共享资源的所有访问都需要首先获得锁。不过，某些锁可能允许对共享资源并发访问，如 [`ReadWriteLock`](https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/locks/ReadWriteLock.html) 的读取锁。

`synchronized` 方法或语句的使用提供了对与每个对象相关的隐式监视器锁的访问，但却强制所有锁获取和释放均要出现在一个块结构中：当获取了多个锁时，它们必须以相反的顺序释放，且必须在与所有锁被获取时相同的词法范围内释放所有锁。

虽然 `synchronized` 方法和语句的范围机制使得使用监视器锁编程方便了很多，而且还帮助避免了很多涉及到锁的常见编程错误，但有时也需要以更为灵活的方式使用锁。例如，某些遍历并发访问的数据结果的算法要求使用 "hand-over-hand" 或 "chain locking"：获取节点 A 的锁，然后再获取节点 B 的锁，然后释放 A 并获取 C，然后释放 B 并获取 D，依此类推。`Lock` 接口的实现允许锁在不同的作用范围内获取和释放，并允许以任何顺序获取和释放多个锁，从而支持使用这种技术。

随着灵活性的增加，也带来了更多的责任。不使用块结构锁就失去了使用 `synchronized` 方法和语句时会出现的锁自动释放功能。在大多数情况下，应该使用以下语句：

```java
     Lock l = ...; 
     l.lock();
     try {
         // access the resource protected by this lock
     } finally {
         l.unlock();
     }
 
```

**锁定和取消锁定出现在不同作用范围中时，必须谨慎地确保保持锁定时所执行的所有代码用 try-finally 或 try-catch 加以保护，以确保在必要时释放锁。**

`Lock` 实现提供了使用 `synchronized` 方法和语句所没有的其他功能，包括提供了一个非块结构的获取锁尝试 ([`tryLock()`](https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/locks/Lock.html#tryLock()))、一个获取可中断锁的尝试 ([`lockInterruptibly()`](https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/locks/Lock.html#lockInterruptibly())) 和一个获取超时失效锁的尝试 ([`tryLock(long, TimeUnit)`](https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/locks/Lock.html#tryLock(long, java.util.concurrent.TimeUnit)))。

`Lock` 类还可以提供与隐式监视器锁完全不同的行为和语义，如**保证排序、非重入用法或死锁检测**。如果某个实现提供了这样特殊的语义，则该实现必须对这些语义加以记录。

注意，**`Lock` 实例只是普通的对象，其本身可以在 `synchronized` 语句中作为目标使用**。获取 `Lock` 实例的监视器锁与调用该实例的任何 [`lock()`](https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/concurrent/locks/Lock.html#lock()) 方法没有特别的关系。为了避免混淆，建议除了在其自身的实现中之外，决不要以这种方式使用 `Lock` 实例。

除非另有说明，否则为任何参数传递 `null` 值都将导致抛出 [`NullPointerException`](https://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/NullPointerException.html)。

### 内存同步

所有 `Lock` 实现都*必须* 实施与内置监视器锁提供的相同内存同步语义，如 [The Java Language Specification, Third Edition (17.4 Memory Model)](http://java.sun.com/docs/books/jls/) 中所描述的:

- 成功的 `lock` 操作与成功的 *Lock* 操作具有同样的内存同步效应。
- 成功的 `unlock` 操作与成功的 *Unlock* 操作具有同样的内存同步效应。

不成功的锁定与取消锁定操作以及重入锁定/取消锁定操作都不需要任何内存同步效果。

### 实现注意事项

三种形式的锁获取（可中断、不可中断和定时）在其性能特征、排序保证或其他实现质量上可能会有所不同。而且，对于给定的 `Lock` 类，可能没有中断*正在进行的* 锁获取的能力。因此，并不要求实现为所有三种形式的锁获取定义相同的保证或语义，也不要求其支持中断正在进行的锁获取。实现必需清楚地对每个锁定方法所提供的语义和保证进行记录。还必须遵守此接口中定义的中断语义，以便为锁获取中断提供支持：完全支持中断，或仅在进入方法时支持中断。

由于中断通常意味着取消，而通常又很少进行中断检查，因此，相对于普通方法返回而言，实现可能更喜欢响应某个中断。即使出现在另一个操作后的中断可能会释放线程锁时也是如此。实现应记录此行为。



