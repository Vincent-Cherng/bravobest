package javapractice.javaApi;

import javax.script.*;

/**
 * @ClassName ScriptEngineTest
 * @Description
 * @Author chengfeng
 * @Date 2/3/21 5:30 PM
 **/
public class ScriptEngineTest {


    public static void main(String[] args) throws ScriptException {

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
//        String script = "10+((D-parseInt(D/28)*28)/7+1)*10";
        String script="function getNum(num){" +
                "   if(num%7==0){ return 5+5*(parseInt(5*(num-parseInt(num/29)*28)/28)+1)}" +
                "   else{return 5;}} " +
                "getNum(num)";
//        Object eval = engine.eval(script);

        Compilable compilable=(Compilable) engine;
        CompiledScript compile = compilable.compile(script);
        Bindings bindings = engine.createBindings();
        bindings.put("D","7");
        Object eval = compile.eval(bindings);
        System.out.println(eval);


    }
}
