package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.exceptions.AssetNotFoundException;
import io.ciborete.model.Comment;
import io.ciborete.model.Item;
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
public class ItemServiceImpl implements ItemService {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public Item addItem(Item item) {
        item.setItemId(UUID.randomUUID().toString());
        item.setCreatedTime(new Date());
        item.setModifiedTime(new Date());
        mongoOperations.save(item);
        return item;
    }

    @Override
    public void deleteItem(String itemId) {
        Item item = mongoOperations.findById(itemId,Item.class);
        if(item!=null){
            mongoOperations.remove(Query.query(Criteria.where("itemId").is(itemId)),Item.class);
        }
        else {
            throw new AssetNotFoundException("Item not found with item Id "+itemId);
        }
    }

    @Override
    public void deleteItems(List<String> itemIds) {
        mongoOperations.remove(Query.query(Criteria.where("itemId").in(itemIds)));
    }

    @Override
    public List<Item> findItems() {
        return mongoOperations.findAll(Item.class);
    }

    @Override
    public List<Item> findItems(Request request) {
        Query query = new Query();
        query.with(new PageRequest(request.getPageOffset(),request.getPageLimit()));
        if(request.getSortKey()==null || request.getSortKey().isEmpty()){
            request.setSortKey("createdTime");
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.valueOf(request.getSortOrder().name()),request.getSortKey())));
        return mongoOperations.find(query,Item.class);
    }

    @Override
    public Item findItem(String itemId) {
        return mongoOperations.findById(itemId,Item.class);
    }

    @Override
    public List<Item> findItemsByIds(List<String> itemIds) {
        return mongoOperations.find(Query.query(Criteria.where("itemId").in(itemIds)),Item.class);

    }

    @Override
    public Item updateItem(String itemId, Item item) {
        Item currentItem = mongoOperations.findById(itemId,Item.class);
        if(currentItem!=null){
            item.setModifiedTime(new Date());
            mongoOperations.save(item);
        }
        else {
            throw new AssetNotFoundException("Item not found with item Id " + itemId);
        }
        return item;
    }
}
