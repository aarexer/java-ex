package aarexer.crud.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "warehouseId"})
public class Good {
    public static final String TABLE_NAME = "good";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String WAREHOUSE_ID_COLUMN = "warehouse_id";

    private Long id;
    private String name;
    private Long warehouseId;

    public Good(String name, Long warehouseId) {
        this.name = name;
        this.warehouseId = warehouseId;
    }
}
