package com.hgx.lambda.demo3;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description com.hgx.lambda.demo3
 * @Author huogaoxu
 * @Date 2023-05-22 14:23
 * @Version 1.0
 **/
public class LambdaDemo3 {


    public static void main(String[] args) {


//        test20();
        test19();
//        test18();
//        test17();
//        test16();
//        test15();
//        test14();
//        test13();
//        test12();
//        test11();
//        test10();
//        test09();
//        test08();
//        test07();
//        test06();
//        test05();
//        test04();
//        test03();
//        test02();
//        System.out.println(getAuthors());
//        test01();




    }

    private static void test20() {
        //optional 进行非空判断

        Author authors = (Author) getAuthors();
        Optional<Author> authorOptional = Optional.ofNullable(authors);

    }

    private static void test19() {
//        用一个参数的重载方法求最小值
//        使用reduce求所有作者中年龄的最小值
        List<Author> authors = getAuthors();
        Optional<Integer> optional = authors.stream()
                .distinct()
                .map(Author::getAge)
                .reduce((result, element) -> result > element ? element : result);
        optional.ifPresent(System.out::println);

    }

    private static void test18() {
//        使用reduce求所有作者年龄的和
        List<Author> authors = getAuthors();
        Integer reduce = authors.stream()
                .distinct()
                .map(Author::getAge)
                .reduce(0, (result, element) -> result + element);
        System.out.println(reduce);

//        使用reduce求所有作者中年龄的最大值
        Integer reduce1 = authors.stream()
                .map(Author::getAge)
                .reduce(Integer.MIN_VALUE, (result, element) -> result <element? element : result);
        System.out.println(reduce1);

//        使用reduce求所有作者中年龄的最小值
        Integer reduce2 = authors.stream()
                .map(Author::getAge)
                .reduce(Integer.MAX_VALUE, (result, element) -> result > element ? element : result);
        System.out.println(reduce2);


    }

    private static void test17() {
//        获取一个年龄最小的作家，并输出他的姓名.
        List<Author> authors = getAuthors();
        Optional<Author> first = authors.stream()
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .findFirst();
        System.out.println(first);
    }

    private static void test16() {
//        获取任意一个年龄大于18的作家，如果存在就输出他的名字
        List<Author> authors = getAuthors();
        Optional<Author> any = authors.stream()
                .filter(author -> author.getAge() > 18)
                .findAny();
        System.out.println(any);
    }

    private static void test15() {
//        判断作家是否都没有超过100岁的.
        List<Author> authors = getAuthors();
        boolean noneMatch = authors.stream()
                .noneMatch(author -> author.getAge() > 100);
        System.out.println(noneMatch);
    }

    private static void test14() {
//        判断是否所有的作家都是成年人
        List<Author> authors = getAuthors();
        boolean allMatch = authors.stream()
                .allMatch(author -> author.getAge() >= 18);
        System.out.println(allMatch);
    }

    private static void test13() {
//        判断是否有年龄在29以上的作家
        List<Author> authors = getAuthors();
        boolean b = authors.stream()
                .anyMatch(author -> author.getAge() > 29);
        System.out.println(b);

    }

    private static void test12() {
//        获取一个存放所有作者名字的List集合.
       List<Author> authors = getAuthors();
//        List<String> collect = authors.stream()
//                .map(Author::getName)
//                .collect(Collectors.toList());
//        System.out.println(collect);

//        获取一个所有书名的Set集合。
//        Set<Book> bookSet = authors.stream()
//                .flatMap(author -> author.getBooks().stream())
//                .collect(Collectors.toSet());
//        System.out.println(bookSet);

//        获取一个Map集合，map的key为作者名，value为List<Book>

        Map<String, List<Book>> listMap = authors.stream()
                .distinct()
                .collect(Collectors.toMap(Author::getName, Author::getBooks));
        System.out.println(listMap);
    }

    private static void test11() {
//        分别获取这些作家的所出书籍的最高分和最低分并打印.
        List<Author> authors = getAuthors();
        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(Book::getScore)
                .max((o1, o2) -> o1 - o2);
        System.out.println(max);

        Optional<Integer> min = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(Book::getScore)
                .min((o1, o2) -> o1 - o2);
        System.out.println(min);
    }

