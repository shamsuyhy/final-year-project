package com.backend.app.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Long agencyId;
    @OneToMany(
            mappedBy = "deliveryAgency"
    )
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();
    @Column(
            name = "agency_name",
            nullable = false,
            columnDefinition = "VARCHAR(30)"
    )
    private String agencyName;
    @Column(
            name = "api_id",
            columnDefinition = "TEXT"
    )
    private String apiId;
    @Column(
            name = "api_token",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String apiToken;

    public DeliveryAgency() {
    }

    public DeliveryAgency( List<Order> orders, String agencyName, String apiId, String apiToken) {

        this.orders = orders;
        this.agencyName = agencyName;
        this.apiId = apiId;
        this.apiToken = apiToken;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

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
