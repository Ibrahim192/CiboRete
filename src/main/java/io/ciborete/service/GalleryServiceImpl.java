package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.exceptions.AssetNotFoundException;
import io.ciborete.model.Gallery;
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
public class GalleryServiceImpl implements GalleryService {
    @Autowired
    MongoOperations mongoOperations;

    @Override
    public Gallery addGallery(Gallery gallery) {
        gallery.setGalleryId(UUID.randomUUID().toString());
        gallery.setCreatedTime(new Date());
        gallery.setModifiedTime(new Date());
        mongoOperations.save(gallery);
        return gallery;
    }

    @Override
    public void deleteGallery(String galleryId) {
        Gallery gallery = mongoOperations.findById(galleryId,Gallery.class);
        if(gallery!=null){
            mongoOperations.remove(new Query(Criteria.where("galleryId").is(galleryId)));
        }
        throw new AssetNotFoundException("Gallery not found with gallery Id "+galleryId);
    }

    @Override
    public void deleteGalleries(List<String> galleryIds) {
        mongoOperations.remove(Query.query(Criteria.where("galleryId").in(galleryIds)));

    }

    @Override
    public List<Gallery> findGalleries() {
        return mongoOperations.findAll(Gallery.class);
    }

    @Override
    public List<Gallery> findGalleries(Request request) {
        Query query = new Query();
        query.with(new PageRequest(request.getPageOffset(),request.getPageLimit()));
        if(request.getSortKey()==null || request.getSortKey().isEmpty()){
            request.setSortKey("createdTime");
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.valueOf(request.getSortOrder().name()),request.getSortKey())));
        return mongoOperations.find(query,Gallery.class);
    }

    @Override
    public Gallery findGallery(String galleryId) {
        return mongoOperations.findById(galleryId,Gallery.class);
    }

    @Override
    public List<Gallery> findGalleriesByIds(List<String> galleryIds) {
        return mongoOperations.find(Query.query(Criteria.where("galleryId").in(galleryIds)),Gallery.class);
    }

    @Override
    public Gallery updateGallery(String wallPostId, Gallery gallery) {
        Gallery currentGallery = mongoOperations.findById(gallery.getGalleryId(),Gallery.class);
        if(currentGallery!=null){
            gallery.setModifiedTime(new Date());
            mongoOperations.save(gallery);
        }
        else {
            throw new AssetNotFoundException("Gallery not found with gallery Id " + gallery.getGalleryId());
        }
        return gallery;
    }

}
