package com.katz.ecom.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katz.ecom.dto.ErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    @JsonProperty("content")
    private Object content;
    @JsonProperty("response")
    private ErrorDTO response;
}
