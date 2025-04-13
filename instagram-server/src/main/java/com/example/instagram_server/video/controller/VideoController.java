package com.example.instagram_server.video.controller;

import com.example.instagram_server.video.domain.Video;
import com.example.instagram_server.video.dto.VideoListResDto;
import com.example.instagram_server.video.dto.VideoResDto;
import com.example.instagram_server.video.dto.VideoSaveReqDto;
import com.example.instagram_server.video.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/video")
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> videoAdd(@RequestBody VideoSaveReqDto videoSaveReqDto) {
        Video video = videoService.add(videoSaveReqDto);
        return new ResponseEntity<>(video.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> videoList() {
        List<VideoListResDto> dtos = videoService.findAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVideoById(@PathVariable("id") Long id) {
        VideoResDto video = videoService.findById(id);
        return new ResponseEntity<>(video, HttpStatus.OK);
    }

}
