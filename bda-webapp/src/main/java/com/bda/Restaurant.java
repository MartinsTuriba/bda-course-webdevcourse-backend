package com.bda;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Restaurant {

  private int commentId = 0;
  private final List<Review> reviews;
  private final List<String> tags;
  private int id = 0;
  private String name;
  private double longitude;
  private double latitude;

  public Restaurant() {
    reviews = new ArrayList<>();
    tags = new ArrayList<>();
  }

  public Restaurant addReview(Review review) {
    review.setId(commentId++);
    reviews.add(review);
    return this;
  }

  public void removeReview(int reviewId) {
    for (Iterator<Review> iterator = reviews.iterator(); iterator.hasNext();) {
      Review review = iterator.next();
      if (review.getId() == reviewId) {
        iterator.remove();
      }
    }
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public String getName() {
    return name;
  }

  public List<String> getTags() {
    return tags;
  }

  public double getLongitude() {
    return longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public int getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public void setId(int id) {
    this.id = id;
  }
}
