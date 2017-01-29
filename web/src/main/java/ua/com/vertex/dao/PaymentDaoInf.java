package ua.com.vertex.dao;

import ua.com.vertex.beans.Payment;

public interface PaymentDaoInf {

    Payment getPayment(int deal_id);

    int updatePayment(int deal_id, double amount);
}
