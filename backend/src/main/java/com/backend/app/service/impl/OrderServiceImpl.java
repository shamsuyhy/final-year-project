package com.backend.app.service.impl;

import com.backend.app.exception.ResourceNotFoundException;
import com.backend.app.model.*;
import com.backend.app.repository.OrderRepository;
import com.backend.app.repository.ProductOrderRepository;
import com.backend.app.service.OrderService;
import com.backend.app.service.ProductService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "yahia");

    }

    OrderRepository orderRepository;
    ProductOrderRepository productOrderRepository;
    ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository, ProductOrderRepository productOrderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productOrderRepository = productOrderRepository;
        this.productService = productService;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByPhone(String phone) {
        return orderRepository.findOrderByClientPhone(phone);
    }

    @Override
    public List<Order> getOrderByStatus(String status) {
        return null;
    }

    @Override
    public void confirmOrder(Long id) {

    }

    @Override
    public void dispatchOrder(Long id) {

    }

    @Override
    public void shipOrder(Long id) {

    }

    @Override
    public void cancelOrder(Long id) {

    }

    @Override
    public void deliverOrder(Long id) {

    }

    @Override
    public void returnOrder(Long id) {

    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> addOrders(List<Order> orders) {
        orders.forEach(data -> data.addStatus(new Status(data, "Admin", LocalDateTime.now(), "in-confirmation", "created")));
        return orderRepository.saveAll(orders);
    }

    @Override
    public void placeProductOrder(Long orderId, String productId, Integer quantity) {
        Order order = getOrderById(orderId);
        Product product = productService.getProductById(productId);
        order.addProduct(new ProductOrder(new ProductOrderId(orderId, productId), order, product, quantity, product.getProductPrice()));
        orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new ResourceNotFoundException("User", "username", orderId);
        }
    }

    @Override
    public List<Order> getOrdersByStatus(String currentStatus) {
        return orderRepository.findOrderByCurrentStatus(currentStatus);
    }

    @Override
    public void addStatus(Long orderId, String status, String username) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Order order = getOrderById(orderId);
        order.addStatus(new Status(order, username, localDateTime, status, ""));
        orderRepository.save(order);

    }

    @Override
    public Long countByStatus(String currentStatus) {
        return orderRepository.countOrderByCurrentStatus(currentStatus);
    }

    @Override
    public void changeStatus(Long orderId, String nextStatus) {
        Order order = getOrderById(orderId);
        order.setCurrentStatus(nextStatus);
        order.setCurrentStatusDate(LocalDateTime.now());
        order.addStatus(new Status(order, "admin", LocalDateTime.now(), nextStatus, ""));
        orderRepository.save(order);

    }

    @Override
    public List<Order> getOrdersFromExcel(InputStream inputStream) {
        List<Order> orders = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Order order = new Order();
                String productId = "";
                Long orderId;
                Integer quantity = 1;

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> order.setClientName(cell.getStringCellValue());


                        case 1 -> order.setClientPhone(cell.getStringCellValue());

                        case 2 -> order.setWilaya(cell.getStringCellValue());
                        case 3 ->
                            order.setComune(cell.getStringCellValue());

                        case 4 ->
                            productId =  cell.getStringCellValue();

                        case 5 -> quantity = (int) cell.getNumericCellValue();
                        default -> {

                        }
                    }
                    cellIndex++;
                }
                orderId = orderRepository.save(order).getOrderId();
                placeProductOrder(orderId, productId, quantity);

            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return orders;
    }

}
