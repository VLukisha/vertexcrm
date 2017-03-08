package ua.com.vertex.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.vertex.beans.Payment;
import ua.com.vertex.dao.PaymentDaoInf;

import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class PaymentDaoRealization implements PaymentDaoInf {

    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public Payment getPayment(int deal_id) {

        String query = "SELECT payment_id, deal_id, amount" +
                " FROM Payments WHERE deal_id =:deal_id";
        return jdbcTemplate.queryForObject(query, new MapSqlParameterSource("deal_id", deal_id), new PaymentRowMapping());
    }

    @Override
    public int updatePayment(int deal_id, double amount) {
        String query = "UPDATE Payments SET amount =:amount WHERE deal_id =:deal_id";

        return jdbcTemplate.update(query, new MapSqlParameterSource("amount", amount)
                .addValue( "deal_id", deal_id));
    }


    private static final class PaymentRowMapping implements RowMapper<Payment> {
        public Payment mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Payment.Builder()
                    .setPayment_id(resultSet.getInt("payment_id"))
                    .setDeal_id(resultSet.getInt("deal_id"))
                    .setAmount(resultSet.getDouble("amount"))
                    .getInstance();
        }
    }
}
