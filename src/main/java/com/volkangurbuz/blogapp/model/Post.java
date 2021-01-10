package com.volkangurbuz.blogapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Entity
@Table
@Getter
@Setter
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank @Column private String title;
  // @Lob anotation for our content field as it contains HTML code which may also include image
  @Lob @Column @NotEmpty private String content;
  @Column private Instant createdOn;
  @Column private Instant updatedOn;
  @Column @NotBlank private String username;
}
