package io.ciborete.service;

import io.ciborete.dto.Request;
import io.ciborete.model.Gallery;
import io.ciborete.model.Gallery;

import java.util.List;

public interface GalleryService {

    Gallery addGallery(Gallery gallery);
    void deleteGallery(String galleryId);
    void deleteGalleries(List<String> galleryIds);

    List<Gallery> findGalleries();
    List<Gallery> findGalleries(Request request);
    Gallery findGallery(String galleryId);
    List<Gallery> findGalleriesByIds(List<String> galleryIds);
    Gallery updateGallery(String galleryId,Gallery gallery);




}
