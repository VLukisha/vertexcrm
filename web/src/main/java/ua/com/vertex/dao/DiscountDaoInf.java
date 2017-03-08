package ua.com.vertex.dao;

import ua.com.vertex.beans.Discount;



public interface DiscountDaoInf {

    Discount getDiscount(int deal_id);

    int updateDiscount(int deal_id, double amount);
}
