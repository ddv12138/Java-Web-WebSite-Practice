import globalUtils.CommonResult;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

public class TestResult {
    @Test
    public void main(){
        System.out.println(new CommonResult(true,"message",null));
    }
}
