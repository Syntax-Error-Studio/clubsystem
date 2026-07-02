package com.clubsystem.mapper;

import com.clubsystem.bean.Notice;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface NoticeMapper {
    int addNotice(Notice notice);
    int deleteNoticeById(@Param("id") int id);
    int updateNotice(Notice notice);
    List<Notice> getAllNotices();
    List<Notice> getActiveNotices();
    Notice getNoticeById(@Param("id") int id);
}
