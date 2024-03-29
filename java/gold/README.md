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

```java
new Outer().Inner(); // ❌ 没这种 Inner此时是类，如果不是而是方法的话就可以
new Outer.Inner();
```

D 选项仔细看，多了一个`()`

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

这一题幸好没有在 SampleIml 的`test()`加上 overide 的注解，否则是会报错的。

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

- `values()` 返回枚举类中所有的值的数组
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

考察的就是只出不进，接受 0 参数，返回 1 个结果。同时抽象方法是`get()`

符合这个条件的只有 C

Q3

死记硬背 Consumer 消费性，是`accept()`只消费。

Q4

这一个考察的是 Consumer 的用法，接受 1 个参数，然后没有返回值。符合这个条件的只有 A。

B 有了返回值，CD 都有参数。

Q5

这一题是 BiConsumer，她和 Consumer 比就是参数上的问题。给了 2 个参数。且还是没有返回值。符合这个条件的只有 B

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

78 基本和死记硬背差不多了，方法排除掉几个错误方法。

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

## 4 StreamAPI

这一章据说是考察最多的。前 8 题都考察的是新特性 Optional，后面考察的才是 StreamAPI。

Q1

```java
String str = null;
// 要求非空 否则报错
Optional<String> op = Optional.of(str); // NullPointerException
```

Q2

虽然`Optional.ofNullable(null)`可以放 null，但是`get()`会报错的。

```java
public class Q2 {
    public static void main(String[] args) {
        Optional<String> sample = Optional.ofNullable(null);
        System.out.println(sample.get()); // NoSuchElementException
    }
}

```

Q3

`orElse(value)`当里面为 null 的时候才用 value

```java
public class Q3 {
    public static void main(String[] args) {
        Optional<String> sample = Optional.of("A");
        System.out.println(sample.orElse("B")); // A
    }
}

```

Q4

虽然 A 选项的`get()` (NoSuchElementException)也会抛出异常，但任意异常的话还是用`orElseThrow()`

```java
public class Q4 {
    public static void main(String[] args) throws Exception {
        Optional<String> sample = Optional.empty();
        System.out.println(sample.orElseThrow(() -> new Exception()));
    }
}

```

Q5

- boolean isPresent() : 判断是否包含对象
- void ifPresent(Consumer<? super T> consumer) :如果有值，就执行 Consumer 接口的实现代码，并且该值会作为参数传给它

```java
public class Q5 {
    public static void main(String[] args) {

        Optional<String> sample = Optional.of("test");
        sample.ifPresent((str) -> System.out.println(str)); // test

        // 只要有值才能发生，如果没有值，压根不会报错。下面的代码也压根不会执行
        Optional<String> sampleNull = Optional.empty();
        sampleNull.ifPresent((str) -> System.out.println(str));

    }
}
```

Q6

就简单考察 API 记忆能力

`ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)`
如果存在值，则使用值执行给定操作，否则执行给定的基于空的操作。

直接就是 A

```java
public class Q6 {
    public static void main(String[] args) {
        Optional<String> sample = Optional.empty();
        Optional<String> sample2 = Optional.of("hello world");
        sample.ifPresentOrElse((str) -> System.out.println(str), () -> System.out.println("null的话就执行我")); // null的话就执行我
        sample2.ifPresentOrElse((str) -> System.out.println(str), () -> System.out.println("null的话就执行我")); // 2
    }
}
```

Q7

特别 easy

```java
public class Q7 {
    public static void main(String[] args) {
        Optional<String> sample = Optional.of("abcde");
        Optional<String> result = sample.map(str -> str.toUpperCase());

        System.out.println(sample.get()); // abcde
        System.out.println(result.get()); // ABCDE
    }
}
```

Q8

这一题有点意思。主要是 map 的返回值问题，因为 map 里面调用的方法又是一个 optional，所以需要有包裹。解决方法，要么就是包裹一层。要么就是用`flatMap()`

```
<R> Stream<R>
map(Function<? super T,? extends R> mapper)
```

```java
public class Q8 {
    public static void main(String[] args) {
        Optional<Integer> a = Optional.of(100);
        // Optional<Integer> b = a.map(price -> calc(price, 3));
        // 这里的返回值问题，①调用calc的时候返回的其实是一个Optional<Integer>，
        // ②但是map里面又返回了一个Optional 所以会有包裹
        Optional<Optional<Integer>> b = a.map(price -> calc(price, 3));
        System.out.println(b.get());

        System.out.println("******解决方法*******");
        Optional<Integer> b2 = a.flatMap(price -> calc(price, 3));
        System.out.println(b2.get());
    }

    private static Optional<Integer> calc(int price, int qty) {
        if (qty < 0) {
            return Optional.empty();
        }
        return Optional.of(price * qty);
    }
}

```

Q9

单纯的考察 API 而已。Java9 新增了一个只读集合 of()

- 只读
- 不能添加 null
- 不能修改顺序
- 相同的 list，通过 equal 比较是

```java
public class Q9 {
    public static void main(String[] args) {

        // 不可变的
        List<Integer> integers = List.of(1, 2, 3);
        // integers.add(6); ❌ // ImmutableCollections

        List.of(1,2,3).forEach(x -> System.out.println(x)); // 遍历
        List.of(1,2,3).forEach(System.out::println); // 另一种写法
    }
}
```

Q10

概念理解题。选出不符合题意的。

