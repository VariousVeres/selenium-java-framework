package other.hibernate_try.one_to_one;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name="users")
public class User implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn (name="address_id", referencedColumnName = "id")
    private Address address;

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setId(Integer id) {
        this.userId = id;
    }

    public void setName(String name) {
        this.name = name;
    }




}




