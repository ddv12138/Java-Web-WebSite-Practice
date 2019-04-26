package WebComponent.Filters;

import javax.servlet.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class EncodeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String defaultEncode = StandardCharsets.UTF_8.name();
        req.setCharacterEncoding(defaultEncode);
        resp.setCharacterEncoding(defaultEncode);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) {

    }

}
