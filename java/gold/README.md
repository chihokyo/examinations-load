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
