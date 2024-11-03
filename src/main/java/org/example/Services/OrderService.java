package org.example.Services;

import org.example.Entities.*;
import org.example.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        try {
            System.out.println("Attempting to save order: " + order);
            return orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order updateOrder(Order order) {
        if (orderRepository.existsById(order.getId())) {
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Order with ID " + order.getId() + " not found");
        }
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
