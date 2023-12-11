package com.katz.ecom.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {
    private Long id;
    private String name;
    private String icon;
    private String metaTitle;
    private String metaDesc;
    private String metaKeyword;
    private int status;
    private String offerText;
}
