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
    @Column(name = "name")
    private String name;           // tên danh mục
    @Column(name = "description")
    private String description;    // mô tả
    @Column(name = "type")
    private String type;           // loại (phim, nhạc,…)
    @Column(name = "active")
    private Boolean active;        // trạng thái hoạt động
    @Column(name = "icon")
    private String icon;           // đường dẫn icon
    @Column(name = "image")
    private String image;          // ảnh minh họa
}