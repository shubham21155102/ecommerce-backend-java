package com.ecommerce.backend.Users;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String Title;
    @Column(name = "description")
    private String Description;
    @Column(name = "price")
    private int price;
    @Column(name = "discounted_price")
    private int discountedPrice;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "brand")
    private String Brand;
    @Column(name = "color")
    private String Color;
    @Embedded
    @ElementCollection
    @Column(name = "sizes")
    private Set<SizeModel> sizes=new HashSet<>();
    @Column(name = "image_url")
    private String imageURL;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<RatingsModel> ratings=new ArrayList<>();
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ReviewModel> reviews=new ArrayList<>();
    @Column(name = "num_ratings")
    private int numRatings;
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private CategoryModel category;
    public LocalDateTime createdAt;

}
