# Java SE11 Sliver 問題集相关

## 第1章 简单开始

T1

package主要作用

- 提供命名空间 （package.class名 就是完全的类名）
- 配合权限修饰符可以提供权限
- 给类进行分类
- 类必须都要包，如果没有，就是默认

```
package语句作为Java源文件的第一条语句，指明该文件中定义的类所在的包。(若缺省该语句，则指定为无名包)。它的格式为:package 顶层包名.子包名 ;
```

T2

包必须在文件最开始声明，否则就是错误的。因为默认在包之前写的都是注释掉的。

T3

编译器和JVM只能认准完全包名

使用完整的import 可以省略包名，比如本来是

java.lang.String str = "100";  String str = 100;

因为系统默认省略了 import.lang.String

T4

蛮有意思的，就是一个完全名字的包继承了无名包，那么也不能access，因为必须要么都是无名的，要么都是有名字的。

T5

main函数其实事实上是关键字。而且参数的话不仅仅是

```
public static void main(String[] args)
public static void main(String... args) # 都可以的
```

T6

T7 

这一题蛮难的。

首先要知道人家说的是执行.class 我第一上来想的是执行.java

java11之前如果需要命令行运行java程序

需要1.javac命令 2java命令

11之后的话，直接可以

```
java Sample.java a,b,c #就这样就可以了
```

整理一下的意思就是虽然说上面那种可以执行也并不意味着就是说执行之后就不用生成class文件了。其实可以使用 javac -d 内存 Sample.java 这样指定空间。

学个名字

- Java source file 源文件。你写的程序。
- Java bytecode file 二进制人间。编译过后的。

T8

也很难 ？

关于启动参数包含空格还有escape符号的问题。

space空格是当做字符串进行处理的。

## 第2章 数据结构和字符串操作

写这一章之前先确定一个Java的数据类型。

总共分为三种 基本数据类型 Primitivv data 包括 

byte short int long 前俩的范围最好记住 一个是2的8次方 127~ -128 还有一个是 65535 

float double

Boolean Char

关于引用数据类型 枚举 数组 对象（String） 这些都是引用数据类型

**T1**

考察的是Boolean 初始化的时候 不是 Boolean 而不是bool

## 第3章 运算符和判定

C。A。ABC。？。A。C。B。B。B。C。C。D。D。

T1

关于 += -= 这些。都是先计算右边。然后带入到左边。

```
int b = a += 5;
其实就是2部分。
① a = a + 5
② b = a
```

T2

减号 - 俩含义 ① 就是表示减法 ②表示翻转数值（负负得正这样）

```java
int num = 10;
num = (10 * -num);
num = (10 * - num); // 有空格也是可以的
```

T3

关于类型转换问题。

自动提升。强行转换。

小变成大的，就是自动提升。基本上不会出问题。根据右边看。

而大的变成小的。就属于强行转换，会发生一些无法预知的问题。

首先数值按照大小可以这样分 *byte short int long float double*

2<sup>8</sup>2<sup>16</sup>2<sup>32</sup>2<sup>64</sup>

float 是 2<sup>32</sup> 单精度浮动小数点
double 是 2<sup>64</sup> 双精度浮动小数点

整数默认是 int

浮动小数点默认是 double

初次之外的都需要明确的表现类型。

```
int a = 10;
short b = (short a)
```

下面比如说 short b = 10; 这就属于是一个int给了short

但是在int转换成 short和byte的时候不一定会编译出错。这是要看范围的，比如说byte超过了-128~127就会报错。

关于int 和 long之前的转换也是一样的。int c = 16L；这种明确说明了类型的，就是一定会报错的。因为这里右边明确说明了是long。

java的 boolean和其他语言不一样，没有跟int数值类型的互换性。

T4 

关于++a和a++

```java
int a = 10;
++a; // a = 10 + 1; 
a++; // a = 11 + 1; 

int a = 10;
int b = ++a; // int b = 1 + 10
int c = a++; // int c = 
```

这一题我其实搞懂了。

```java
int a = 10;
int b = a++ + a + a-- - a-- + ++a;
System.out.println(b)
// 第1个++是后加。所以说本身是先带入在自己加，加到第2个的时候已经是11的状态。这个时候又遇到了第2个，第三个是先计算在--。所以第三个本身还是11，但是第四个确是10了。第4份的时候要先-- 遇到第5个的时候a的状态是9 ++之后成了10
// 10 + 11 + 11 - 10 + 10 = 32
```

T5

<=,>=,>,< 这四个只能比较数值，不能比较真伪。

T6 略 T7 略

T8 ~ T13 都是一个类型的题目就是比较是否相等的问题。

https://github.com/chihokyo/javava/blob/main/basic/com/objtest/exer/EqualsTest.java

自己写过一篇文章

```
== 运算符
1 基本数据类型 引用数据类型都可以用
2 基本数据类型 比数值是否相等，不一定是同类型
3 引用数据类型比较的是地址是否相同。地址不同就滚。

equal()方法
既然都是方法了，那么肯定基本属性类型就全部歇菜了。
1 引用数据类型ok可以使用。
2 在Object中 equals（）作用和 == 是一样的 比较的是地址
3 但是在 String Date File 等都重写了。重写了之后比的就不是地址，而是实体对象是否相同。
```

T8 B

T9 A

T10 C 

错误原因。equals必须是重写。重载是不行的。

```java
// Object 自己定义的equals的方法是这样写的
public boolean equals(Object obj) {
        return (this == obj);
}
// 那就证明重写的时候也必须按照相同的方法进行覆盖，只是改变参数那样不是重写。而是重载。
public boolean equals(Sample obj){} // NG
```

T11 C

传入null 那么根据最原始的定义那就是

本身的地址和null地址比，那肯定是false

T12 A

这一题考察了常量池。郁闷了。

```java
// Constant pool
String a = "aa";
String b = "aa";
// 其实这俩用了同一个常量池 sysout(a == b); true 就是对的了。
```

T13 A

可以和12题进行对比。这一题就很巧妙是直接new的对象。

```java
String a = new String("aa");
String b = new String("aa");
// sysout(a == b); false 肯定是错误的，因为比较的是地址
```

T14

是一个坑。

常量池和new出来的对象使用的地址是不一样的。

但是如果使用了String.intern() 那么就是从常量池

https://www.cnblogs.com/Qian123/p/5707154.html

T15 ~ T18

if的大括号是可以被省略的

T16 是解释了为什么可以被省略。

T19 ~ T21 考察switch

写了

switch里面的值类型

int以下的整数和他们包装类，char和string，enum

其他都不行。

switch里面不能使用变量。但是可以使用final修饰的常量。

同时如果条件是num 是一个int 那么值的话必须是可以转换成int的类型。

如果放入一个string就是不行的，比如。

```java
int num = 0;
switch(num) {
  case "10": sysout("A");
} // NG!!
    
```

break 只要没有不管值是否相等，就一直执行。

## 第4章 判断句(if, while, do while)

T1 T2 略

关于do while while 这些语句都可以省略｛｝ 但是省略的前提就是语句只有一句，即使写在了一行也不行。

要求必须要一条语句。

```java
for(int i = 0; i < 10; i++){
    total += item[i].price;
}
// 顺序上 先执行int=0 初始化，然后判断是否小于，小于的话直接执行大括号的语句，然后在到第三个进行判断。
// 第三个可以是函数，for(int i = 0; i < 10; function()){
// 判断条件的中间的不可以是2个语句，如果非要使用2个，请使用逻辑符号 || && i < 10;
// 初始化的时候可以写俩
```

## 第5章 数组

T1

考察了直接打印sysout 数组 出来的不是空，而是地址。

因为数组继承了Object的toString方法，这个方法默认打印的就是一个地址。

T2

关于数组的声明

```java
int[][] a;
int a[][];
int[] a[]; // 2层
int[][] a[]; // 3层
// 这种不分前后都可以写，都是对的
```

T3

要记住几个概念，数组变量，数组实例，数组值。这三个不一样。

