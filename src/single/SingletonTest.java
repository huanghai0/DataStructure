package single;

public class SingletonTest {
    public static void main(String[] args) {
//        Singleton s1 = Singleton.getIntance();
//        Singleton s2 = Singleton.getIntance();
//        System.out.println(s1 == s2);
//        System.out.println(s1.hashCode());
//        System.out.println(s2.hashCode());

        B s1 = B.getB();
        B s2 = B.getB();
        System.out.println(s1 == s2);
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

    }
}
//
//class Singleton {
//
//    private Singleton() {
//    }
//
//    private static class SingletonHendle {
//        private static Singleton intance = new Singleton();
//    }
//
//    public static Singleton getIntance() {
//        return SingletonHendle.intance;
//    }
//}
//静态内部类构建单例
class B {
    private B() {

    }

    private static class BB {
        private static B b = new B();
    }

    public static B getB() {
        return BB.b;
    }
}













