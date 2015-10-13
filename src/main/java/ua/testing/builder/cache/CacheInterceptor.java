package ua.testing.builder.cache;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author timofey.sukhachev
 */
@Interceptor
@Cache
public class CacheInterceptor implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(CacheInterceptor.class);

    @Inject
    private CacheStorage cacheStorage;

    @AroundInvoke
    public Object cacheMethodEntry(InvocationContext ctx) throws Exception {
        Method method = ctx.getMethod();
        Class<?> returnType = method.getReturnType();
        LOGGER.info("Return type: " + returnType.getName());
        Object cachedObject = cacheStorage.get(returnType);
        if (cachedObject != null) {
            LOGGER.info("Getting object " + cachedObject.getClass().getSimpleName() + " from cache");
            return cachedObject;
        }
        Object objectToCache = ctx.proceed();
        String methodAtClass = method.getName() + "@"
                + method.getDeclaringClass().getSimpleName();
        if (objectToCache == null) {
            LOGGER.error("Caching null for " + methodAtClass);
        }

        cacheStorage.put(objectToCache);
        return objectToCache;
    }
}

