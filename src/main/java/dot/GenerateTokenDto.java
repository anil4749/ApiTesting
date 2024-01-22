package dot;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GenerateTokenDto {
    String customer_id;
}
