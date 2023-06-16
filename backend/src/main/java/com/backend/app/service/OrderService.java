package com.backend.app.service;

import com.backend.app.model.Order;
import com.backend.app.model.ProductOrder;

import java.io.InputStream;
import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();
    List<Order> getOrdersByPhone(String phone);
    List<Order> getOrderByStatus(String status);
    Order addOrder(Order order);
    void placeProductOrders(List<ProductOrder> productOrders,Long orderId);
    List<Order> addOrders(List<Order> orders);
    void placeProductOrder(Long orderId , String productId, Integer quantity);
    Order getOrderById(Long orderId);
    List<Order> getOrdersByStatus(String currentStatus);

    void addStatus(Long orderId, String status,String username);
    Long countByStatus(String currentStatus);
    void changeStatus(Long orderId,String nextStatus,String typeOfChange);
    public  List<Order> getOrdersFromExcel(InputStream inputStream);
    public  void deleteAllOrders();
    public  void changeStatusAll(List<Order> orders, String nextStatus,String typeOfChange);

}
