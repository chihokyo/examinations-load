package com.gold.chapter10;

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