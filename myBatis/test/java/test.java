import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;



/*Mybatis配置方式：
方式一：不用接口：表对应类+.xml

方式二：用mapper：表对应类+类Mapper接口+.xml

方式三：使用注解：表对应类+类Mapper接口

当然还有SQL语句方式，在另一篇文章*/

//这些测试通过是因为在调用每个测试方法之前创建了一个新的Test实例

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class test {
    SqlSession session;

    @BeforeAll
// BeforeAll   该批注用于静态函数，以与类的静态成员一起使用。 只能用于静态？
// 不能用static ,因session变量 不是静态的 除非，session也用静态关健字。
    void builder() throws IOException {
//FIXME        使用一个建造者模式来构建sqlsessionFactory。这个是产品。一个
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
    }

    @Test
    void xml() {
//        配置文件中的namespace可以随意起一个名字，resultMap将实体类和数据库的表关联起来。
        // 如果用 resultmap 在xml 里定义，那么，实体类也不需要了。完全的xml了。
        //TODO        使用xml 不要接口的方法：test 是乱写的。这不重要。department.java是实体类。
        List<Object> list = session.selectList("test.selectBlog", 1);
        System.out.println(list.toString());
    }

    @Test
    void interfaceto() {
//        使用接口来查询
        Department department = session.selectOne("mapper.select.selectone", 1);
        System.out.println(department.toString());

    }

    @Test
    void getmapper() {
//        这个也可以？
        mapper.select select = session.getMapper(mapper.select.class);
        Department department = select.selectone(1);
        System.out.println(department);
//        一句话完全不懂
    }

    @Test
    void NeedEntiy() {
//        还是得要一个实体类，不然还是会报错
        Object o = session.selectOne("NeedEntiy.NeedEntiy", 1);
//        List<Object> o = session.selectList("complexSql.NeedEntiy");
        System.out.println(o);
    }

    @Test
    void noResultType() {
//        A query was run and no Result Maps were found for the Mapped Statement 'noResultType.no'.  It's likely that neither a Result Type nor a Result Map was specified. 说什么不指定就不行。
//        session.selectOne("noResultType.no",1);
//        这样就可以，可能 selectone 是个固定的返回。update 只是显示影响了多少行。
        Object object = session.update("noResultType.no", 1);
        System.out.println(object); //Fixme 输出了-1 是什么意思？？？
    }

    @Test
    void mapperElementSQL() {
//        发现有个sql的元素,
        Object object = session.update("sql.rt");
//        总的来说，方法update 就只能找xml 中的有update标签的，而不能是其它的、我操
        System.out.println(object);//会输出 update的值，还是jdbc的执行返回值？
    }

    @Test()
    void resultType() {
//        所以重要的还是一个返回修值
//        创建库这种东西，是可以的，返回值 就无法弄了，除了报错，小心 IF NOT EXISTS  无法知道是否
//        test 的出一个异常
        assertThrows(org.apache.ibatis.exceptions.PersistenceException.class, () -> {
            Object objectMap = session.selectMap("sql.createDB", "row");
            System.out.println(objectMap);
        });
    }

    @Test
    void exception() {
        try{
            Object objectMap = session.update("sql.createDB");
            System.out.println(objectMap);
        }catch (org.apache.ibatis.exceptions.PersistenceException e){
            System.out.println("说明异常了会正常执行吗1");
            System.out.println(e);
        }
        System.out.println("会正常执行吗2");
//        答案是会，不会影响程序的执行，这样可以判断成功？？？这个方法不好
    }

    @Test
    void myResultHandler() {
//        自定义结果处理器？
        MyResultHandler handler = new MyResultHandler();
//        <typeHandlers>
//        <typeHandler handler="com.mdd.mybatis.typehandle.MyTypeHandle"></typeHandler>
//    </typeHandlers  xml注册一下
//          <result column="name" property="name" typeHandler="com.mdd.mybatis.typehandle.MyTypeHandle"/>
//        ResultMap 中设置一下
    }

    @Test
    void mutliArgs() {

    }
}
