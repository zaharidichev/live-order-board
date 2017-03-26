package com.zahari.liveorderboard.service;

import com.zahari.liveorderboard.domain.dto.PriceLevelDTO;

import java.util.Comparator;

/**
 * Created by zahari on 26/03/2017.
 */
public class PriceLevelCollectorFactory {


    private static Comparator<PriceLevelDTO> priceLevelAscendingComparator  = Comparator.comparing(PriceLevelDTO::getPricePerKg);

    public static PriceLevelCollector toSellSideLevels() {
        return new PriceLevelCollector(priceLevelAscendingComparator);
    }

    public static PriceLevelCollector toBuySideLevels() {
        return new PriceLevelCollector(priceLevelAscendingComparator.reversed());
    }


}
