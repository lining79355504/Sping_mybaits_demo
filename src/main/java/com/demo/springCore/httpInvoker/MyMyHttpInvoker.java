package com.demo.springCore.httpInvoker;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

/**
 * {@link FactoryBean} for HTTP invoker proxies. Exposes the proxied service
 * for use as a bean reference, using the specified service interface.
 *
 * <p>The service URL must be an HTTP URL exposing an HTTP invoker service.
 * Optionally, a codebase URL can be specified for on-demand dynamic code download
 * from a remote location. For details, see HttpInvokerClientInterceptor docs.
 *
 * <p>Serializes remote invocation objects and deserializes remote invocation
 * result objects. Uses Java serialization just like RMI, but provides the
 * same ease of setup as Caucho's HTTP-based Hessian and Burlap protocols.
 *
 * <p><b>HTTP invoker is the recommended protocol for Java-to-Java remoting.</b>
 * It is more powerful and more extensible than Hessian and Burlap, at the
 * expense of being tied to Java. Nevertheless, it is as easy to set up as
 * Hessian and Burlap, which is its main advantage compared to RMI.
 *
 * <p><b>WARNING: Be aware of vulnerabilities due to unsafe Java deserialization:
 * Manipulated input streams could lead to unwanted code execution on the server
 * during the deserialization step. As a consequence, do not expose HTTP invoker
 * endpoints to untrusted clients but rather just between your own services.</b>
 *
 * @author mort
 * @since 1.1
 * @see #setServiceInterface
 * @see #setServiceUrl
 * @see #setCodebaseUrl
 * @see MyHttpInvokerClientInterceptor
 * @see HttpInvokerServiceExporter
 * @see org.springframework.remoting.rmi.RmiProxyFactoryBean
 * @see org.springframework.remoting.caucho.HessianProxyFactoryBean
 * @see org.springframework.remoting.caucho.BurlapProxyFactoryBean
 */
public class MyMyHttpInvoker extends MyHttpInvokerClientInterceptor
        implements FactoryBean<Object> {

    private Object serviceProxy;


    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        if (getServiceInterface() == null) {
            throw new IllegalArgumentException("Property 'serviceInterface' is required");
        }
        this.serviceProxy = new ProxyFactory(getServiceInterface(), this).getProxy(getBeanClassLoader());
    }


    @Override
    public Object getObject() {
        return this.serviceProxy;
    }

    @Override
    public Class<?> getObjectType() {
        return getServiceInterface();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}

