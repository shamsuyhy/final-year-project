package com.backend.app.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(
        name = "DeliveryAgency"
)
@Table(
        name = "delivery_agency",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_agency_name",columnNames = "agency_name")
        }
)
public class DeliveryAgency {
    @Id
    @GeneratedValue(
            strategy = IDENTITY
    )
    @Column(
            name = "agency_id",
            nullable = false,
            updatable = false
    )
    private Long agencyId;
    @OneToMany(
            mappedBy = "deliveryAgency"
    )
    private List<Order> orders = new ArrayList<>();
    @Column(
            name = "agency_name",
            nullable = false,
            columnDefinition = "VARCHAR(30)"
    )
    private String agencyName;
    @Column(
            name = "api_id",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String apiId;
    @Column(
            name = "api_token",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String apiToken;

    public void addOrder(Order order){
        if(!this.orders.contains(order)){
            this.orders.add(order);
        }
    }
    public  void removeOrder(Order order){
        if(this.orders.contains(order)){
            this.orders.remove(order);
        }
    }

}
