package io.ciborete.enums;

public enum Props {

    DEFAULT_POST_VISIBILITY("defaultPostVisibility"),
    SHOW_BRAND_DROPDOWN("showBrandDropDown"),
    CHECKED_SEPARATE_REVIEW_BOX("separateReviewBox"),
    SHOW_GROUPED_IMAGES_SINGLE_CAPTION("singleCaptionedImages");

    Props(String detail){
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    String detail;


}
