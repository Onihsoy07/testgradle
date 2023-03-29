package com.example.testgradle.data.entity;

import com.example.testgradle.data.dto.ProductDto;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product_entity", schema = "product", uniqueConstraints = {@UniqueConstraint(columnNames = "product_id")})
public class ProductEntity extends BaseEntity {

    @Id
    @NotNull
    @Column(name = "product_id", unique = true, nullable = false)
    private String productId;

    @Column(name = "product_name")
    @NotNull
    private String productName;

    @Column(name = "product_price")
    @NotNull
    @Min(value = 500)
    @Max(value = 3000000)
    private int productPrice;

    @Column(name = "product_stock")
    @NotNull
    @Min(value = 0)
    @Max(value = 9999)
    private int productStock;

//   productStock public ProductDto toDto() {
//        return ProductDto.builder()
//                .
//    }

}