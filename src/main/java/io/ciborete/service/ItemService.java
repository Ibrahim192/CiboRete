package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.model.Item;
import io.ciborete.model.Item;

import java.util.List;

public interface ItemService {
     Item addItem(Item item);
     void deleteItem(String itemId);
     void deleteItems(List<String> itemIds);

     List<Item> findItems();
     List<Item> findItems(Request request);
     Item findItem(String itemId);
     List<Item> findItemsByIds(List<String> itemIds);
     Item updateItem(String itemId,Item item);
}
