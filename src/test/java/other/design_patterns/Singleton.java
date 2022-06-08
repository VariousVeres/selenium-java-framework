package other.design_patterns;

import org.testng.annotations.Test;

class Example {
    private String str;

    private Example(String s) {
        this.str = s;
    }

    private static Example instance;

    public static Example initializeClass(String s) {
        if (instance == null) {
            instance = new Example(s);
        }
        return instance;
    }

    public void sayHi() {
        System.out.println("Say Hi! - " + str);
    }

}

public class Singleton {
    @Test
    public void main() {
        Example v = Example.initializeClass("Veres");
        //Тут вже не створить новий інстанс бо є вже створений
        Example p = Example.initializeClass("Petro");
        v.sayHi();
        p.sayHi();
    }
}



