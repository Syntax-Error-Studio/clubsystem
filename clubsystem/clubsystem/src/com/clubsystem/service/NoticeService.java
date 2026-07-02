package com.clubsystem.service;

import com.clubsystem.bean.Notice;
import com.clubsystem.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    public boolean addNotice(Notice notice) {
        return noticeMapper.addNotice(notice) > 0;
    }

    public boolean deleteNotice(int id) {
        return noticeMapper.deleteNoticeById(id) > 0;
    }

    public boolean updateNotice(Notice notice) {
        return noticeMapper.updateNotice(notice) > 0;
    }

    public List<Notice> listAllNotices() {
        return noticeMapper.getAllNotices();
    }

    public List<Notice> listActiveNotices() {
        return noticeMapper.getActiveNotices();
    }

    public Notice getNoticeById(int id) {
        return noticeMapper.getNoticeById(id);
    }
}
