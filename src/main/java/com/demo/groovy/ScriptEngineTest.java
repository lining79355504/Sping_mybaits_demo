package com.demo.groovy;

import com.alibaba.fastjson.JSON;
import groovy.lang.GroovyObject;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.IOException;
import java.util.*;

/**
 * Author:  lining17
 * Date :  2020-03-01
 */
public class ScriptEngineTest {


    public  void runScriptFile() {
        String[] roots = new String[]{"src/main/java/com/demo/groovy"};
        GroovyScriptEngine engine;
        try {
            engine = new GroovyScriptEngine(roots);
            GroovyObject groovyObject = (GroovyObject) engine.loadScriptByName("Script.groovy").newInstance();

            TestBean testBean = generateTestBean();

            Object ret = groovyObject.invokeMethod("test", testBean);
            System.out.println(ret);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResourceException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public static TestBean generateTestBean() {
        TestBean testBean = new TestBean();
        testBean.setId(2);
        testBean.setAge(10);
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        testBean.setList(list);
        return testBean;
    }

    public static Map<String, Integer> buildMap() {
        Map<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("id",10);
        hashMap.put("age",5);
        return hashMap;
    }

    public Object runScriptString(String script, String funName, Object... params) {
            try {
                ScriptEngineManager factory = new ScriptEngineManager();
                ScriptEngine engine = factory.getEngineByName("groovy");

                engine.eval(script);
                Invocable inv = (Invocable) engine;
                return inv.invokeFunction(funName, params);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        }




    public static void main(String[] args) {

        ScriptEngineTest scriptEngine = new ScriptEngineTest();
        scriptEngine.runScriptFile();
        /*
        注意groovy entity不需要在参数类型里声明参数类型 可以是空
        map 一样可以用"."号访问。
        理论上所有的复杂对象都可以通过 groovy实现访问。
        */

        String script = "def test(testBean) {\n" +
                "    return testBean.id + testBean.age\n" +
                "}";
        String functionName = "test";
        //param is bean
        TestBean bean = scriptEngine.generateTestBean();
        Object ret = scriptEngine.runScriptString(script, functionName, bean);
        System.out.println(JSON.toJSON(ret));
        // Param is map
        Map<String, Integer> hashMap = buildMap();
        ret = scriptEngine.runScriptString(script, functionName, hashMap);
        System.out.println(JSON.toJSON(ret));

        //param is complex Object

        script = "def testComplex(obj, c) {\n" +
                "    int sum = 0;\n" +
                "    obj.list.each {\n" +
                "        sum += it\n" +
                "    }\n" +
                "    return sum + c.age;\n" +
                "}";

        ret = scriptEngine.runScriptString(script, "testComplex", bean, hashMap);
        System.out.println(JSON.toJSON(ret));

    }


}
