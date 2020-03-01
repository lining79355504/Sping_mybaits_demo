import com.demo.groovy.ScriptEngineTest
import com.demo.groovy.TestBean



//支持 map bean DTO
def test(testBean) {
    return testBean.id + testBean.age
}


//def test(def testBean) {
//    return testBean.id + testBean.age
//}

//支持bean
def testBean(TestBean testBean) {
    return testBean.getId() + testBean.getAge()
}


def testComplex(obj, c) {
    int sum = 0;
    obj.list.each {
        sum += it
    }
    return sum + c.age;
}

def test(obj, c) {
    int sum = 0;
    obj.list.each {
        sum = sum + it
    }
    return sum + c.age;
}

def combine(Object... objs){
    int sum = 0 ;
    objs.each {
        def map = ScriptEngineTest.buildMap();
        if(it instanceof TestBean) {
            sum += testComplex(it, map);
        }
    }
    println "${sum}"
    return sum;
}

public static void main(String[] args) {
    def bean = ScriptEngineTest.generateTestBean();
    println combine(bean);
    println combine(bean,bean,bean);
}



/*
list map 迭代
public List<Map<String, Object>> ClobToStringList(List<Map<String, Object>> objList) {
    objList.each {
        it.each {
            key, value ->
                if (value instanceof Clob) {
                    //将clob转字符串
                    value = utilSqlDateChange.ClobToString(value);
                    it.put(key, value);
                }
        }
    }
    return objList;
}
*/

