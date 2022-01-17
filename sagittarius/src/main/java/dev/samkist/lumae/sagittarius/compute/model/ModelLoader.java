/**
 * File created by Samuel Pizette on 16 January 2022
 * https://github.com/Samkist/
 **/

package dev.samkist.lumae.sagittarius.compute.model;

import com.google.common.collect.Streams;
import com.google.gson.reflect.TypeToken;
import dev.samkist.lumae.sagittarius.data.models.MilkyModel;
import dev.samkist.lumae.sagittarius.exceptions.RecordNotFoundException;
import dev.samkist.lumae.sagittarius.storage.HttpResponseGenerator;
import dev.samkist.lumae.sagittarius.storage.RESTProvider;
import dev.samkist.lumae.sagittarius.storage.Routes;
import kong.unirest.GenericType;
import kong.unirest.UnirestException;
import org.redisson.api.RBucket;
import org.redisson.api.RFuture;
import org.redisson.api.RedissonClient;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({"unchecked"})
public class ModelLoader {

    private final RedissonClient redis;
    private final RESTProvider rest;
    private final HttpResponseGenerator generator;

    public ModelLoader(RedissonClient redis, RESTProvider rest) {
        this.redis = redis;
        this.rest = rest;
        this.generator = rest.getGenerator();
    }

    public <T extends MilkyModel<T>> T load(TypeToken<T> type, List<String> arguments) {
        Routes route = Routes.getLoadRoutes(type);
        Map<String, String> kv = new HashMap<>();
        Streams.forEachPair(
                route.parameters().stream(),
                arguments.stream(),
                kv::put);
        String routeString = buildRoute(route, kv);
        RBucket<T> bucket = redis.getBucket(routeString);
        RFuture<T> modelFuture = bucket.getAsync();
        AtomicReference<T> result = new AtomicReference<>();
        try {
            result.set(modelFuture.get());
            if(Objects.nonNull(result)) {
                return result.get();
            }
        } catch (Exception e) {

        }

        generator.request(route, new GenericType<T>(){}, arguments).ifSuccess(s -> result.set(s.getBody())).ifFailure(f -> {
            throw new RecordNotFoundException(scope(type), extractId(kv));
        });

        return result.get();
    }

    public String buildRoute(Routes route, Map<String, String> paramToArgs) {
        AtomicReference<String> rawRoute = new AtomicReference<>(route.route());
        paramToArgs.forEach((p, a) -> rawRoute.set(param(rawRoute.get(), p, a)));
        return rawRoute.get();
    }

    public String param(String route, String name, String value) {
        Matcher matcher = Pattern.compile("\\{" + name + "\\}").matcher(route);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        if (count == 0) {
            throw new UnirestException("Can't find route parameter name \"" + name + "\"");
        }
        return route.replaceAll("\\{" + name + "\\}", value);
    }

    public String extractId(Map<String, String> paramArgMap) {
        List<String> ids = List.of("name", "id", "uuid", "uid");
        return paramArgMap.entrySet().stream().filter(e -> ids.contains(e.getKey()))
                .map(e -> e.getValue()).findFirst().orElse("id unavailable");
    }

    public <T extends MilkyModel<T>> String scope(TypeToken<T> type) {
        T dummy = dummy(type);
        return Objects.nonNull(dummy) ? dummy.scope() : null;
    }

    public <T extends MilkyModel<T>> T dummy(TypeToken<T> type) {
        Constructor<T> noArgs = noArgsConstructor(type);
        try {
            return noArgs.newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    public <T extends MilkyModel<T>> Constructor<T> noArgsConstructor(TypeToken<T> type) {
        return (Constructor<T>) Arrays.asList(
                type.getRawType()
                        .getConstructors()
        )
                .stream()
                .filter(c -> c.getParameterCount() == 0)
                .findFirst().orElse(null);
    }
}
