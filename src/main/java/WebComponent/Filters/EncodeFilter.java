package WebComponent.Filters;

import globalUtils.CommonUtils;

import javax.servlet.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class EncodeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        CommonUtils.Logger().info(req.getLocalName());
        String defaultEncode = StandardCharsets.UTF_8.name();
        req.setCharacterEncoding(defaultEncode);
        resp.setCharacterEncoding(defaultEncode);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) {

    }

}
