package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testCreateWithNullId() {
        // Test covering the branch: if (product.getProductId() == null)
        Product product = new Product();
        product.setProductName("Product No ID");
        product.setProductQuantity(10);

        productRepository.create(product);

        assertNotNull(product.getProductId());
    }

    @Test
    void testFindByIdFound() {
        Product product = new Product();
        product.setProductId("uuid-1");
        productRepository.create(product);

        Product found = productRepository.findById("uuid-1");
        assertNotNull(found);
    }

    @Test
    void testFindByIdNotFound() {
        Product found = productRepository.findById("uuid-random-404");
        assertNull(found);
    }

    @Test
    void testUpdateSuccess() {
        Product product = new Product();
        product.setProductId("uuid-1");
        product.setProductName("Original Name");
        productRepository.create(product);

        Product updatedData = new Product();
        updatedData.setProductId("uuid-1");
        updatedData.setProductName("New Name");
        updatedData.setProductQuantity(99);

        Product result = productRepository.update(updatedData);

        assertNotNull(result);
        assertEquals("New Name", result.getProductName());
        assertEquals(99, result.getProductQuantity());
    }

    @Test
    void testUpdateNotFound() {

        Product product = new Product();
        product.setProductId("uuid-1");

        Product result = productRepository.update(product);
        assertNull(result);
    }

    @Test
    void testDeleteSuccess() {
        Product product = new Product();
        product.setProductId("uuid-1");
        productRepository.create(product);

        productRepository.delete("uuid-1");

        assertNull(productRepository.findById("uuid-1"));
    }

    @Test
    void testDeleteNotFound() {

        productRepository.delete("uuid-random");
        Iterator<Product> iterator = productRepository.findAll();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testFindByIdNotFoundWhenDataExists() {
        // Data ada, tapi ID yang dicari tidak ada
        Product product1 = new Product();
        product1.setProductId("uuid-1");
        product1.setProductName("Sampo");
        productRepository.create(product1);

        Product found = productRepository.findById("uuid-zombie");
        assertNull(found);
    }

    @Test
    void testUpdateNotFoundWhenDataExists() {
        // Data ada, tapi ID yang mau diupdate tidak ada
        Product product1 = new Product();
        product1.setProductId("uuid-1");
        product1.setProductName("Sampo");
        productRepository.create(product1);

        Product productZ = new Product();
        productZ.setProductId("uuid-zombie");
        productZ.setProductName("Sabun");

        Product result = productRepository.update(productZ);
        assertNull(result);
    }
}