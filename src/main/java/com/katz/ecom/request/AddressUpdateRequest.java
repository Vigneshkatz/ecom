package com.katz.ecom.request;

import com.katz.ecom.model.Address;
import lombok.Data;

@Data
public class AddressUpdateRequest  {
    private Address address;
    private Long userId;
}
