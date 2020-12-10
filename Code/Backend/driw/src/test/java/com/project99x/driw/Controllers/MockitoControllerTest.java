package com.project99x.driw.Controllers;

import com.project99x.driw.DriwApplication;
import com.project99x.driw.Entities.Product;
import com.project99x.driw.Repositories.ProductRepository;
import com.project99x.driw.Services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class MockitoControllerTest {

    /*@InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository repository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }*/
    private final static String URI = "/product/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;


    /*@Test
    public void testGetProductByUuidforTest(){

        Product mockProduct = new Product(1,"f261b978-dfb2-40e7-9c65-e83edda7ce00", "Penguin-ear",20, 100, 175);
        when(repository.findByUuid("f261b978-dfb2-40e7-9c65-e83edda7ce00")).thenReturn(mockProduct);

        Product product = productController.getProductByUuidforTest("f261b978-dfb2-40e7-9c65-e83edda7ce00");

        verify(repository).findByUuid("f261b978-dfb2-40e7-9c65-e83edda7ce00");

        Assertions.assertEquals("f261b978-dfb2-40e7-9c65-e83edda7ce00", product.getUuid());

    }*/

    @Test
    public void testGetProductByUuid() throws Exception {

        Product product = new Product(1,"f261b978-dfb2-40e7-9c65-e83edda7ce00", "Penguin-ear",20, 100, 175);

        given(productService.getProductByUuid(product.getUuid())).willReturn(product);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI+"{uuid}", "f261b978-dfb2-40e7-9c65-e83edda7ce00")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.uuid").value("f261b978-dfb2-40e7-9c65-e83edda7ce00"));

    }


}
