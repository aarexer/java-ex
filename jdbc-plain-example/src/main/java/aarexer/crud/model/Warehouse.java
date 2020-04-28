package aarexer.crud.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
public class Warehouse {
    public static final String TABLE_NAME = "warehouse";
    public static final String ID_COLUMN = "id";
    public static final String ADDRESS_COLUMN = "address";

    private Long id;
    private String address;

    public Warehouse(String address) {
        this.address = address;
    }
}
