package com.example.instagram_server.video.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoSaveReqDto {
    private String title;
    private String videoId;
}