声明的数组变量是不可以加数组的 *int[2] a* 这样单独写一个数组并不可以声明。声明变量不能使用数字。

T4

这一题也是比较难的。也是声明变量和初始化的问题。

- 不能使用小数点

- 前后次元必须相同

- 数字不能只写后面

  ```java
  int a[] = new int[2][3]; // 错在了次元不一样，前1层。后面2层。错误。
  int a[] = new int[4.5]; // 小数，错了。
  int a[][] = new int[][3] // 不能只写后面 但可以只写前面 new int[2][]
  ```

  T5

  就是实例化数组的时候，其实并没有实例化对象。而只是初始化了数组。

  ```java
  public class item {
      String name;
      int price;
  }
  
  public class main {
     public static void main(String[] args) {
         Item[] item = new item[3];
         int total = 0;
         for(int i = 0; i < items.length; i++){
          total += item[i].price;
         }
         System.out.prinln(total)
     }
  }
  // 错就错在 这里 Item[] item = new item[3]; 其实并没有new item
  ```

  T6

  null打印出来就是字符串

  T7

  关于使用{}进行初始化的问题。初始化的时候可以使用{} 但是需要有几个要求就是。

  `int[] array = {2, 3}`

  `int[] array = new int[]{2, 3}`

  这俩本质是一样的，如果你要使用了new 后面就一定要加上相应的层次。但是不能写数字。

  `int[] array = new int[2]{2,3}; `这样就不行了 只能表层次。不能写数字。

  - 次元必须一样（层次）
  - 可以为空

  T8 ~ T10

  并没有什么难理解的，除非数组的要素里面有了null，这样就会有空指针的情况发生。这样不好。

## 第6章 实例对象和方法(instance and method)

**T1 ~ T2** 没啥好说的，都是基础。

**T3**

首先要看好题，需要输出的大写的`NULL` 空对象直接输出的是小写null 而不是大小， 空对象 false啥的也都不是。

**T4** 考察不用的引用就会被gc回收，回收是随机的不保证一定会被回收。

**T5**

静态属性的问题。如果new了多个对象，静态属性则是 【公用】的，那么设置了一个。就相当于都会被设置

**T6** 错了 蛮有难度的

结论就是 static 方法 不可以 叫非静态的方法。

静态的方法可以调用其他静态的field 。

非静态的方法可以调用静态的field

这个结论其实也蛮好理解的，只要记住静态的很特殊，静态里面不能叫其他非静态的，因为静态的都要先于其他的对象进行加载到内存里。

所以静态的不能调用非静态的，但是非静态都可以调用静态的，并且静态内部自己可以自由调用静态的。

**T7 T8 T9** 简单跳过

**T10**

一个方法一定要有 权限修饰符 + 方法名 + 参数列表{} 这样子。void不可以大写。有返回值的要看好返回值类型。

**T11 T12** 返回值错误 不难的题

关于返回值的问题。如果返回值是一个float 但是你写的是double 大于float 是可以的。自动提升了。或者是float的包装类 Float 也是可以的。

**T13** 简单

**T14** 关于可变列表问题。

`String... args`

`String[] args`

上面俩是一样的，注意类型不能混搭。必须写在参数列表的最后一个，且只有一个。

**T15**

return 后面不能写语句 会编译报错 

**T16 T17**

重载的条件 overload

要看函数名和参数列表。参数列表数量，类型，顺序不一样，都可以使重载。 

还有就是重载的时候虽然写了俩不一样的构成重载，但是调用的时候含糊不清的话就会出错。

```java
public void name() {
    public static void main(String[] args) {
        Main m = new Main();
      // 虽然构成了重载，但是数字类型初始化默认是int类型，这个时候 double比int大 所以又会自动提升
      // 编译器不知道选哪个，编译就会报错
        System.out.println(m.calc(2, 3))
    }

    public void calc(double a, int b) {
        return (a + b) / 2;
    }

    public void calc(int a, double b) {
        return (a + b) / 2;
    }
}
```

**T18** 简单

**T19**

构造器可以写任何权限。构造器权限是private的时候说明只有内部的类可以new 其他不可以new

主要就是三条就可以了。权限啥的都可以。static都可以。所以可以用作单例模式 

- 方法名必须和类名一样
- 不能写返回值
- 只能用new实例化

```java
public class Sample {
    // 私有的静态实例
    private static Sample instance = null;
    private Sample() {
        
    }
    // 只能被实例化一次，如果已经被实例化就返回已有的
    public static Sample getInstance() {
        if (instance == null) {
            instance = new Sample();
        }
        return instance;
    }
}
```

**T20** 太简单 跳过

**T21** 这一题需要重点看一下了。蛮有意思的。

```java
public class Sample {
    Sample () {
        System.out.println("A");
    }
    {
        System.out.println("B");
    }
}

public class Main {
    public static void main(String[] args) {
        Sample s = new Sample();
    }
}
```

这一题就是考察代码块 main函数 静态代码块 对象等等顺序问题了。

结论就是 执行顺序优先级：静态块,main(),构造（非静态）块,构造方法。

意思就是说 在new Sample的时候，新建了一个对象。(构造（非静态）块)

这个时候要输出B 已经输出B之后 默认调用了构造方法 那么就是输出A

**T22** 经典

```java
public class Sample {
    static int num;
    {
        num 10;
    }
    public Sample() {
        num = 100;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(Sample.num);
    }
}
// 这一题很简单的就是，直接调用了静态属性num 由于没有new 所以非静态代码块和构造方法都没有被执行。结果就是int的初始值 0
```

**T23** 经典

这一题主要是构造器初始化的问题。

