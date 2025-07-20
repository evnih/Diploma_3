package api.clients;

import constant.Locators;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private String email;
    private String password;
    private String name;
    private String accessToken;


}