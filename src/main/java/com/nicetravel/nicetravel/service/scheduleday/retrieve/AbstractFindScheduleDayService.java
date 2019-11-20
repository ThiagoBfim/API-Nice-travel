package com.nicetravel.nicetravel.service.scheduleday.retrieve;

import com.nicetravel.nicetravel.dto.ScheduleDayDTO;
import org.springframework.lang.NonNull;

import java.util.List;

public abstract class AbstractFindScheduleDayService {
    public abstract List<ScheduleDayDTO> getScheduleDays(@NonNull Long scheduleId);
}
