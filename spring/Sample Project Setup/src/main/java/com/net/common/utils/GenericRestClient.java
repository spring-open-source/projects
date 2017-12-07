package com.net.common.utils;

import com.net.common.models.ErrorResponse;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @param <U> Input object type
 * @param <T> Output Object Type
 */
public class GenericRestClient<U, T>
{

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericRestClient.class);

    private RestTemplate restClient = null;

    public GenericRestClient()
    {
        restClient = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

    /**
     *
     * @param uri
     * @param params
     * @param headers
     * @param clazz
     * @return
     * @throws RuntimeException
     */
    public T doGet(String uri, Map<String, String> params, Map<String, String> headers, Class<T> clazz) throws RuntimeException
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
     * @param uri
     * @param headers
     * @param clazz
     * @return
     * @throws RuntimeException
     */
    public T doGet(String uri, Map<String, String> headers, Class<T> clazz) throws RuntimeException
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
     * @param uri
     * @param params
     * @param headers
     * @param body
     * @param clazz
     * @return
     * @throws RuntimeException
     */
    public T doPost(String uri, Map<String, String> params, Map<String, String> headers, U body, Class<T> clazz) throws RuntimeException
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
        HttpEntity<U> entity = new HttpEntity<>(body, httpheaders);

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
    public void doPost(String uri, Map<String, String> params, Map<String, String> headers, U body) throws RuntimeException
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
     * @param clazz output object class
     * @return
     * @throws RuntimeException
     */
    public T doPut(String uri, Map<String, String> params, Map<String, String> headers, U body, Class<T> clazz) throws RuntimeException
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
        HttpEntity<U> entity = new HttpEntity<>(body, httpheaders);

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
    public void doPut(String uri, Map<String, String> params, Map<String, String> headers, U body) throws RuntimeException
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
        HttpEntity<U> entity = new HttpEntity<>(body, httpheaders);

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
    public void doDelete(String uri, Map<String, String> params, Map<String, String> headers, U body) throws RuntimeException
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
        HttpEntity<U> entity = new HttpEntity<>(body, httpheaders);

        ResponseEntity result = restClient.exchange(uri, HttpMethod.POST, entity, void.class, params);
        if(!(HttpStatus.OK == result.getStatusCode()))
        {
            ErrorResponse errResp = (ErrorResponse) result.getBody();
            throw new RuntimeException(errResp);
        }
    }
}
