package com.arborwoodshop.persistence;


import com.arborwoodshop.model_dto.ListingDetailDisplay;
import com.arborwoodshop.model_dto.ListingDisplay;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


class ListingDisplayMapper implements RowMapper<ListingDisplay> {

    public ListingDisplay mapRow(ResultSet rs, int rowNum) throws SQLException {

        ListingDisplay listing = new ListingDisplay();
        listing.setListingId(rs.getLong("listing_id"));
        listing.setTitle(rs.getString("title"));
        listing.setPrice(rs.getBigDecimal("price"));
        listing.setLocation(rs.getString("location"));
        listing.setImageOne(rs.getString("image_one"));
        listing.setImageTwo(rs.getString("image_two"));
        listing.setImageThree(rs.getString("image_three"));
        return listing;

    }
}


class ListingDetailDisplayMapper implements RowMapper<ListingDetailDisplay> {

    public ListingDetailDisplay mapRow(ResultSet rs, int rowNum) throws SQLException {

        ListingDetailDisplay listing = new ListingDetailDisplay();
        listing.setListingId(rs.getLong("listing_id"));
        listing.setUserId(rs.getLong("user_id"));
        listing.setTitle(rs.getString("title"));
        listing.setDescription(rs.getString("description"));
        listing.setPrice(rs.getBigDecimal("price"));
        listing.setState(rs.getString("state"));
        listing.setCity(rs.getString("city"));
        listing.setLocation(rs.getString("location"));
        listing.setZipcode(rs.getString("zipcode"));
        listing.setPhone(rs.getString("phone"));
        listing.setEmail(rs.getString("email"));
        listing.setShippingAvailable(rs.getBoolean("shipping_available"));
        listing.setDeliveryAvailable(rs.getBoolean("delivery_available"));
        listing.setCreatedDate(rs.getObject("created_date", LocalDateTime.class));
        listing.setUpdatedDate(rs.getObject("updated_date", LocalDateTime.class));
        listing.setImageOne(rs.getString("image_one"));
        listing.setImageTwo(rs.getString("image_two"));
        listing.setImageThree(rs.getString("image_three"));
        return listing;

    }
}


