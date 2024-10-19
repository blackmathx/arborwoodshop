package com.arborwoodshop.model_dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
    Used to display in two cases, where both contain title, desc, price, location, all image urls, etc
    1. The listing detail when someone clicks on the item to view it
    2. When the user adds a new listing. It needs all this information added at input time
 */
public class ListingDetailDisplay {
    private Long listingId;
    private Long listingImagesId;
    private Long userId;
    private String title;
    private String description;
    private BigDecimal price;
    private String state;
    private String city;
    private String location;
    private String zipcode;
    private String phone;
    private String email;
    private Boolean shippingAvailable;
    private Boolean deliveryAvailable;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String imageOne;
    private String imageTwo;
    private String imageThree;

    public Long getListingId() {
        return listingId;
    }

    public void setListingId(Long listingId) {
        this.listingId = listingId;
    }

    public Long getListingImagesId() {
        return listingImagesId;
    }

    public void setListingImagesId(Long listingImagesId) {
        this.listingImagesId = listingImagesId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getShippingAvailable() {
        return shippingAvailable;
    }

    public void setShippingAvailable(Boolean shippingAvailable) {
        this.shippingAvailable = shippingAvailable;
    }

    public Boolean getDeliveryAvailable() {
        return deliveryAvailable;
    }

    public void setDeliveryAvailable(Boolean deliveryAvailable) {
        this.deliveryAvailable = deliveryAvailable;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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
}
