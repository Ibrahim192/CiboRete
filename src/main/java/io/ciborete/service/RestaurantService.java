package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant addRestaurant(Restaurant restaurant);
    void deleteRestaurant(String RestaurantId);
    void deleteRestaurants(List<String> restaurants);
    List<Restaurant> findRestaurants();
    List<Restaurant> findRestaurants(Request request);
    Restaurant findRestaurant(String restaurantId);
    List<Restaurant> findRestaurantsByIds(List<String> restaurantIds);
    Restaurant updateRestaurant(String restaurantId,Restaurant restaurant);
}
