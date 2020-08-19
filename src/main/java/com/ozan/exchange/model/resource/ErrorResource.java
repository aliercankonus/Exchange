package com.ozan.exchange.model.resource;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResource {
    private final String id;
    private final String message;
}
