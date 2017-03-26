package com.zahari.liveorderboard.domain;

import java.util.List;

/**
 * Created by zahari on 26/03/2017.
 */
public class LiveOrderBoardDTO {

    private List<PriceLevelDTO> bids;
    private List<PriceLevelDTO> asks;

    public LiveOrderBoardDTO() {
    }

    public LiveOrderBoardDTO(List<PriceLevelDTO> bids, List<PriceLevelDTO> asks) {
        this.bids = bids;
        this.asks = asks;
    }

    public List<PriceLevelDTO> getBids() {
        return bids;
    }

    public List<PriceLevelDTO> getAsks() {
        return asks;
    }
}
