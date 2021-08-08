### 字符串常量池（重要）
我们知道字符串的分配和其他对象分配一样，是需要消耗高昂的时间和空间的，而且字符串我们使用的非常多。JVM为了提高性能和减少内存的开销，在实例化字符串的时候进行了一些优化：使用字符串常量池。每当我们创建字符串常量时，JVM会首先检查字符串常量池，如果该字符串已经存在常量池中，那么就直接返回常量池中的实例引用。如果字符串不存在常量池中，就会实例化该字符串并且将其放到常量池中。由于String字符串的不可变性我们可以十分肯定常量池中一定不存在两个相同的字符串（这点对理解上面至关重要）。

Java中的常量池，实际上分为两种形态：静态常量池和运行时常量池。
所谓静态常量池，即*.class文件中的常量池，class文件中的常量池不仅仅包含字符串(数字)字面量，还包含类、方法的信息，占用class文件绝大部分空间。
而运行时常量池，则是jvm虚拟机在完成类装载操作后，将class文件中的常量池载入到内存中，并保存在方法区中，我们常说的常量池，就是指方法区中的运行时常量池。


```java
String a="hello";
String b="hello";
```
很明显，a、b和字面上的chenssy都是指向JVM字符串常量池中的"chenssy"对象，他们指向同一个对象。而且是在编译器就确定了。

所以a==b 为true

```java
String c = new String("hello");
```
new关键字一定会产生一个对象chenssy（注意这个chenssy和上面的chenssy不同），同时这个对象是存储在堆中。所以上面应该产生了两个对象：保存在栈中的c和保存堆中chenssy。但是在Java中根本就不存在两个完全一模一样的字符串对象。故堆中的chenssy应该是引用字符串常量池中chenssy。所以c、chenssy、池chenssy的关系应该是：c--->chenssy--->池chenssy。整个关系如下：


总结：虽然a、b、c、chenssy是不同的对象，但是从String的内部结构我们是可以理解上面的。String c = new String("chenssy");虽然c的内容是创建在堆中，但是他的内部value还是指向JVM常量池的chenssy的value，它构造chenssy时所用的参数依然是chenssy字符串常量。



基本道理是上面所说的。

我们来从实际的例子中看一看String 的各种情况。

```java
//1.编译器确定
public void test1(){
    String str1="aaa";
    String str2="aaa";
    System.out.println("===========test1============");
    System.out.println(str1==str2);//true 可以看出str1跟str2是指向同一个对象 
}
```
我们来看一看内部操作的过程：

当执行String str1="aaa"时，JVM首先会去字符串池中查找是否存在"aaa"这个对象，不存在，则在字符串池中创建"aaa"这个对象，然后将池中"aaa"这个对象的引用地址返回给字符串常量str1，这样str1会指向池中"aaa"这个字符串对象；

当创建字符串对象str2时，字符串池中已经存在"aaa"这个对象，直接把对象"aaa"的引用地址返回给str2，这样str2指向了池中"aaa"这个对象，也就是说str1和str2指向了同一个对象，因此语句System.out.println(str1 == str2)输出：true。


```java
//编译器不确定
public void test2(){
    String str3=new String("aaa");
    String str4=new String("aaa");
    System.out.println("===========test2============");
    System.out.println(str3==str4);//false 可以看出用new的方式是生成不同的对象 
}
```
采用new关键字新建一个字符串对象时，JVM首先在字符串池中查找有没有"aaa"这个字符串对象，如果有，则不在池中再去创建"aaa"这个对象了，直接在堆中创建一个"aaa"字符串对象，然后将堆中的这个"aaa"对象的地址返回赋给引用str3，这样，str3就指向了堆中创建的这个"aaa"字符串对象；

如果没有，则首先在字符串池中创建一个"aaa"字符串对象，然后再在堆中创建一个"aaa"字符串对象，然后将堆中这个"aaa"字符串对象的地址返回赋给str3引用，这样，str3指向了堆中创建的这个"aaa"字符串对象。

当执行String str4=new String("aaa")时， 因为采用new关键字创建对象时，每次new出来的都是一个新的对象，也即是说引用str3和str4指向的是两个不同的对象，因此语句System.out.println(str3 == str4)输出：false。


```
/**
 * 编译期确定
 */
public void test3(){
    String s0="helloworld";
    String s1="helloworld";
    String s2="hello"+"world";
    System.out.println("===========test3============");
    System.out.println(s0==s1); //true 可以看出s0跟s1是指向同一个对象 
    System.out.println(s0==s2); //true 可以看出s0跟s2是指向同一个对象 
}
```
因为例子中的s0和s1中的"helloworld”都是字符串常量，它们在编译期就被确定了，所以s0 == s1为true；而"hello”和"world”也都是字符串常量，当一个字符串由多个字符串常量连接而成时，它自己肯定也是字符串常量，所以s2也同样在编译期就被解析为一个字符串常量，所以s2也是常量池中"helloworld”的一个引用。所以我们得出s0==s1==s2。


