package ua.testing.builder;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

/**
 * @author timofey.sukhachev
 */
public class BuilderUtils {

    private BuilderUtils() {
    }

    /**
     * copies all the properties of enum value to destination builder object if fields have same type and a field of a builder is not set yet
     *
     * @param dest object
     * @param src  enum value
     * @param <K>  extends AbstractBuilder
     */
    public static <K extends AbstractBuilder, E extends Enum<E>> void fromEnumToBuilder(K dest, E src) {
        if (src != null && dest != null) {
            PropertyDescriptor[] origDescriptors =
                    PropertyUtils.getPropertyDescriptors(src);
            for (PropertyDescriptor origDescriptor : origDescriptors) {
                Class<?> propertyType = origDescriptor.getPropertyType();
                if (!propertyType.isInstance(Class.class)) {
                    try {
                        Field field = FieldUtils.getField(dest.getClass(), origDescriptor.getName(), true);
                        if (field.getType().equals(origDescriptor.getPropertyType())) {
                            Object value = Optional.ofNullable(field.get(dest))
                                    .orElse(origDescriptor.getReadMethod().invoke(src));
                            field.set(dest, value);
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        // just ignore fields' mismatch
                    }
                }
            }
        }
    }

    /**
     * copies values from builder fields to object properties if their name and type match
     *
     * @param dest destination object
     * @param src  source builder
     * @param <S>  extends AbstractBuilder
     * @param <D>  domain object
     */
    public static <S extends AbstractBuilder, D> void fromBuilderToObject(D dest, S src) {
        Field[] allFields = FieldUtils.getAllFields(dest.getClass());
        for (Field destField : allFields) {
            if (!destField.getType().isInstance(Class.class)) {
                destField.setAccessible(true);
                try {
                    Field srcField = FieldUtils.getField(src.getClass(), destField.getName(), true);
                    if (srcField != null && srcField.getType().equals(destField.getType())) {
                        destField.set(dest, srcField.get(src));
                    }
                } catch (IllegalAccessException e) {
                    // just ignore fields' mismatch
                }
            }
        }
    }

    /**
     * copy from scr to destination if property of source is not null
     *
     * @param dest object
     * @param src  object
     */
    public static void copyProperties(Object dest, Object src) {
        PropertyDescriptor[] origDescriptors =
                PropertyUtils.getPropertyDescriptors(dest);
        for (PropertyDescriptor origDescriptor : origDescriptors) {
            try {
                String propertyName = origDescriptor.getName();
                Object srcProperty = PropertyUtils.getProperty(src, propertyName);
                if (srcProperty != null) {
                    PropertyUtils.setProperty(dest, propertyName, srcProperty);
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                // ignore mismatch
            }
        }
    }

    public static <K> K createInstance(Class builder) {
        Class aClass = builder.getClass();
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