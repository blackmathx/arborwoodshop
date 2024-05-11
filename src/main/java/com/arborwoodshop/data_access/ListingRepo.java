package com.arborwoodshop.data_access;

import com.arborwoodshop.model.Listing;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ListingRepo {

    private final JdbcTemplate jdbcTemplate;

    public ListingRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Listing> findByUserId(Long id){
        String sql = "select * from listing where user_id = ?";
        return jdbcTemplate.query(sql, new ListingMapper(), id);
    }

    public int create(Listing listing){
        String sql = "insert into listing (title, description, price, created_date, updated_date, dimensions, delivery_available, user_id)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                listing.getTitle(), listing.getDescription(), listing.getPrice(), listing.getCreatedDate(),
                null, listing.getDimensions(), listing.getDeliveryAvailable(), listing.getUserId());
    }

    public Listing findById(Long id){
        String sql = "select * from listing where id = ?";
        return jdbcTemplate.queryForObject(sql, new ListingMapper(), id);
    }

    public List<Listing> findAll(){
        String sql = "select * from listing";
        return jdbcTemplate.query(sql, new ListingMapper());
    }
}
