package com.clubsystem.mapper;

import com.clubsystem.bean.Checkin;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface CheckinMapper {
    int addCheckin(Checkin checkin);
    List<Checkin> getCheckinsByActivityId(@Param("activityId") int activityId);
    List<Checkin> getCheckinsByUserId(@Param("userId") int userId);
    Checkin getCheckinBySignupId(@Param("signupId") int signupId);

    int deleteCheckinsByActivityId(@Param("activityId") int activityId);

    int deleteCheckinBySignupId(@Param("signupId") int signupId);
}
