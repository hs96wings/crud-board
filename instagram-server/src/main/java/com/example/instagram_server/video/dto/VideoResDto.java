package com.example.instagram_server.video.dto;

import com.example.instagram_server.video.domain.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoResDto {
    private Long id;
    private String title;
    private String videoId;

    public VideoResDto(Video video) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.videoId = video.getVideoId();
    }
}
