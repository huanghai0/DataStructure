package head;

public class LazySingleton {
    private LazySingleton() {
        System.out.println("LazySingleton is creat");
    }

    private static LazySingleton instance = null;

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();

        }
        return instance;
    }
}
