package by.teachmeskills.homeworks.hw_10032023.part2.shop;

public class Product implements Comparable<Product> {
    private int id;
    private int price;
    private String name;

    {
        id = 0;
        price = 0;
        name = "unknown";
    }

    public Product() {

    }

    public Product(int id, int price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Id " + id + "; price: " + price + "; name: " + name;
    }

    @Override
    public int compareTo(Product product) {
        return this.price - product.price;
    }
}
