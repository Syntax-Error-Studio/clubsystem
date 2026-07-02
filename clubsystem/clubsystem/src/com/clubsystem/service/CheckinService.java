package com.clubsystem.service;

import com.clubsystem.bean.Checkin;
import com.clubsystem.mapper.CheckinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckinService {
    @Autowired
    private CheckinMapper checkinMapper;

    public boolean addCheckin(Checkin checkin) {
        return checkinMapper.addCheckin(checkin) > 0;
    }

    public List<Checkin> listCheckinsByActivity(int activityId) {
        return checkinMapper.getCheckinsByActivityId(activityId);
    }

    public List<Checkin> listCheckinsByUser(int userId) {
        return checkinMapper.getCheckinsByUserId(userId);
    }

    public Checkin getCheckinBySignupId(int signupId) {
        return checkinMapper.getCheckinBySignupId(signupId);
    }
}
