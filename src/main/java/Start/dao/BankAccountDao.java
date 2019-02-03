package Start.dao;

import Start.entity.BankAccountEntity;
import Start.exception.BankTransactionException;
import Start.model.BankAccountInfo;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class BankAccountDao {

    @Autowired
    private SessionFactory sessionFactory;


    public BankAccountEntity findById(int id) {
        Session session = sessionFactory.openSession();
        BankAccountEntity accountEntity =
                session.get(BankAccountEntity.class, id);
        return accountEntity;


    }

    public List<BankAccountInfo> listBankAccountInfo() {
        Session session = sessionFactory.openSession();

        return session.createQuery("SELECT a.id, a.fullName, a.balance FROM  BankAccountEntity a",
                BankAccountInfo.class).list();

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void addAmount(Long id, double amount) throws BankTransactionException {
        BankAccountEntity bankAccountEntity = this.findById(id);
        if (bankAccountEntity = null) {
            throw new BankTransactionException();
        }
        double newBalance = bankAccountEntity.getBalance() + amount;
        if (bankAccountEntity.getBalance() + amount < 0) {
            throw new BankTransactionException(  "The money in the account '" +
                    id +
                    "' is not enough (" + bankAccountEntity.getBalance() + ")");
        }
        bankAccountEntity.setBalance(newBalance);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
    public void sendMoney(Long fromAccountId, Long toAccountId, double amount) throws BankTransactionException {
        addAmount(toAccountId, amount);
        addAmount(fromAccountId, -amount);

    }


}
