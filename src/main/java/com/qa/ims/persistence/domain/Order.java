package com.qa.ims.persistence.domain;

public class Order {

    private Long id;
    private Long cID;

    public Order(Long id, Long cID) {
        this.id = id;
        this.cID = cID;
    }

    public Order(Long cID) {
        this.cID = cID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getcID() {
        return cID;
    }

    public void setcID(Long cID) {
        this.cID = cID;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", cID=").append(cID);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        return cID != null ? cID.equals(order.cID) : order.cID == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cID != null ? cID.hashCode() : 0);
        return result;
    }
}
