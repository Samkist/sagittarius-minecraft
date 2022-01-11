package dev.samkist.lumae.sagittarius.storage;

import com.google.gson.Gson;
import kong.unirest.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Generates requests with a route,
 * which may also have route parameters,
 * a typed generic body,
 * or a typed generic return
 */
@SuppressWarnings("unchecked")
public class HttpResponseGenerator {
    private final Gson gson;
    private final String apiHost;

    public HttpResponseGenerator(Gson gson, String apiHost) {
        this.gson = gson;
        this.apiHost = apiHost;
    }

    /*

    NO TYPE REQUESTS (USUALLY DELETE REQUESTS)

     */

    /*
    WITHOUT ROUTE PARAMS
     */

    public HttpResponse request(Routes route) {
        return request(route, List.of());
    }

    /*
    WITH ROUTE PARAMS
     */

    public HttpResponse request(Routes route, String... values) {
        return request(route, Arrays.asList(values));
    }

    private HttpResponse request(Routes route, List<String> values) {
        return request(route, null, (GenericType) null, values);
    }

    /*


    CLASS BASED REQUESTS


     */

    /*

    WITHOUT ROUTE PARAMS

     */

    /*
    Without Body
     */
    public <T> HttpResponse<T> request(Routes route, Class<T> clazz) {
        return request(route, null, clazz);
    }

    /*
    With Body
     */

    public <T> HttpResponse<T> request(Routes route, T body, Class<T> clazz) {
        return request(route, body, clazz, List.of());
    }

    /*

    WITH ROUTE PARAMS

     */

    /*
    Without Body
     */

    public <T> HttpResponse<T> request(Routes route, Class<T> clazz, String... values) {
        return request(route, clazz, Arrays.asList(values));
    }

    private <T> HttpResponse<T> request(Routes route, Class<T> clazz, List<String> values) {
        return request(route, null, clazz, values);
    }

    /*
    With Body
     */

    public <T> HttpResponse<T> request(Routes route, T body, Class<T> clazz, String... values) {
        return request(route, body, clazz, Arrays.asList(values));
    }

    private <T> HttpResponse<T> request(Routes route, T body, Class<T> clazz, List<String> values) {
        if(Objects.nonNull(clazz)) {
            return request(route, body, new GenericType<>() {}, values);
        } else {
            return request(route, body, (GenericType<T>) null, values);
        }
    }

    /*


    GENERIC TYPE BASED REQUESTS


    */

    /*

    WITHOUT ROUTE PARAMS

     */

    /*
    Without Body
     */

    public <T> HttpResponse<T> request(Routes route, GenericType<T> type) {
        return request(route, null, type);
    }

    /*
    With Body
     */

    public <T> HttpResponse<T> request(Routes route, T body, GenericType<T> type) {
        return request(route, body, type, List.of());
    }
    /*

    WITH ROUTE PARAMS

     */

    /*
    Without Body
     */

    public <T> HttpResponse<T> request(Routes route, GenericType<T> type, String... values) {
        return request(route, type, Arrays.asList(values));
    }

    private <T> HttpResponse<T> request(Routes route, GenericType<T> type, List<String> values) {
        return request(route, null, type, values);
    }

    /*
    With Body
     */

    public <T> HttpResponse<T> request(Routes route, T body, GenericType<T> type, String... values) {
        return request(route, body, type, Arrays.asList(values));
    }

    private <T> HttpResponse<T> request(Routes route, T body, GenericType<T> type, List<String> values) {
        HttpRequest request = generateRequest(route, body, values);
        if(Objects.nonNull(type)) {
            return request.asObject(type);
        }
        return request.asEmpty();
    }


    /*

    REQUEST GENERATOR

     */

    private <T> HttpRequest generateRequest(Routes route, T body, List<String> values) {
        HttpRequest request = null;
        String unDevelopedRoute = apiHost + route.getRoute();
        System.out.println(String.format("Building: %s", unDevelopedRoute));
        switch(route.getRequestType()) {
            case GET:
                request = Unirest.get(unDevelopedRoute);
                break;
            case POST:
                request = Unirest.post(unDevelopedRoute);
                break;
            case DEL:
                request = Unirest.delete(unDevelopedRoute);
                break;
        }

        if(request instanceof HttpRequestWithBody && Objects.nonNull(body)) {
            HttpRequestWithBody requestWithBody = (HttpRequestWithBody) request;
            requestWithBody.body(gson.toJsonTree(body));
        }

        List<String> variables = route.getVariables();

        for(int i = 0; i < variables.size(); ++i) {
            request.routeParam(variables.get(i), values.get(i));
        }

        return request;
    }
}
