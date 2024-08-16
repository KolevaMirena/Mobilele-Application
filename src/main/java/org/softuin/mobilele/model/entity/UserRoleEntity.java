package org.softuin.mobilele.model.entity;



import jakarta.persistence.*;
import org.softuin.mobilele.model.enums.UserRoleEnum;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity{


    @Enumerated(EnumType.STRING)

    private UserRoleEnum role;

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }
}
