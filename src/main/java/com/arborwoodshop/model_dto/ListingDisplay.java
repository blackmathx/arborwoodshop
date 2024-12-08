package com.arborwoodshop.model_dto;

import java.math.BigDecimal;

/*
    Object used to display on the listings-by-city page. Bare bones stuff, like display image, title, price, location
 */
// TODO rename to ListingCardDisplay
public class ListingDisplay {
    private Long listingId;
    private String title;
    private String category;
    private BigDecimal price;
    private String location;
    private String imageOne;
    private String imageTwo;
    private String imageThree;


    public Long getListingId() {
        return listingId;
    }

    public void setListingId(Long listingId) {
        this.listingId = listingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageOne() {
        return imageOne;
    }

    public void setImageOne(String imageOne) {
        this.imageOne = imageOne;
    }

    public String getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(String imageTwo) {
        this.imageTwo = imageTwo;
    }

    public String getImageThree() {
        return imageThree;
    }

    public void setImageThree(String imageThree) {
        this.imageThree = imageThree;
    }

    @Override
    public String toString() {
        return "ListingDisplay{" +
                "listingId=" + listingId +
                ", title='" + title +
                ", price=" + price +
                '}';
    }
}
