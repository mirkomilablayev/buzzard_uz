package uz.buzzard.buzzard_uz.dto.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostShowDto {
    private Long id;
    private Long fileId;
    private String title;
    private String text;
    private List<PostShortInfoDto> posts;
}
