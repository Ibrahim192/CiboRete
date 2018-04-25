package io.ciborete.controllers;


import io.ciborete.dto.Request;
import io.ciborete.model.Item;
import io.ciborete.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/item")
public class ItemsController {

    @Autowired
    ItemService itemService;

    @RequestMapping(path="/add",method = {RequestMethod.POST})
    public ResponseEntity<?> addItem(@RequestBody Item item) {
        Item createdItem = itemService.addItem(item);
        return (createdItem != null) ?
                (new ResponseEntity<Item>(createdItem, HttpStatus.CREATED)) :
                (new ResponseEntity<Item>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Item> findItems() {
        return itemService.findItems();
    }

    @RequestMapping(path = "/{itemId}", method = RequestMethod.GET)
    public ResponseEntity<Item> findItem(@PathVariable String itemId) {
        Item item = itemService.findItem(itemId);
        return (item != null) ?
                new ResponseEntity<Item>(item, HttpStatus.OK) :
                new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path ="/ids",method = RequestMethod.POST)
    public List<Item> findItemByIds(@RequestBody List<String> itemIds) {
        return itemService.findItemsByIds(itemIds);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<Item>> findItems(@RequestBody Request request) {
        System.out.println(request.toString());
        if(request==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(itemService.findItems(request),HttpStatus.OK);
    }

    @RequestMapping(path = "/{itemId}", method = RequestMethod.PUT)
    public Item updateItem(@PathVariable String itemId, @RequestBody Item item) {
        return itemService.updateItem(itemId, item);
    }

    @RequestMapping(path = "/{itemId}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable String itemId) {
        itemService.deleteItem(itemId);
    }
}
