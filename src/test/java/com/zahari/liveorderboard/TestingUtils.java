package com.zahari.liveorderboard;

import com.zahari.liveorderboard.domain.PriceLevelDTO;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

/**
 * Created by zahari on 26/03/2017.
 */
public class TestingUtils {

    public static Matcher<? super List<PriceLevelDTO>> sellOrdersInAscendingOrder() {
        return isInDescendingOrdering(true);
    }

    public static Matcher<? super List<PriceLevelDTO>> buyOrdersInDescendingOrder() {
        return isInDescendingOrdering(false);
    }


    private static Matcher<? super List<PriceLevelDTO>> isInDescendingOrdering(boolean descending)
    {
        return new TypeSafeMatcher<List<PriceLevelDTO>>()
        {
            @Override
            public void describeTo (Description description)
            {
                description.appendText("describe the error has you like more");
            }

            @Override
            protected boolean matchesSafely (List<PriceLevelDTO> item)
            {
                for(int i = 0 ; i < item.size() -1; i++) {

                    if(descending) {
                        if(item.get(i).getPricePerKg() <= item.get(i+1).getPricePerKg()) return false;
                    } else {
                        if(item.get(i).getPricePerKg() >= item.get(i+1).getPricePerKg()) return false;

                    }

                }
                return true;
            }
        };
    }

}
