package com.bda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantService {
	  private final Map<Integer, Restaurant> restaurants;
	  private static int restaurantId = 0;

	  public RestaurantService() {
	    restaurants = new HashMap<>();
	    addRestaurant("Pizza Corner", "Italian", "Pizza", "Fast-Food").addReview(newReview(5, "Excelent service, Quick and tasty")).addReview(newReview(5, "I wasn't expecting something so good at such a price!"));
	    addRestaurant("Sushi Express", "Japanese").addReview(newReview(3, "Not so express, really good nonetheless")).addReview(newReview(3, "It was Ok."));
	    addRestaurant("The Pasta Master", "Italian").addReview(newReview(5, "Masterful indeed")).addReview(newReview(4, "It was really good"));
	    addRestaurant("Taco Place", "Mexican").addReview(newReview(3, "Not my favorite but they do the job")).addReview(newReview(2, "It was a little expensive."));
	    addRestaurant("Ice creams and more", "Desserts", "Ice-cream", "Coffee").addReview(newReview(5, "The cakes are delicious and the coffee is great!")).addReview(newReview(2, "I did not like the ice-cream :("));
	  }

	  public List<Restaurant> getNearbyRestaurants(double longitude, double latitude) {
	    return new ArrayList<>(restaurants.values());
	  }

	  public Restaurant getRestaurant(int id) {
	    return restaurants.get(id);
	  }

	  public void addReview(int restaurantId, Review review) {
	    getRestaurant(restaurantId).addReview(review);
	  }

	  public void removeReview(int restaurantId, int reviewId) {
	    getRestaurant(restaurantId).removeReview(reviewId);
	  }

	  private Restaurant addRestaurant(String name, String... tags) {
	    Restaurant restaurant = new Restaurant();
	    restaurant.setLatitude(restaurantId);
	    restaurant.setLongitude(restaurantId);
	    restaurant.setName(name);
	    restaurant.getTags().addAll(Arrays.asList(tags));
	    restaurant.setId(restaurantId++);
	    restaurants.put(restaurant.getId(), restaurant);
	    return restaurant;
	  }

	  private Review newReview(int rating, String text) {
	    final Review review = new Review();
	    review.setRating(rating);
	    review.setText(text);
	    return review;
	  }
}
