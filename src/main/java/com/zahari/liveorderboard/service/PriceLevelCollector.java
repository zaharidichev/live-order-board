package com.zahari.liveorderboard.service;

import com.zahari.liveorderboard.domain.dto.OrderDTO;
import com.zahari.liveorderboard.domain.dto.PriceLevelDTO;
import com.zahari.liveorderboard.error.LiveOrderBookServiceException;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 *
 * A custom collecotor created for the purpose of
 * turning a stream of OrderDTO objects into a sorted
 * list of PriceLevelDTO ones. It uses a custom comparator
 * passed in the constructor, so we are capable of reusing
 * logic for both Sell and Buy side order collection operations
 * as they need different sorting criteria
 *
 * Created by zahari on 26/03/2017.
 */
public class PriceLevelCollector implements Collector<OrderDTO, Map<Double,PriceLevelDTO>, List<PriceLevelDTO>> {

    private Comparator<PriceLevelDTO> priceLevelComparator;

    public PriceLevelCollector(Comparator<PriceLevelDTO> priceLevelComparator) {
        this.priceLevelComparator = priceLevelComparator;
    }

    @Override
    public Supplier<Map<Double, PriceLevelDTO>> supplier() {
        return HashMap::new;
    }

    private static void sanityCheckLevels(PriceLevelDTO lv1, PriceLevelDTO lv2) {
        if(!lv1.getPricePerKg().equals(lv2.getPricePerKg())) {
            throw new LiveOrderBookServiceException("Prices for " + lv1 + " and " + lv2 + " are different but should be the same");
        }
        if(!lv1.getSide().equals(lv2.getSide())) {
            throw new LiveOrderBookServiceException("Market sides  for " + lv1 + " and " + lv2 + " are different but should be the same");
        }
    }

    @Override
    public BiConsumer<Map<Double, PriceLevelDTO>, OrderDTO> accumulator() {
        return (acc,elem) -> {
            PriceLevelDTO existingLevel = acc.get(elem.getPricePerKg());
            Set<String> orderIds = new HashSet<>();

            if(existingLevel != null) {
                // if a level exists,   we combine it with the incoming order
                Double newQuantity = existingLevel.getQuantityInKg() + elem.getQuantityInKg();
                orderIds.addAll(existingLevel.getOrderIds());
                orderIds.add(elem.getOrderId());
                PriceLevelDTO newLevel = new PriceLevelDTO(newQuantity,elem.getPricePerKg(),elem.getSide(),orderIds);
                acc.put(elem.getPricePerKg(),newLevel);
            }
            else {
                //if a level does not exist, we simply create one
                orderIds.add(elem.getOrderId());
                acc.put(elem.getPricePerKg(),new PriceLevelDTO(elem.getQuantityInKg(),elem.getPricePerKg(),elem.getSide(),orderIds));
            }
        };
    }

    public static PriceLevelDTO combineTwoLevels(PriceLevelDTO lv1, PriceLevelDTO lv2) {
        sanityCheckLevels(lv1,lv2);
        Set<String> newOrderIdsSet = Stream.concat(lv1.getOrderIds().stream(),lv2.getOrderIds().stream()).collect(toSet());
        return new PriceLevelDTO(lv1.getQuantityInKg() + lv2.getQuantityInKg(), lv1.getPricePerKg(),lv1.getSide(),newOrderIdsSet);
    }

    @Override
    public BinaryOperator<Map<Double, PriceLevelDTO>> combiner() {
        return (acc1, acc2) -> Stream.of(acc1, acc2)
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> combineTwoLevels(x, y)));
    }

    @Override
    public Function<Map<Double, PriceLevelDTO>, List<PriceLevelDTO>> finisher() {
        return (acc) -> acc.values()
                .stream()
                .sorted(priceLevelComparator)
                .collect(toList());    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

}
