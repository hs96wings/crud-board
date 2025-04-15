package com.example.instagram_server.video.dto;

import com.example.instagram_server.video.domain.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoResDto {
    private Long id;
    private String title;
    private String videoId;
    private LocalDateTime uploadedAt;

    public VideoResDto(Video video) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.videoId = video.getVideoId();
        this.uploadedAt = video.getUploadedAt();
    }
}