首先由俩误区，第一个就是Sample 这个没有空参构造器。因为 void Sample() { 有返回值，所以这个本质上就是一个函数。

而第二个就是当类没有构造器的时候，注意是一个都没有的时候。就会给类一个默认空参构造器。

如果这个时候一旦你定义了构造器，系统就不在提供构造器。于是这一题的问题也就迎刃而解了。会编译出错。

```java
public class Sample {
    void Sample() {
        System.out.println("A");
    }
  // 没有空参构造器呢 这里出错
    Sample (String str) {
        System.out.println(str);
    }
}

public class Main {
    public static void main(String[] args) {
        Sample s = new Sample();
    }
}
```

**T24** 不难 偶尔可以看一下

**T25** 和24有异曲同工之妙

其实就是当一个构造器里调用其他构造器的时候this一定要最先写。不然就编译报错。

```java
public class Sample {
    public Sample() {
        System.out.println("A");
        this.("B") // NG!
    }
    public Sample(String str) {
        System.out.println(str);
    }
}

public class Main {
    public static void main(String[] args) {
        Sample s = new Sample();
    }
}
```

**T26** 有点难

就是权限修饰符的问题。

其实最不用担心的就是`public`和`private` 因为这俩一个啥都行，一个啥都不行。

`protected` 同一个包 or 不同包但是继承了

 `default` 啥都不写。只能是同一个包。继承都不行。所以上面多了一层继承性。

```java
package ex26;
public class Parent() {
    int num = 10; // 这里的权限是默认的，也就是说继承也没用。编译报错。
}

package other; // 可以看出来这俩不是一个包
import ex26.Parent; 

public class Child extends Parent { // 继承了
    public static void main(String[] args) {
        System.out.println(num); // 报错了
    }
}
```

**T27** 蛮难的。

| access modifiers | 图示符号 |
| ---------------- | -------- |
| public           | +        |
| protected        | #        |
| 没有(default)    | -        |
| private          | -        |

好了。貌似我懂了。

```java
package other;
public class Book {
    private String isbn;
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    protected void printInfo() {
        System.out.println(isbn);
    }
}

package ex27;
import other.Book;
public class StoryBook extends Book {} // 这里继承了 Book 类

package ex27;

public class Main {
    public static void main(String[] args) {
        StoryBook story = new StoryBook(); // new 了 一个 类
        story.setIsbn("xxxx"); // 设置是可以的 public 
        story.printInfo();// 但是这个不行 main 函数是没有资格调用 protected 
    }
}
```

**T28** 不是很懂

懂了，关于封装的话。为了内部封装。就要获取数值的都需要是`public`的，但是修改的那种就是`private`

```java
// 封装前
public class Sample {
    int num;
    int getNum () { return num; }
    void setNum (int num) { this.num = num; }
}

// 封装后
public class Sample {
    private int num;
    private void setNum(int num) {
        this.num = num;
    }
  	// 获取的是公开的
    public int getNum() {
        return num;
    }
}
```

**T29 ~ T30**

考察的值传递的问题。基本类型就是拷贝了一份数据进去。数据本身是不会变化的。

如果是引用类型，拷贝的是地址。那么修改地址内的数据，那么数据本身是会变化的。

```java
public class Sample {
    int num;
    public Sample(int num) {
        this.num = num;
    }
}

// 值传递 基本类型
public class Main {
    public static void main(String[] args) {
        Sample s = new Sample(10);
        modify(s.num);
        System.out.println(s.num); // 10
    }
    private static void modify(int num) {
        num *= 2; 
    }
}

// 值传递 引用类型
public class Main {
    public static void main(String[] args) {
        Sample s = new Sample(10);
        modify(s);
        System.out.println(s.num); // 20
    }
    private static void modify(Sample s) {
        s.num *= 2;
    }
}
```

## 第7章 继承抽象类

**T1**

B

**T2**

关于继承。继承default这个不能access，继承的话不能继承构造器的。

关于各种权限的东西要搞明白。

- 构造器无法继承

- private的属性和方法无法继承

  ```java
  class A {
      A(String val) {
  
      }
  }
  
  class B extends A {}
  
  public class Main {
      public static void main(String[] args) {
          B b = new B("hello"); // 编译报错，根本没这个构造器
      }
  }
  ```

  关于缺省的权限就是继承也没有，必须在一个包里的同一个class

**T3**

关于接口  （常量和抽象方法的集合）

方法 method 默认 接口是 *public abstract void name(){}* 可以简写成  *void name();*

属性 field 默认是 *public static final XXX*

接口中没有构造器。接口可以多继承（实现）

接口以前不可以初始化方法。自从java8之后也可以了。

实现类必须要实现父接口的所有方法，如果不实现，那么这个方法就要变成抽象方法。接口当然也可以继承。

AE

```java
public interface Sample {
	public void hello(){} // 错误，接口里的方法不能写内容
}
```

**T4** 有点难度。

函数式接口，质保函一个抽象方法的接口，成为函数是接口

关于接口的默认方法 interface default method的问题。如果说接口有俩方法，1个公用的，1个需要重写分开的。

那么每次实现这个接口都需要重复的写这个公用的，可是接口里的抽象方法无法写具体的逻辑代码。那么就会造成浪费。从java8开始，就多了这个接口的默认方法。可以灵活的实现。但是需要注意的是。

```java
default void name(){} // 默认的方法，这个方法默认权限修饰符是public
```

T5

接口的默认方法是其他实现类需要拿过来进行实现的，是为了方便实现类重复写而生的。如果需要其他实现类需要修改这个默认方法的话，必须要重写。

```java
public interface A {
    default void sample(){
        System.out.println("A");
    }
}

public interface B extends A {
    @Override
    default void sample(){
        System.out.println("B");
    }
}

// 但java.lang.object下面定义的方法不能被重写。toString就不行。
```

**T6**

感觉也蛮难的。

重写的方法里调用父类接口的默认方法的话，需要这些写。

*接口名字.super.方法名()*

这样。平常继承类想要调用父类继承类的方法的时候使用super，但是接口还需要加上接口名。

但是这个只限定直接继承接口 比如B接口继承A接口，C实现了B接口。那么B就无法调用A接口里的默认方法。

**T7**

关于多重实现接口的时候重名问题。

```java
public interface A {
    default void test(){
        System.out.println("A");
    }
}

public interface B {
    default void test(){
        System.out.println("B");
    }
}

public class Main implements A, B {
    public static void main(String[] args) {
        new Main().test() // 编译出错，因为重名找不到。
    }
}
// 必须要指名是调用了哪一个。
public class Main implements A, B {
    public static void main(String[] args) {
        new Main().test()
    }

    @Override
    public void test() {
        A.super.test();
    }
}

// 同理；接口继承也是一样的
public implements C extends A, B {
    @Override
    public void test() {
        A.super.test();
    }
}

```

**T8**

关于抽象类的说明

抽象类不能生成实例，抽象类可以继承。抽象方法只有声明，没有实现，分号结束。

抽象继承过来的类可以不必重写所有方法。不必全写的情况下子类必须是抽象类。全写了可以是普通类。就可以被实例化。没重写完是不能被实例化的。

抽象类可以拥有非抽象方法（普通的），也可以拥有抽象方法（只有个分号那种）

非抽象方法自动继承下来，抽象方法必须重写才能继承。否则没戏。

```java
public abstract class AbstractSample {
    public void methodA() {
        // 子类继承
    }
    public abstract void methodA() {
        // 子类必须要重写才能继承
    }
}
```

T9 涉及了多态了。很有意思的一道题。

```
 * 一。关于多态性 Polymorphism
 *      理解 1个事物的多种形态
 * 二。多态的使用
 *       当调用子父类同名同参数方法调用的时候，实际执行的是子类重写父类的方法 ---虚拟方法的调用
 *       此时父类的方法叫虚拟方法，注意，多态是一个动态绑定，是运行时行为。而不是编译时行为。
 * 三。多态的使用 虚拟方法的调用
 *      有了对象的多态性以后 
 *          编译 只能调用父类声明方法
 *          运行 实际执行子类重写父类的方法
 *      【编译看左，运行看右】
 * 四。使用前提
 *      1 有类的继承关系，有继承关系 才有多态
 *      2 要有子类重写父类（不然谁还new子类呢）
 * 五。为什么要使用多态呢
 *      AnimalTest 进行说明了
 * 六。属性的多态是没有的。
 *      【对象多态性只适用于方法，不适用于属性。不存在多态性。】
 *          heap里面虽然都有2个相同属性，但是都是要看左边的。
```

上面最重要的一条信息就是关于编译和多态的一些解释。就是编译的时候是按照左边的，执行方法的时候是看右边的。

```java
abstract class AbstractSample {
    public void sample() {
        System.out.println("A");
        test();
        System.out.println("C");
    }

    protected abstract void test());
}

class Con extends AbstractSample {
    protected void test() {
        System.out.println("B");
    }
}

public class Main {
    public static void main(String[] args) {
      // 编译的时候看做，也就是说写入内存的是 AbstractSample 但是执行的是子类重写方法
      // 抽象方法没有逻辑代码，这时候执行的是子类继承之后实现的逻辑代码。
        AbstractSample s = new Con();
        s.sample();
    }
}
```

**T10**

关于重写的定义。override

重写要求

- 子类权限 >= 父类权限 【父类如果方法已经是private 就不能进行重写了，因为都看不到了更别说重写】
- 重写 子类 返回值类型 不能大于 父类
- 重写 父类如果是void 子类也必须是 void【这里有一个争议，并非是一定要一样的，如果返回值是同一个类型的子类，或者包装类是可以的，下面代码演示】

```java
public Number method(){} // 
public Integer method(){}// Integer是Number的子类。所以可以是返回值。
```



- 子类 抛出的异常也不能大于父类
- 开发中其实真正的重写就是把父类的完全复制一份，除了方法体重写一下就好了
- 子类父类 同名同参数 要么static 要么都不是static【因为static不能被重写

**T11** 

关于继承之后子类重写方法的权限 

权限必须是要大于父类的，父类要是小的。比如父类是`protected`，那么子类就是`public`。

**T12**

这一题也很有意思。关于继承之后多态的属性问题。属性根据左边类型判断。方法根据类型 + 是否重写来判断。

为什么呢。因为属性在编译的时候就加载到了内存里了，什么类型的已经决定好。但是方法是在运行中决定，多态的时候就要看是哪个实例对象。哪个重写的方法。

```java
class A {
    String val = "A";
    void print(){
        System.out.println(val);
    }
}

class B extends A {
    String val = "B";
}

public class Main {
    public static void main(String[] args) {
        A a = new A();
        A b = new B();
        System.out.println(a.val); // 属性根据类型来的A
        System.out.println(b.val); // 属性根据类型来的A
        a.print(); // 都是A类型的，都是运行的A
        b.print(); // 都是A类型的，都是运行的A（虽然new的是B 但是根据类型来 继承之后也没重写）

    }
}
// 如果B重写了方法之后。那么答案就是AAAB
class B extends A {
    String val = "B";
  	void print(){
        System.out.println(val);
    } 
}
```

**T13**

这一题就是说多态不仅仅是适用于子父类继承，还适用于接口实现。

```java
interface Worker {
    void work();
}

class Employee {
    public void work() {
        System.out.println("work");
    }
}

class Engineer extends Employee implements Worker { }

public class Main {
    public static void main(String[] args) {
        // Engineer 继承了 Employee 实现了 Worker
      	// 继承了 Employee 证明可以继承了同类的方法。同样多态也适用于接口。所以没问题。
        Worker worker = new Engineer();
        worker.work();
    }
}
```

**T14**

这一题也很有趣。重点看一下吧。可以和上面一个一起看。

关于继承多态的时候

```java
public class A {
    public void hello() {
        
    }
}

public class B extends A {
    public void sample() {
        
    }
}

public class Main {
    public static void main(String[] args) {
        A a = new B(); // 这里B继承了A 所以不会报错
        a.sample(); //  这里会报错，因为类型是A A类里面并没有sample这个方法
    }
}
```

关于多态的条件。有继承关系么，有实现关系么。没有这些就无从谈起。

```java
public interface Worker {
    void work();
}
class Employee implements Worker {
    public void work() {
        System.out.println("work");
    }
    public void report() {
        System.out.println("report");
    }
}

class Engineer extends Employee {
    public void create() {
        System.out.println("create");
    }
}

public class Main {
    public static void main(String[] args) {
        Worker a = new Engineer(); // new的是Engineer 类型是worker 又没重写
        Engineer b = new Engineer();
        Engineer c = new Engineer();
        a.create(); // new的是Engineer 类型是 Worker 没这个方法 报错
        b.work(); // new的是Engineer 有这个方法，从 Employee 继承下来的没错
        c.report();  // 同理
    }
}
```

**T15**

这一题我大意了。

```java
public interface A {}
public class B implements A {}
public class C extends B {}
public class D {}

public class Main {
    public static void main(String[] args) {
        A[] array = {
            new B(), // 这里不会出错 毕竟有关系
            new C(), // 同上
            new A(), // 这里真的是无语了，接口怎么可能实例化
            new D() // 不同多说
        }
    }
}

```

**T16**

从父类哪里继承的子类，实现多态的时候，如果父类想调用子类特有的方法，那就需要强转。

```java
class A {}
class B extends A {
    void hello () {
        System.out.println("hello");
    }
}

public class Main {
    public static void main(String[] args) {
        A a = new B();
        B b = (B) a; // 记得强转
        b.hello();
    }
}
```

**T17**

这个多态根本没实现，怎么可能强转。

**T18**

挺好的一道题。我第一次反正没作对。

```java
class Sample {
    private int num;
    public Sample(int num) {
        ???
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;   
    }
}

public class Main {
    public static void main(String[] args) {
        Sample s = new Sample(10);
        System.out.println(s.getNum());
    }
}
```

问号这里填写什么才能表示输出的是10

this.num = num 这肯定没问题，初始化我想出来了。但是另一个没想到竟然是

setNum(num)

这一题考察的是作用域的问题。

总结就是本地变量的作用域在距离自己最近的大括号内有效。

```java
public void sample() {
    int num = 10; // sample 大括号位置 这里都是他的作用域
    if (num < 11) {
        int num = 20; // 编译报错，已经有了num
        int value = 100; // 这个在这个大括号为作用域，这里就没了
    }
    int value = 200; // 这里不会，因为本来也不属于if里面的作用域
}

// 但是这样却可以 因为上面是一个方法内，而这里是一个类里面。类里面的类属性和方法里的变量不一样。
public class Sample {
    int num = 10;
    void test() {
        int num = 20;
        System.out.println(num); // 20 
    }
}
```

这里的输出结果是20，为什么，因为按照就近原则。那么如果我想输出的是类里面的属性10呢。

那么就要用到this了。this指的是实例对象本身。this.属性名 就可以调用了。

**T19**

这里有一个点就是this指向。this只会指向自己的实例对象。

```java

class Parent {
    String name;
    String getName(){
        return this.name;
    }
}

public class Child extends Parent {
    String name;
}

public class Main {
    public static void main(String[] args) {
        Child child = new Child(); // new了一个child 继承了父类方法 getName 但请注意。并没有重写！！
        child.name = "sample"; // 设置child name是sample 和父类无关
        System.out.println(child.getName()); // 子类并没有重写 getName 所以这里调用的应该是父类的getName 但是父类的 getName 的 name 是空的
    }
}
```

子类和父类有同名属性的时候，各自有自己的空间。不冲突。

父类的name由于没有赋值是空的，所以name是初始值。name是String 所以是空对象。String 打印出来的是null。

**T20**

挺有意思的一道题目。可以加深对构造器实例化的理解。

在new子类的时候首先就会调用父类的空参构造器，也就是说省略了 `super();`

```java
class A {
    public A() {
        System.out.println("A");
    }
}

class B extends A {
    public B() {
        System.out.println("B");
    }
}

public class Main {
    public static void main(String[] args) {
        //super(); 省略了这个 于是先调用A的空参构造器 输出A 然后在调用B的空参构造器输出B
        A a = new B();
    }
}

```

**T21**

明白了上面那个子类一定要先调用父类空参构造器之后，这个就没什么了。 按照我给的顺序。

```java
class Parent {
    public Parent() {
        System.out.println("A");//4 A
    }
    public Parent(String val) {
        this(); //3
        System.out.println(val); // 5 B
    }
}

class Child extends Parent {
    public Child() {
        super("B"); //2
        System.out.println("C"); // 6 C
    }

    public Child(String val) {
        this(); // 1
        System.out.println(val); // 7 D
    }
}

public class Main {
    public static void main(String[] args) {
        new Child("D");
    }
}

```

## 第8章 函数式接口 Lambda表达式

函数式接口的问题。如果一个接口只有一个抽象方法，那么这个接口就是函数式接口。

Lambda 表达式就是在函数是接口里有应用的。这一章只有8道题。主要靠的就是函数是接口的 Lambda 表达式。

这个表达式需要 **函数式接口实例化 + 匿名实例化**缺一不可。当需要对一个函数式接口实例的时候，基本上就是new出来。

```java
Comparator<Integer> com = new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
};
// 可以看出来这里是匿名的实现了一个接口实例
Comparator<Integer> com = (o1, o2) -> Integer.compare(o1, o2);
        int res = com.compare(1, 2);
```

然后有个**四大核心函数是接口**

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20201215161856.png)



