package com.backend.app.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.GenerationType.*;

@Entity(
        name = "Order"
)
@Table(
        name = "orders"
)
public class Order {
    @Id
    @GeneratedValue(
            strategy = IDENTITY

    )
    @Column(
            name = "order_id",
            nullable = false,
            updatable = false
    )
    private Long orderId;
    @OneToMany(
            mappedBy = "order",
            orphanRemoval = true,
            cascade = CascadeType.ALL

    )
    private List<Status> statuses= new ArrayList<>();
    @ManyToOne
    @JoinColumn(
            name= "assigned_to",
            referencedColumnName = "username",
            foreignKey = @ForeignKey(
                    name= "user_order_fk"
            )
    )
    private User user;
    @Column(
            name = "client_name",
            columnDefinition = "TEXT",
            nullable = false
    )

    private String clientName;
    @Column(
            name = "client_phone",
            columnDefinition = "CHAR(10)",
            nullable = false
    )
    private  String clientPhone;
    @Column(
            name = "wilaya",
            columnDefinition = "VARCHAR(25)",
            nullable = false
    )
    private String wilaya;
    @Column(
            name = "comune",
            columnDefinition = "VARCHAR(25)",
            nullable = false
    )
    private String comune;
    @Column(
            name = "current_status",
            columnDefinition = "VARCHAR(20)"

    )
    private String currentStatus="in-confirmation";
    @Column(
            name = "current_status_date",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"

    )
    private LocalDateTime currentStatusDate = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(
            name = "delivery_agency_id",
            nullable = true,
            referencedColumnName = "agency_id",
            foreignKey = @ForeignKey(
                    name = "order_delivery_agency_fk"
            )
    )
    private DeliveryAgency deliveryAgency;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "order",
            orphanRemoval = true

    )
    private List<ProductOrder> productOrders= new ArrayList<>();

    @Column(
            name = "notes",
            columnDefinition = "TEXT"
    )
    private String notes;
    @Column(
            name = "delivery_type",
            columnDefinition = "INT"
    )
    private Integer deliveryType;
    @Column(
            name = "delivery_fee"
    )
    private Integer deliveryFee;
    @Column(
            name = "total_price",
            columnDefinition = "INT"
    )
    private  Integer totalPrice;

    public Order() {
    }

    public Order(Long orderId, User user, String clientName, String clientPhone, String wilaya, String comune, String currentStatus, LocalDateTime currentStatusDate, DeliveryAgency deliveryAgency, String notes, Integer deliveryType, Integer deliveryFee, Integer totalPrice) {
        this.orderId = orderId;
        this.user = user;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.wilaya = wilaya;
        this.comune = comune;
        this.currentStatus = currentStatus;
        this.currentStatusDate = currentStatusDate;
        this.deliveryAgency = deliveryAgency;
        this.notes = notes;
        this.deliveryType = deliveryType;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getWilaya() {
        return wilaya;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public LocalDateTime getCurrentStatusDate() {
        return currentStatusDate;
    }

    public void setCurrentStatusDate(LocalDateTime currentStatusDate) {
        this.currentStatusDate = currentStatusDate;
    }

    public DeliveryAgency getDeliveryAgency() {
        return deliveryAgency;
    }

    public void setDeliveryAgency(DeliveryAgency deliveryAgency) {
        this.deliveryAgency = deliveryAgency;
    }

    public List<ProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Integer getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Integer deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(statuses, order.statuses) && Objects.equals(user, order.user) && Objects.equals(clientName, order.clientName) && Objects.equals(clientPhone, order.clientPhone) && Objects.equals(wilaya, order.wilaya) && Objects.equals(comune, order.comune) && Objects.equals(currentStatus, order.currentStatus) && Objects.equals(currentStatusDate, order.currentStatusDate) && Objects.equals(deliveryAgency, order.deliveryAgency) && Objects.equals(productOrders, order.productOrders) && Objects.equals(notes, order.notes) && Objects.equals(deliveryType, order.deliveryType) && Objects.equals(deliveryFee, order.deliveryFee) && Objects.equals(totalPrice, order.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, statuses, user, clientName, clientPhone, wilaya, comune, currentStatus, currentStatusDate, deliveryAgency, productOrders, notes, deliveryType, deliveryFee, totalPrice);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", statuses=" + statuses +
                ", user=" + user +
                ", clientName='" + clientName + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", wilaya='" + wilaya + '\'' +
                ", comune='" + comune + '\'' +
                ", currentStatus='" + currentStatus + '\'' +
                ", currentStatusDate=" + currentStatusDate +
                ", deliveryAgency=" + deliveryAgency +
                ", productOrders=" + productOrders +
                ", notes='" + notes + '\'' +
                ", deliveryType=" + deliveryType +
                ", deliveryFee=" + deliveryFee +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public void addStatus(Status status){
        if(!this.statuses.contains(status)){
            this.statuses.add(status);
            status.setOrder(this);
        }
    }
    public void addProduct(ProductOrder productOrder){
        if (!productOrders.contains(productOrder)){
            productOrders.add(productOrder);
        }
    }
    public void removeProduct(ProductOrder productOrder){
        productOrders.remove(productOrder);
    }
    public void removeAllProductOrders(){
        productOrders.removeAll(productOrders);
    }

}
