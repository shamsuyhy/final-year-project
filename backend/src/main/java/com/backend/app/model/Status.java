package com.backend.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(
        name = "Status"
)
@Table(
        name = "status"
)
public class Status {
    @Id
    @SequenceGenerator(
            name = "sequence_id",
            allocationSize = 1,
            sequenceName = "sequence_id"
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "sequence_id"
    )
    @Column(
            name = "status_id",
            updatable = false,
            nullable = false
    )
    private Long id;
    @ManyToOne
    @JoinColumn(
            name= "order_id",
            nullable = false,
            referencedColumnName = "order_id",
            foreignKey = @ForeignKey(
                    name= "order_status_fk"
            )
    )
    @JsonIgnore
    private Order order;
    @Column(
            name = "changed_by",
            nullable = false,
            columnDefinition = "VARCHAR(25)"
    )
    private String changedBy;
    @Column(
            name = "create_date",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime creatDate;
    @Column(
            name = "status",
            columnDefinition = "VARCHAR(20)",
            nullable = false
    )
    private String status;
    @Column(
            name = "type_of_change",
            columnDefinition = "TEXT"
    )
    private String typeOfChange;

    public Status() {
    }

    public Status(Order order, String changedBy, LocalDateTime creatDate, String status, String typeOfChange) {
        this.order = order;
        this.changedBy = changedBy;
        this.creatDate = creatDate;
        this.status = status;
        this.typeOfChange = typeOfChange;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public LocalDateTime getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(LocalDateTime creatDate) {
        this.creatDate = creatDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeOfChange() {
        return typeOfChange;
    }

    public void setTypeOfChange(String typeOfChange) {
        this.typeOfChange = typeOfChange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status1 = (Status) o;
        return Objects.equals(id, status1.id) && Objects.equals(order, status1.order) && Objects.equals(changedBy, status1.changedBy) && Objects.equals(creatDate, status1.creatDate) && Objects.equals(status, status1.status) && Objects.equals(typeOfChange, status1.typeOfChange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, changedBy, creatDate, status, typeOfChange);
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", order=" + order +
                ", changedBy='" + changedBy + '\'' +
                ", creatDate=" + creatDate +
                ", status='" + status + '\'' +
                ", typeOfChange='" + typeOfChange + '\'' +
                '}';
    }
}
