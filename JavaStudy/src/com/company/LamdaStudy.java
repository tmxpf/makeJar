package com.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class LamdaStudy {

    public static void main(String[] args) {

        final Section section = new Section(1);

        final Function<Integer, Section> sectionFactoryWithLambdaExpression = number -> new Section(number);
        final Section sectionWithLambdaExpression = sectionFactoryWithLambdaExpression.apply(1);

        final Function<Integer, Section> sectionFactoryWithMethodReference = Section::new;
        final Section sectionWithMethodExpression = sectionFactoryWithMethodReference.apply(1);

        System.out.println("Section(new) : " + section.getNumber());
        System.out.println("Section(Lambda) : " + sectionWithLambdaExpression.getNumber());
        System.out.println("Section(Method Reference) : " + sectionWithMethodExpression.getNumber());

        OldProduct product = new OldProduct(1L, "A", new BigDecimal("100"));
        System.out.println(product);

        OldProductCreator productCreator = OldProduct::new;
        OldProduct product1 = productCreator.create(1L, "A", new BigDecimal("100"));
        System.out.println(product1);

        ProductA a = createProduct(10L, "A", new BigDecimal("999"), (id, name, price) -> {
            return new ProductA(id, name, price);
        });
        ProductB b = createProduct(10L, "B", new BigDecimal("100"), ProductB::new);

        System.out.println(a);
        System.out.println(b);

    }

    private static <T extends Product> T createProduct(final Long id, final String name, final BigDecimal price, final ProductCreator<T> productCreator) {
        if(id == null || id < 1L) {
            throw new IllegalArgumentException("The id must be a positive Long.");
        }
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name is not given.");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The price must be greater then 0");
        }

        return productCreator.create(id, name, price);
    }

}

@FunctionalInterface
interface OldProductCreator {
    OldProduct create(Long id, String name, BigDecimal price);
}

@FunctionalInterface
interface ProductCreator<T extends Product> {
    T create(Long id, String name, BigDecimal price);
}

@AllArgsConstructor
@Data
class Section {
    private int number;
}

@NoArgsConstructor
@AllArgsConstructor
@Data
class OldProduct {
    private Long id;
    private String name;
    private BigDecimal price;
}

@AllArgsConstructor
@Data
abstract class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}

class ProductA extends Product {

    public ProductA(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "A " + super.toString();
    }
}

class ProductB extends Product {

    public ProductB(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "B " + super.toString();
    }
}

class TestClass {

    private Integer number;
    private String name;

    public static TestClass build() {
        return new TestClass();
    }

    public TestClass nonBuild() {
        return new TestClass();
    }

    public Integer getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}