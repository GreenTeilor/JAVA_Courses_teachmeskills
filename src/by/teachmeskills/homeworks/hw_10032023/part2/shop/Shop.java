package by.teachmeskills.homeworks.hw_10032023.part2.shop;

import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EmptyProductListException;
import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EntityAlreadyExistsException;
import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EntityNotFoundException;

import java.util.Arrays;
import java.util.Collections;

public class Shop {
    private Product[] products;

    public Shop() {
        products = new Product[0];
    }

    public void add(Product newProduct) throws EntityAlreadyExistsException {
        for (Product product : products) {
            if (product.getId() == newProduct.getId()) {
                throw new EntityAlreadyExistsException("The product exists");
            }
        }
        Product[] updatedProducts = new Product[products.length + 1];
        System.arraycopy(products, 0, updatedProducts, 0, products.length);
        updatedProducts[updatedProducts.length - 1] = newProduct;
        products = updatedProducts;
    }

    public Product[] getAllProducts() throws EmptyProductListException {
        if (products.length == 0) {
            throw new EmptyProductListException("No products found");
        }
        return products;
    }

    public void remove(int id) throws EmptyProductListException, EntityNotFoundException {
        if (products.length == 0)
            throw new EmptyProductListException("There is nothing to remove");
        Product[] result = new Product[products.length - 1];
        for (int i = 0; i < products.length; ++i) {
            if (products[i].getId() == id) {
                System.arraycopy(products, 0, result, 0, i);
                System.arraycopy(products, i + 1, result, i, products.length - i - 1);
                products = result;
                return;
            }
        }
        throw new EntityNotFoundException("Product with <id=" + id + "> not found");
    }

    public void edit(int id, int newPrice, String newName) throws EmptyProductListException, EntityNotFoundException {
        if (products.length == 0)
            throw new EmptyProductListException("There is nothing to remove");
        for (Product product : products) {
            if (product.getId() == id) {
                product.setPrice(newPrice);
                product.setName(newName);
                return;
            }
        }
        throw new EntityNotFoundException("Product with <id=" + id + "> not found");
    }

    public void sortInAscendingOrder() {
        Arrays.sort(products);
    }

    public void sortInDescendingOrder() {
        Arrays.sort(products, Collections.reverseOrder());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Product product : products) {
            result.append(product).append("\n");
        }
        return result.toString();
    }
}
