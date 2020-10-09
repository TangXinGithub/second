import cn.hutool.core.util.ReUtil;
import cn.hutool.log.Log;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
public class test {

    reg reg=new reg();
    @Test
    void  test1(){
        reg.isValidMobileNumber("12345678901");

        assertFalse(reg.isValidMobileNumber("36438487"));
    }

    @Test
    void hutool() {
        //    hutool

        String content = "ZZZaaabbbccc中文1234";
        String resultExtractMulti = ReUtil.extractMulti("(\\w)aa(\\w)", content, "$1-$2");
        System.out.println(resultExtractMulti);
        assertEquals("Z-a", resultExtractMulti);

    }


}