还有就是各种省略。了解了几乎就可以开始解题了。

**T1**

- 参数如果只有一个（）可以省略
- （内参数）和后面使用的参数必须一致，参数的作用域和这个实例是一起的，虽然类型有时候省略不写。但是也是要有的

**T2**

表达式具体怎么写和省略的问题

- 参数只有1个可以省略（）*Funcion f = name -> {sys out(name)}*
- 语句只有1句，可以省略{}
- return 如果和大括号一起出现并且只有1条语句，就可以省略。
- 不可以只是省略retrun 不省略{} 要省就要一起省。

```java
Function f = (name) -> {"hello", name;}; // NG!!!
Function f = (name) -> return "hello", name; // NG!!!
Function f = (name) ->"hello", name; // OK
```

**T3 - T4**

都是作用域的变量问题。

- 函数式接口的变量是final的不能随便变化
- 作用域共享的，所以不可以重复声明。默认省略了类型，所以看起来像是一起。

```java
String val = "A";
Funcion f = (val) -> {sysout("val")}; // 编译报错，因为这里默认省略了String val 这样就是重复定义了
f.test("B");
```

```java
int cnt = 0;
Runnable r = () -> {
  for(cnt = 0; cnt < 10; cnt++;){
    sysout(cnt++); // 报错，final不能修改
  }
}
```

