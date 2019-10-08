package recursive;

public class jiemul {
    public static void main(String[] args) {

       int res = mul(4);
       System.out.println(res);
    }

    public  static int mul(int  n){
        if(n == 1){
            return  1;
        }
        return mul(n-1)*n;
    }

}
