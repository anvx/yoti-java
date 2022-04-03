package com.yoti.sample.hoover.clean.delivery.converters;

import com.yoti.sample.hoover.clean.Hoover;
import com.yoti.sample.hoover.clean.Position;
import com.yoti.sample.hoover.clean.common.RestConverter;
import com.yoti.sample.hoover.clean.delivery.rest.HooverRestRs;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link RestConverter}
 *
 * @author Artem Lukyanau
 */
@Component
public class HooverRestRsConverter implements RestConverter<HooverRestRs, Hoover> {

    @Override
    public HooverRestRs mapToRest(final Hoover entity) {
        Integer[] coords = getCoords(entity);
        long patchesCounter = getPatchesCounter(entity);

        return new HooverRestRs(coords, patchesCounter);
    }

    private long getPatchesCounter(Hoover entity) {
        return entity.getPatchesCounter();
    }

    private Integer[] getCoords(Hoover entity) {
        Position startPosition = entity.getCurrentPosition();
        return new Integer[]{startPosition.getX(), startPosition.getY()};
    }
}