**T5-T8**

只要记住上面那张图，4个核心函数是接口对应的函数就不会出错的。

## 第9章 API

**T1**

这一章主要就是对于常见的类的一些提问。主要是Math类，LocalDate类，Collection大家族，Lambda大家族。

**T2~T3**

这一题考察的是比较类，在基本数据类型的时候使用逻辑运算符就可以很简单的比较的话，那么类怎么比较呢。

其实类比较主要是通过了一个接口 *Comparator*  定制排序  还有 *Comparable*接口 自然排序 sort

自然排序，只要是排序都应该实现了*Comparable*接口，

基本上一些包装类，都给你写好了compare这个方法。所以不用操心。

如果你自定义的类是实现了这个类，那么重写就好了。

关于定制排序，如果对上面的排序不是很满意或者不方便修改源码的情况下，那么就可以定制排序*Comparator*  。实现方法是compareTo

sort()这个方法可以接受一个实现类，然后控制。

- 标准的自然排序都是从小到大，前面大于后面 1 前面小于后面-1 前面等于后面0

**T4**

包装类，主要考察API记不记得住。

**T5~T6**

考察LocalDate类，注意，这个是不可变类型。不可变类型！

**T7**

下面开始都是Collection了。ArrayList 传统的数组有无法改变长度数据类型等等等等弊端，所以有了这个类。

- 可以重复
- 可以null
- 可以动态改变
- 非线程安全

**T8**

可以重复的。不是很难

**T9**

ArrayList不存在的index，这个时候如果调用时会出错的。执行知错。

**IndexOutOfBoundsException**

**T10**

不难，略。

**T11** 

remove（） 删除第一个匹配的。因为remove在删除的时候要先看equals方法，只有equals方法相等的，才能判断相等。

所以一定要看好到底删除的是什么。

- equals进行判断
- 删除第一个匹配
- index可删除

```java
ArrayList list = new ArrayList();
list.add("A");
list.add("B");
list.add("C");
list.remove(1); // 这样删除的就是index1 就是B
```



**T12~13** 经典

因为上面说了 ArrayList 是非线程安全的，那么在修改的过程中，如果还有其他处理那么就会报错。

这个时候就是A 输出，判断B是，就remove B 这个时候数组就是AC，那么继续判断下一个的时候发现下一个没有了元素。此时C已经向前移动了。所以就只会输出A

```java
class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        for(String str : list) {
            if("B".equals(str)) {
                list.remove(str);// 这个进程进去之后已经删除掉了B 那么C就会自动向前进一位
            } else {
                System.out.println(str);
            }
        }
    }
}
```

#### T13

**ConcurrentModificationException** 这个错误。在remove这个操作的时候一定要看清楚。

```java
public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        for(String str: list) {
            if("C".equals(str)){
                list.remove(str);
            }
        }
        // 下面这个和上面这个其实是一起进行的，上面整在remove 下面正在输出的情况下。就有可能报错
        for(String str : list) {
            System.out.println(str);
        }
        
    }
```

**T14**

ArrayList 又名 动态数组

动态 → 静态 `var list = Arrays.asList(new Integer[]{1, 2, 3});`

` var list = List.of(1, 2, 3);`

上面的一旦生成就是无法修改的，运行的话就会报错**UnSupportedOperationException**。

**T15**

考察了mismatch方法。逐个对比是否一致，返回第一个不一致的index

如果都一致，那么就是*-1* 

**T16**

比较大小。默认从小到大。英文根据unicode码来对比 B反正比A大。

**T17**

这一题第一次看解析的时候没看懂，现在看一下。

```java
class Main {
    public static void main(String[] args) {
      // 这里生成了一个list 注意，这里不是静态的，因为外面包裹了一层 ArrayList  是动态的
        List<String> list = new ArrayList<>(
            Arrays.asList(new String[]{"A", "B", "C"})
        );
      // 这里实际上是有一个Predicate的test函数式接口在里面。
      // s如果是B的情况下Lambda表达式就会返回true 然后用遍历注意去处。
        list.removeIf(
            (String s) -> {
                return s.equals("B");
            }
        )
        System.out.println(list);
    }
}
```



**T18**

抽象类→接口→匿名子类→函数式接口→四大核心→Lambda表达式→方法引用

上面是一个大概的思路。就是从类到整个方法引用更大概经历了什么过程。

List 首先是Collection一个接口，然后实现了Iterable接口之后就可以迭代了，其中可迭代里面又一个forEach方法。

这个方法就是函数是接口中 Consumer类型，接收一个参数，然后不停的输出。

记住就好了吧。

`List.forEach( str -> System.out.println(str));`

`List.forEach(System.out::println);`

**T19**

关于HashMap的常用API。

- key不可以重复，value可以重复。key重复了会被覆盖
- 可以有null的key和value
- key无序的，不可重复的。

## 第10章 异常处理

这一章有一个很严重的问题，就是trycatchfinally执行顺序问题 +  throw throws的区别问题。

**T1**

指针错误，直接就是被catch住了。这一题问题不大。

**T2**

Òfinally一般都是必须会去执行的。所以这一题也不难。

**T3**

这一题有点难度。

看完题解也是没看懂。就是相等于不懂了。

```java
public class SampleException extends Exception {}
public class SubSampleException extends SampleException {}

public Main {
    public static void main(String[] args) {
        try {
            sample();
            sub();
        } catch (SampleException e) {
            System.out.println("A");
        } catch (SubSampleException e) {
            System.out.println("B");
        }
    }

    private static void sample() throws SampleException {
        throw new SampleException();
    }

    private static void sub() throws SubSampleException {
        throw new SubSampleException();
    }
}
```

**T4**

try-catch-finally 按照这个顺序。不然都是错的。

**T5**

关于catch里有return 和finally执行顺序。

结论就是先执行finally 然后在输出return语句

https://blog.csdn.net/yongh701/article/details/50057975

**T6**

关于finally和return作用域和变量问题。

```java
public Main {
    public static void main(String[] args) {
        int result = sample();
    }

    private static void sample() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            return 10; 
        } finally {
            return 20;// 在变量名一样的情况下，finally里的return 会覆盖前面的
        }
    }
}
```

**T7**

可以和上一题进行对比。作为返回值的变量和函数里使用的变量是不一样的。（基本数据类型）

```java
public Main {
    public static void main(String[] args) {
        int result = sample();
        System.out.println(result);
    }

    private static void sample() {
        int val = 0;
        try {
            String[] args = {"A", "B", "C"};
            System.out.println(args[3]);
        } catch (RuntimeException e) {
            val = 10;
          	return val; // 返回值的变量
        } finally {
            val += 10; // 这里的变量和上面返回值的变量是不一样的
        }
        return val;
    }
}
```

**T8**

- finally在一个语句里只能有1个，不同于catch为了对应不同的异常情况
- try-catch-finally 里面 可以没有catch 没有catch 的情况下。
- 没有catch情况下必须在函数里声明throws

**T9**

try里面又有try的情况下按照位置和顺序下来就好。记得2个finally都要执行

**T10**

- try里面的变量作用域只在try里面不能出去
- finally里面关闭资源可能也会出现问题，这个时候必须也要用try-catch进行异常处理。

**T11**

重点的题。

- 异常的分类。
异常的分类就是error和Exception2大类。

Exception分为执行异常和非执行异常。

非执行异常必须要写上try-catch。throws 这样进行先处理。

runtime因为是执行起来才会发生的错误所以可以不必写出来。

