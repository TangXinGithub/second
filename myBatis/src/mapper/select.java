package mapper;
public interface select {
//    要用泛型？？为什么？类型参数能被用来声明返回值类型，并且能作为泛型方法得到的实际参数类型的占位符
     <Department> Department selectone(Integer id);
}
