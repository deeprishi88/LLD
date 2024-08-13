package model.account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import model.account.common.Person;

@Getter
@Builder
@NoArgsConstructor
public abstract class Account {
    private String id;
    private String username;
    private String password;
    private Person person;

}
