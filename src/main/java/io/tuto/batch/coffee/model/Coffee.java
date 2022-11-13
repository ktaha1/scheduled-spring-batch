package io.tuto.batch.coffee.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Coffee {
    private String brand;
    private String origin;
    private String characteristics;
}
