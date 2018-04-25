package io.ciborete.controllers;


import io.ciborete.dto.Request;
import io.ciborete.model.Restaurant;
import io.ciborete.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/restaurants")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @RequestMapping(path="/add",method = {RequestMethod.POST})
    public ResponseEntity<?> addRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant createdRestaurant = restaurantService.addRestaurant(restaurant);
        return (createdRestaurant != null) ?
                (new ResponseEntity<Restaurant>(createdRestaurant, HttpStatus.CREATED)) :
                (new ResponseEntity<Restaurant>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Restaurant> findRestaurants() {
        return restaurantService.findRestaurants();
    }

    @RequestMapping(path = "/{restaurantId}", method = RequestMethod.GET)
    public ResponseEntity<Restaurant> findRestaurant(@PathVariable String restaurantId) {
        Restaurant restaurant = restaurantService.findRestaurant(restaurantId);
        return (restaurant != null) ?
                new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK) :
                new ResponseEntity<Restaurant>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path ="/ids",method = RequestMethod.POST)
    public List<Restaurant> findRestaurantByIds(@RequestBody List<String> restaurantIds) {
        return restaurantService.findRestaurantsByIds(restaurantIds);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<Restaurant>> findRestaurants(@RequestBody Request request) {
        System.out.println(request.toString());
        if(request==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(restaurantService.findRestaurants(request),HttpStatus.OK);
    }

    @RequestMapping(path = "/{restaurantId}", method = RequestMethod.PUT)
    public Restaurant updateRestaurant(@PathVariable String restaurantId, @RequestBody Restaurant restaurant) {
        return restaurantService.updateRestaurant(restaurantId, restaurant);
    }

    @RequestMapping(path = "/{restaurantId}", method = RequestMethod.DELETE)
    public void deleteRestaurant(@PathVariable String restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
    }
}