```java
public class SampleException extends Exception {}
public class TestException extends RuntimeException {}

public class Sample {
    public void hello(String name) XXX {
        if(name = null)
            throw new SampleException(); // 这里因为是 SampleException 是非执行，所以必须要throws
        if("".equals(name))
            throw new TestException();// 这里是继承的runtime，所以可写可不写。

    }
}
```

**T12**

关于error的问题。这个也是系统执行时候发生的错误。

- error可以被继承
- jvm里面处理，无需try-catch。throws 
- 也可以在异常时处理catch上。

**T13**

程序编译运行命令行 java Main这样的时候不写参数的时候也不会被报错，默认生成一个不包含参数的数组。所以不会空指针。

但是如果调用不存在的参数就会出现index异常。`args[0]`

**T14**

IndexOutOfBoundsException

	- ArrayIndexOutOfBoundsException
	- StringIndexOutOfBoundsException

有这样2个子类异常。

T15 是一个类型转换异常 ClassCastException

**T16**

空指针异常 NullPoniterException

```java
public Main {
    public static void main(String[] args) {
        String str = null;
        if(str.equals(""))
            System.out.println("blank");
        else
            System.out.println("null");
    }   
}
```

**T17**

死循环 - StackOverflowError

## 第11章 模块系统

被调用模块名下*module-info.java*d

```java
module 被调用模块名 {
  exports xxx.xxx.xxx;// 需要导出的包
}
```

调用模块

*src/module-info.java*

```java
module 调用模块名 {
  requires 被调用模块名
}
```

## 一轮

第一题就错了呢。

#### T1

source file mode java指令可以直接进行编译了。

编译a的时候顺便依赖的b就编译了。编译和运行时2码事。

#### T2 简单 略

#### T3

关于import的时候要import全名的问题。

java.lang里面的类无需import

引入的时候有2种。

- 完全修饰
- 部分修饰

#### T4

lambda表示的变量想要使用必须是final 所以如果卸载里面了进行修改就会报错。

```java
public class Sample {
  void sample () {
    int i = 0;
    Supplier<Integer> foo = () -> i;
    i++; // 这里进行修改了就会报错
    System.out.println(foo.get());
  }
}
```

#### T5

关于变量其实的区别。下面的num明显指向不明，所以会出现变异报错。

如果想要是类变量就死`int num = Sample.num` 如果想是实例变量的话`int num = this.num`

class和方法里都不能有重名的变量，但是class和方法里可以分别有一样名字的变量。但这个时候就要明确指出了。

```java
public class Sample {
  static int num = 23;
  public static void main(String[] args){
    int num = num; // java里的变量有实例变量和类变量。这里的num 如果想指的是类变量，就要指名是Sample.num
     System.out.println(num);
  }
}
```

#### T6 略

#### T7

很经典。可以写。

```java
public static void main (String[] args) throws java.lang.Exception
	{
		int data = 1;
		switch(data) {
		    default: System.out.println("C"); // 输出B 只要没有break就会无限下去。
		    case 0: System.out.println("A"); // 输出A 然后遇到了break 直接out
		            break;
		    case 10: System.out.println("B");
		            break;
		}
	}
```

#### T8

记住几个常用的方法API

```java
java.lang.String
  charAt();
	endsWith();
	substring();
java.lang.Character;
 	isAlphabetic();
	isDigit();
```

#### T9

查询类和module之间的关系。使用 jdeps

查询module之间的关系 java --show-module-resolution

jmod 是生成module的命令。

jar  打包命令

#### T10

这一题很好了。

```java
public interface A {
  abstract void x();
}

public abstract class B {
  public void x(){};
  public abstract void z();

public class C extends B implements A {
  // 这里写什么呢。
  因为C继承了B实现了A 所以z肯定是要重写的。按理说x也要重写。但是由于继承了B的x 所以没必要重写。
}
```

#### T11 略

#### T12

```java
public static void main (String[] args) throws java.lang.Exception
{
		String str = "abce ef gh";
	  int x = str.indexOf("ef"); // 5
  	str.substring(x + 3); // 这里是关键，只是substring不会改变str
	  x = str.indexOf("ef"); // 5
  	System.out.println(str + "" + x); // 结果就是源字符串 + 个5而已
}
```

#### T13 这一题可以和第3章T19一起配合服用。

从java7开始switch条件里面就可以使用String和Enum这样的引用数据类型了。但是这样的数据类型有一个需要注意的就是不能为null。

因为javap生成中间code的时候可以看出来，关于case 使用的是hashCode来计算去到哪里的。null的数值无法 `null.hashCode();`

![image-20201223141323650](/Users/Chihokyo/Library/Application Support/typora-user-images/image-20201223141323650.png)

#### T20

```java
public static void main (String[] args) throws java.lang.Exception
	{
		char a = 'A';
		int b = 1;
		System.out.println(a + b); // char会自动转换成int 但是这个int是 Unicode
		System.out.println(a +"," + b);
	}
```

#### T21 略

数组初始化&赋值。参考第5张，T4，T7。

#### T22

不知道这个怎么弄

```java
public class A {
    int num = 10;//field 是一个default 意思就是说只能在一个包里可以用 所以下面都可以用
    public double getValue() {
        return 0.0;
    }
}

public class B extends A {
  	// 这里就是重写了 
    @Override
    public double getValue(){
        return 1.0
    }
}
public class C extends B {
  // 这里也是重写了
    @Override
    public double getValue(){
       System.out.println(super.num); 
       return super.getValue();
    }
}
```

#### T23

很难。从提问上来说

执行的是`a1.sample(list)` 那肯定就是A的，输出A。

如果是`a2.sample(list)` 既然运行在右的话，已经override的，那么就是子类重写过的。

关于`b1.sample(list)` 这个就是看传入的类型，传入啥就是啥。

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20201223175251.png)

#### T24

这种多重继承的。有个叫菱形继承问题。这种继承问题一般都要指名super到底是谁。

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20201223180457.png)

#### T25

override怎么可以参数不一致。那样就是overload了。

所以继承在重写的时候函数的权限和参数等等必须全部一致。

```java
public abstract class A {
    abstract void sample();
}

public class B extends A {
    @Override
    void sample(int i) { // 这里NG！！
        System.out.println(10);
    }
    
   public static void main (String[] args) {
       A a = new B();
       a.sample();
   }
}
```

#### T26

这一题真的是大大意了。

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20201223181356.png)

#### T27

关于重载和数据默认类型问题。

整数都是默认为int，小数都是默认为double。

这一题主要是在Integer和int之间犹豫了。虽然Integer是int的包装类，但是不用自动装包的int其实是首选。

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20201223182248.png)

#### T28

关于实现和继承关系。

- 作为接口的A 肯定是不可以final的 因为是要继承的
- B当然是可以抽象的，也可以不是抽象的。不是抽象的话，就要重写所有的抽象方法

```java
public interface A {
    public A build(String str);
}

public class B implements A {
    public B(String str){}
    
    @Override
    public B build(String str){
        return new B(str); // 这里都new了 肯定不可能是抽象的B
    }
}
```

#### T29

略

#### T30

构造器都要默认有个`super();`

#### T31

这一题大意了。完全没看到前面的 -

```java
import java.util.ArrayList;
import java.util.Arrays;
public class Main {
  public static void main(String[] args) {
    var alphabet = new ArrayList<>(
        Arrays.asList(
            new String[] {"A", "B", "C", "D"};
        )
    );
    alphabet.sort((var a, var b)->a.compareTo(b));
    alphabet.forEach(System.out::println)
  }
}
```

#### T32

抽象类里面可以有抽象方法也可以有普通方法。

抽象方法的话要有以下2个限制。

- 必须用abstract修饰
- 不准有大括号，只能`();` 结束。

#### T33

`List.of()` 生成的是不可以变的。

跟第9章T14一样的。

#### T34

终于明白了switch的用法了。

这里就是说虽然b符合了条件，但是并没有break的情况下。

