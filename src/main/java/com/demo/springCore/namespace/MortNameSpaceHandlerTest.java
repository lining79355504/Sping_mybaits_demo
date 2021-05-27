package com.demo.springCore.namespace;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.stereotype.Component;

/**
 * @author mort
 * @Description
 * @date 2021/5/25
 *
 * Spring在解析xml文件时，主要用到NamespaceHandlerResolver接口、NamespaceHandler接口、BeanDefinitionParser接口。
 * NamespaceHandlerResolver接口，是为了获取BeanDefinitionParser接口NamespaceHandler接口实例
 *
 * 可参照 AopNamespaceHandler
 *
 *
<aop:config>
    <aop:aspect id="aspect" ref="xmlHandler">
        <aop:pointcut id="pointUserMgr" expression="execution(* com.tgb.aop.*.find*(..))"/>
        <aop:before method="doBefore"  pointcut-ref="pointUserMgr"/>
    </aop:aspect>
</aop:config>
 *
 **/
public class MortNameSpaceHandlerTest extends NamespaceHandlerSupport {

    @Override
    public void init() {

    }
}
