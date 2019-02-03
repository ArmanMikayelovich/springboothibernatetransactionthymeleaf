package Start.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SendMoneyForm {
    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;

    public SendMoneyForm() {
    }

    public SendMoneyForm(Long fromAccountId, Long toAccountId, Double amount) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }


}
