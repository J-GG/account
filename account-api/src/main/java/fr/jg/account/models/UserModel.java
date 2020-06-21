package fr.jg.account.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserModel extends BaseModel {

    private String name;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private EstateModel estate;

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public EstateModel getEstate() {
        return this.estate;
    }

    public void setEstate(final EstateModel estate) {
        this.estate = estate;
    }
}
