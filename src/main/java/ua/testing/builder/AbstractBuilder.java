package ua.testing.builder;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author timofey.sukhachev
 */
public abstract class AbstractBuilder<K> implements Serializable {

    /**
     * creates object and copies all the data set to a builder to an object
     *
     * @return instance of an object specified
     */
    public K build() {
        K k = createInstance();
        BuilderUtils.fromBuilderToObject(k, this);
        return k;
    }

    public K build(Enum anEnum) {
        K k = createInstance();
        BuilderUtils.fromEnumToBuilder(this, anEnum);
        BuilderUtils.fromBuilderToObject(k, this);
        return k;
    }

    private K createInstance() {
        Class aClass = getClass();
        while (aClass.getSuperclass() != AbstractBuilder.class) {
            aClass = aClass.getSuperclass();
        }
        Type actualTypeArgument = ((ParameterizedType) aClass.getGenericSuperclass()).getActualTypeArguments()[0];
        try {
            return (K) ((Class) actualTypeArgument).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            // throwing later with non checked exception
            throw new IllegalStateException("Instantiation of " + actualTypeArgument.getTypeName() + " by default request method failed");
        }
    }

}
