import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Lambda {

    @Test
    void test2(){
     /*函数式编程（Functional Programming）是把函数作为基本运算单元，函数可以作为变量，可以接收函数，还可以返回函数。历史上研究函数式编程的理论是Lambda演算，所以我们经常把支持函数式编程的编码风格称为Lambda表达式。*/
        String[] array = new String[] { "Apple", "Orange", "Banana", "Lemon" };
        Arrays.sort(array,(s1,s2)->{
            return s1.compareTo(s2);
        });
//        观察Lambda表达式的写法，它只需要写出方法定义：-> { ... }表示方法体，
        System.out.println(String.join(",",array));
    }

    @Test
    void concise() {
        String[] array = new String[] { "Apple", "Orange", "Banana", "Lemon" };
//        如果只有一行return xxx的代码，完全可以用更简单的写法：
        Arrays.sort(array,(s1,s2)-> s1.compareTo(s2) );
        System.out.println(String.join(",",array));
    }

    @FunctionalInterface
    public interface function{
        int re(String s1,String s2);

    default void equa(Object obj) {

        }
    }

    @Test
    void stream() {
//        成链式操作  :: 是方法的引用 。所谓方法引用，是指如果某个方法签名和接口恰好一致
        Stream<String> stream1 = Arrays.stream(new String[] { "A", "B", "C" });
        Stream<String> stream2 = List.of("X", "Y", "Z").stream();
        stream1.forEach(System.out::println);
        stream2.forEach(System.out::println);

        Stream<Integer> natual = Stream.generate(new NatualSupplier());
        // 注意：无限序列必须先变成有限序列再打印:
        natual.limit(20).forEach(System.out::println);
    }
    class NatualSupplier implements Supplier<Integer> {
        int n = 0;
        public Integer get() {
            n++;
            return n;
        }
    }
    class FibonacciSupplier implements Supplier<Long> {
        private long n0 = 0;  // 无中生有 无理取闹 凭空想象 凭空捏造
        private long n1 = 1; // 一生二 二生三 三生万物
        private long next;  // 一定不能被外界访问到
        @Override
        public Long get() {
            next = n0 + n1;
            n0 = n1;  // 第二位的数组位移到第一位
            n1 = next;  // 第二位的数字填充next，为下次输出做准备
            return n0;  // 拥有多少只=(:з」∠)_兔子
        }
    }
}

