package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.exceptions.AssetNotFoundException;
import io.ciborete.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        restaurant.setRestaurantId(UUID.randomUUID().toString());
        restaurant.setCreatedTime(new Date());
        restaurant.setModifiedTime(new Date());
        mongoOperations.save(restaurant);
        return restaurant;
    }

    @Override
    public void deleteRestaurant(String restaurantId) {
        Restaurant restaurant = mongoOperations.findById(restaurantId,Restaurant.class);
        if(restaurant!=null){
            mongoOperations.remove(new Query(Criteria.where("restaurantId").is(restaurantId)));
        }
        throw new AssetNotFoundException("Restaurant not found with restaurant Id "+restaurantId);
    }

    @Override
    public List<Restaurant> findRestaurants() {
        return mongoOperations.findAll(Restaurant.class);
    }

    @Override
    public List<Restaurant> findRestaurants(Request request) {
        Query query = new Query();
        query.with(new PageRequest(request.getPageOffset(),request.getPageLimit()));
        if(request.getSortKey()==null || request.getSortKey().isEmpty()){
            request.setSortKey("createdTime");
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.valueOf(request.getSortOrder().name()),request.getSortKey())));
        return mongoOperations.find(query,Restaurant.class);
    }

    @Override
    public Restaurant findRestaurant(String restaurantId) {
        return mongoOperations.findById(restaurantId,Restaurant.class);
    }

    @Override
    public List<Restaurant> findRestaurantsByIds(List<String> restaurantIds) {
        return mongoOperations.find(Query.query(Criteria.where("restaurantId").in(restaurantIds)),Restaurant.class);
    }

    @Override
    public Restaurant updateRestaurant(String wallRestaurantId, Restaurant restaurant) {
        Restaurant currentRestaurant = mongoOperations.findById(restaurant.getRestaurantId(),Restaurant.class);
        if(currentRestaurant!=null){
            restaurant.setModifiedTime(new Date());
            mongoOperations.save(restaurant);
        }
        throw new AssetNotFoundException("Restaurant not found with restaurant Id "+restaurant.getRestaurantId());
    }

    @Override
    public void deleteRestaurants(List<String> restaurants){
        mongoOperations.remove(Query.query(Criteria.where("restaurantId").in(restaurants)),Restaurant.class);
    }
}
