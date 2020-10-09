import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

public class MyResultHandler implements ResultHandler<Department> {
//    自定义实现 mybatis的结果集处理器
    @Override
    public void handleResult(ResultContext<? extends Department> resultContext) {
        System.out.println("结果集："+resultContext.getResultObject());
    }
}
