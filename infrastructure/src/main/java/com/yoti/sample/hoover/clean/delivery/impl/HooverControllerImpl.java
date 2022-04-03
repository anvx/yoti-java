package com.yoti.sample.hoover.clean.delivery.impl;

import com.yoti.sample.hoover.clean.Hoover;
import com.yoti.sample.hoover.clean.common.constants.RestConstants;
import com.yoti.sample.hoover.clean.delivery.HooverController;
import com.yoti.sample.hoover.clean.delivery.converters.HooverRestRqConverter;
import com.yoti.sample.hoover.clean.delivery.converters.HooverRestRsConverter;
import com.yoti.sample.hoover.clean.delivery.rest.HooverRestRq;
import com.yoti.sample.hoover.clean.delivery.rest.HooverRestRs;
import com.yoti.sample.hoover.clean.usecase.CleanTheRoomUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_HOOVER)
@RequiredArgsConstructor
public class HooverControllerImpl implements HooverController {

    private final CleanTheRoomUseCase cleanTheRoomUseCase;
    private final HooverRestRqConverter hooverRestRqConverter;
    private final HooverRestRsConverter hooverRestRsConverter;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public HooverRestRs cleanTheRoom(@Valid @RequestBody HooverRestRq hooverRestRq) {
        Hoover hooverEntity = cleanTheRoomUseCase.execute(hooverRestRqConverter.mapToEntity(hooverRestRq));
        return hooverRestRsConverter.mapToRest(hooverEntity);
    }
}