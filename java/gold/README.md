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

## 2 lambda

学习半天，做题半天。

这个是很难理解的，函数式编程如果学的好的话，这个估计理解的会快一点。

导入看这个[什么是 lambda 表达式？lambda 表达式的应用场景分析](https://www.shouxicto.com/article/824.html)

常用的函数式接口（本质就是 1 个接口只有 1 个抽象方法，1+1）

![Java的函数式接口](https://static001.geekbang.org/infoq/af/af36efa5b914cedb149629c20fed07c2.png)

Q1

死记硬背

Supplier 供给型，是`get()`。只产出。

Q2

考察的就是只进不出，接受 0 参数，返回 1 个结果。同时抽象方法是`get()`

符合这个条件的只有 C

Q3

死记硬背 Consumer 消费性，是`accept()`只消费。

Q4

这一个考察的是 Consumer 的用法，接受 1 个参数，然后没有返回值。符合这个条件的只有 A

Q5

这一题是 BiConsumer，她和 Consumer 比就是参数上的问题。给了 2 个参数。切还是没有返回值。符合这个条件的只有 B

Q6

死记硬背，Predicate，判断的，`test()`

Q7

这一题考察了 Predicate 的 default 的`or()`方法。

用于判定 2 个方法同样的输入是否相同。语法糖一样。

```java
default Predicate<T> or(Predicate<? super T> other) {
  Objects.requireNonNull(other);
  return (t) -> test(t) || other.test(t);
}
```

```java
public class Q7 {
    public static void main(String[] args) {
        Predicate<Integer> p1 = x -> x > 0;
        Predicate<Integer> p2 = x -> x < 0;
        // 原本写法
        System.out.println(p1.test(100) || p2.test(100)); // true
        // 可以简写为
        System.out.println(p1.or(p2).test(100));
    }
}

```

Q8

考察 BiPredicate，其实和 Predicate 一模一样，只是参数多了 1 个而已。返回值是 boolean

```java
boolean test(T t, U u);
```

Q9

死记硬背，Function 对应的是`apply()`

Q10

基本和死记硬背差不多了，方法排除掉几个错误方法。

区别就是

```java
a.compose(b); // 先b
a.andThen(b); // 先a
```

```java
public interface Function<T, R> {

    R apply(T t);

    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    static <T> Function<T, T> identity() {
        return t -> t;
    }
}

```

Q11

输入 T，U，返回 R 类型。

```java
public interface BiFunction<T, U, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    R apply(T t, U u);
}
```

差不多就是这样 ↓

```java
public class Q11 {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, String> test = (a, b) -> Integer.toString(a + b);
        System.out.println(test.apply(10, 6)); // 16
    }
}
```

Q12

死记硬背。继承自 Function，那肯定`apply()`了。

返回一个一元运算符，它总是返回其输入参数。

```java
@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {

    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @param <T> the type of the input and output of the operator
     * @return a unary operator that always returns its input argument
     */
    static <T> UnaryOperator<T> identity() {
        return t -> t;
    }
}

```

Q13

对列表进行统一处理最好的就是 UnaryOperator，使用如下

```java
public class Q13 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        // replaceAll方法这里接受一个
        /**
         * default void replaceAll(UnaryOperator<E> operator) {
         *         Objects.requireNonNull(operator);
         *         final ListIterator<E> li = this.listIterator();
         *         while (li.hasNext()) {
         *             li.set(operator.apply(li.next()));
         *         }
         *     }
         */

        list.replaceAll(x -> x.toUpperCase());
        for (String l : list) {
            System.out.println(l); // A B C
        }
    }
}
```

Q14

考察的 BinaryOperator，继承自 BiFunction，只是参数是 1 个泛型。

```java
public interface BinaryOperator<T> extends BiFunction<T,T,T>
```

使用例子

这里最重要的是看泛型，参数，返回值。

```java
public class Q14 {
    public static void main(String[] args) {
        // 连接字符串
        BinaryOperator<String> b = (str, add) -> str.concat(add);
        System.out.println(b.apply("hello", "chin")); // hellochin
    }
}

// 下面是用一个工厂方法,工厂生产的只是方法体
class OperatorFactory {
    public static BinaryOperator<Integer> add() {
        return (a, b) -> a + b;
    }

    public static BinaryOperator<Integer> minus() {
        return (a, b) -> a - b;
    }
}

class UseOperatorFactory {
    public static void main(String[] args) {
        BinaryOperator<Integer> op = OperatorFactory.add();
        System.out.println(op.apply(1, 9)); // 10

        BinaryOperator<Integer> op2 = OperatorFactory.minus();
        System.out.println(op2.apply(1, 7)); // -6
    }
}
```

## 3 并发

这一章如果要理解的话太难，但实际考察不是那么多。所以建议放弃。

可以直接背诵答案了。

## 4 StreamAPI（2）

## 5 IO（1）

## 6 JDBC （1）

## 7 集合（1）

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

然后就是很基础的东西了，没有什么特别难以理解的。基本上死记硬背足以解决。

Q1

死记硬背型

```java
// 获得此Java虚拟机当前线程默认的语言环境值
Locale locale = Locale.getDefault();
```

Q2

这个也是死记硬背型，创建 locale 对象总共有几种方式

[这个笔记绝了，记得看 Java8 官网笔记](https://zq99299.github.io/java-tutorial/i18n/locale/create.html#locale-构造函数)

创建 `Locale` 对象的四种方法是：

- `Locale.Builder` 类
- `Locale` 构造函数
- `Locale.forLanguageTag` 工厂方法
- `Locale` 常量 / Constants

Q2 考的是构造器，

```java
new Locale("语言", "地区"); // 地区大写的多
```

Q3

这一题考的是 Locale` 常量 创建

答案背下来就好

```java
Locale  locale2 = Locale.US;
System.out.println(locale2); // en_US
```

下面 3 个是等效的

```java
j4Locale = Locale.JAPAN;
j5Locale = new Locale.Builder().setLanguage("ja").setRegion("JP").build();
j6Locale = new Locale("ja", "JP");
```

Q4

`Locale.Builder` 类创建

本质就是一个链式调用，最后一个使用`build()`函数来构建

感觉这个 builder 模式蛮有趣的，写一下吧。其实就是通过内部类给外部类增加属性。

```java
public class Q4 {

    public static void main(String[] args) {
        Item a = new Item.Builder().setName("sample").build();
        System.out.println(a.getName()); // sample
    }
}


class Item {
    // 成员属性（私有）
    private String name;

    // 内部静态类
    public static class Builder {
        // 这里是内部类的属性
        private String name;
        // 走到这里设置上面的Builder的name也就是sample了
        public Builder setName(String name) {
            this.name = name;
            // 这个this是什么呢？
            return this;
        }

        public Item build() {
            if (this.name == null) {
                throw new IllegalStateException();
            }
            // 在内部类里创建了Item对象
            Item item = new Item();
            // 这一步很经典，就是把Builder的name给了Item的name
            // 这样就给Item的name赋值了
            item.name = this.name;
            // 赋值之后返回item，此时item已经有了name了
            return item;
        }
    }

    // 只有一个属性默认就是this.name
    // 也就是成员属性了
    public String getName() {
        return name;
    }
}
```

Q5

工厂方法创建

> 如果您具有符合 IETF BCP 47 标准的语言标签字符串，则可以使用 Java SE 7 发行版中引入的 [`forLanguageTag(String)`](https://docs.oracle.com/javase/8/docs/api/java/util/Locale.html#forLanguageTag-java.lang.String-) 工厂方法。

```java
Locale aLocale = Locale.forLanguageTag("en-US");
Locale bLocale = Locale.forLanguageTag("ja-JP-u-ca-japanese");
```

Q6

从这里开始就是 Properties 的内容了,properties 本质就是一个 HashTable。

书写格式 2 种都可以

- `key:value`
- `key=value`

Q7

既然是 HashTable 就是一个可迭代对象，考察 API 的。不难

Q8

这个考察打印 properties 信息的方法。

最好的是直接用 list 方法对字符串直接打印，选 D

Q9

这个考察了就是`get()`和`getProperty()`的区别，前一个取出来是 Object，后一个是 String，一般打印都是直接输出的 String，所以选 getProperty()。

Q10

这一题就是死记硬背

```
native2ascii input output
```

Q11

死记硬背

- ISO-8895-1
- UTF-8

Q12

记住这一题是一个静态方法`getBundle()`

Q13

这一题其实也是个死记硬背，主要是考察的 ResourceBundle 这个库可以跟随你设置的语言环境进行加载不同的配置文件

Q14

Locale 发生异常使`MissingResourceException`

Q15

`getBundle()`第 2 个参数可以设置**位置**情报

Q16

死记硬背，选一下 A

Q17

货币信息死记硬背选`getCurrencyInstance()`

## 11 模块化

9 开始导入的，在 package 之上多了一个 module，以前没有的时候，就是引入依赖之后包里面的所有都是暴露的，引入 module 之后，就是需要多一个`module-info.java`，只有你暴露出去的包才能被引入

导出

```java
module common（这里是模块的名字） {
  exports 包名
}
```

导入

```java
module 模块名（这里是模块的名字） {
  requires 模块
  uses 具体到哪个包
}
```

open 和 exports

- exports 编译和运行都可以对外提供
- open 只能在运行时期通过**反射**操作来获取当前类，来创建对象等操作（解耦合）

还有一些其他特点，比如 java.lang 这种，都是可以自动被导入的，无需写 requires。

Q1

模块化有很多优点，但是并不能像 private 那样，具备隐蔽性。

优点如下

Q2

关于反射的关键字，那么就是 opens 了，运行时通过反射来获取当前类。

Q3

大概就是说

> Java 9 中引入的模块系统除了类路径之外，还增加了**模块路径**的概念。顾名思义，模块路径就是放置模块的路径，编译器和 JVM 按照模块路径搜索需要的模块。如果要在运行时将任意位置添加到模块路径，请使用 --module-path 选项。

模块系统可以将模块分为三种类型：命名模块、自动模块和匿名模块

- 命名模块
- 自动模块
- 匿名模块

这一题主要考察的是 3 者的关系，大概就是这样的。

[![異なる種類のモジュール間の参照可否](https://news.mynavi.jp/techplus/article/imajava-5/images/001.jpg)](https://news.mynavi.jp/techplus/photo/article/imajava-5/images/001l.jpg)

**命名模块是无法直接去引用匿名模块的。**

具体可以看这篇文章[Java のモジュールシステムを理解しよう](https://news.mynavi.jp/techplus/article/imajava-5/)

Q4

考察的是对于匿名模块的**自底向上** bottom-up 查找规则，从最多被引用的模块开始查找，然后逐渐到命名的。

Q5

考察的是对于匿名模块的**自顶向下** top-down 查找规则，从上面被引用的顺序开始，先变换成自动模块。还有就是，从上面被引用的顺序开始，从命名模块开始转换。

Q6

这一题建议死记硬背吧。考的是 java 命令行。

要运行一个 jar，使用`java -jar xxx.jar`命令。

要运行一个模块 👇🏻

```java
java --module-path hello.jar（jar包） --module hello.world（具体类）
```

Q7

死记硬背，考察的`java-util-ServiceLoader`用来干嘛的

考察的是 SPI 第三方机制，具体可以看这文章，不理解的话直接看这个就好。这一题可以背下来。就是一个类的加载器。

[ServiceLoader 详解](https://www.cnblogs.com/aspirant/p/10616704.html)

Q8

使用第三方 SPI 的接口，要用 uses

Q9

背下来就好，这一题考察的是 jdep 命令行的参数。

jdeps(java dependencies)是 java8 推出一个命令行工具，此工具主要是帮助开发者们列出类依赖及层级关系的信息。

```java
jdeps -jdkinternals // 在 JDK 内部 API 上查找类级别的被依赖对象
jdeps -verbose:class  // 默认情况下输出类级别被依赖对象
jdeps -profile // 显示配置文件或包含程序包的文件
jdeps -apionly // 只是用来限制分析对象是否是public or protected
```

## 12 安全（0.5）

## 总
