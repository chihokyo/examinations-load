// qpackage this的由来;
//
// /**
//  * 如果方法的形参和类的属性重名时候
//  * 必须显式使用，不能省略。
//  *
//  * this(); 调用空参构造器 不能自己调用自己！
//  */
//
// public class 9 {
//
//     public static void main(String[] args) {
//         Person p = new Person();
//         p.setName("chin");
//         p.studyHard();
//     }
// }
//

class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // 这里为什么用this
        // name = name;  因为如果这样写 形参和类属性的就重名 ❌是不能省略的
        // 这里的this单独特指当前对象，当前对象是创建时候才有 动态的
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void study() {
        System.out.println(getName() +" is study...");
    }

    public void studyHard(){
        study(); // 这俩都可以 不写就是默认👇
        this.study();
    }
}