package com.bda;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;


@Path("/restaurants/")
public class RestaurantResource {

	private static RestaurantService restaurantService = new RestaurantService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRestaurants(@QueryParam("latitude") double latitude,
			@QueryParam("longitude") double longitude) {
		List<Restaurant> restaurants = restaurantService.getNearbyRestaurants(longitude, latitude);
		return Response.status(200).entity(restaurants).build();
	}

	@GET
	  @Path("/{restaurantId}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response getRestaurant(@PathParam("restaurantId") int restaurantId) {
		  return Response.status(200).entity(restaurantService.getRestaurant(restaurantId)).build();
	  }

	@POST
	@Path("/{restaurantId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addReview(@PathParam("restaurantId") int restaurantId, Review review) {
		System.out.println(("Review recieved: "+review));
		Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
		restaurant.addReview(review);
	}

	@DELETE
	@Path("/{restaurantId}/{reviewId}")
	public void deleteReview(@PathParam("restaurantId") int restaurantId, @PathParam("reviewId") int reviewId) {
		Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
		restaurant.removeReview(reviewId);
	}
}
