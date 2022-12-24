package ge.tsu.shoppingcartproject.model;

public class Product {
    Integer id;
    String name;
    String complexity;
    double price;
    String image;

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String name, String complexity, double price, String image) {
        this.id = id;
        this.name = name;
        this.complexity = complexity;
        this.price = price;
        this.image = image;
    }

    public Product() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", complexity='" + complexity + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
