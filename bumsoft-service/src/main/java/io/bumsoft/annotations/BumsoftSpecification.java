package io.bumsoft.annotations;


@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Documented
@org.springframework.stereotype.Component
public @interface BumsoftSpecification {
    @org.springframework.core.annotation.AliasFor(annotation = org.springframework.stereotype.Component.class)
    java.lang.String value() default "";
}
