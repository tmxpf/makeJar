package com.company;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;l
import java.util.function.Function;

@Deprecated
@SuppressWarnings("1111")
@TestInfo(testedBy = "aaa", testDate = @DateTime(yymmdd = "160101", hhmmss = "235959"))
public class Main {

    public static void main(String[] args) {
        ///
        TestClass.build();
        TestClass.non();
        ///


        Function<String, Integer> test = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return null;
            }
        };

        final Function<String, Integer> toInt = value -> Integer.parseInt(value);

        final Integer number = toInt.apply("100");
        System.out.println(number);

        final Function<Integer, Integer> identity = Function.identity();
        final Function<Integer, Integer> identity2 = t -> t;

        System.out.println(identity.apply(999));
        System.out.println(identity2.apply(7777));

        //////////////////////////////////////////////////////////////////////

        //Main의 class객체를 얻습니다.
        Class<Main> cls = Main.class;

        TestInfo anno = cls.getAnnotation(TestInfo.class);
        System.out.println("anno.testedBy() = " + anno.testedBy());
        System.out.println("anno.testDate().yymmdd() = " + anno.testDate().yymmdd());
        System.out.println("anno.testDate().hhmmss() = " + anno.testDate().hhmmss());

        for(String str : anno.testTools()) {
            System.out.println("testTools = " + str);
        }

        System.out.println();

        //Main에 적용된 모든 어노테이션을 가져옵니다.
        Annotation[] annoArr = cls.getAnnotations();

        for(Annotation a : annoArr) {
            System.out.println(a);
        }

    }

    public static String getLambdaFn() {
        return "9999";
    }

}

@Retention(RetentionPolicy.RUNTIME)
@interface TestInfo {
    int count()     default 1;
    String testedBy();
    String[] testTools()    default "JUnit";
    TestType testType()     default TestType.FIRST;
    DateTime testDate();
}

@Retention(RetentionPolicy.RUNTIME)
@interface DateTime {
    String yymmdd();
    String hhmmss();
}

enum TestType { FIRST, FINAL }