package com.arborwoodshop.persistence;

import com.arborwoodshop.model.Listing;
import com.arborwoodshop.model_dto.ListingDetailDisplay;
import com.arborwoodshop.model_dto.ListingDisplay;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class ListingRepo {

    String FIND_LISTING_BY_LISTING_ID = """
            SELECT * FROM listing WHERE listing_id = ?
            """;
    String CREATE_LISTING = """
        INSERT INTO listing (title, description, price, state, city, location, created_at, delivery_available, shipping_available, user_id)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
    String DELETE_LISTING_IMAGES = """
        DELETE FROM listing_images WHERE listing_id = ?
        """;
    String DELETE_LISTING_BY_LISTING_ID = """
        DELETE FROM listing WHERE listing_id = ?
        """;
    String FIND_LISTING_BY_USER_ID = """
        SELECT * FROM listing WHERE user_id = ?
        """;
    String CREATE_LISTING_IMAGES = """
        INSERT INTO listing_images (listing_id, image_one, image_two, image_three)
        VALUES (?, ?, ?, ?)
        """;
    String FIND_LISTING_DISPLAY_ITEMS_BY_USER_ID = """
        SELECT
            listing.listing_id, listing.title, listing.price, listing.location,
            images.image_one, images.image_two, images.image_three
        FROM listing
        LEFT JOIN listing_images AS images
        ON listing.listing_id = images.listing_id
        WHERE listing.user_id = ?
        """;
    String FIND_LISTING_DISPLAY_ITEMS = """
        SELECT
            listing.listing_id, listing.title, listing.price, listing.location,
            images.image_one, images.image_two, images.image_three
        FROM listing
        LEFT JOIN listing_images AS images
        ON listing.listing_id = images.listing_id
        """;
    String FIND_LISTING_DETAIL_ITEM_BY_ID = """
        SELECT
            listing.listing_id, listing.user_id, listing.title, listing.description, listing.price, listing.state, listing.city,
            listing.location, listing.zipcode, listing.phone, listing.email, listing.shipping_available, listing.delivery_available,
            listing.created_at, listing.updated_at,
            images.listing_images_id, images.image_one, images.image_two, images.image_three
        FROM listing
        LEFT JOIN listing_images AS images
        ON listing.listing_id = images.listing_id
        WHERE listing.listing_id = ?
        """;


    private final JdbcTemplate jdbcTemplate;

    public ListingRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Listing> findByUserId(Long userId){
        return jdbcTemplate.query(FIND_LISTING_BY_USER_ID, new ListingMapper(), userId);
    }

    public int create(ListingDetailDisplay listing){

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_LISTING, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, listing.getTitle());
            ps.setString(2, listing.getDescription());
            ps.setBigDecimal(3, listing.getPrice());
            ps.setString(4, listing.getState());
            ps.setString(5, listing.getCity());
            ps.setString(6, listing.getLocation());
            ps.setObject(7, listing.getCreatedAt());
            ps.setBoolean(8, listing.getDeliveryAvailable());
            ps.setBoolean(9, listing.getShippingAvailable());
            ps.setLong(10, listing.getUserId());
            return ps;
        }, keyHolder);

        long createdListingId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return jdbcTemplate.update(CREATE_LISTING_IMAGES, createdListingId, listing.getImageOne(), listing.getImageTwo(), listing.getImageThree());
    }

    public int delete(Long listingId){
        jdbcTemplate.update(DELETE_LISTING_IMAGES, listingId);
        return jdbcTemplate.update(DELETE_LISTING_BY_LISTING_ID, listingId);
    }

    public Listing findById(Long listingId){
        return jdbcTemplate.queryForObject(FIND_LISTING_BY_LISTING_ID, new ListingMapper(), listingId);
    }

    public List<ListingDisplay> findListingDisplayItemsByUserId(Long userId){
        return jdbcTemplate.query(FIND_LISTING_DISPLAY_ITEMS_BY_USER_ID, new ListingDisplayMapper(), userId);

    }
    public List<ListingDisplay> findListingDisplayItems(){
        return jdbcTemplate.query(FIND_LISTING_DISPLAY_ITEMS, new ListingDisplayMapper());
    }

    public ListingDetailDisplay findListingDetailItemById(Long listingId){
        return jdbcTemplate.queryForObject(FIND_LISTING_DETAIL_ITEM_BY_ID, new ListingDetailDisplayMapper(), listingId);
    }
}
