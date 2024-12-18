/*
 * Class: Recorrencia
 * Description: Model for the Recorrencia document.
 * Author: Camilla Ucci de Menezes
 * Creation Date: 03/10/2024
 * Last Updated: 29/10/2024
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

    @NotBlank(message = "O tipo da recorrência ('nm_tipo') não pode estar vazio.")
    @Schema(description = "Tipo de recorrência.", example = "semanal")
    @Field(name = "nm_tipo")
    private String nmTipo;

    @Schema(description = "Lista de dias da semana para recorrência semanal.", example = "[\"Segunda-feira\", \"Quarta-feira\"]")
    @Field(name = "ds_dias_semana")
    private List<String> dsDiasSemana;

    @Schema(description = "Dia do mês para recorrência mensal.", example = "15")
    @Field(name = "nr_dia_mes")
    private Integer nrDiaMes;

    @NotNull(message = "O código do evento da recorrência ('id_evento') não pode estar vazio.")
    @Schema(description = "Código do evento associado à recorrência.", example = "1")
    @Field(name = "id_evento")
    private Long idEvento;
}
