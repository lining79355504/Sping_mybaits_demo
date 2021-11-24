package com.demo.file;

/**
 * @author mort
 * @Description
 * @date 2021/8/27
 **/
public class TestDto {

    @ExcelTitle(title = "年龄")
    private Integer age;

    @ExcelTitle(title = "姓名")
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestDto(Integer age, String name) {
        this.age = age;
        this.name = name;
    }
}
