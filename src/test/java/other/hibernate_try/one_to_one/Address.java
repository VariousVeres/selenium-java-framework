package other.hibernate_try.one_to_one;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="addresses")
public class Address   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private int houseNumber;

    @OneToOne (mappedBy = "address")
    private User user;

    public void setId(int id) {
        this.id = id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }
}
