package com.arborwoodshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


// TODO add fields to flag the listing and for admin to block/remove the listing
public class Listing {
    private Long listingId;
    private Long userId;
    private String title;
    private String description;
    private String category;
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


    public Long getListingId() {
        return listingId;
    }

    public void setListingId(Long listingId) {
        this.listingId = listingId;
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

    @Override
    public String toString() {
        return "Listing{" +
                "id=" + listingId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                '}';
    }
}