关于为什么要用 stream 错误的就是。不能执行 for 语句这样的 break，continue 的关键字。主要也不是为了遍历的，而是为了处理数据的。

Q11

考察 array 转换成 stream，转换成 stream，有很多方式。

```java
public class Q11 {
    public static void main(String[] args) {
        // 方式1创建方法 通过集合
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        arrayList.stream(); // 1 创建一个顺序流（按照顺序来）
        arrayList.parallelStream(); // 1 创建一个并行流

        // 方式2 通过数组，调用 Arrays 的静态方法 stream
        int[] arr = new int[]{1, 2, 3};
        IntStream stream = Arrays.stream(arr);

        // 方式3 Stream的方法
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);

        // 方式4 创建无限流,0开始，会0,2,4,5...
        Stream.iterate(0, t -> t + 2); // 因为是无限的
    }
}

```

Q12

概念理解，关于 stream 的顺序问题。根据的不是追加顺序，而是根据你数据的特性进行的。

```java
public class Q12 {
    public static void main(String[] args) {
        // 按照顺序
        List<String> list = Arrays.asList("A", "B", "C", "D", "E");
        Stream<String> stream = list.stream();
        stream.forEach(x -> System.out.println(x)); // A B C D E

        // 按照特性
        Set<String> set = new HashSet<>();
        set.add("E");
        set.add("D");
        set.add("C");
        set.add("B");
        set.add("A");
        Stream<String> stream2 = set.stream();
        stream2.forEach(System.out::println); // A B C D E
    }
}
```

Q13

创建 stream 流的方式。

> Java8 中的 Collection 接口被扩展，提供了两个获取流 的方法:
> default Stream<E> stream() : 返回一个顺序流
> default Stream<E> parallelStream() : 返回一个并行流

```java
public class Q13 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A", "B", "C");
        List<String> list2 = Arrays.asList("A", "B", "C");

        Stream<String> stream = list.stream(); // 顺序流
        Stream<String> stream1 = list2.parallelStream();// 并行流
    }
}
```

Q14

AB 用`filter()`是对的，CD`map()`返回的又是一个流。肯定不行。

```java
public class Q14 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        list.stream().filter(x -> x > 5).forEach(System.out::println);
    }
}

```

Q15

考察 API

```java
public class Q15 {
    public static void main(String[] args) {
        // 这一题我都不想写，意思就是说distinct是要看equal的，但是这里全是true，判断全都是一个
        List<Value> list = Arrays.asList(
                new Value("A"),
                new Value("B"),
                new Value("C"),
                new Value("A")
        );

        long count = list.stream().distinct().count();
        System.out.println(count); //

    }
}

class Value {
    private String data;

    public Value(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int hashCode() {
        return 100;
    }
}
```

Q16

概念理解，就是说`stream()`还是`parallelStream()`他们的区别

`findFisrt()`

- 没区别都是返回第一个

`findAny()`

- `stream()` 每次都是返回任一 （同一个）
- `parallelStream()` 每次不保证是同一个

```java
public class Q16 {
    public static void main(String[] args) {
        // 顺序流
        String[] array = {"A", "B", "C"};
        Stream<String> stream = Arrays.stream(array);
        Optional<String> res1 = stream.findAny();
        res1.ifPresent(System.out::println); // A


        String[] array2 = {"A", "B", "C"};
        Stream<String> stream2 = Arrays.stream(array2);
        Optional<String> res2 = stream2.findFirst();
        res2.ifPresent(System.out::println); // A

        // 并行流
        List<Integer> integers = List.of(1, 2, 3);
        integers.parallelStream().findAny().ifPresent(System.out::println);

        List<Integer> integers2 = List.of(1, 2, 3);
        integers2.parallelStream().findFirst().ifPresent(System.out::println);


    }
}

```

> 题意说的都是**处理**的第一个，事实上不是的。返回的是符合条件的，而不是处理的第一个。

Q17

单纯考察`sorted()`的 API

```java
public class Q17 {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(4, 5, 3, 2, 1);
        integers.stream().sorted((a, b) -> {
            if (a < b) return -1;
            if (b < a) return 1;
            return 0;
        }).forEach(System.out::println);

        // 和题目无关，这一题可以直接转换成
        List<Integer> integers2 = Arrays.asList(4, 5, 3, 2, 1);
        integers2.stream().sorted((a, b) -> {
            return a - b;
        }).forEach(System.out::println);

        // 进一步进化
        List<Integer> integers3 = Arrays.asList(4, 5, 3, 2, 1);
        integers3.stream().sorted(Comparator.comparingInt(a -> a)).forEach(System.out::println);

    }
}
```

Q18

其实也就是变量考察 API

map 返回值的泛型，其实就是 map 里面函数的返回值

```java
public class Q18 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        // String::valueOf返回是String类型，所以这里也要返回这个
        Stream<String> res = list.stream().map(String::valueOf);
        System.out.println(res);
    }
}
```

Q19

也是考察的 API，`reduce()`返回类型。

```java
public class Q19 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        // reduce第一个参数是初始值，初始值是啥类型，返回值就是啥类型
        Integer res = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(res); // 15
    }
}
```

> 补充一下，上面是因为有初始值了才这样。如果没有了 0 这个初始值那是什么返回类型呢？这个就是 Optional\<T>

Q20

考察 API，找最大值 max

```java
public class Q21 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("B,", "A", "C", "D");
        Optional<String> res = list.stream().max(String::compareTo);
        res.ifPresent(System.out::println); // D
    }
}

```

