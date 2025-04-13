package com.example.instagram_server.video.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoListResDto {
    private Long id;
    private String title;
    private LocalDateTime uploadedAt;
}
