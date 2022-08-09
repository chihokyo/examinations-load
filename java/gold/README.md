# Java SE11 Gold 問題集相关

## 1 类与接口

Q1 考的是内部类，这一题考的很偏，一般内部类都没有这么偏的。

内部类的分类

- inner class 成员内部类
- static inner class 静态内部类
- local class 内部类（简单理解成方法内部写的类就行）
- anonymous class 匿名类

> 内部类的分类：
>
> 成员内部类（静态、非静态 也就是上面说的成员内部类 和静态） vs 局部内部类(方法内、代码块内、构造器内 也就是上面说的内部类)

```java
class Student {
    // 1成员静态类（什么都可以用来修饰，4大修饰，af都可
    private static class Teacher {

    }

    // 2成员非静态类（除了static，其他都可
    private class Teacher2 {

    }

    public Student() {
        // 3构造器：局部内部类（local class 只允许abstract和final 来修饰）
        abstract class Teacher3 {

        }
    }

    public void learn() {
        // 4方法内：局部内部类（local class 只允许abstract和final 来修饰）
        final class Teacher4 {

        }
    }

    {
        // 5代码块：局部内部类（local class 只允许abstract和final 来修饰）
        final class Teacher5 {

        }
    }

}
```

关于匿名内部类的快速理解（只能是内部，外部不能定义）

属于啥都没有，只能用于临时实现接口。比如

```java
interface A {
    void foo();
}

class B {
    @Test
    public void bar() {
        // 这里你想临时调用A里面的foo
        // 这里就是匿名类的实现 其实就是看起来 new 接口()
        // 但实际上应该是你写了一个类实现了一个接口，但是没有写类名而已。
        call(new A() {
            @Override
            public void foo() {
                System.out.println("我是在B里面的foo哦");
            }
        });
    }

    public void call(A a) {
        a.foo();
    }
}

```

Q2 考察的是在内部类调用外部类的方法

- 调用 static 方法 → 可以直接写 `new 类名()`　也可以直接写 new 外层类名.类名()

```java
Person.Dog dog = new Person.Dog();
Person.Dog dog = new Dog();
```

- 调用非 static 方法 → 必须新建一个对象，才可以

```java
erson.Bird bird = new Person.Bird(); //错误的
Person p = new Person(); // 1-a
Person.Bird bird = p.new Bird(); // 1-b
Person.Bird bird = new Person().new Bird(); // 2
```

Q3 是在考验内部类调用 static 外部类的方法

题解同上

Q4

这个就是说 static 内部类，没办法去获取外部非 static 的方法 or 属性

```java
public class A {
	private int num;
  private static class Inner {
    private int data;
    void execute(){
      // 这里肯定会有错误，因为Inner这个class是static的
      // num不是的，如果num是static的就可以解决了
      System.out.println(num * data);
    }
  }
}
```

Q5

这里主要问题是属性 static，类不是。需要改类才行。

```java
public class Q5 {
    void test() {
        Inner.msg = "Hello java";

    }
		// ✅ static class Inner
    class Inner {
        // 这里定义了一个static的field，但是你的class却不是static的。
        private static String msg;

        public void test() {
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {
        Q5 q5 = new Q5();
        q5.test();
        q5.new Inner().test();

    }
}
```

Q6

这一题蛮综合的。考的在方法内部写的类的问题。先说结论。

方法内部定义的属性可以被方法内部的类所拿到，但是方法内部定义的属性是 final 的，无法更改，且必须在内部类前面定义。

- 属性必须是 final
- 必须在内部类前面定义

```java
public class Q6 {

    void test() {
        // 这个name必须在Sample这个类之前定义
        // 而且必须是final的，jdk7之后默认都是final的。
        String name = "sample";

        class Sample {
            void hello() {
                // ❌ 不能修改 name = "yes";
                System.out.println("hello" + name);
            }
        }
        // ❌ String name = "sample";

    }
}
```

Q7

考的是匿名内部类的 var 类型推断问题。