```java
/**
 * 编译期无法确定
 */
public void test4(){
    String s0="helloworld"; 
    String s1=new String("helloworld"); 
    String s2="hello" + new String("world"); 
    System.out.println("===========test4============");
    System.out.println( s0==s1 ); //false  
    System.out.println( s0==s2 ); //false 
    System.out.println( s1==s2 ); //false
}
```
分析：用new String() 创建的字符串不是常量，不能在编译期就确定，所以new String() 创建的字符串不放入常量池中，它们有自己的地址空间。

s0还是常量池中"helloworld”的引用，s1因为无法在编译期确定，所以是运行时创建的新对象"helloworld”的引用，s2因为有后半部分new String(”world”)所以也无法在编译期确定，所以也是一个新创建对象"helloworld”的引用。


```java
/**
 * 继续-编译期无法确定
 */
public void test5(){
    String str1="abc";   
    String str2="def";   
    String str3=str1+str2;
    System.out.println("===========test5============");
    System.out.println(str3=="abcdef"); //false
}
```
分析：因为str3指向堆中的"abcdef"对象，而"abcdef"是字符串池中的对象，所以结果为false。JVM对String str="abc"对象放在常量池中是在编译时做的，而String str3=str1+str2是在运行时刻才能知道的。new对象也是在运行时才做的。而这段代码总共创建了5个对象，字符串池中两个、堆中三个。+运算符会在堆中建立来两个String对象，这两个对象的值分别是"abc"和"def"，也就是说从字符串池中复制这两个值，然后在堆中创建两个对象，然后再建立对象str3,然后将"abcdef"的堆地址赋给str3。

步骤：
1)栈中开辟一块中间存放引用str1，str1指向池中String常量"abc"。
2)栈中开辟一块中间存放引用str2，str2指向池中String常量"def"。
3)栈中开辟一块中间存放引用str3。
4)str1 + str2通过StringBuilder的最后一步toString()方法还原一个新的String对象"abcdef"，因此堆中开辟一块空间存放此对象。
5)引用str3指向堆中(str1 + str2)所还原的新String对象。
6)str3指向的对象在堆中，而常量"abcdef"在池中，输出为false。




```java
public void test6(){
    String s0 = "a1"; 
    String s1 = "a" + 1; 
    System.out.println("===========test6============");
    System.out.println((s0 == s1)); //result = true  
    String s2 = "atrue"; 
    String s3= "a" + "true"; 
    System.out.println((s2 == s3)); //result = true  
    String s4 = "a3.4"; 
    String s5 = "a" + 3.4; 
    System.out.println((s4 == s5)); //result = true
}
```
分析：在程序编译期，JVM就将常量字符串的"+"连接优化为连接后的值，拿"a" + 1来说，经编译器优化后在class中就已经是a1。在编译期其字符串常量的值就确定下来，故上面程序最终的结果都为true。


```java
/**
 * 编译期无法确定
 */
public void test7(){
    String s0 = "ab"; 
    String s1 = "b"; 
    String s2 = "a" + s1; 
    System.out.println("===========test7============");
    System.out.println((s0 == s2)); //result = false
}
```
分析：JVM对于字符串引用，由于在字符串的"+"连接中，有字符串引用存在，而引用的值在程序编译期是无法确定的，即"a" + s1无法被编译器优化，只有在程序运行期来动态分配并将连接后的新地址赋给s2。所以上面程序的结果也就为false。


```java
/**
 * 编译期确定
 */
public void test9(){
    String s0 = "ab"; 
    final String s1 = "b"; 
    String s2 = "a" + s1;  
    System.out.println("===========test9============");
    System.out.println((s0 == s2)); //result = true
}
```
执行上述代码，结果为：true。

分析：和例子7中唯一不同的是s1字符串加了final修饰，对于final修饰的变量，它在编译时被解析为常量值的一个本地拷贝存储到自己的常量池中或嵌入到它的字节码流中。所以此时的"a" + s1和"a" + "b"效果是一样的。故上面程序的结果为true。


```java
/**
 * 编译期无法确定
 */
public void test10(){
    String s0 = "ab"; 
    final String s1 = getS1(); 
    String s2 = "a" + s1; 
    System.out.println("===========test10============");
    System.out.println((s0 == s2)); //result = false 
    
}

private static String getS1() {  
    return "b";   
}
```
分析：这里面虽然将s1用final修饰了，但是由于其赋值是通过方法调用返回的，那么它的值只能在运行期间确定，因此s0和s2指向的不是同一个对象，故上面程序的结果为false。



intern方法：
```java
public static void main(String args[]){
		String s1=new String("hello1");
		System.out.println(s1.intern()==s1);//false
		System.out.println(s1.intern()=="hello1");//true
		
		String s2="hello2";
		System.out.println(s2.intern()==s2);//true
		
	}
```
1、执行intern方法时，如果常量池中存在和String对象相同的字符串，则返回常量池中对应字符串的引用；

2、如果常量池中不存在对应的字符串，则添加该字符串到常量中，并返回字符串引用；



字符串池的优缺点：

字符串池的优点就是避免了相同内容的字符串的创建，节省了内存，省去了创建相同字符串的时间，同时提升了性能；另一方面，字符串池的缺点就是牺牲了JVM在常量池中遍历对象所需要的时间，不过其时间成本相比而言比较低。