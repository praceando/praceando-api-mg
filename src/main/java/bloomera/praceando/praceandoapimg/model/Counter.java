/*
 * Class: Counter
 * Description: Model for the Counter document.
 * Author: Camilla Ucci de Menezes
 * Creation Date: 28/10/2024
 * Last Updated: 28/10/2024
 */
package bloomera.praceando.praceandoapimg.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "counter")
@Schema(description = "Representa os contadores no sistema Praceando.")
public class Counter {
    @Id
    @Schema(description = "Identificador único", example = "avaliacao")
    private String id;
    @Schema(description = "Código sequencial.", example = "1")
    @Field(name = "sequence_value")
    private Long sequenceValue;
}
