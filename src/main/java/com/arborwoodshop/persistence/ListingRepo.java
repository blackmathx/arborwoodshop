package com.arborwoodshop.persistence;

import com.arborwoodshop.model.Listing;
import com.arborwoodshop.model_dto.ListingDetailDisplay;
import com.arborwoodshop.model_dto.ListingDisplay;
import com.arborwoodshop.service.EnumOfCategory;
import com.arborwoodshop.service.EnumOfLocation;
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
        INSERT INTO listing (title, description, category, price, state, city, location, created_at, delivery_available, shipping_available, user_id)
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
            listing.listing_id, listing.title, listing.category, listing.price, listing.location,
            images.image_one, images.image_two, images.image_three
        FROM listing
        LEFT JOIN listing_images AS images
        ON listing.listing_id = images.listing_id
        WHERE listing.user_id = ?
        """;
    String FIND_LISTING_DISPLAY_ITEMS_BY_SITE = """
        SELECT
            listing.listing_id, listing.title, listing.category, listing.price, listing.location, listing.city,
            images.image_one, images.image_two, images.image_three
        FROM listing
        LEFT JOIN listing_images AS images
        ON listing.listing_id = images.listing_id
        WHERE listing.city = ?
        """;
    String FIND_LISTING_DISPLAY_ITEMS_BY_SITE_AND_CAT = """
        SELECT
            listing.listing_id, listing.title, listing.category, listing.price, listing.location, listing.city,
            images.image_one, images.image_two, images.image_three
        FROM listing
        LEFT JOIN listing_images AS images
        ON listing.listing_id = images.listing_id
        WHERE listing.city = ?
        AND listing.category = ?
        """;
    String FIND_LISTING_DETAIL_ITEM_BY_ID = """
        SELECT
            * FROM listing
        JOIN listing_images AS images
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
            ps.setString(3, listing.getCategory());
            ps.setBigDecimal(4, listing.getPrice());
            ps.setString(5, listing.getState());
            ps.setString(6, listing.getCity());
            ps.setString(7, listing.getLocation());
            ps.setObject(8, listing.getCreatedAt());
            ps.setBoolean(9, listing.getDeliveryAvailable());
            ps.setBoolean(10, listing.getShippingAvailable());
            ps.setLong(11, listing.getUserId());
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
    public List<ListingDisplay> findListingDisplayItemsBySite(EnumOfLocation siteEnum){
        return jdbcTemplate.query(FIND_LISTING_DISPLAY_ITEMS_BY_SITE, new ListingDisplayMapper(), siteEnum.name());
    }
    public List<ListingDisplay> findListingDisplayItemsBySite(EnumOfLocation siteEnum, EnumOfCategory categoryEnum){
        return jdbcTemplate.query(FIND_LISTING_DISPLAY_ITEMS_BY_SITE_AND_CAT, new ListingDisplayMapper(), siteEnum.name(), categoryEnum.name());
    }

    public ListingDetailDisplay findListingDetailItemById(Long listingId){
        return jdbcTemplate.queryForObject(FIND_LISTING_DETAIL_ITEM_BY_ID, new ListingDetailDisplayMapper(), listingId);
    }
}
