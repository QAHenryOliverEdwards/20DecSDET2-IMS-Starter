package com.qa.ims.persistence.domain;

public class Order {

    private Long id;
    private Long c_id;

    public Order(Long id, Long c_id) {
        this.id = id;
        this.c_id = c_id;
    }

    public Order(Long c_id) {
        this.c_id = c_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getC_id() {
        return c_id;
    }

    public void setC_id(Long c_id) {
        this.c_id = c_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", c_id=" + c_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        return c_id != null ? c_id.equals(order.c_id) : order.c_id == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (c_id != null ? c_id.hashCode() : 0);
        return result;
    }
}
