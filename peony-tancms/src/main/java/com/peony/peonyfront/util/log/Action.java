/**
 * 
 */
package com.peony.peonyfront.util.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志切面
 * 
 * @date 2013-10-22 下午01:33:05
 * @author zhaoshutao version 1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Action {

    /**
     * 方法描述
     * 
     * @return
     */
    public String description() default "no description";

    /**
     * 操作类型
     */
    public OperateType operateType() default OperateType.FIND;

    /**
     * 操作模块
     */
    public OperateMode operateMode();

    /**
     * 登录类型
     */
    public LoginType loginType() default LoginType.PC;

    /**
     * 操作内容
     * 
     * @return
     */
    public OperateContent operateContent() default OperateContent.DEFAULT;

    /**
     * 区别登录或操作
     * 
     * @return
     */
    public Type type() default Type.OPERATE;
}
