package com.ortecfinance.openapi_generator_demo.controllers;

import com.ortecfinance.openapi_generator_demo.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Orders", description = "Operations related to webshop orders")
public class WebshopOrderController {
    
    private static final Logger logger = LoggerFactory.getLogger(WebshopOrderController.class);

    // In-memory "database" of orders
    private final List<Order> orders = new ArrayList<>();

    public WebshopOrderController() {
        // Initialize with some sample orders
        UUID uuid1 = UUID.randomUUID();
        logger.info(uuid1.toString());
        UUID uuid2 = UUID.randomUUID();
        logger.info(uuid2.toString());
        UUID uuid3 = UUID.randomUUID();
        logger.info(uuid3.toString());
        orders.add(new Order("1", "Laptop", 999.99, "John Doe"));
        orders.add(new Order("2", "Smartphone", 599.99, "Jane Smith"));
        orders.add(new Order("3", "Headphones", 149.99, "Bob Johnson"));
        logger.info("WebshopOrderController initialized with {} sample orders", orders.size());
    }

    // GET all orders
    @Operation(summary = "Get all orders", description = "Returns a list of all available orders")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved order list")
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        logger.info("Retrieving all orders. Found {} orders", orders.size());
        return ResponseEntity.ok(orders);
    }

    // GET order by ID
    @Operation(summary = "Get order by ID", description = "Returns a single order by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found", 
                    content = @Content(schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(
            @Parameter(description = "ID of the order to retrieve") @PathVariable String id) {
        logger.info("Retrieving order with ID: {}", id);
        Optional<Order> order = orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
        
        if (order.isPresent()) {
            logger.info("Order found: {}", order.get());
            return ResponseEntity.ok(order.get());
        } else {
            logger.warn("Order not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    // CREATE new order
    @Operation(summary = "Create a new order", description = "Creates a new order with the provided details")
    @ApiResponse(responseCode = "201", description = "Order successfully created")
    @PostMapping
    public ResponseEntity<Order> createOrder(
            @Parameter(description = "Order data to create") @RequestBody Order order) {
        logger.info("Creating new order: {}", order);
        // Generate a new ID for the order
        order.setId(UUID.randomUUID().toString());
        orders.add(order);
        logger.info("Order created with ID: {}", order.getId());
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    // UPDATE existing order
    @Operation(summary = "Update an existing order", description = "Updates the order with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order successfully updated"),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(
            @Parameter(description = "ID of the order to update") @PathVariable String id,
            @Parameter(description = "Updated order data") @RequestBody Order updatedOrder) {
        logger.info("Updating order with ID: {}", id);
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId().equals(id)) {
                updatedOrder.setId(id); // Ensure ID remains the same
                orders.set(i, updatedOrder);
                logger.info("Order updated successfully: {}", updatedOrder);
                return ResponseEntity.ok(updatedOrder);
            }
        }
        logger.warn("Order not found for update. ID: {}", id);
        return ResponseEntity.notFound().build();
    }

    // DELETE order
    @Operation(summary = "Delete an order", description = "Removes the order with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Order successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(
            @Parameter(description = "ID of the order to delete") @PathVariable String id) {
        logger.info("Attempting to delete order with ID: {}", id);
        boolean removed = orders.removeIf(order -> order.getId().equals(id));
        if (removed) {
            logger.info("Order successfully deleted. ID: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Order not found for deletion. ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
