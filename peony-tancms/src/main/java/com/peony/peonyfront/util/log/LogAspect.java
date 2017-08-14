/**
 * 
 */
package com.peony.peonyfront.util.log;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.peony.core.common.utils.JsonUtil;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;

/**
 * 切面记录日志
 */
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 前置通知
     * 
     * @param point
     * @throws Throwable
     */
    public void before(JoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName(); // 获取方法名
        Method method = null;

        if (StringUtils.isNotEmpty(methodName)) {
            Method[] methods = point.getTarget().getClass().getDeclaredMethods();
            if (methods != null) {
                // 查询到当前执行方法具体信息，获得annotation信息、记录日志
                for (int i = 0; i < methods.length; i++) {
                    if (methodName.equals(methods[i].getName())) {
                        method = methods[i];
                        if (method != null) {
                            boolean hasAnnotation = method.isAnnotationPresent(Action.class); // 查看方式是否有日志注解
                            if (hasAnnotation) {
                                String[] methodParamNames = getParamName(point.getTarget().getClass(), methodName); // 获得方法参数名称列表
                                String[] methodParamValues = getParamValue(point); // 获得方法参数具体值

                                Action annotation = method.getAnnotation(Action.class); // 获取action对象
                                String description = processDescription(annotation.description(), methodParamNames, methodParamValues); // 日志信息
                                OperateType operateType = annotation.operateType(); // 操作类型
                                                                                    // 增删改查
                                OperateMode operateMode = annotation.operateMode(); // 操作模块
                                LoginType loginType = annotation.loginType();// 登录方式
                                                                             // 1.pc
                                                                             // 2.手机
                                Type type = annotation.type();// 却别是登录还是操作

                                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

                                User user = (User) request.getSession().getAttribute("userfront");

                                if (user != null) {
                                    OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(type.getValue()), String.valueOf(loginType.getValue()), description, operateType.toString(), operateMode.toString());
                                    this.operationLogService.insertSelective(operationLog);
                                }
                            }

                        }
                        break;
                    }
                }

            }
        }

    }

    /**
     * 封装日志描述（将方法参数名称和值加到描述中来）
     * 
     * @param sourceDes
     * @param methodParamNames
     * @param methodParamValues
     * @return
     */
    private String processDescription(String sourceDes, String[] methodParamNames, String[] methodParamValues) {
        StringBuffer description = new StringBuffer(sourceDes);

        if (methodParamNames == null || methodParamNames.length <= 0 || methodParamValues == null || methodParamValues.length <= 0) {
            description.append("[无参数信息]");
        } else {
            description.append("[参数信息:");
            for (int i = 0; i < methodParamNames.length; i++) {
                if (i == methodParamNames.length - 1) {
                    description.append(methodParamNames[i]).append(":").append(methodParamValues[i]).append("]");
                } else {
                    description.append(methodParamNames[i]).append(":").append(methodParamValues[i]).append(",");
                }
            }
        }

        return description.toString();
    }

    /**
     * 获得方法参数值s列表
     * 
     * @param point
     * @return
     */
    private String[] getParamValue(JoinPoint point) {
        String[] paramValue = null;

        Object[] args = point.getArgs();
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            paramValue = new String[args.length];

            if (arg != null) {
                // 如果是基本类型，直接获取值，如果不是基本类型，把该对象序列化成json数据
                if (arg.getClass().isPrimitive()) {
                    paramValue[i] = arg.toString();
                } else {
                    try {
                        paramValue[i] = JsonUtil.toJson(arg);
                    } catch (IOException e) {
                        logger.error(e.toString());
                    }
                }
            }
        }

        return paramValue;
    }

    /**
     * 获得方法参数名称列表
     * 
     * @param obj
     * @param methodName
     * @return
     */
    private String[] getParamName(Class<?> obj, String methodName) {
        String[] paramNames = null;

        try {
            ClassPool classPool = ClassPool.getDefault();
            classPool.insertClassPath(new ClassClassPath(this.getClass()));
            CtClass ctClass = classPool.get(obj.getName());
            CtMethod ctMethod = ctClass.getDeclaredMethod(methodName);

            // 使用javaassist的反射方法获取方法的参数名
            MethodInfo methodInfo = ctMethod.getMethodInfo();
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
            if (attr == null) {
                logger.error("======通过javaassist反射方法参数名称时异常，LocalVariableAttribute is null=========");
            } else {
                paramNames = new String[ctMethod.getParameterTypes().length];
                int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
                for (int i = 0; i < paramNames.length; i++) {
                    paramNames[i] = attr.variableName(i + pos);
                }
            }
        } catch (NotFoundException e) {
            logger.error(e.toString());
        }

        return paramNames;
    }
}
