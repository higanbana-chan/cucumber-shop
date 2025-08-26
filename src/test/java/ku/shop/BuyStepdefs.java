package ku.shop;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BuyStepdefs {

    private ProductCatalog catalog;
    private Order order;
    private Exception lastError;

    @Given("the store is ready to service customers")
    public void the_store_is_ready_to_service_customers() {
        catalog = new ProductCatalog();
        order = new Order();
        lastError = null;
    }

    @Given("a product {string} with price {double} and stock of {int} exists")
    public void a_product_with_price_and_stock_exists(String name, double price, int stock) {
        catalog.addProduct(name, price, stock);
    }

    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) {
        try {
            Product prod = catalog.getProduct(name);
            order.addItem(prod, quantity);
            lastError = null;
        } catch (Exception e) {
            lastError = e;
        }
    }

    @Then("total should be {double}")
    public void total_should_be(double expectedTotal) {
        assertEquals(expectedTotal, order.getTotal(), 0.01);
    }

    @Then("an error should be thrown")
    public void an_error_should_be_thrown() {
        assertNotNull(lastError, "Expected an error but none was thrown");
    }
}