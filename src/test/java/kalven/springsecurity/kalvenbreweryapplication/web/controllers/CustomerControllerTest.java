package kalven.springsecurity.kalvenbreweryapplication.web.controllers;

import kalven.springsecurity.kalvenbreweryapplication.domain.Customer;
import kalven.springsecurity.kalvenbreweryapplication.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    @Mock
    CustomerRepository customerRepository;
    @InjectMocks
    CustomerController customerController;
    List<Customer> customerList;
    UUID uuid;
    Customer customer;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        customerList = new ArrayList<>();
        customerList.add(Customer.builder().customerName("Andy Jesse").build());
        customerList.add(Customer.builder().customerName("Steve Jobs").build());

        final String id = "493410b3-dd0b-4b78-97bf-289f50f6e74";
        uuid = UUID.fromString(id);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void findCustomers() {
    }

    @Test
    void processFindFormReturnMany() {
    }

    @Test
    void showCustomer() {
    }

    @Test
    void initCreationForm() {
    }

    @Test
    void processCreationForm() {
    }

    @Test
    void initUpdateCustomerForm() {
    }

    @Test
    void processUpdationForm() {
    }
}