    private static void test10() {
//        打印这些作家的所出书籍的数目，注意删除重复元素。
        List<Author> authors = getAuthors();
        long count = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();
        System.out.println(count);


    }

    private static void test09() {
//        输出所有作家的名字
        List<Author> authors = getAuthors();
        authors.stream()
                .map(Author::getName)
                .distinct()
                .forEach(System.out::println);
    }

    private static void test08() {
//        打印所有书籍的名字。要求对重复的元素进行去重.
       List<Author> authors = getAuthors();
//        authors.stream()
//                .flatMap(author -> author.getBooks().stream())
//                .distinct()
//                .forEach(book -> System.out.println(book.getName()));

//        打印现有数据的所有分类。要求对分类进行去重。不能出现这种格式：哲学,爱情
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .forEach(System.out::println);

    }

    private static void test07() {
//        打印除了年龄最大的作家外的其他作家，要求不能有重复元素，并且按照年龄降序排序.
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted()
                .skip(1)
                .forEach(author -> System.out.println(author.getName()));
    }

    private static void test06() {
//        对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素,然后打印其中年龄最大的两个作家的姓名。
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .limit(2)
                .forEach(author -> System.out.println(author.getName()));
    }

    private static void test05() {
//        对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素
        List<Author> authors = getAuthors();
//        authors.stream()
//                .distinct()
//                .sorted(( o1, o2)-> o2.getAge() - o1.getAge())
//                .forEach(author -> System.out.println(author.getAge()));
        authors.stream()
                .distinct()
                .sorted() // 如果调用空参的sorted()方法，需要流中的元素是实现了Comparable。
                .forEach(author -> System.out.println(author.getAge()));
    }

    private static void test04() {
//        打印所有作家的姓名，并且要求其中不能有重复元素
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .forEach(author -> System.out.println(author.getName()));
    }

    private static void test03() {
//        打印所有作家的姓名
        List<Author> authors = getAuthors();
        // 简写
//        authors.stream()
//                .map(Author::getName)
//                .forEach(System.out::println);
//
//        authors.stream()
//                .map(author -> author.getName())
//                .forEach(name -> System.out.println(name));

        authors.stream()
                .map(Author::getAge)
                .map(age ->age + 10)
                .forEach(System.out::println);
    }

    private static void test02() {
//        打印所有姓名长度大于1的作家的姓名
        List<Author> authors = getAuthors();
        authors.stream()
                .filter(author -> author.getName().length() > 1)
                .forEach(author -> System.out.println(author.getName()));
    }

    private static void test01() {
        // 打印所有年龄小于18的作家的名字，并且要注意去重。
        List<Author> authors = getAuthors();
        Stream<Author> stream = authors.stream();

        int[] arr = {1,2,3,4,5,6};
        IntStream intStream = Arrays.stream(arr);
        Stream<Integer> of = Stream.of();

        Map<String,Integer> map = new HashMap<>();
        map.put("zs",18);
        map.put("ls",20);
        map.put("we",21);
        Stream<Map.Entry<String, Integer>> stream1 = map.entrySet().stream();

        authors.stream()
                .distinct()
                .filter(author -> author.getAge() < 18)
                .forEach(author -> System.out.println(author.getName()));
    }


    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L,"蒙多",33,"一个从菜刀中明悟哲理的祖安人",null);
        Author author2 = new Author(2L,"亚拉索",15,"狂风也追逐不上他的思考速度",null);
        Author author3 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);
        Author author4 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L,"刀的两侧是光明与黑暗","哲学,爱情",88,"用一把刀划分了爱恨"));
        books1.add(new Book(2L,"一个人不能死在同一把刀下","个人成长,爱情",99,"讲述如何从失败中明悟真理"));

        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(4L,"吹或不吹","爱情,个人传记",56,"一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L,"你的剑就是我的剑","爱情",56,"无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author,author2,author3,author4));
        return authorList;
    }
}
