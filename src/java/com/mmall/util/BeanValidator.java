
package com.mmall.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mmall.exception.ParamException;
import org.apache.commons.collections.MapUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;


public class BeanValidator {
    //校验工厂
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    /*
     * java使用省略号代替多参数（参数类型... 参数名称）
     * */
    private static <T> Map<String, String> validate(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validate = validator.validate(t, groups);
        if (validate.isEmpty()) {
            return Collections.emptyMap();
        } else {
            /*
            * <dependency>
              <groupId>com.google.guava</groupId>
              <artifactId>guava</artifactId>
              <version>18.0</version>
              </dependency>
            * */
            LinkedHashMap errors = Maps.newLinkedHashMap();
            Iterator<ConstraintViolation<T>> iterator = validate.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation violation = (ConstraintViolation) iterator.next();
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return errors;
        }
    }

    public static Map<String, String> validateList(Collection<?> collection) {
        //google工具类
        Preconditions.checkNotNull(collection);
        Iterator<?> iterator = collection.iterator();
        Map errors;
        do {
            if (!iterator.hasNext()) {
                return Collections.emptyMap();
            }
            Object next = iterator.next();
            errors = validate(next, new Class[0]);
        } while (errors.isEmpty());
        return errors;
    }

    public static Map<String, String> validateObject(Object first, Object... objects) {
        if (objects != null && objects.length > 0) {
            return validateList(Lists.asList(first, objects));
        } else {
            return validate(first, new Class[0]);
        }
    }

    public static void check(Object param) throws ParamException {
        Map<String, String> map = BeanValidator.validateObject(param);
        /*
        * <!--tools-->
    <dependency>
      <groupId>commons-collections</groupId>
      <artifactId>commons-collections</artifactId>
      <version>3.2.2</version>
    </dependency>
    <dependency>
      <groupId>commons-codec</groupId>
      <artifactId>commons-codec</artifactId>
      <version>1.10</version>
    </dependency>
        * */
        if (MapUtils.isNotEmpty(map)) {
            throw new ParamException(map.toString());
        }
    }
}