后面的语句所有都会执行，直到有了break。

所以最后结果就是 3

```java
public static void main (String[] args) throws java.lang.Exception
	{
	    char c = 'b';
	    int i = 0;
	    switch (c) {
	        case 'a': i++;break;
	        case 'b': i++; // 1
	        case 'c': i++; // 2
	        case 'e': i++;break; // 3 直接break switch语句
	        case 'f': i++;break;
	        default: System.out.println(c);
	    }
	    System.out.println(i); // 到这里输出
	}
```

#### T35

```java
public class A {
    private void print(){
        System.out.println("A");
    }
    public void a(){
        print()
    }
}

public class B extends A {
  // 因为上面print是一个private 是不能进行重写的。所以这这里不能加@Override
    private void print(){
        System.out.println("B");
    }
    public void v() {
        print();
    }
    public static void main (String[] args) {
        B b = new B(); //  这里new的是B 
        b.a();// 这里输出的就是A 如果重写的话 那么这里输出的就是B了
        b.b();
    }
}
```

#### T36

很经典的一道题目。关于for循环里面的写法。还有++a和a++

因为这里是先加的 所以这里最先输出的就是1

如果是后加的，那么就是先输出0.然后再加。

```java
int x = 0;
while(x < 10) {
  System.out.println(x++);
}
/**和上面这个相等的是***/
public static void main (String[] args) {
        for (int a = 0;a <10 ; ) {
            System.out.print(a);
            a++;
        }
}
下面都是不相等的
int b = 0;
for(; b< 10;){
  System.out.print(++b); // 这里是要先加b 然后输出，第一次输出的不是0，而是！！
}
```

#### T37

略

#### T38

of不可以被修改。参考上面的T33

#### T39

```
> java A "A B" A B 
三个参数 分别是
1 A B
2 A
3 B
```

#### T40

跟着走，不会出错的。

```java
class A {
    public static void main (String[] args) {
        String[] array = {"A", "B", "C", "D"};
        for (int i = 0;i < array.length ; i++ ) {
            System.out.print(array[i] + " ");// 输出A
            if (array[i].equals("C")) {
                continue;
            } 
            System.out.println("end");
            break;// 直接跳出整个for循环了
        } 
    }
}
```

#### T41

这一题还是很难的。B和C继承了A ，但是a方法的返回值不一样。

有一个需要注意的就是 Iterable 是其他两个的父类。那么D在继承BC的时候必须要指名到底返回的什么类型。所以C是正确的。

```java
public interface A {
    public Iterable a();
}
public interface B extends A {
    public Collection a();
}
public interface C extends A {
    public Path a();
}
public interface D extends B, C {
  
}
```

#### T42

这一题很有意思。关于子父类属性重名的问题。

```java
public class A {
    public String x = "A";
    protected A () {};
}
public B extends A {
    String x = "B";
    public B() {
        super();
    }
}

import A;
import B;
public class Main{
    public static void main (String[] args) {
        A a = new B();// x是重名的，new的时候调用了A的方法。是可以使用A定义的x的所以就可以了。
        B a = new B(); // 但是这样是不行的。因为String x = "B" 是default的 引入的没有权限
        System.out.println(a.x);
    }
}
```

#### T43

这一题考的是权限。由于本人没有睁大眼睛看清楚，所以犯了一个低级错误。`private` 是 只能在同包同类才能用的，继承是不行的。

#### T44

关于构造器的问题

- 构造器没有的话默认给你个无参的
- 如果你写了构造器，那么就一定要手写一个无参的。
- 子类在实例化的时候自动调用父类的无参构造器 `super()`
- 子类`super()`之后不能`在this`了，不然这就是叫了2次`super`。绝对不行的。

```java
public class Sample {
    String name;
    int num;
  // 错误1 这里竟然没无参的构造器
    public Sample(String name, int num){
        this.name = name;
        this.num = num;
    }
}
public class SubSample extends Sample {
    int price;
    public SubSample (int price) {
      // super(); 这里会默认有一个super 但是会发现上面无参
        this.price = price;
    }
    public SubSample (String name, int num, int price) {
        super(name, num);
        this(price); // 错误2 这里用了一个重载的构造器，但其实也默认super了，为了防止这种情况，super之后不能使用this
    }
}
public class Main {
    public static void main (String[] args) {
        SubSample s1 = new SubSample(100);
        SubSample s2 = new SubSample("sample", 200, 100);
        System.out.println(s1.name + "," + s1.num + "," + s1.price);
        System.out.println(s2.name + "," + s2.num + "," + s2.price);
    }
}
```

#### T45

就是考察override的条件

- 函数名和参数要一样
- 返回值要一样 或者是子类
- 修饰权限一定要大于父类的

还有一个很重要的，2个要存在实现或者子父类关系。不然就没任何意义。

```java
public interface Sample {
    void test(); // 这里其实省略了public static
}
// 没问题 函数名&参数&返回值&权限修饰符
public class SampleTest implements Sample {
    @Override
    public void test(){
        System.out.println("test");
    }
}
// 根本没关系
public class SampleTest2 {
    @Override
    public void test(){
        System.out.println("test");
    }
}
// 抽象方法 没问题
public abstract class SampleTest3 implements Sample {
    @Override
    public abstract void test();
}
// 抽象方法 没问题
// 这里的抽象方法是必须重写的 
public abstract class SampleTest4 implements Sample {
    @Override
    public void test(){
        System.out.println("test");

```

#### T46

`java.time.LocalDate`class的话，LocalDate.now() 

返回的就是年月日 2020-12-26 这样

#### T47

不难。略

#### T48

这一题如果是以前的我肯定是会做错的，因为前++和后++我记得不清楚。

但是现在记得清楚了。这个++是后加的，所以在输出的时候是1，后来进行了++操作。

```java
> java Main a b c
public class Main {
  public static void main(String[] args) {
    int i = 1;
    for(String s: args) {
      System.out.println((i++) + ")" + s);
    }
  }
}
```

#### T49

考察关于field和local variable的作用域问题。

- 如果重名。在method里面必须要用this明示是哪里。如果没写。就默认是local variable

这一题主要就俩点。=执行顺序是从右到左。所以先开始的

```
a = b = this.c = 10
所以这样 this.c就是10 也就是说field的c就是10
```

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20201231001946.png)

#### T50

这一题刚开始绝对傻逼了。

跳出来之后是不会执行 System.out.println(num);  

然后因为num 这个时候已经是1了 所以在while判断num<1的时候就是错的，根本没机会了。

所以答案是没有输出结果的。

```java
public class Main {
    public static void main(String[] args) throws Exception {
        int num = 0;
        do {
            num++;
            if (num == 1) { //这里判断正确之后就跳过这一层循环，进入第2次了
                continue;
            } 
            System.out.println(num); 
        } while (num < 1);
    }
}
```

#### T51

try-catch-finally注意点

- tr-catch-finally 必须按照顺序
- catch可以有多个
- finally只能有一个

finally都是最后一个执行，哪怕在try-with-resource的情况下。

#### T52

这一题不是很难。就是要知道`mismatch()`还有`compare()`的区别

mismatch是找第一个不同的index到底在哪里

compare比较大小

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20201231003016.png)

#### T53

第一次做不会。全蒙的。考察的`java.lang.Math`这个库

round这个方法参数要么是double 要么是float 浮动小数的四舍五入 出来的是int

反正是不能接受俩参数的。

**这一题需要注意的是 类型转换优先于算数演算** 所以。先转型

```java
float a = Math.round((float) x / y * 100) / (float) 100;
// 先把round的结果除以已经转换的100，x也是先转换成了float
// 所以就是 5.0/2 * 100 这个时候结果是250.0 然后除以100.0 那就是2.5
float a = (float)(Math.round((float) x / y * 100)/100)
// 这里的问题就是 Math.round((float) x / y * 100) 之后是250 250/100 相当于int/int 这个时候出来的是2 所以是错的
```

#### T54

这一题有一个误区。不小心掉进去的我做错了。

