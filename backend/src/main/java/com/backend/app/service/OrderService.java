package com.backend.app.service;

import com.backend.app.model.Order;

import java.io.InputStream;
import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();
    List<Order> getOrdersByPhone(String phone);
    List<Order> getOrderByStatus(String status);
    void confirmOrder(Long id);
    void dispatchOrder(Long id);
    void shipOrder(Long id);
    void cancelOrder(Long id);
    void deliverOrder(Long id);
    void returnOrder(Long id);
    Order addOrder(Order order);
    List<Order> addOrders(List<Order> orders);
    void placeProductOrder(Long orderId , String productId, Integer quantity);
    Order getOrderById(Long orderId);
    List<Order> getOrdersByStatus(String currentStatus);

    void addStatus(Long orderId, String status,String username);
    Long countByStatus(String currentStatus);
    void changeStatus(Long orderId,String nextStatus);
    public  List<Order> getOrdersFromExcel(InputStream inputStream);

}
