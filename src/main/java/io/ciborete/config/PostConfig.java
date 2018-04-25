package io.ciborete.config;

import io.ciborete.enums.UserType;

public class PostConfig {

    boolean defaultGlobalVisibility;

    public boolean isDefaultGlobalVisibility() {
        return defaultGlobalVisibility;
    }

    public void setDefaultGlobalVisibility(boolean defaultGlobalVisibility) {
        this.defaultGlobalVisibility = defaultGlobalVisibility;
    }

    public UserType getImageLevelVisibilityValue() {
        return imageLevelVisibilityValue;
    }

    public void setImageLevelVisibilityValue(UserType imageLevelVisibilityValue) {
        this.imageLevelVisibilityValue = imageLevelVisibilityValue;
    }

    public boolean isGroupedImageVisibility() {
        return groupedImageVisibility;
    }

    public void setGroupedImageVisibility(boolean groupedImageVisibility) {
        this.groupedImageVisibility = groupedImageVisibility;
    }

    public boolean isBrandDropdownPresence() {
        return brandDropdownPresence;
    }

    public void setBrandDropdownPresence(boolean brandDropdownPresence) {
        this.brandDropdownPresence = brandDropdownPresence;
    }

    public boolean isSeparateReviewFlag() {
        return separateReviewFlag;
    }

    public void setSeparateReviewFlag(boolean separateReviewFlag) {
        this.separateReviewFlag = separateReviewFlag;
    }

    UserType imageLevelVisibilityValue;

    boolean groupedImageVisibility;

    boolean brandDropdownPresence;

    boolean separateReviewFlag;




}
