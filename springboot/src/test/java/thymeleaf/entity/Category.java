package thymeleaf.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "Categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;           // tên danh mục
    private String description;    // mô tả
    private String type;           // loại (phim, nhạc,…)
    private Boolean active;        // trạng thái hoạt động
    private String icon;           // đường dẫn icon
    private String image;          // ảnh minh họa
}
