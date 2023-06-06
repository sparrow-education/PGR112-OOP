package package1;

import package2.ClassB;

public class ClassA {
    public void method() {
        ClassB classB = new ClassB();
        classB.classPublicMethod();
    }

    public static void main(String[] args) {
        ClassA a = new ClassA();
        a.method();
    }
}
