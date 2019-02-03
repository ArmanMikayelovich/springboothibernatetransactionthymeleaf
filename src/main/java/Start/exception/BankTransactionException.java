package Start.exception;

public class BankTransactionException extends Exception {
        public static final long serialVersionUID = -3128681006635769411L;

    public BankTransactionException(String message) {
        super(message);
    }
}
