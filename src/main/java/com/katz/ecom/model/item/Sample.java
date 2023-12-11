package com.katz.ecom.model.item;

import com.katz.ecom.enums.item.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "trial_products")
@NoArgsConstructor
@AllArgsConstructor
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sampleId;
    private Boolean isFavorite;
    private Integer mrp;
    private String category;
    private String subcategory;
    private String collection;
    private String categorySlug;
    private String subcategorySlug;
    private String ingredients;
    private String features;
    private String title;
    private List<String> images;
    private String brandName;
    private String skuId;
    private Integer brandId;
    private Integer productPoint;
    private Integer orderCount;
    private Integer cartQuantity;
    private Integer categoryId;
    private Integer subcategoryId;
    private Tag tag;
}
