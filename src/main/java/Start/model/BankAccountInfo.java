package Start.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BankAccountInfo {
    private long id;

    private String fullName;

    private double balance;

    public BankAccountInfo(long id, String fullName, double balance) {
        this.id = id;
        this.fullName = fullName;
        this.balance = balance;
    }
}




