/*
 * Class: Recorrencia
 * Description: Model for the Recorrencia document.
 * Author: Camilla Ucci de Menezes
 * Creation Date: 03/10/2024
 * Last Updated: 03/10/2024
 */
package bloomera.praceando.praceandoapimg.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "recorrencia")
@Schema(description = "Representa a recorrência de eventos no sistema Praceando.")
public class Recorrencia {
    @Id
    @Schema(description = "Identificador único da recorrência.", example = "66fd42eb50119ee6449ea103")
    private String id;

    @Schema(description = "Identificador da recorrência.", example = "1")
    @Field(name = "id_recorrencia")
    private Long idRecorrencia;

    @NotBlank(message = "O tipo da recorrência ('ds_tipo') não pode estar vazio.")
    @Schema(description = "Tipo de recorrência.", example = "semanal")
    @Field(name = "ds_tipo")
    private String dsTipo;

    @Schema(description = "Dias da semana em que a recorrência ocorre.", example = "[1, 2, 3]")
    @Field(name = "ds_dias_da_semana")
    private List<Integer> dsDiasDaSemana;

    @NotNull(message = "O código do evento da recorrência ('cd_evento') não pode estar vazio.")
    @Schema(description = "Código do evento associado à recorrência.", example = "1")
    @Field(name = "cd_evento")
    private Long cdEvento;
}
