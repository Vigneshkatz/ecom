package com.katz.ecom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDTO {
    @JsonProperty("error")
    private Integer errorCode;
    @JsonProperty("error_message")
    private String errorMessage;
}
