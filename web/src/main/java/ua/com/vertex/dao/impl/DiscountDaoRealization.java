package ua.com.vertex.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.vertex.beans.Discount;
import ua.com.vertex.dao.DiscountDaoInf;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DiscountDaoRealization implements DiscountDaoInf {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Discount getDiscount(int deal_id) {
        String query = "SELECT discount_id, deal_id, amount" +
                " FROM Discount WHERE deal_id =:deal_id";
        return jdbcTemplate.queryForObject(query, new MapSqlParameterSource("deal_id", deal_id), new DiscountRowMapping());

    }

    @Override
    public int updateDiscount(int deal_id, double amount) {
        String query = "UPDATE Discount SET amount =:amount WHERE deal_id =:deal_id";

        return jdbcTemplate.update(query, new MapSqlParameterSource("amount", amount)
                .addValue( "deal_id", deal_id));
    }

    private static final class DiscountRowMapping implements RowMapper<Discount> {
        public Discount mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Discount.Builder()
                    .setDiscount_id(resultSet.getInt("discount_id"))
                    .setDeal_id(resultSet.getInt("deal_id"))
                    .setAmount(resultSet.getDouble("amount"))
                    .getInstance();
        }
    }
}