因为匿名内部类是可以自定义函数的，自定义的函数在你原来的类里根本没有，就会发生编译错误。为了防止这种编译错误都使用的是类型推断。

```java
public class Q7 {
    public static void main(String[] args) {
        // 此处Object根本没有这个test方法，编译肯定是会报错的
        // 解决方法 Object 类型改成 var 类型推断一下
        var obj =  new Object() {
            public void test() {
                System.out.println("test");
            }
        };
        obj.test(); // java: cannot find symbol
    }
}
```

Q8

匿名内部类没有构造方法，想初始化请用代码块{}

```java
public class Q8 {
    public static void main(String[] args) {
        Sample sample = new Sample() {
            // 构造器是不会允许的
            // public Sample(){
            //     System.out.println("A");
            // }
        };

        //****************************************
        // 想要初始化，请用的代码块
        List<String> list = new ArrayList<>() {
            {
                super.add("A");
                super.add("B");
                super.add("C");
            }
        };
        for (String str : list) {
            System.out.println(str);
        }
    }

}

class Sample {
    public Sample() {
        System.out.println("A");
    }
}
```

Q9

接口中定义的静态方法，只能通过接口调用。

实现类的对象，实现类本身都是无法直接调用的。

```java
interface A {
    static void test() {
        System.out.println("A");
    }
}

interface B extends A {

}

interface C extends A {

}

class D implements A {
    public static void main(String[] args) {
        A.test();
        // B.test(); 接口的静态方法，不支持继承。必须自己写。
        // C.test(); 同理
        // test(); 必须自己实现，不支持继承
    }
}
```

Q10

这一题考察的是接口写的 static 方法也不能被重写

```java
public interface Q10 {
    static void test() {
        System.out.println("A");
    }
}

class SampleIml implements Q10 {
    // 不能被重写的，此处相当于就是 SampleIml自己的方法
    public static void test() {
        System.out.println("B");
    }

    public static void main(String[] args) {
        Q10.test(); // A
    }
}

```

Q11

考察的是接口里面的 default 方法。

- 只能 1 层继承 (Q11 考察)
- 类优先原则
- 重写要显示的调用

```java
public interface Q11 {
    default void test() {
        System.out.println("A");
    }
}

interface B11 {
    default void test() {
        System.out.println("B");
    }
}

interface C11 extends B11 {
}

interface D11 extends C11 {
}

class Sample10 implements Q11, D11 {
    @Override
    public void test() {
        Q11.super.test();
        // B11.super.test(); sample和10只是间接继承不可以的
        // C11.super.test(); 同上
    }
}

```

Q12

考察的是类优先原则

```java
public class Q12 {
    public static void main(String[] args) {
        A12 a = new C12();
        a.test(); // B 类优先
    }
}


interface A12 {
    default void test() {
        System.out.println("A");
    }
}

class B12 {
    public void test() {
        System.out.println("B");
    }
}

class C12 extends B12 implements A12 {

}

```

Q13

这里重写必须是一个 public 的方法

```java
interface A13 {
    public default void test() {
        System.out.println("A");
    }
}

abstract class B13 {
    // 必须是public的才可以 protected不行
    public void test() {
        System.out.println("B");
    }
}

class Sample13 extends B13 implements A13 {
    public static void main(String[] args) {
        new Sample13().test(); // B
    }
}
```

Q14

接口的私有默认方法。这个是 java9 新增了。

9 之前接口里面只能

- public final static 属性 （全局常量）
- public abstract 方法
- static 方法
- default 方法

9 之后新增 private 方法

注意点，就是必须写上方法体。`void test()`不可以，必须要`void test(){}`不。

```java
public interface Q14 {

    // 8之前
    public final static int NUM = 99; // 1️⃣全局常量
    int NUM2 = 99; // 全局常量(简写)

    public abstract void eat(); // 2️⃣抽象方法

    void eat2(); // 抽象方法(简写)

    // 8新增
    public static void foo() {
    } // 3️⃣静态方法

    default void bar() {
    } // 4️⃣默认方法

    // 9新增 5️⃣私有方法
    private void test() {
    }

}

```

