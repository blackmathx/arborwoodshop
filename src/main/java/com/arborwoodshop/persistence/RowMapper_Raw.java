package com.arborwoodshop.persistence;

import com.arborwoodshop.model.Listing;
import com.arborwoodshop.model.Message;
import com.arborwoodshop.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setEnabled(rs.getBoolean("enabled"));
        user.setSellerActive(rs.getBoolean("seller_active"));
        user.setSellerActiveDate(rs.getObject("seller_active_date", LocalDateTime.class));
        user.setSellerExpireDate(rs.getObject("seller_expire_date", LocalDateTime.class));
        user.setCreatedDate(rs.getObject("created_date", LocalDateTime.class));
        user.setRoles(rs.getString("roles"));
        return user;
    };
}

class ListingMapper implements RowMapper<Listing> {
    public Listing mapRow(ResultSet rs, int rowNum) throws SQLException {
        Listing listing = new Listing();
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
        listing.setDeliveryAvailable(rs.getBoolean("delivery_available"));
        listing.setShippingAvailable(rs.getBoolean("shipping_available"));
        listing.setCreatedDate(rs.getObject("created_date", LocalDateTime.class));
        listing.setUpdatedDate(rs.getObject("updated_date", LocalDateTime.class));
        return listing;
    }
}

class MessageMapper implements RowMapper<Message> {
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message message = new Message();
        message.setMessageId(rs.getLong("message_id"));
        message.setMessage(rs.getString("message"));
        message.setContactEmail(rs.getString("contact_email"));
        message.setContactPhone(rs.getString("contact_phone"));
        message.setUserId(rs.getLong("user_id"));
        return message;
    }
}
