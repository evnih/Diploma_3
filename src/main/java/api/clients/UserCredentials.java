package api.clients;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserCredentials {
    private String email;
    private String password;
}