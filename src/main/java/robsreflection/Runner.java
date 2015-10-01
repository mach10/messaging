package robsreflection;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * User: robshield
 * Date: 26/12/2011
 * Time: 12:16
 */
public class Runner {


    public static final String RIGHT = "<<<";
    public static final String LEFT = ">>>";

    public static void main(String... args) throws ClassNotFoundException {
        Runner runner = new Runner();
        
        runner.runGetClassOnString();
        runner.runGetClassOnHashSet();
        runner.runGetClassOnBooleanPrimativeType();
        
        runner.say(Class.forName("robsreflection.Runner"));
    }

    private void runGetClassOnBooleanPrimativeType() {
        say(boolean.class);
    }

    private void runGetClassOnHashSet() {
        Set<String> s = new HashSet<String>();
        say(s.getClass());
        say(HashSet.class);
    }

    private void runGetClassOnConsole() {
        this.say(System.console().getClass());
    }

    private void runGetClassOnString() {
        Class c = "test".getClass();
        this.say(c);
    }

    private void say(Class c) {
        System.out.println(LEFT +c+ RIGHT);
        
        Method[] methods = c.getDeclaredMethods()  ;
        for(Method m: methods){
            System.out.println(m.getName()+"\n");
        }
    }


}
