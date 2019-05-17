import com.alibaba.fastjson.JSON;
import org.junit.Test;
import tk.ddvudo.ssrdetection.Utils.URLHandler.URLConnHandler;
import tk.ddvudo.ssrdetection.Utils.URLHandler.URLIOHandler;
import tk.ddvudo.ssrdetection.Utils.dataResolve.DataResolve;
import tk.ddvudo.ssrdetection.beans.Airport;
import tk.ddvudo.ssrdetection.beans.Result;

import java.net.URLConnection;
import java.util.ArrayList;

public class TestSSR {
    @Test
    public void MainTest() {
        try {
            String linkurl = "https://sub.2cy.network/link/HxivbVvNR6KfVNUB?mu=0";
            URLConnection con = URLConnHandler.getInstance(linkurl).getConnection();
            Airport data = DataResolve.getInstance().Decode(URLIOHandler.getInstance(URLIOHandler.LinkType.SSR).getResponseContent(con), URLIOHandler.LinkType.SSR);
            ArrayList<Result> testRsult = DataResolve.getInstance().serverPingTestMultiThread(1000, data.getServers());
            System.out.println(JSON.toJSONString(testRsult));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
