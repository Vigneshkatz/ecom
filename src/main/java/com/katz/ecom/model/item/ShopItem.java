package com.katz.ecom.model.item;

import com.katz.ecom.enums.item.Tag;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopItem {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shopItemId;
    private String sku;
    private String category;
    private String subcategory;
    private String collectionSlug;
    private int quantity;
    private boolean notify;
    private int price;
    private String discount;
    private double discountPer;
    private int mrp;
    private String size;
    private String color;
    private boolean mini;
    private boolean isFavorite;
    private String extraDiscount;
    private int brandId;
    private String brandName;
    private String imageUrl;
    private boolean isOrdered;
    private int categoryId;
    private int subcategoryId;
    private Tag tag;
}
