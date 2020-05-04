package co.edu.ff.orders.products.controller;

import co.edu.ff.orders.products.domain.*;
import co.edu.ff.orders.products.exceptions.ProductDoesNotExists;
import co.edu.ff.orders.products.services.ProductServices;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    private ProductServices services;

    @Test
    @DisplayName("Estas son las pruebas success")
    void findByIdSusses() throws Exception {
        Product product = Product.of(1L,
                Name.of("sadsad"),
                Description.of("asdasd"),
                BasePrice.of(new BigDecimal(14544)),
                TaxRate.of(new BigDecimal(1)),
                ProductStatus.BORRADO,
                InventoryQuantity.of(18));

        when(services.findById(1L)).thenReturn(ProductOperationSuccess.of(product));
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.get("/api/v1/products/1");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Estas son las pruebas error")
    void findByIdFailure() throws Exception {
        when(services.findById(any())).thenReturn(ProductOperationFailure.of(ProductDoesNotExists.of(null)));
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.get("/api/v1/products/q");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Estas son las pruebas Susses")
    void findAllSusses() throws Exception {
        List<Product> products = services.findAll();
        when(services.findAll()).thenReturn(products);
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.get("/api/v1/products");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Estas son las pruebas success")
    void insertOneSuccess() throws Exception {
        ProductOperationRequest productOperationRequest = ProductOperationRequest.of(
                Name.of("sadsad"),
                Description.of("asdasd"),
                BasePrice.of(new BigDecimal(12334)),
                TaxRate.of(new BigDecimal(1)),
                ProductStatus.BORRADO,
                InventoryQuantity.of(18));

        Product product = Product.of(
                1L,
                productOperationRequest.getName(),
                productOperationRequest.getDescription(),
                productOperationRequest.getBasePrice(),
                productOperationRequest.getTaxRate(),
                productOperationRequest.getProductStatus(),
                productOperationRequest.getInventoryQuantity()
                );

        when(services.createProduct(productOperationRequest)).thenReturn(ProductOperationSuccess.of(product));
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(productOperationRequest));
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Estas son las pruebas Failure")
    void insertOneFailure() throws Exception {
        when(services.createProduct(any())).thenReturn(ProductOperationFailure.of(null));
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.post("/api/v1/products");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Estas son las pruebas susses")
    void updateOneSusses() throws Exception {
        ProductOperationRequest productOperationRequest = ProductOperationRequest.of(
                Name.of("sadsad"),
                Description.of("asdasd"),
                BasePrice.of(new BigDecimal(14544)),
                TaxRate.of(new BigDecimal(1)),
                ProductStatus.BORRADO,
                InventoryQuantity.of(18));

        Product product = Product.of(
                1L,
                productOperationRequest.getName(),
                productOperationRequest.getDescription(),
                productOperationRequest.getBasePrice(),
                productOperationRequest.getTaxRate(),
                productOperationRequest.getProductStatus(),
                productOperationRequest.getInventoryQuantity()
        );
        when(services.updateById(1L,productOperationRequest)).thenReturn(ProductOperationSuccess.of(product));
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.put("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(productOperationRequest));
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("estas son las pruebas updateFail")
    void updateFail() throws Exception {
        when(services.updateById(anyLong(),any())).thenReturn(ProductOperationFailure.of(ProductDoesNotExists.of(null)));
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.put("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Estas son las pruebas deleteSuccess")
    void deleteSuccess() throws Exception {
        Product product = Product.of(
                1L,
                Name.of("sadsad"),
                Description.of("asdasd"),
                BasePrice.of(new BigDecimal(14544)),
                TaxRate.of(new BigDecimal(1)),
                ProductStatus.PUBLICADO,
                InventoryQuantity.of(18));

        when(services.deleteById(anyLong())).thenReturn(ProductOperationSuccess.of(product));

        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.delete("/api/v1/products/1");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Estas son las pruebas deleteFail")
    void deleteFail() throws Exception {
        when(services.deleteById(anyLong())).thenReturn(ProductOperationFailure.of(ProductDoesNotExists.of(1L)));

        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.delete("/api/v1/products/");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

}