package com.backend.app.controller;

import com.backend.app.model.Order;
import com.backend.app.model.ProductOrder;
import com.backend.app.model.User;
import com.backend.app.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static java.lang.Long.parseLong;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Authorization")
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
    @PostMapping("status/orders/{nextStatus}/{typeOfChange}")
    public ResponseEntity changeStatusAll(@RequestBody List<Order> orders, @PathVariable(name = "nextStatus") String nextStatus,@PathVariable(name = "typeOfChange")String typeOfChange){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName="yahia";
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();

        }

        orderService.changeStatusAll(orders,nextStatus,typeOfChange);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public List<Order> getOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("{orderId}/{productId}/{quantity}")
    public ResponseEntity placeOrder(@PathVariable(name = "orderId") Long orderId,@PathVariable(name = "productId") String productId,@PathVariable(name = "quantity") Integer quantity){

        orderService.placeProductOrder(orderId,productId,quantity);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("{orderId}")
    public ResponseEntity<Order> getUser(@PathVariable(name = "orderId") Long orderId) {
        return new ResponseEntity<Order>(orderService.getOrderById(orderId), HttpStatus.OK);
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

    @PostMapping("excel")
    public ResponseEntity updloadOrders(@RequestParam("file")MultipartFile file){
        try {
            orderService.getOrdersFromExcel(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity (HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity deletAllorders(){
        orderService.deleteAllOrders();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/addOrder")
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        return new ResponseEntity<Order>(orderService.addOrder(order),HttpStatus.CREATED);
    }
    @PostMapping("addOrder/{orderId}")
    public ResponseEntity placeOrders(@RequestBody List<ProductOrder> productOrders, @PathVariable(name = "orderId") Long orderId){

        orderService.placeProductOrders(productOrders,orderId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