Q21

可以先看这篇文章。[Java 基础系列-Collector 和 Collectors](https://www.cnblogs.com/V1haoge/p/10748925.html)

Q22

考察方法

```java
public interface Collector<T, A, R> {
    // supplier参数用于生成结果容器，容器类型为A
    Supplier<A> supplier();
    // accumulator用于消费元素，也就是归纳元素，这里的T就是元素，它会将流中的元素一个一个与结果容器A发生操作
    BiConsumer<A, T> accumulator();
    // combiner用于两个两个合并并行执行的线程的执行结果，将其合并为一个最终结果A
    BinaryOperator<A> combiner();
    // finisher用于将之前整合完的结果R转换成为A
    Function<A, R> finisher();
    // characteristics表示当前Collector的特征值，这是个不可变Set
    Set<Characteristics> characteristics();
}
```

真正的操作，就写在`accumulator()`里

Q23

就是考察的 API 的用法。

```java
public class Q23 {
    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> res = list.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        res.forEach(System.out::println);
    }
}
```

Q24

这一题的题目给的代码明显有问题。少了 Employee 这个类。

但是大概率靠什么应该是知道的。就是考察`groupingBy()`的返回值。是一个 Map，key 是指定的 lambda 返回值。value 是一个 list 数组。

```java
public class Q24 {
    public static void main(String[] args) {
        Department tokyo = new Department("tokyo");
        Department osaka = new Department("osaka");
        Department nagoya = new Department("nagoya");
        Department hokaidou = new Department("hokaidou");
        List<Department> list = List.of(tokyo, osaka, nagoya, hokaidou);
        // 这一题就是考察的groupBy的返回值，返回一个Map
        // key是你在groupingBy指定的函数的返回值，value是结果
        Map<String, List<Department>> res = list.stream().collect(Collectors.groupingBy(Department::getName));
        System.out.println(res);
    }

}

class Department {
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Department) {
            Department other = (Department) o;
            return this.name.equals(other.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

```

Q25

本质就是考察 API，计算平均价格。

```java
public class Q25 {
    public static void main(String[] args) {
        Map<String, IntSummaryStatistics> map = Arrays.asList(new Book("A", 1000), new Book("B", 2000), new Book("A", 500))
                .stream()
                .collect(Collectors.groupingBy(
                        Book::getTitle,
                        Collectors.summarizingInt(Book::getPrice)
                ));
        map.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });

    }
}

class Book {
    private String title;
    private int price;

    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }
}

```

Q26

这一题也是考察 API，考的是`partitioningBy()`

> 该方法将流中的元素按照给定的校验规则的结果分为两个部分，放到一个 map 中返回，map 的键是 Boolean 类型，值为元素的列表 List。

这一题只打印的是 key。

```java
public class Q26 {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("banana", "apple", "orange");
        Stream<String> stream = list.stream();
        Set<Boolean> booleans = stream.collect(Collectors.partitioningBy(str -> str.length() > 5)).keySet();
        booleans.forEach(System.out::println); // false,true

    }
}

```

Q27

考察 API 的`peek()`，还有执行结果。

要记住是 1 个个执行的。

```java
public class Q27 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("banana", "apple", "orange");
        Stream<String> stream = list.stream();
        stream.filter(str -> str.length() > 5)
                // 这是一个debug方法，会输出到控制台
                .peek(str -> System.out.println(str + " "))
                // 这里先变为大写
                .map(str -> str.toUpperCase(Locale.ROOT))
                // 再次打印
                .peek(str -> System.out.println(str + " "))
                // 输出
                .forEach(System.out::println); // 所以最后的结果就是单一的单词，(banana)小大大,(orange)小大大,
    }
}

```

Q28

这个很 easy，其实就是流的话。终止操作只能执行 1 次，`forEach()`只能执行一次，否则就会报异常。

```java
public class Q28 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("banana", "apple", "orange");
        Stream<String> stream = list.stream();
        System.out.println(stream.count());
        stream.forEach(System.out::println); // ❌ IllegalStateException
    }
}

```

## 5 IO

这一章节考察死记硬背比较多。

Q1

就是考察 File 这个类主要是干什么的。具体就是只是表达一种抽象的概念，并不是实际操作文件和文件夹的。

```java
/**
 * 1. File类的一个对象，代表一个文件或一个文件目录(俗称：文件夹)
 * 2. File类声明在java.io包下
 * 3. File类中涉及到关于文件或文件目录的创建、删除、重命名、修改时间、文件大小等方法，
 * 并未涉及到写入或读取文件内容的操作。如果需要读取或写入文件内容，必须使用IO流来完成。
 * 4. 后续File类的对象常会作为参数传递到流的构造器中，指明读取或写入的"终点".
 *
 *
 * 我感觉就是这个类只能处理文件和文件夹，相当于cd和mkdir和touch，顶多在stat一下。
 * 但是对于写入写出这些流的操作，这个类是不可以的。
 */
```

Q2

就是考察 File 的 API 而已，想获取 File 类型那么肯定就是`listFiles()`

```java
File f2 = new File("/Users/chin/coolcode/examinations-load/java/gold");
String[] list = f2.list();
// 注意看类型
for (String l : list) {
  System.out.println(l);
}

File[] files = f2.listFiles();
// 注意看类型
for (File f : files) {
  System.out.println(f);
}
```

Q3

接下来都是考察几个 IO 流的区别。

可以看一下这篇文章[两张图总结 Java 中的 IO 流分类](https://ca3tie1.github.io/post/java-io-tips/)

![两张图总结Java中的IO流分类](https://ca3tie1.github.io/post-images/java-io-tips.jfif)

这一题文的是对于文本的处理，对于文本，也就是字符流。说到字符就是**Reader/Writer**。

![image-20220815170437432](https://raw.githubusercontent.com/chihokyo/image_host/develop/image-20220815170437432.png)

Q4

这一题考察的是处理图片音频这种字节流的 API，同时又写了更有效率。

首先考虑缓冲。又是读取。所以就是**BufferedInputStream**。

Q5

这一题考察的是 FileReader API 的用法。

```
public FileWriter (String fileName,
                  Charset charset,
                  boolean append)
           throws IOException
构造一个FileWriter给定一个文件名， charset和一个布尔值，指示是否附加写入的数据。
参数
fileName - 要写入的文件的名称
charset - charset
append - 布尔值。 如果是true ，则编写器会将数据写入文件的末尾而不是开头。
异常
IOException - 如果指定的文件存在但是是目录而不是常规文件，则不存在但无法创建，或者由于任何其他原因无法打开
从以下版本开始：
11
```

Q6

关于 java.io.BufferedWriter 的说法。

`write()`只是写入缓冲区， 手动将 buffer 中内容写入文件，用`flush()`

Q7

死记硬背吧，readAllBytes 一次性读写全部。

Q8

返回 char 数组

```java
// java.lang.Object
//		java.io.Console
public char[] readPassword()
```

Q9

就是考察的流，普通的代码输出。

```java
public class Q9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner("A,B,C,D,E");
        scanner.useDelimiter(",");
        try (scanner) {
            scanner.next();
            scanner.next();
            scanner.next();
            scanner.next();
            System.out.println(scanner.next()); // E
        }
    }
}
```

Q10

考察`public interface Serializable` 这个接口什么抽象方法都没有。只是表达可以被序列化。

![image-20220817142637594](https://raw.githubusercontent.com/chihokyo/image_host/develop/image-20220817142637594.png)

Q11

写入序列化`writeObject()`

读取序列化`readObject()`

Q12

nio2 可以简单理解成多了 3 大 API

- java.nio.file.Paths
- java.nio.file.Path
- java.nio.file.Files

这一题就是选择错误的，其实根本没有`Files.get()`这个方法。

Q13

考察 API

当有的文件已经存在的时候，`createFile()`会出现 **FileAlreadyExistsException** - If a file of that name already exists (optional specific exception)

```java
public static Path createFile(Path path,
 FileAttribute<?>... attrs)
                       throws IOException
```

Q14

```java
public class Q14 {
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("a", "b");
        Path file = dir.resolve(Paths.get("data.txt"));
        // 相当于在指定文件夹下创建一个文件 a/b/data.txt
        Files.createFile(file);
    }
}

```

Q15

就是考察 API 记忆，拷贝使用`copy()`，移动`move()`

Q16

死记硬背类型，直接查看这个就好[Enum StandardOpenOption API](https://www.apiref.com/java11-zh/java.base/java/nio/file/StandardOpenOption.html)

| Enum Constant       | 描述                                                                                                                                                       |
| :------------------ | :--------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `APPEND` ⭐️        | 如果为 [`WRITE`](https://www.apiref.com/java11-zh/java.base/java/nio/file/StandardOpenOption.html#WRITE)访问打开文件，则字节将写入文件末尾而不是开头。     |
| `CREATE`            | 如果它不存在，请创建一个新文件。                                                                                                                           |
| `CREATE_NEW`        | 如果文件已存在，则创建新文件失败。                                                                                                                         |
| `DELETE_ON_CLOSE`   | 关闭时删除。                                                                                                                                               |
| `DSYNC`             | 要求将文件内容的每次更新同步写入底层存储设备。                                                                                                             |
| `READ`              | 打开以进行读取访问。                                                                                                                                       |
| `SPARSE`            | 稀疏文件。                                                                                                                                                 |
| `SYNC`              | 要求将文件内容或元数据的每个更新同步写入底层存储设备。                                                                                                     |
| `TRUNCATE_EXISTING` | 如果该文件已存在且已打开以进行 [`WRITE`](https://www.apiref.com/java11-zh/java.base/java/nio/file/StandardOpenOption.html#WRITE)访问，则其长度将截断为 0。 |
| `WRITE`             | 打开以进行写访问。                                                                                                                                         |

Q17

递归遍历文件夹，请用`find()`可以接收参数指定深度。

```java
public static Stream<Path> find(Path start,
                                    int maxDepth,
                                    BiPredicate<Path, BasicFileAttributes> matcher,
                                    FileVisitOption... options)
            throws IOException
```

Q18

也是 API 死记硬背

- 进入文件夹
- 从文件夹出去
- 访问文件
- 无法访问文件

没有处理完这一过程的 API。

| 变量和类型        | 方法                                                  | 描述                                           |
| :---------------- | :---------------------------------------------------- | :--------------------------------------------- |
| `FileVisitResult` | `postVisitDirectory(T dir, IOException exc)`          | 在目录中的条目及其所有后代访问后，为目录调用。 |
| `FileVisitResult` | `preVisitDirectory(T dir, BasicFileAttributes attrs)` | 在访问目录中的条目之前为目录调用。             |
| `FileVisitResult` | `visitFile(T file, BasicFileAttributes attrs)`        | 为目录中的文件调用。                           |
| `FileVisitResult` | `visitFileFailed(T file, IOException exc)`            | 为无法访问的文件调用。                         |

## 6 JDBC

这一章就是连接数据库的具体操作。

> JDBC(Java Database Connectivity)是一个独立于特定数据库管理系统、通用的 SQL 数据库存取和操作的公共接口(一组 API)，定义了用来访问数据库的标准 Java 类库，(java.sql,javax.sql)使用这些类库可以以一种标准的方法、方便地访问数据库资源。

一些重要的概念，接下来做题会用到。

- 在程序中不需要直接去访问实现了 Driver 接口的类，而是由驱动程序管理器类(java.sql.DriverManager)去调用

  这些 Driver 实现

- **jdbc:mysql://localhost:3306/test** → （协议:子协议://地址）

- 在 java.sql 包中有 3 个接口分别定义了对数据库的调用的不同方式:

  - **Statement** :用于执行静态 SQL 语句并返回它所生成结果的对象。
  - **PrepatedStatement** :SQL 语句被预编译并存储在此对象中，可以使用此对象多次高效地执行该语句。
  - **CallableStatement** :用于执行 SQL 存储过程

Q1

基础概念，

A 不只是操作数据，还有一些其他 API。C 他只是提供一些和其他数据库交互的驱动，是基础。并不直接提供类。D 不是特定的数据库，而是一种通用的。

Q2

URL 死记硬背

Q3

具体操作的类

```
DriverManager → getConnection()
Driver → connec()
```

Q4

关闭资源用的 Connection 里的`close()`

Q5

这一题考察不同接口数据库的调用

不用传递参数的肯定是 Statetment

Q6

直接考察 API 是啥。`ps = conn.prepareStatement(sql语句);`

```java
	//通用的增、删、改操作(体现一:增、删、改 ; 体现二:针对于不同的表)
public void update(String sql,Object ... args){
    Connection conn = null;
    PreparedStatement ps = null;
    try {
				//1.获取数据库的连接
				conn = JDBCUtils.getConnection();
				//2.获取PreparedStatement的实例 (或:预编译sql语句)
				ps = conn.prepareStatement(sql);
				//3.填充占位符
				for(int i = 0;i < args.length;i++){
            ps.setObject(i + 1, args[i]);
        }
				//4.执行sql语句
        ps.execute();
    } catch (Exception e) {
        e.printStackTrace();
    }finally{
} }
```

Q7

Java 与数据库交互涉及到的相关 Java API 中的索引都从 1 开始。相当于设置每一个表的哪一列。

Q8

根本没填充占位符，肯定错误的。

需要填充站位这个步骤。

Q9

`executeUpate()`方法可以查询成功更新了多少条数据。

- `executeQuery()` 查询操作 返回查询结果
- `execute()` 增删改语句操作 返回 boolean 更新结果
- `executeBatch()` 批量操作用

Q10

是不需要参数的，不接受语句的。`prepareSatement()`里才写语句。

```
int executeUpdate() throws SQLException
```

Q11

关于查询操作，`executeQuery()`查询之后得到的是一个 ResulteSet 的结果集。如果需要具体继续操作，需要通过`next()`进行,通过这一步才可以具体拿到哪一列的值。

Q12

关于`execute()`语句的问题，虽然说增删改可以用`executeUpate()`，查询可以用`executeQuery()` 。但是`execute()`相当于两者的结合体。

当你不知道是查询还是增删改的时候可以用，因为当他是查询的时候返回的是 true，其他操作都是 false。

```java
boolean execute()
         throws SQLException
Executes the SQL statement in this PreparedStatement object, which may be any kind of SQL statement. Some prepared statements return multiple results; the execute method handles these complex statements as well as the simpler form of statements handled by the methods executeQuery and executeUpdate.
The execute method returns a boolean to indicate the form of the first result. You must call either the method getResultSet or getUpdateCount to retrieve the result; you must call getMoreResults to move to any subsequent result(s).

Returns:
true if the first result is a ResultSet object; false if the first result is an update count or there is no result
```

Q13

记忆 API 用的，批量操作用 Statement 里面的`executeBatch()`。

Q14

这也是记忆 API，

```java
CallableStatement proc = connection.prepareCall("{ call test_proc_mulresultset(?)}")
 // 调用存储过程的sql语句。
 // test_proc_mulresultset这个是存储过程的名称。这个sql是可以在数据库中执行的。你可以传入参数和获得还回结果等。
```

## 7 集合与常用类

Q1

这一题考的是包装类。

| 基本类型 | 对应的引用类型          |
| :------- | :---------------------- |
| boolean  | java.lang.Boolean       |
| byte     | java.lang.Byte          |
| short    | java.lang.Short         |
| int      | java.lang.**Integer**   |
| long     | java.lang.Long          |
| float    | java.lang.Float         |
| double   | java.lang.Double        |
| char     | java.lang.**Character** |

注意有俩很特殊，不是首字母大小就 OK 的。

Q2

考察不指定泛型默认就是 Object

> 泛型如果不指定，将被擦除，泛型对应的类型均按照 Object 处理，但不等价 于 Object。经验:泛型要使用一路都用。要不用，一路都不要用。

```java
public class Q2 {
    public static void main(String[] args) {
        Value v = new Value("Hello");
        Object val = v.getVal();
        // 所有的不确定泛型都可以写成Object，准没错。
    }
}

class Value<T> {
    T val;

    public Value(T val) {
        super();
        this.val = val;
    }

    public T getVal() {
        return val;
    }
}

```

Q3

考察泛型可以用在哪里

- 变量赋值
- 方法的参数
- 方法的返回值

```java
public class Q3 {
    public static void main(String[] args) {
        // 变量代入
        List<String> list = new ArrayList<>();
        // 参数
        execute(new ArrayList<>());
    }

    private static List<String> test() {
        // 返回值
        return new ArrayList<>();
    }

    private static void execute(List<String> list) {

    }
}
```

Q4

考察泛型在继承上的体现。

> 如果 B 是 A 的一个子类型(子类或者子接口)，而 G 是具有泛型声明的
>
> 类或接口，G<B>并不是 G<A>的子类型! 比如:String 是 Object 的子类，但是 List<String >并不是 List<Object>
>
> 的子类

```java
public class Q4 {
    public static void main(String[] args) {
        // A和B有继承关系，但是泛型是不适用的
        // ❌ Item<A> a = new Item<B>();
        // ❌ Item<Object> a = new Item<A>();
        Item<A> a = new Item<A>(); // ✅
    }
}

class A {

}

class B extends A {

}


class Item<T> {

}
```

Q5

考察有限制的通配符

- 类型通配符 `List<?>`
- `<? extends Number> (无穷小 , Number]` → 只允许泛型为 Number 及 Number 子类的引用调用 就是
- `<? super Number> [Number , 无穷大)` → 只允许泛型为 Number 及 Number 父类的引用调用
- `<? extends Comparable>` → 只允许泛型为实现 Comparable 接口的实现类的引用调用

这个太简单。

Q6

这个也太简单了。

Q7

这一题考察的是通配符使用注意

- **读取**List<?>的对象 list 中的元素时，永远是安全的，因为不管 list 的真实类型 是什么，它包含的都是 Object。
- **写入**list 中的元素时，不行。因为我们不知道 c 的元素类型，我们不能向其中添加对象。null 除外

```java
public class Q7 {
    public static void main(String[] args) {
        List<?> list = null;
        list.add(null); // 只被允许添加null，其他都不可以
        // list.add("AA"); ❌
        Object o = list.get(0); // ？默认的返回值类型都是Object
    }

}
```

Q8

其实和上一题差不多，在有？的情况下，除了 null 都不能添加。

```java
public class Q8 {
    public static void main(String[] args) {
        List<? extends Number> list = new ArrayList<Integer>();
        // list.add(1); 除了add(null) 其他都无法添加
        // list.add(2);
    }
}
```

Q9

这一题很 easy

<? super B> 意思就是要比B和B大的类才行

Q10

考察Collection和Map的体系。

![Java Map Collection Tutorial and Examples](https://www.codejava.net/images/articles/javacore/collections/collections%20framework%20overview.png)

反正这一题的答案是List，考察有顺序的列表。

Q11

考察Queue，先进先出。FIFO

Q12

考察Deque，双端队列。

Q13

考察Set的特点。就是无序的，唯一的。

> |----Set接口：存储无序的、不可重复的数据   -->高中讲的“集合”
>  * |----HashSet：作为Set接口的主要实现类；线程不安全的；可以存储null值
>
>    * |----LinkedHashSet：作为HashSet的子类；遍历其内部数据时，可以按照添加的顺序遍历
>
>      ​		对于频繁的遍历操作，LinkedHashSet效率高于HashSet.
>
>  * |----TreeSet：可以按照添加对象的指定属性，进行**排序**。

Q14

考察Map的特点。

简单理解成键值对。

> |----Map:双列数据，存储key-value对的数据   ---类似于高中的函数：y = f(x)
>  *         |----HashMap:作为Map的主要实现类；线程不安全的，效率高；存储null的key和value
>            *         |----LinkedHashMap:保证在遍历map元素时，可以按照添加的顺序实现遍历。
>                      *         原因：在原有的HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素。
>                      *         对于频繁的遍历操作，此类执行效率高于HashMap。
>  *         |----TreeMap:保证按照添加的key-value对进行排序，实现排序遍历。此时考虑key的自然排序或定制排序
>            *         底层使用红黑树
>  *         |----Hashtable:作为古老的实现类；线程安全的，效率低；不能存储null的key和value
>            *         |----Properties:常用来处理配置文件。key和value都是String类型

Q15

考察API而已。

```java
public class Q15 {
    public static void main(String[] args) {
        var a = Map.entry(1, "A");
        var b = Map.entry(2, "B");
        var c = Map.entry(3, "C");
        Map<Integer, String> map = Map.ofEntries(a, b, c);
        // Set<Map.Entry<Integer, String>> entries = map.entrySet(); 这是idea给我生成的，本质一样
        // 只是把Map换成了Set，更具体了而已
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
```

Q16

这个就是考察Comparable的`compareTo()`

> Comparable接口的使用举例：  自然排序
>     1.像String、包装类等实现了Comparable接口，重写了compareTo(obj)方法，给出了比较两个对象大小的方式。
>     2.像String、包装类重写compareTo()方法以后，进行了从小到大的排列
>     3. 重写compareTo(obj)的规则：
>         如果当前对象this大于形参对象obj，则返回正整数，
>         如果当前对象this小于形参对象obj，则返回负整数，
>         如果当前对象this等于形参对象obj，则返回零。
>         4. 对于自定义类来说，如果需要排序，我们可以让自定义类实现Comparable接口，重写compareTo(obj)方法。
>        在compareTo(obj)方法中指明如何排序

默认是从小到大排序的。大于返回正，小于返回负数，等于返回0

```java
class Goods implements Comparable {
    private String name;
    private double price;

    public Goods() {
    }

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Goods) {
            Goods goods = (Goods) o;
            if (this.price > goods.price) {
                return 1;
            } else if (this.price < goods.price) {
                return -1;
            } else {
                return 0;
            }
        }
        throw new RuntimeException("传入数据类型不一致");
    }
}
```

Q17

这个就是考察Comparator的`compare()`方法

> Comparator接口的使用：定制排序
>     1.背景：
>     当元素的类型没有实现java.lang.Comparable接口而又不方便修改代码，
>     或者实现了java.lang.Comparable接口的排序规则不适合当前的操作，
>     那么可以考虑使用 Comparator 的对象来排序
>     2.重写compare(Object o1,Object o2)方法，比较o1和o2的大小：
>     如果方法返回正整数，则表示o1大于o2；
>     如果返回0，表示相等；
>     返回负整数，表示o1小于o2。

```java
public class Q17 {
    public static void main(String[] args) {
        String[] arr = new String[]{"AA", "CC", "KK", "MM", "GG", "JJ", "DD"};
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1 instanceof String && o2 instanceof String) {
                    String s1 = (String) o1;
                    String s2 = (String) o2;
                    // 直接翻转一下就好
                    return -s1.compareTo(s2);
                }
                return 0;
            }
        });
        System.out.println(Arrays.toString(arr));

        // 用lambda写
        Arrays.sort(arr, (o1, o2) -> {
            if (o1 instanceof String && o2 instanceof String) {
                String s1 = (String) o1;
                String s2 = (String) o2;
                // 直接翻转一下就好
                return -s1.compareTo(s2);
            }
            return 0;
        });
        System.out.println(Arrays.toString(arr));
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

考察的是异常执行。还有异常处理会隐蔽的特性。

AutoCloseable 抛出 Exception

Closeable 抛出 IOException

```java
public class Q9 {
    public static void main(String[] args) {
        try (TroubleResource troubleResource = new TroubleResource();) {
          // 1 try这里发生了异常，会先去用close进行关闭。
            throw new Exception();// 3 然后close异常被无视之后，来到try的catch。仔细看这里是Exception异常
            System.out.println("A");
        } catch (Exception e) {
            // 4 上面是Exception，输出B
          
            System.out.println("B");
        }
    }
}


// AutoCloseable 抛出的是 Exception 异常
class TroubleResource implements AutoCloseable {
    @Override
    public void close() throws Exception {
      // 但是发现close这里也出现了异常，但是会被屏蔽
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

考察的是对于匿名模块的**自顶向下** top-down 查找规则

- 1 从上面被引用的顺序开始，先变换成命名模块。
- 2 还有就是从依赖的开始，先转换成自动模块。

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

## 12 安全

这一章全是死记硬背，都是理论。

## 总（1）

### 1~40

Q1

考察IO流的，这个是考察的`System.in` ，目前会显示的待输入。

```java
public class Q1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("in : ");
            String input = br.readLine();
            System.out.println("out : " + input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

```

Q2

同样考察的也是IO，nio

```java
public class Q2 {
    public static void main(String[] args) throws Exception {
        String fileName = "";
        Stream<String> lines = Files.lines(Paths.get(fileName));
        // 题目给的是这种 类型推断
        var a = Files.lines(Paths.get(fileName));
    }
}

```

Q3

这一题，死记硬背吧。看不懂。

Q4

考察lambda的forEach，不会返回任何值。

Q5

考察StreamAPI的。

```java
public class Q5 {
    public static void main(String[] args) {
        List<Item> list = List.of(
                new Item("apple", 100),
                new Item("banana", 80),
                new Item("orange", 120)
        );

        double result = list.stream().filter(e -> e.getName().equals("apple"))
                .mapToInt(Item::getPrice)
                .average()
                .getAsDouble();
        System.out.println(result); // 100.00
    }
}

```

Q6

这一题也是考察API的。主要是comparable和comparator的区别。

Comparable - `o1.compareTo(o2)` 1个参数

Comparator - `compare(o1,o2)` 2个参数

这一题主要难点在于，compare参数在没有泛型的情况下。默认是Object，并且返回值一定要是一个int。

A返回的是布尔。B没有使用泛型，用的String。D虽然用了泛型，但是实现方法错误，不该用`compareTo()`

```java
public class Q6 {
    public static void main(String[] args) {
        // 匿名实现类
        // 1 这里用泛型，下面就可以不用Object
        new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 2 这里返回String默认已经实现的，返回值正好是一个int
                return o1.compareTo(o2);
            }
        };
    }
}
```

Q7

考察的类型提升。

```
List<Integer> list = List.of(0,1,2,3,4);
```

如果这个时候想获取里面的数字

```java
❌ Double a = list.get(0); //这是不可以的，默认是返回Integer类型。你现在不可以转换成另一个类。 Integer不可以转换成Double。
double a = list.get(0); // 这个是可以的，相当于Integer → int → 提升double
```

Q8

接口的默认方法，和抽象类的方法重名情况。

这一题建议配合第一章的，12,13题一起看。尤其是13题，一定要去看。

```java
interface Test {
    public default void execute(String str) {
        System.out.println("A");
    }
}

abstract class AbstractClass {
    private void execute(String str) {
        System.out.println("B");
    }
}
// 这里实现了接口继承了抽象类。
// 编译是不会报错的，因为编译器把上面2个接口和类当成各自独立的方法
// 并且编译时期，是类优先于接口！！所以会使用
// 但是执行会出错，因为这里并没有说明你要调用哪个方法。一定要说明。
class Sample extends AbstractClass implements Test {
    public static void main(String[] args) {
        new Sample().execute("hello"); // IllegalAccessError
    }
}
```

Q9

简单。

Q10

这个第一要结合第9章的第9题一起看。

Q11

纯粹记忆的题目。

Q12

考察多线程的，建议死记硬背跳过。

Q13

这一个是考察日期格式的。

```java
public class Q13 {
    public static void main(String[] args) {
        Locale l = new Locale("en", "US");
        LocalDate today = LocalDate.of(2021, 4, 1);
        String mToday = today.format(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(l)
        );

        String sToday = today.format(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(l)
        );
        System.out.println(mToday); // Apr 1, 2021
        System.out.println(sToday); // 4/1/21
    }
}
```

Q14

死记硬背类型。

Q15

死记硬背类型。

Q16

考察Stream，此处写个代码就行。没什么难以理解的。

Q17

考察Stream，主要是关于stream的返回值。

缺少代码。主要是2种方案都可以。

Q18

考察password在`System.console`的时候不显示输入信息。

Q19

看清楚题目。主要来考察的是HashSet和LinkedList的区别。

Set是自动去重的。

Q20

考察Runnable的lambda表示，是没参数和返回值的。

Q21

考察在stream没有一个符合条件的时候，是没有任何反应的。

```java
public class Q21 {
    public static void main(String[] args) {
        var list = List.of("apple", "banana", "orange", "melon");
        Optional<String> res = list.stream().filter(x -> x.contains("x")).reduce((i, j) -> i + "," + j);
        res.ifPresent(System.out::println); // 没有符合条件的 就什么都不会发生
    }
}
```



Q22

死记硬背

Q23

peek用来输出debug的感觉

```java
public class Q23 {
    public static void main(String[] args) {
        int array[][] = {{1, 2}, {3, 4}, {5, 6}};
        long count = Stream.of(array)
                .flatMapToInt(IntStream::of)
                .map(n -> n * 2)
                // 相当于debug输出到控制台
                .peek(System.out::println)
                .filter(n -> n % 3 == 0)
                .count();
        System.out.println(count);
    }
}

```



Q24

死记硬背

Q25

enum默认的构造器都要是private的。

Q26

理论，死记硬背。

interface接口在后期既有default方法，又可以用static方法。但是由于都要继承，所以方法不可能是final的，abstract类也同理。

接口也没有构造器，抽象类是有的。

接口的方法只能是private和public，没有protected。

Q27

没有很难，大家都关闭了。

Q28

一个API，记住就好。

Q29

建议和Q10一起看。重要。                                                    

Q30

考察`Files.deleteIfExists`的用法，不存在就是false，存在就是true。

Q31

在使用module的时候，无需必须有实现

```java
module Sample {
  exports test;
  uses test.Hello;
}
```

但必须有

- require
- 无需再次编译可以添加新的class

Q32

```java
public class Q32 {
    public static void main(String[] args) {
        Stream<Integer> a = Stream.of(1, 2, 3, 4, 5);
        IntStream b = a.mapToInt(n -> n);
        DoubleStream c = b.mapToDouble(n -> n);
        // 上面都是从Steam转换成下一级的 IntStream和 DoubleStream 这是可以的
        // ❌ 但是下面相当于从 DoubleStream 转换成stream不可以
        // ❌ Stream<Integer> d = c.mapToInt(n->n);
    }
}

```

Q33

记住就行了。dead lock 死锁。

Q34

这一题只要走一遍就知道了，删除了index为2的，数组长度根本就没3了。

```java
public class Q34 {
    public static void main(String[] args) {
        var list = new ArrayList<>();
        list.add("A");
        list.add(100);
        list.add("B");
        list.set(1, 200);
        list.remove(2);
        list.set(3, 300); // ❌ IndexOutOfBoundsException
        System.out.println(list);
    }
}

```

Q35

记下来就可以了。其实就是br2这个资源生成的时候，没有try-with-resource住。

Q36

这一题主要是`copyOf()`这个方法他返回的是一个不可变的，后面怎么可能操作呢。所以直接无法编译。

Q37

这一题其实很有意思。主要是`toString()`这个方法，你要重写的话。而枚举类的`valueOf()`方法，直接会调用`toString()`这个方法。

需返回值肯定要是一个String，A首先这个`Type.values()[1]`返回是数组。然后`valueOf()`还是调用的`toString()`，死循环。B直接修饰符都给弄没了。C这个只会返回A.num 也就是1

Q38

考察的就是一个类的关系。

Number下面就是Float，Integer，Short这些子类。

extend 只能找比自身or自身小的类。

super 只能找比自身or自身大的类

Q39

直接背下来吧。

Q40

```java
ps.setInt(1,100);
ps.setString(2,"Sample");
ps.executeUpdate(); // 这个时候已经更新了俩 100 sample
ps.setInt(1,101);// 更新了101 sample
```

最后相当于俩都正常执行的

### 41~80

搁置了。略。
