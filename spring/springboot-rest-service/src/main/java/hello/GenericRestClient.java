package hello;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Generic Rest client implementation of RestTemplate
 *
 * @author hardiku
 */
public class GenericRestClient
{

    private static final RestTemplate restClient = new RestTemplate(new OkHttp3ClientHttpRequestFactory());

    /**
     *
     * @param <T>
     * @param uri
     * @param params
     * @param headers
     * @param clazz
     * @return
     * @throws RuntimeException
     */
    public <T> T doGet(String uri, Map<String, String> params, Map<String, String> headers, Class<T> clazz) throws RuntimeException
    {
        if(headers == null)
        {
            headers = new HashMap<>();
        }
        if(params == null)
        {
            params = new HashMap<>();
        }
        HttpHeaders httpheaders = new HttpHeaders();
        headers.putAll(headers);
        HttpEntity entity = new HttpEntity(httpheaders);

        ResponseEntity<T> result = restClient.exchange(uri, HttpMethod.GET, entity, clazz, params);
        if(HttpStatus.OK == result.getStatusCode())
        {
            T resp = result.getBody();
            return resp;
        }
        else
        {
            ErrorResponse errResp = (ErrorResponse) result.getBody();
            throw new RuntimeException(errResp);
        }
    }

    /**
     *
     * @param <T>
     * @param uri
     * @param headers
     * @param clazz
     * @return
     * @throws RuntimeException
     */
    public <T> T doGet(String uri, Map<String, String> headers, Class<T> clazz) throws RuntimeException
    {
        if(headers == null)
        {
            headers = new HashMap<>();
        }

        HttpHeaders httpheaders = new HttpHeaders();
        headers.putAll(headers);
        HttpEntity entity = new HttpEntity(httpheaders);

        ResponseEntity<T> result = restClient.exchange(uri, HttpMethod.GET, entity, clazz);
        if(HttpStatus.OK == result.getStatusCode())
        {
            T resp = result.getBody();
            return resp;
        }
        else
        {
            ErrorResponse errResp = (ErrorResponse) result.getBody();
            throw new RuntimeException(errResp);
        }
    }

    /**
     *
     * @param <T>
     * @param uri
     * @param params
     * @param headers
     * @param body
     * @param clazz
     * @return
     * @throws RuntimeException
     */
    public <T> T doPost(String uri, Map<String, String> params, Map<String, String> headers, Object body, Class<T> clazz) throws RuntimeException
    {
        if(headers == null)
        {
            headers = new HashMap<>();
        }
        if(params == null)
        {
            params = new HashMap<>();
        }
        HttpHeaders httpheaders = new HttpHeaders();
        headers.putAll(headers);
        HttpEntity entity = new HttpEntity(body, httpheaders);

        ResponseEntity<T> result = restClient.exchange(uri, HttpMethod.POST, entity, clazz, params);
        if(HttpStatus.CREATED == result.getStatusCode())
        {
            T resp = result.getBody();
            return resp;
        }
        else
        {
            ErrorResponse errResp = (ErrorResponse) result.getBody();
            throw new RuntimeException(errResp);
        }
    }

    /**
     *
     * @param uri
     * @param params
     * @param headers
     * @param body
     * @throws RuntimeException
     */
    public void doPost(String uri, Map<String, String> params, Map<String, String> headers, Object body) throws RuntimeException
    {
        if(headers == null)
        {
            headers = new HashMap<>();
        }
        if(params == null)
        {
            params = new HashMap<>();
        }
        HttpHeaders httpheaders = new HttpHeaders();
        headers.putAll(headers);
        HttpEntity entity = new HttpEntity(body, httpheaders);

        ResponseEntity result = restClient.exchange(uri, HttpMethod.POST, entity, void.class, params);
        if(!(HttpStatus.CREATED == result.getStatusCode()))
        {
            ErrorResponse errResp = (ErrorResponse) result.getBody();
            throw new RuntimeException(errResp);
        }
    }

    /**
     *
     * @param <T>
     * @param uri
     * @param params
     * @param headers
     * @param body
     * @param clazz
     * @return
     * @throws RuntimeException
     */
    public <T> T doPut(String uri, Map<String, String> params, Map<String, String> headers, Object body, Class<T> clazz) throws RuntimeException
    {
        if(headers == null)
        {
            headers = new HashMap<>();
        }
        if(params == null)
        {
            params = new HashMap<>();
        }
        HttpHeaders httpheaders = new HttpHeaders();
        headers.putAll(headers);
        HttpEntity entity = new HttpEntity(body, httpheaders);

        ResponseEntity<T> result = restClient.exchange(uri, HttpMethod.POST, entity, clazz, params);
        if(HttpStatus.CREATED == result.getStatusCode())
        {
            T resp = result.getBody();
            return resp;
        }
        else
        {
            ErrorResponse errResp = (ErrorResponse) result.getBody();
            throw new RuntimeException(errResp);
        }
    }

    /**
     *
     * @param uri
     * @param params
     * @param headers
     * @param body
     * @throws RuntimeException
     */
    public void doPut(String uri, Map<String, String> params, Map<String, String> headers, Object body) throws RuntimeException
    {
        if(headers == null)
        {
            headers = new HashMap<>();
        }
        if(params == null)
        {
            params = new HashMap<>();
        }
        HttpHeaders httpheaders = new HttpHeaders();
        headers.putAll(headers);
        HttpEntity entity = new HttpEntity(body, httpheaders);

        ResponseEntity result = restClient.exchange(uri, HttpMethod.POST, entity, void.class, params);
        if(!(HttpStatus.CREATED == result.getStatusCode()))
        {
            ErrorResponse errResp = (ErrorResponse) result.getBody();
            throw new RuntimeException(errResp);
        }
    }

    /**
     *
     * @param uri
     * @param params
     * @param headers
     * @param body
     * @throws RuntimeException
     */
    public void doDelete(String uri, Map<String, String> params, Map<String, String> headers, Object body) throws RuntimeException
    {
        if(headers == null)
        {
            headers = new HashMap<>();
        }
        if(params == null)
        {
            params = new HashMap<>();
        }
        HttpHeaders httpheaders = new HttpHeaders();
        headers.putAll(headers);
        HttpEntity entity = new HttpEntity(body, httpheaders);

        ResponseEntity result = restClient.exchange(uri, HttpMethod.POST, entity, void.class, params);
        if(!(HttpStatus.OK == result.getStatusCode()))
        {
            ErrorResponse errResp = (ErrorResponse) result.getBody();
            throw new RuntimeException(errResp);
        }
    }
}
