package com.staxter.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CodeDescriptionResponseModel {

    private final String code;
    private final String description;
}
