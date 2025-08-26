package ku.shop;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
    private List<Product> products;

    public ProductCatalog() {
        this.products = new ArrayList<>();
    }

    public void addProduct(String name, double price, int stock) {
        Product product = new Product(name, price, stock);
        products.add(product);
    }

    public Product getProduct(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        throw new IllegalArgumentException("Product not found: " + name);
    }

    public List<Product> getProducts() {
        return products;
    }
}