package head;

public class StaticSingleton {
    private StaticSingleton() {
        System.out.println("StaticSingleton is creat");
    }

    private static class StaticHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance() {
        return StaticHolder.instance;
    }
}
