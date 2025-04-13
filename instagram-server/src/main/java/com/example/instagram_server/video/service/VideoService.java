package com.example.instagram_server.video.service;

import com.example.instagram_server.video.domain.Video;
import com.example.instagram_server.video.dto.VideoListResDto;
import com.example.instagram_server.video.dto.VideoResDto;
import com.example.instagram_server.video.dto.VideoSaveReqDto;
import com.example.instagram_server.video.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VideoService {
    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video add(VideoSaveReqDto videoSaveReqDto) {
        // 이미 등록되어 있는 비디오 검증
        if (videoRepository.findByVideoId(videoSaveReqDto.getVideoId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 영상입니다");
        }
        Video newVideo = Video.builder()
                .title(videoSaveReqDto.getTitle())
                .videoId(videoSaveReqDto.getVideoId())
                .build();
        return videoRepository.save(newVideo);
    }

    public List<VideoListResDto> findAll() {
        List<Video> videos = videoRepository.findAll();
        List<VideoListResDto> videoListResDtos = new ArrayList<>();
        for (Video v : videos) {
            VideoListResDto videoListResDto = new VideoListResDto();
            videoListResDto.setId(v.getId());
            videoListResDto.setTitle(v.getTitle());
            videoListResDto.setUploadedAt(v.getUploadedAt());
            videoListResDtos.add(videoListResDto);
        }
        return videoListResDtos;
    }

    public VideoResDto findById(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 영상이 존재하지 않습니다"));
        return new VideoResDto(video);
    }
}
