package servlet.filter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;

/**
 * Servlet filter for forcing HTTPS when behind a SSL termination proxy that
 * sends X-Forwarded-Proto This same can be achieved by adding following wo
 * web.xml
 * <security-constraint>
 * <display-name>HTTPS-redirect</display-name>
 * <web-resource-collection>
 * <web-resource-name>secured-environments</web-resource-name>
 * <description>Do HTTPS redirect so that the connection is
 * secured</description>
 * <url-pattern>/*</url-pattern>
 * </web-resource-collection>
 * <user-data-constraint>
 * <transport-guarantee>CONFIDENTIAL</transport-guarantee>
 * </user-data-constraint>
 * </security-constraint>
 *
 * @author hardiku
 */
public class HttpsFilter implements Filter
{

    private boolean enabled;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.enabled = "production".equals(System.getenv("ENV"));
    }

    @Override
    public void destroy()
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if(!enabled)
        {
            chain.doFilter(request, response);
            return;
        }

        String xfp = httpRequest.getHeader("X-Forwarded-Proto");

        if("https".equals(xfp))
        {
            httpResponse.setHeader("Strict-Transport-Security", "max-age=60");

            chain.doFilter(request, response);
        }
        else if("http".equals(xfp))
        {
            try
            {
                URI uri1 = new URI(httpRequest.getRequestURL().toString());

                if(uri1.getPort() >= 0)
                {
                    throw new ServletException(format("Only standard ports are supported (given %s)", uri1.getPort()));
                }

                URI uri2 = new URI("https",
                        uri1.getUserInfo(),
                        uri1.getHost(),
                        /* port: */ -1,
                        uri1.getPath(),
                        httpRequest.getQueryString(),
                        /* fragment: */ null);

                httpResponse.sendRedirect(uri2.toString());
            }
            catch(URISyntaxException e)
            {
                throw new ServletException("Something went wrong with the URIs", e);
            }
        }
        else
        {
            throw new ServletException(format("Unsupported value for X-Forwarded-Proto: %s", xfp));
        }
    }
}
