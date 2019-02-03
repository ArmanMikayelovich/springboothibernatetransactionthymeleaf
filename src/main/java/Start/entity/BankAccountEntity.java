package Start.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "BANK_ACCOUNT")
public class BankAccountEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "FULL_NAME",unique = false,nullable = false,
            length = 128)
    private String fullName;


    @Column(name = "BALANCE", nullable = false)
    private long balance;




}
