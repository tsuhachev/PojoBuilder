package ua.testing.builder.cache;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author timofey.sukhachev
 */
@ApplicationScoped
public class CacheStorage implements Serializable {

    private Map<Class, Object> classObjectMap = new HashMap<>();

    public void put(Object o) {
        classObjectMap.put(o.getClass(), o);
    }

    public <K> K get(Class<K> aClass) {
        return (K) classObjectMap.get(aClass);
    }

}
