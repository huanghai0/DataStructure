package head;

public class Product {
    private final String name;
    private final String no;
    private final double price;

    public Product(String name, String no, double price) {
        this.name = name;
        this.no = no;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getNo() {
        return no;
    }

    public double getPrice() {
        return price;
    }
}
