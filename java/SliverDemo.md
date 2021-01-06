

# Sliver demo

> 又找到了一套试卷，因为并没有解答。所以有一些需要自己手写一下demo。
>
> 打算记录一下。

```java
import java.io.*;
public class Main{
    public static void main (String[] args) {
        try {
            doA();
            doB();
        } catch(IOException e) {
            System.out.print("c");
            return;
        } finally {
            System.out.print("d");
        }
        System.out.println("f");
    }
    
    private static void doA() {
        System.out.print("a");
        if (false){
            throw new IndexOutOfBoundsException();
        }
    }
    private static void doB() throws FileNotFoundException{
        System.out.print("b");
        if (true){
            throw new FileNotFoundException();
        } 
    }
}

// abcd
```

```java
public class Main{
    private int num = 1;
    private int div = 0;
    public void divide(){
        try {
            num = num / div;
            System.out.println("exception");
        } catch(ArithmeticException e) {
            num = 100;
        } catch(Exception e) {
            num = 200;
        }finally {
            num = 300;
        }
        System.out.print(num);
    }

    public static void main (String[] args) {
        Main test = new Main();
        test.divide();
    }
}
// 300
```

```java
public class Main {
    public static void main(String[] args) throws Exception {
        int[] a = {2, 4, 6, 8, 10};
        int[] b = {2, 4, 8, 6, 10};
        int res1 = Arrays.mismatch(a, b);
        int res2 = Arrays.compare(a, b);
        System.out.println(res1 + " " + res2);
    }
}
// 2 -1
```

```java
public class Main {
    public static void main(String[] args) throws Exception {
        var i = 10;
        var j = 5;
        i += (j * 5 + j) / i - 2;
        System.out.println(i);
    }
}
public class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder(5);
        sb.append("HOWDY");
        sb.insert(0, ' '); // 0HOWDY
        sb.replace(3, 5, "LL"); // 0HOLLY
        sb.insert(6, "COW");// 0HOLLYCOW
        sb.delete(2, 7);// 0HOW
        System.out.println(sb);// 0HOW
        System.out.println(sb.length()); // 4
    }
}
```

Q10 有点难。放弃。

Q11

```java
public class Main {
    public static void main(String[] args) throws Exception {
        var i = 10;
        var j = 5;
        i += (j * 5 + j) / i - 2;
        System.out.println(i); // 11
    }
}

public class Main {
    private int x;
    private static int y;
    public static void main(String[] args) throws Exception {
        Main m1 = new Main();
        m1.x = 2;
        Main.y = 3;
        Main m2 = new Main();
        m2.x = 4;
        m2.y = 5;
        System.out.println(m1.x + "," + m1.y);
        System.out.println(m2.x + "," + Main.y);
        System.out.println(m2.x + "," + m1.y);
    }
}

// 2,5
// 4,5
// 4,5
// 一堆错误 编译不会通过
```

```java
public static int sample1(){
    try {
        throw new Exception();
    } catch (Exception e){
        return 0;
    } finally {
        return 1;
    }
}

public static int sample2(){
    int val;

    try {
        throw new Exception();
    } catch (Exception e){
        val = 0;
        return val;
    } finally {
        val = 1;
    }
}

public static void main (String args[]){
    System.out.println(sample1()); // 1
    System.out.println(sample2()); // 0
}
```

```java
public interface EulerInterface{
    double getEulerValue();
}
public class Main {

    public static void main(String[] args) throws Exception {
        EulerInterface myEulerInterface;
        myEulerInterface = () -> "2.71833"; // 表达式错误
        System.out.println("Value of Euler = " + myEulerInterface.getEulerValue);
    }
}

class Myclass {
     public static void main(String[] args){
         System.out.println(args[1] + "--" + args[3] + "--" + args[0]);
     }
 }
// Car--red--My
```

