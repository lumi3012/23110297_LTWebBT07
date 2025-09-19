package thymeleaf.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;

@Entity
@Table(name = "Users",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"username"}),
           @UniqueConstraint(columnNames = {"email"})
       })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String fullname;

    @Column(nullable = false)
    private String password; // BCrypt hashed

    @Column(length = 20)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(name = "roleid", nullable = false)
    private Integer roleid; // 1=admin, 2=manager, 3=user
    
    @Column(name = "avatar")
    private String avatar;        // ảnh đại diện
    
    @Column(name = "active", nullable = false)
    private Boolean active;       // còn hoạt động không
}
