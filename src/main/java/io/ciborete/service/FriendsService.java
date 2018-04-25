package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.model.Friends;
import io.ciborete.model.Restaurant;

import java.util.List;

public interface FriendsService {
    Friends addFriend(String userId1, String userId2);
    void deleteRestaurant(String RestaurantId);
    void deleteRestaurants(List<String> restaurants);
    List<Restaurant> getRestaurants();
    List<Restaurant> getRestaurants(Request request);
    Restaurant findRestaurant(String restaurantId);
    List<Restaurant> findRestaurants(List<String> restaurantIds);
    Restaurant updateRestaurant(String restaurantId,Restaurant restaurant);

}
