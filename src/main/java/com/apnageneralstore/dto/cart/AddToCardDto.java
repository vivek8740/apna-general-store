package com.apnageneralstore.dto.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AddToCardDto {

    private Integer id;
    private @NotNull Long productId;
    private @NotNull Integer quantity;
}
