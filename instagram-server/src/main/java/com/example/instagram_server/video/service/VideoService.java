package com.example.instagram_server.video.service;

import com.example.instagram_server.video.domain.Video;
import com.example.instagram_server.video.dto.VideoListResDto;
import com.example.instagram_server.video.dto.VideoResDto;
import com.example.instagram_server.video.dto.VideoSaveReqDto;
import com.example.instagram_server.video.repository.VideoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Video> getVideos(Pageable pageable) {
        return videoRepository.findAll(pageable);
    }

    public VideoResDto findById(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 영상이 존재하지 않습니다"));
        return new VideoResDto(video);
    }

    @Transactional
    public void update(VideoSaveReqDto videoSaveReqDto, Long id) {
        // 1. 기존 엔티티 조회
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영상입니다"));

        // 2. 필드 변경
        video.setTitle(videoSaveReqDto.getTitle());
        video.setVideoId(videoSaveReqDto.getVideoId());

        // 3. 저장은 안 해도 됨 -> Dirty Checking 자동 반영
    }

    public void delete(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영상입니다"));
        videoRepository.delete(video);
    }

    public Page<Video> searchByTitle(String title, Pageable pageable) {
        if (title == null) title = "";

        return videoRepository.findByTitleContaining(title, pageable);
    }
}
