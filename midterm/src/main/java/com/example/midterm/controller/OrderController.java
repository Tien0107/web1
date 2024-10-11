package com.example.midterm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PatchMapping;

import com.example.midterm.service.OrderService;
import com.example.midterm.service.UserService;

import jakarta.validation.Valid;

// import com.example.midterm.repository.UserRepository;
import com.example.midterm.model.Order;
import com.example.midterm.model.User;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    // private UserService userService;

    public OrderController(){
    }

    // Get all Users    
    @GetMapping("/orders")
    @ResponseBody
    public List<Order> getOrderList() {
        return orderService.findAll();
    }

    // Get user by id
    @GetMapping("orders/{id}")
    @ResponseBody
    public ResponseEntity<Order> getOrderbyId(@PathVariable("id") int userId) {
        for (Order order : orderService.findAll()) {
            if (order.getOrderId() == userId) {
                return ResponseEntity.status(200).body(order);
            }
        }
        return ResponseEntity.status(404).body(null);
    }
    
    // create new order
    // @PostMapping("/orders")
    // public ResponseEntity<Order> createOrder(@RequestBody Order newOrder){
    //     orderService.save(newOrder);
    //     return ResponseEntity.status(201).body(newOrder);
    // }

//create
    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order newOrder) {
        Order createdOrder = orderService.save(newOrder);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    
    //put
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long orderId, @RequestBody Order updateOrder) {
        Order order = orderService.findById(orderId);
        if (order != null) {
            order.setTotalAmount(updateOrder.getTotalAmount());
            order.setOrderStatus(updateOrder.getOrderStatus());
            order.setShippingAddress(updateOrder.getShippingAddress());
            order.setShippingCity(updateOrder.getShippingCity());
            order.setShippingCountry(updateOrder.getShippingCountry());
            order.setShippingPostalCode(updateOrder.getShippingPostalCode());
            order.setPaymentMethod(updateOrder.getPaymentMethod());
            orderService.save(order);  // Lưu lại bản ghi đã cập nhật
            return ResponseEntity.status(200).body(order);
        }
        return ResponseEntity.status(404).body(null);
    }


    //patch
    @PatchMapping("/orders/{id}")
    @ResponseBody
    public ResponseEntity<Order> patchOrder(@PathVariable("id") Long orderId, @RequestBody Order patchUpdate) {
        Order order = orderService.findById(orderId);
        if (order != null) {
            if (patchUpdate.getTotalAmount() != null) {
                order.setTotalAmount(patchUpdate.getTotalAmount());
            }
            if (patchUpdate.getOrderStatus() != null) {
                order.setOrderStatus(patchUpdate.getOrderStatus());
            }
            if (patchUpdate.getShippingAddress() != null) {
                order.setShippingAddress(patchUpdate.getShippingAddress());
            }
            if (patchUpdate.getShippingCity() != null) {
                order.setShippingCity(patchUpdate.getShippingCity());
            }
            if (patchUpdate.getShippingCountry() != null) {
                order.setShippingCountry(patchUpdate.getShippingCountry());
            }
            if (patchUpdate.getShippingPostalCode() != null) {
                order.setShippingPostalCode(patchUpdate.getShippingPostalCode());
            }
            if (patchUpdate.getPaymentMethod() != null) {
                order.setPaymentMethod(patchUpdate.getPaymentMethod());
            }
            orderService.save(order);  // Lưu lại bản ghi đã cập nhật
            return ResponseEntity.status(200).body(order);
        }
        return ResponseEntity.status(404).body(null);
    }

     //delete
    @DeleteMapping("/orders/{id}")
    @ResponseBody
    public List<Order> removeOrderById(@PathVariable("id") Long orderId){
        orderService.delete(orderId);
        return orderService.findAll();
    }
}
