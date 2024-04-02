package com.OrderService.OrderService.controller;

import com.OrderService.OrderService.entity.Order;
import com.OrderService.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private RestTemplate restTemplate;

    // Endpoint to search for a book by its title
    @GetMapping("/searchBook/{title}")
    public ResponseEntity<?> searchBook(@PathVariable String title) {
        String uri = "http://localhost:5432/getBookByTitle/" + title;

        try {
            // Send a GET request to the external book service
            ResponseEntity<?> response = restTemplate.getForEntity(uri, Object.class);

            // Handle different HTTP status codes returned by the external service
            if (response.getStatusCode().is2xxSuccessful()) {
                return response; // Return the response if successful
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND); // Return appropriate message if book not found
            } else {
                return new ResponseEntity<>("An error occurred while retrieving the book", HttpStatus.INTERNAL_SERVER_ERROR); // Handle other unexpected errors
            }
        } catch (RestClientException e) {
            return new ResponseEntity<>("Book not found", HttpStatus.SERVICE_UNAVAILABLE); // Handle communication errors with the external service
        }
    }

    // Endpoint to place a new order
    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        // Save the order in the database
        Order savedOrder = orderRepo.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    // Endpoint to retrieve all orders
    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        // Retrieve all orders from the database
        List<Order> orderList = orderRepo.findAll();

        // Return appropriate response based on whether orders were found or not
        if (orderList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return NO_CONTENT status if no orders found
        }

        return new ResponseEntity<>(orderList, HttpStatus.OK); // Return the list of orders with OK status
    }
}
