package com.remindlog.services;

import com.remindlog.models.Reminder;
import com.remindlog.models.ReminderPost;
import com.remindlog.models.ShareGroup;
import com.remindlog.models.User;

public interface ReminderService {
//    Reminder findAReminderByNameAndUser(String name, User user);
    void editReminder(Reminder reminder);
    void deleteReminderById(Long id);
    Reminder findAReminderById(Long id);
    Reminder convertReminder(ReminderPost reminderPost, ShareGroup shareGroup);
}
