package head;


class Singleton {
    private Singleton() {
        System.out.println("creat a Singleton");
    }

    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }
}