Q15

考察的枚举类的方法

- `values()` 返回枚举类中所有的值
- `valueOf()`方法返回指定字符串值的枚举常量

```java
public enum Q15 {

    RED, GREEN, BLUE;
}


class TestQ15 {
    public static void main(String[] args) {
        // 获取所有的枚举类的值 返回数组，所以可以使用索引
        Q15[] arrs = Q15.values();
        for (Q15 arr : arrs) {
            System.out.println(arr);
        }
        // 获取枚举常量
        System.out.println(Q15.valueOf("RED"));
    }
}
```

Q16

这一题考察的是枚举类的本质。很难。

本质就是每一个常量都是一个单例的。

```java
package com.gold.chapter01;

public class Q16 {
    public static void main(String[] args) {
        // 而由于你又重写了toString() 相当于这里也要打印最后一次，也就是第4次
        System.out.println(SampleQ16.A);
    }
}

enum SampleQ16 {
    // 这里相当于生成了3个实例，也就是new了3次。
    // 所以触发了3次构造器，那么就打印了3次hello
    A("hello"), B("hello"), C("hello"),
    ;
    private final String value;

    private SampleQ16(String value) {
        System.out.println(value);
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
```

## 8 注解

注解感觉就是给代码贴标签。表示一种含义，加个标签 mark 一下。

JDK 内置的三个基本注解

> @Override: 限定重写父类方法, 该注解只能用于方法
>
> @Deprecated: 用于表示所修饰的元素(类, 方法等)已过时。通常是因为 所修饰的结构危险或存在更好的选择
>
> @SuppressWarnings: 抑制编译器警告

Q1

这一题很 easy，就是考察的自定义注解怎么写.

> - ① 注解声明为：@interface
> - ② 内部定义成员，通常使用 value 表示
> - ③ 可以指定成员的默认值，使用 default 定义
> - ④ 如果自定义注解没有成员，表明是一个标识作用。

```java
@Inherited // 继承性
@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE,TYPE_PARAMETER,TYPE_USE})
public @interface MyAnnotation {
    String value() default "hello";
}

```

Q2

考察的是指定成员怎么写，感觉就是 类型 + 值方法

```java
String test();
// 用的时候就是
@MyAnnotation(test="字符串")
```

Q3

这一题考察的是元注解，什么是元注解。修饰注解的注解就是元注解。

> RetentionPolicy.SOURCE:在源文件中有效(即源文件保留)，编译器直接丢弃这种策略的 注释
>
> RetentionPolicy.CLASS:在 class 文件中有效(即 class 保留) ， 当运行 Java 程序时, JVM 不会保留注解。 这是默认值
>
> RetentionPolicy.RUNTIME:在运行时有效(即运行时保留)，当运行 Java 程序时, JVM 会 保留注释。程序可以通过反射获取该注释。
>
> 上面的话很简单，编译之后不见了就是 source，编译之后还有就是 class，如果运行在内存之后还要继续保留就是 runtime，（一般用反射拿到标签都要在这里，因为反射是运行时行为。

问的是编译之后不见，那肯定是 source 了。

Q4

Target 也是个元注解。不写就默认修饰全部。

Q5

检查子类父类是否重写对的时候，用@override 注解。可以防止手误。

```java
class P {
	void love();
}

@Override // 可以在你编译阶段防止你手残
class C extends P {
	void iove();
}

```

Q6

这个送分题目。

Q7

记住就不会错

```java
@SuppressWarnings("unchecked") // 编译阶段无视检查
@SuppressWarnings("removal") // 非推荐且未来确定会被移除方法采用这个
@SuppressWarnings("deprecation") // 一般非推荐用这个
```

## 9 异常&断言

Q1

考察的多异常捕获技术。

java7 开始推出了多异常捕获技术。

