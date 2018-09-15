package pxlgen.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Name.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 14/09/2018
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Name {

    String value() default "";
}
