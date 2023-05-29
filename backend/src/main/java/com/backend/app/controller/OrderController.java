package com.backend.app.controller;

import com.backend.app.model.Order;
import com.backend.app.model.ProductOrder;
import com.backend.app.model.User;
import com.backend.app.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Long.parseLong;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("place-product")
    public ResponseEntity addProductOrder(@RequestBody ProductOrder productOrder){

        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity addOrders(@RequestBody List<Order> orders){
        orderService.addOrders(orders);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public List<Order> getOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("{orderId}/{productId}")
    public ResponseEntity placeOrder(@PathVariable(name = "orderId") Long orderId,@PathVariable(name = "productId") String productId){

        orderService.placeProductOrder(orderId,productId);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("{orderId}")
    public ResponseEntity<Order> getUser(@PathVariable(name = "orderId") Long orderId) {
        return new ResponseEntity<Order>(orderService.getOrderById(orderId), HttpStatus.OK);
    }
    @PostMapping("{orderId}/{status}/{username}")
    public ResponseEntity addStatus(@PathVariable(name = "orderId") Long orderId,@PathVariable(name = "status") String status,@PathVariable(name = "username") String username){

        orderService.addStatus(orderId,status,username);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("status/{currentStatus}")
    public List<Order> getOrderByStatus(@PathVariable(name = "currentStatus")String currentStatus){
        return orderService.getOrdersByStatus(currentStatus);
    }
    @GetMapping("phone/{clientPhone}")
    public  List<Order> getClientByPhone(@PathVariable(name = "clientPhone")String clientPhone){
        return orderService.getOrdersByPhone(clientPhone);
    }
    @GetMapping("count/{currentStatus}")
    public ResponseEntity<Long> count(@PathVariable(name = "currentStatus") String currentStatus){
        return new ResponseEntity<>(orderService.countByStatus(currentStatus),HttpStatus.OK);
    }
    @PutMapping("status/{nextStatus}/{orderId}")
    public ResponseEntity changeStatus(@PathVariable(name = "nextStatus") String nextStatus, @PathVariable(name = "orderId") Long orderId){
        orderService.changeStatus(orderId,nextStatus);
        return new ResponseEntity(HttpStatus.OK);
    }
}