```java
public interface Sample {
  void test();
}
// 这里是一个普通类实现的接口 必须重写全部的方法的。（抽象类的话就不用重写全部了
class SampleImpl implements Sample {
  @Override
  public void test(){
    
  }
}
// 这里是继承不是实现啊，所以重载是可以的啊
class SubSampleImpl extend SampleImpl {
  void test(int x){
    
  }
}
```

#### T55

把握不住。

**最重要的就是char和数值型的互相转换。B可以使因为强制转换是可以的。**

**但是C那种不是强制转换的情况是不可以的。**

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20201231005159.png)

#### T56

注意层次就不会出问题。

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20201231005803.png)

#### T57

反正就是不能把父类写前面。不然就是编译错误。

#### T58

```java
int num = 9;
if(num++ < 10){ // 因为是后++ 所以先比较后++ 也就是比较用的是9 输出是10
  sysout(num);
}else {
  sysout("B")
}
```

#### T59

不难。String和数字连接一起的时候就成了String。略

#### T60

关于各种数据类型的转换问题。

short int 可以转换成 long这些更大的。Integer可以解包成int

但是这些数字和String不能转换的。

```java
short s1 = 10;
Integer s2 = 20;
Long s3 = (long) s1 + s2;
String s4 = (String)(s3 + s2); //出错了
sysout(s4)
```

#### T61

String是不可变类型。所以只要有一个不一样的。那么常量池就会有一个。

**== 引用数据类型判断的是地址是否相等。**

```java
String s1 = new String("Java")
String s2 = "Java";
String s3 = s1.intern(); 
// 上面第一种方式就new一个对象。s2就是常量池那种。s3就是复制一份s1的地址。
```

#### T62

很简单 略

#### T63

错了。所以分析一下。

```java
public class Main {
    public static void main(String[] args) throws Exception {
        int x; // int x = 8;随便赋值一个都是对的
        int y = 3;
        if (y > 2) {
            x = ++y;
            y = x + 5;
        }  else {
            y++;
        }
        System.out.println(x + "," + y);// 这里x没有初始化的 对应上面的int x;
    }
}
```

#### T64

不用解释的

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20201231154753.png)

#### T65

记着默认省略了一个super 就没事了

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20201231155025.png)

#### T66

就是一个很简单的记忆。略

#### T67

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20201231155501.png)

#### T68

不难

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20201231155710.png)

#### T69

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20201231160008.png)

#### T70

关于java想执行module内的class的话。

那么就要指定module路径和class全名

```
java --module-path （module的root路径）-m (module的class)
```

#### T71

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20201231161646.png)

#### T72

主要是明白API用法 还有必须记着，这个是`public StringBuilder replace(int start, int end, String `str)

不是String 这里的用法和上面的不一样。

`replace（起始位置，结束为止，需要替换的字符串）`  还有 `indexOf()`　的用法

只要明白了这个用法就行了

#### T73

不难。略。

#### T74

即使生成了Object的数组实例，但是里面的元素并没有被实例化。所以就只是一个空壳。要素都是null。

![image-20201231163228600](/Users/Chihokyo/Library/Application Support/typora-user-images/image-20201231163228600.png)

`NullPointerException` 空指针错误

#### T75

module-info.java

需要的，使用requires

想要公开的，使用exports

#### T76

简单。略。

#### T77

这个就是重写的时候，虽然可以使用更大的父类。**多态**

但是如果已经有了具体的类型，就用具体的类型。

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20201231163728.png)

#### T78

子类的权限只能比父类大。略

#### T79

略。记忆类的。java.base 里面有标准库

#### T80

略。不难。

## 二轮

E ABD A DEF C C A D D D

#### T1

关于异常的体系问题。

- error

- 异常（执行异常，非执行异常）

可以看一下这篇文章

https://www.cnblogs.com/sqjinbao/p/13372901.html

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20210102234653.png)

只有checked异常才必须要写。

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20210102235156.png)

#### T2

这一题没错，不是很难。主要就是不可变类型不能被继承不能重写不能改变。如果想改变必须复制或者生成一个新的不相干的实例。

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20210102235517.png)

#### T3

不难

小转大 自动的 无需显示 *int -> double*

小转大 强制的 必须明示 *double -> int*

#### T4

不是很难。主要是继承的问题。继承之后记住如果field权限是default（没写），那么只有一个包的才能调用。否则不行。

#### T5

这一题也不是很难，主要分清**本地变量**和**类变量**。

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20210103000240.png)

#### T6

不难，记住继承关系。

public 什么都可以

private 什么都不可以

protected 继承就可以

default（不写） 必须在一个包

#### T7

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20210103000701.png)

#### T8

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20210103000829.png)

#### T9

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20210103001418.png)

#### T10

这一题完全是考察 var的用法

var只能用在本地变量里不能用在类变量。而且var也不能用作返回值。我这个傻子。

#### T11

不难。注意this 略。

#### T12

这个var的类型推断问题。var 右边的时候一定要明确推断的是什么。不然就是错误的。

#### T13

和上面T6一样

#### T14

super ⭕️ super.super ❌

#### T15

构造器不能继承。但是构造器在调用父类的时候一定要是父类存在的构造器。

#### T16

这一题就是没看清楚题意，就这么做错了。

虽然是一个包，但并非是一个文件。import还是分文件的

#### T17

这一题属于我个人容易混淆的题目。

- {} 可以初始化，但是必须要配合声明一起使用 主要用于一次性匿名这样`sample(new int[]{1, 2, 3})` 这样一下子就声明了一个匿名数组作为参数
- 承接上面那条`[]` 这里不能写数组 比如 `new [3]{1, 2, 3}` 这样不行 
- 个人混淆的就是这个 `Double[] array = {null,1.0}`我错误的认为null不行，其实这已经是一个数组了。引用类型当然可以存放null

#### T18

看清权限修饰符和关键字staic就可以 略

#### T19

int c = b = 22 = b + a/5 略

#### T20

略

#### T21

这一题做错了。分析了一下。是自己没分析对这一题。

主要看这一题的答案。

```java
public class Sample {
  private int num;
  private int test(){
    for(int i = 0; i < 3; i++){
      num += i;// 3 这里就开始修改上面的num
    }
    return num;// 4 返回
  }
  public static void main(String[] args){
    Sample s = new Sample(); // 1 新建对象
    int num = s.test();// 2 - 5 这里num就是接收返回值3 但是注意，这里的num 是这个main函数的变量。和上面的都无关 
    s.test();// 6这里继续修改了 但是下面输出的是本地的变量num
    System.out.println(num);// 输出3
    System.out.println(s.num);// 输出6
  }
}
```

#### T22

略

#### T23

这一题也是蛮有意思的。

关于多态的变量field问题。

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20210103225022.png)

#### T24

略

#### T25

这一题做对了。但是原因错了。

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20210103225343.png)

#### T26

```java
public class Main {
	int num;
  private static void test(){
    num ++;// 这个num不是static的 所以就会报错的
    System.out.println(num);
  }
  public static void main(String[] args){
    Main.test();// static方法只能呼出static方法 但是这一题的问题是↑
    Main.test();
  }
}
```

#### T27

这一题真是大意了。

String 作为不可变类型。怎么就这么写了呢。

```java
String str = "abcde";
str.replace('c', 'x');
str.substring(2, 4);
System.out.println(str);// abcde


class Sample {
  public static void main(String[] args){
    String str = "abcde";
    str = str.replace('c', 'x');
    str = str.substring(2, 4);
    System.out.println(str);// xd
  }
}
```

#### T28

这一题纯粹是根本不会。不熟悉吧。

List这个接口的forEach方法作为消费性Consumer的lambda表达式。

是没有返回值的。

```java
list.forEach((x) -> System.out.println(x));
```

#### T29

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20210103230856.png)

#### T30

不解释了 ，直接看图吧。

![image-20210103231041492](/Users/Chihokyo/Library/Application Support/typora-user-images/image-20210103231041492.png)

T31

B