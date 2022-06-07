package other.hibernate_try.one_to_many;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ACCOUNT")
public class AccountEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer accountId;
    @Column(name = "ACC_NUMBER")
    private String accountNumber;

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
//Other fields, getters, setters are hidden for brevity
}
