package com.arborwoodshop.model;

public class ListingImages {

    private Long listingImagesId;
    private Long listingId;
    private String imageOne;
    private String imageTwo;
    private String imageThree;

    public Long getListingImagesId() {
        return listingImagesId;
    }

    public void setId(Long listingImagesId) {
        this.listingImagesId = listingImagesId;
    }

    public Long getListingId() {
        return listingId;
    }

    public void setListingId(Long listingId) {
        this.listingId = listingId;
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


