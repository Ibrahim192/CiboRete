package io.ciborete.controllers;

import io.ciborete.dto.Request;
import io.ciborete.model.Gallery;
import io.ciborete.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gallery")
public class GalleryController {

    @Autowired
    GalleryService galleryService;

    @RequestMapping(path="/add",method = {RequestMethod.POST})
    public ResponseEntity<?> addGallery(@RequestBody Gallery gallery) {
        Gallery createdGallery = galleryService.addGallery(gallery);
        return (createdGallery != null) ?
                (new ResponseEntity<Gallery>(createdGallery, HttpStatus.CREATED)) :
                (new ResponseEntity<Gallery>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Gallery> findGallerys() {
        return galleryService.findGalleries();
    }

    @RequestMapping(path = "/{galleryId}", method = RequestMethod.GET)
    public ResponseEntity<Gallery> findGallery(@PathVariable String galleryId) {
        Gallery gallery = galleryService.findGallery(galleryId);
        return (gallery != null) ?
                new ResponseEntity<Gallery>(gallery, HttpStatus.OK) :
                new ResponseEntity<Gallery>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path ="/ids",method = RequestMethod.POST)
    public List<Gallery> findGalleryByIds(@RequestBody List<String> galleryIds) {
        return galleryService.findGalleriesByIds(galleryIds);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<Gallery>> findGallerys(@RequestBody Request request) {
        System.out.println(request.toString());
        if(request==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(galleryService.findGalleries(request),HttpStatus.OK);
    }

    @RequestMapping(path = "/{galleryId}", method = RequestMethod.PUT)
    public Gallery updateGallery(@PathVariable String galleryId, @RequestBody Gallery gallery) {
        return galleryService.updateGallery(galleryId, gallery);
    }

    @RequestMapping(path = "/{galleryId}", method = RequestMethod.DELETE)
    public void deleteGallery(@PathVariable String galleryId) {
        galleryService.deleteGallery(galleryId);
    }
}