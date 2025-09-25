package thymeleaf.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Videos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;          // tên video
    private String description;    // mô tả
    private String thumbnail;      // ảnh đại diện
    private String path;           // file video
    private String genre;          // thể loại
    private Boolean active;        // trạng thái

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
