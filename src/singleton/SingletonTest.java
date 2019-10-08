package singleton;

import java.io.*;
class  Singleton implements Serializable
{
    private static Singleton instance;
    private String name;
    private Singleton(String name){
        System.out.println("调用有参数的构造器");
        this.name = name;
    }
    public static Singleton getInstance(String name){
        if(instance == null){
            instance = new Singleton(name);
        }
        return instance;
    }
    private Object readResolve()throws ObjectStreamException {
        return instance;
    }
}

 public class SingletonTest{
    public static void main(String[] args)throws Exception{
        Singleton s = Singleton.getInstance("灰太狼");
        System.out.println("Wolf对象创建完成");
        Singleton s2 = null;
        try(
            ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream("a.txt"));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("a.txt"));

        ) {
            oos.writeObject(s);
            oos.flush();
            s2 = (Singleton) ois.readObject();
            System.out.println(s == s2);
        }
    }
}

