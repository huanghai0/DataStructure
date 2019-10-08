package single;

public class SingletonTest {
    public static void main(String[] args) {
       Singleton s1 = Singleton.getIntance();
       Singleton s2 = Singleton.getIntance();
       System.out.println(s1== s2);
       System.out.println(s1.hashCode());
       System.out.println(s2.hashCode());

    }
}

class Singleton {

    private Singleton() {
    }

    private static class SingletonHendle {
        private static Singleton intance = new Singleton();
    }

    public static Singleton getIntance() {
        return SingletonHendle.intance;
    }
}