- 捕获多种类型的异常时，多种异常类型之间用竖线`|`隔开
- 捕获多种类型的异常时，异常变量有隐式的 final 修饰，因此程序不能对异常变量重新赋值。

Q2

多个异常捕获必须是**没有继承关系**的，不可以是父子类包裹。

由于 RuntimeException 和 Exception 是有子父类关系的

```java
try {

}
catch( RuntimeException | Exception) {
  ❌
}

```

Q3

考察自定义异常

- 一般地，用户自定义异常类都是 RuntimeException 的子类。
- 自定义异常类通常需要编写几个重载的构造器。
- 自定义异常需要提供 serialVersionUID
- 自定义的异常通过 throw 抛出。
- 自定义异常最重要的是异常类的名字，当异常出现时，可以根据 名字判断异常类型。

体系如下，简言之。所有的错误 error 和异常 exception 都是**Throwable 的子类**。error 基本上是无视掉的，

所以

> **自定义异常的话，都是要继承 exception 的。**

![Exceptionクラス図](https://i0.wp.com/freelance-jak.com/wp-content/uploads/2018/11/ddff3151292065db3f28b48804a10f14.jpg?resize=608%2C551&ssl=1)

Q4

下面的题目到 9 为止都可以读一读这篇文章，写的不错

[【Java】例外構文 3 種類の活用方法解説](https://workteria.forward-soft.co.jp/blog/detail/10226)

普通的考察 try-with-resource 的定义而已。本质就是并不是处理异常的，只是为了关闭资源的。

Q5

这一题考察了特点，有 3 个。

- try 语句中声明的资源被隐式声明为 final，资源的作用局限于带资源的 try 语句。
- 可以在一条 try 语句中声明或初始化多个资源，每个资源以`;`隔开即可。
- 需要关闭的资源必须实现了 **AutoCloseable 或 Closeable** 接口。

Q6

考察的 try-with-resource 特点，就是在 try 里面使用的资源。必须是**不可改变**的，比如说更改资源。

```java
try (A a = new A()) {
		a = new A(); // ❌
}
```

Q7

资源关闭的顺序，是和定义相反的。

```java
A a = new A();
try (a:B b = new B(); C = new C()) {
  // 定义的时候是A → B → C
}
```

但是关闭的顺序是相反的。

Q8

考察在 try-with-resource 里面如果 try 发生的错误，close，catch，finally 的执行顺序

```java
public class Q9 {
    public static void main(String[] args) {
        try (SampleResource sampleResource = new SampleResource();) {
            // 这里发生错误了之后按照close → catch → finally的顺序
            throw new Exception();
        } catch (Exception e) {
            System.out.println("catch");
        } finally {
            System.out.println("finally");
        }
    }
}

class SampleResource implements AutoCloseable {
    @Override
    public void close() throws Exception {
        System.out.println("close");
    }
}
```

Q9

考察的是异常执行

AutoCloseable 抛出 Exception

Closeable 抛出 IOException

```java
public class Q9 {
    public static void main(String[] args) {
        try (TroubleResource troubleResource = new TroubleResource();) {
            throw new Exception();
        } catch (RuntimeException e) {
            System.out.println("A");
        } catch (Exception e) {
            // 一旦catch到了这里 后面就会被无视
            System.out.println("B");
        }
    }
}


// AutoCloseable 抛出的是 Exception 异常
class TroubleResource implements AutoCloseable {
    @Override
    public void close() throws Exception {
        throw new RuntimeException("trouble");
    }
}
```

Q10

考察的断言

语句`assert x >= 0;`即为断言，断言条件`x >= 0`预期为`true`。如果计算结果为`false`，则断言失败，抛出`AssertionError`

Q11

死记硬背就可以了。

```
java -ea assert模块
```

## 10 国际化

可以先读一下这篇文章，写的还不错的。

[Java - Properties 和 ResourceBundle 类学习](https://www.jianshu.com/p/7ab8a900eb7d)
