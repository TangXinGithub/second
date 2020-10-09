import cn.hutool.core.util.ReUtil;
import org.junit.Test;

public class reg {
    boolean isValidMobileNumber(String s) {
        return s.matches("\\d{11}");
    }


}
