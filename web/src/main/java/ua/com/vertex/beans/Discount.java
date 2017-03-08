package ua.com.vertex.beans;


import java.util.Objects;

public class Discount {
    private int discount_id;
    private int deal_id;
    private double amount;


    @Override
    public String toString() {
        return "Discount{" +
                "discount_id=" + discount_id +
                ", deal_id=" + deal_id +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount)) return false;

        Discount discount = (Discount) o;

        if (getDiscount_id() != discount.getDiscount_id()) return false;
        if (getDeal_id() != discount.getDeal_id()) return false;
        return Double.compare(discount.getAmount(), getAmount()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(discount_id, deal_id, amount);
    }

    public int getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(int discount_id) {
        this.discount_id = discount_id;
    }

    public int getDeal_id() {
        return deal_id;
    }

    public void setDeal_id(int deal_id) {
        this.deal_id = deal_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public static class Builder {
        private final Discount instance;

        public Builder() {
            instance = new Discount();
        }

        public Discount.Builder setDiscount_id(int id) {
            instance.setDiscount_id(id);
            return this;
        }

        public Discount.Builder setDeal_id(int id) {
            instance.setDeal_id(id);
            return this;
        }

        public Discount.Builder setAmount(double amount) {
            instance.setAmount(amount);
            return this;
        }

        public Discount getInstance() {
            return instance;
        }
    }
}
