//package com.mmall.util;
//
//import com.google.common.base.Preconditions;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import com.mmall.exception.ParamException;
//import org.apache.commons.collections.MapUtils;
//
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import java.util.*;
//
//public class BeanValidator {
//
//    //定义全局的校验工厂
//    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//
//    /*
//     * java使用省略号代替多参数（参数类型... 参数名称）
//     * */
//    public static <T> Map<String, String> validate(T t, Class... groups) {
//        Validator validator = validatorFactory.getValidator();
//        //自动的获取一个校验结果
//        Set validateResult = validator.validate(t, groups);
//        //判断当前校验是否有值
//        //如果是空值的话
//        if (validateResult.isEmpty()) {
//            //返回一个空的值
//            return Collections.emptyMap();
//            //如果不是空值
//        } else {
//            LinkedHashMap errors = Maps.newLinkedHashMap();
//            //将值遍历出来
//            Iterator iterator = validateResult.iterator();
//            while (iterator.hasNext()) {
//                ConstraintViolation violation = (ConstraintViolation) iterator.next();
//                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
//            }
//            return errors;
//        }
//    }
//
//    //可能传进去的是list
//    public static Map<String, String> validateList(Collection<?> collection) {
//        //判断当前collection是否为空
//        Preconditions.checkNotNull(collection);
//        //遍历collections
//        Iterator iterator = collection.iterator();
//        Map errors;
//
//        do {
//            //判断是否有数据
//            if (!iterator.hasNext()) {
//                //没有数据的话，直接返回一个空值
//                return Collections.emptyMap();
//            }
//            //如果不为空的话，把值取出来
//            Object object = iterator.next();
//            errors = validate(object, new Class[0]);
//        } while (errors.isEmpty());//判断errors是否为空
//
//        return errors;
//    }
//
//    //随便传入的什么对象
//    public static Map<String, String> validateObject(Object first, Object... objects) {
//
//        if (objects != null && objects.length > 0) {
//
//            return validateList(Lists.asList(first, objects));
//        } else {
//            return validate(first, new Class[0]);
//        }
//    }
//
//    public static void check(Object param) throws ParamException {
//        Map<String, String> map = BeanValidator.validateObject(param);
//        if (MapUtils.isEmpty(map)) {
//            throw new ParamException(map.toString());
//        }
//    }
//}
//
//
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
