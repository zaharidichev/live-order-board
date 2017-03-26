package com.zahari.liveorderboard.domain.dto;

import java.util.List;

/**
 * Created by zahari on 26/03/2017.
 */
public class LiveOrderBoardDTO {

    private List<PriceLevelDTO> sellLevels;
    private List<PriceLevelDTO> buyLevels;

    public LiveOrderBoardDTO() {
    }

    public LiveOrderBoardDTO(List<PriceLevelDTO> sellLevels, List<PriceLevelDTO> buyLevels) {
        this.sellLevels = sellLevels;
        this.buyLevels = buyLevels;
    }

    public List<PriceLevelDTO> getSellLevels() {
        return sellLevels;
    }

    public List<PriceLevelDTO> getBuyLevels() {
        return buyLevels;
    }
}
