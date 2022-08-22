package com.gold.charpter04;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
