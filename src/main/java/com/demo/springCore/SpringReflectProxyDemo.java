package com.demo.springCore;

import com.demo.springCore.annotation.OpenApiVerify;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import javax.servlet.*;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author mort
 * @Description
 * @date 2020/12/17
 *  根据Method 上的 annotation 获取对应的spring 代理bean 和method , 并通过反射执行
 *
 * 注意   Field ob= ReflectionUtils.findField(os.getClass(),"ob");
 * AopUtils 用法
 *
 * 可参考 https://juejin.cn/post/6844903858393595917
 **/
public class SpringReflectProxyDemo implements ApplicationListener, ApplicationContextAware , Filter {


    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

//        AopUtils

        // 根据Method 上的 annotation 获取对应的spring 代理bean 和method , 并通过反射执行
        Class<? extends Annotation> annotationClass = OpenApiVerify.class;
        for (String definitionName : applicationContext.getBeanDefinitionNames()) {
            try {
                Object bean = applicationContext.getBean(definitionName);
                Method[] methods = bean.getClass().getMethods();
                for (Method method : methods) {
                    boolean annotationPresent = method.isAnnotationPresent(annotationClass);
                    if (annotationPresent) {
                        // map <>  key annotation 的value ，map value is 对应的可反射执行的
                    }
                }
                String simpleName = bean.getClass().getSimpleName();
            } catch (Exception e) {

            }

        }


    }




    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


            /*

    // 从Spring中获取代理对象（可能被JDK或者CGLIB代理）
        Object proxyObject = applicationContext.getBean(classz);

        // 获取代理对象执行的方法
        Method method = getMethod(proxyObject.getClass(), methodName);

            // 获取代理对象中的目标对象
        Class target = AopUtils.getTargetClass(proxyObject);
           // 获取目标对象的方法，为什么获取目标对象的方法：只有目标对象才能通过 DefaultParameterNameDiscoverer 获取参数的方法名，代理对象由于可能被JDK或CGLIB代理导致获取不到参数名
        Method targetMethod = getMethod(target, methodName);

        // 执行方法
        method.invoke(proxyObject, objects.toArray());
     */
    }

    @Override
    public void destroy() {

    }
}
