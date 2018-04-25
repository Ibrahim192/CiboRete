package io.ciborete.enums;

import java.util.Collections;
import java.util.Set;

public enum FriendShipStatus {

    CONFIRMED(Collections.emptySet()),
    CANCELLED(Collections.emptySet()),
    HOLD(Set.of(CONFIRMED,CANCELLED)),
    INITIATED(Set.of(HOLD,CANCELLED,CONFIRMED));

    public Set<FriendShipStatus> getStates() {
        return states;
    }

    public void setStates(Set<FriendShipStatus> states) {
        this.states = states;
    }

    private Set<FriendShipStatus> states;

    FriendShipStatus(Set<FriendShipStatus> friendShipStatuses){
        this.states = friendShipStatuses;
    }


}
