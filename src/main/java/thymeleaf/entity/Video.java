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

    @Column(name = "title")
    private String title;          // tên video
    @Column(name = "description")
    private String description;    // mô tả
    @Column(name = "thumbnail")
    private String thumbnail;      // ảnh đại diện
    @Column(name = "path")
    private String path;           // file video
    @Column(name = "genre")
    private String genre;          // thể loại
    @Column(name = "active")
    private Boolean active;        // trạng thái

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}