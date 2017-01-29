package ua.com.vertex.beans;
import java.util.Objects;

public class Payment {


    private int payment_id;
    private int deal_id;
    private double amount;


    @Override
    public String toString() {
        return "Payment{" +
                "payment_id=" + payment_id +
                ", deal_id=" + deal_id +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;

        Payment payment = (Payment) o;

        if (getPayment_id() != payment.getPayment_id()) return false;
        if (getDeal_id() != payment.getDeal_id()) return false;
        return Double.compare(payment.getAmount(), getAmount()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(payment_id, deal_id, amount);
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
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
        private final Payment instance;

        public Builder() {
            instance = new Payment();
        }

        public Payment.Builder setPayment_id(int id) {
            instance.setPayment_id(id);
            return this;
        }

        public Payment.Builder setDeal_id(int id) {
            instance.setDeal_id(id);
            return this;
        }

        public Payment.Builder setAmount(double amount) {
            instance.setAmount(amount);
            return this;
        }

        public Payment getInstance() {
            return instance;
        }
    }
}
