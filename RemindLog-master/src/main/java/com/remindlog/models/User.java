package com.remindlog.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Transient
    private String passwordVal; //password check

    @Column(unique = true)
    private String email;
    private String role;
    private boolean enabled;
//    private int phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Reminder> reminders;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //TEST cascade = CascadeType.ALL
    @JoinTable(
            name="user_sharegroups",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="sharegroup_id"))
    private List<ShareGroup> shareGroups; //instantiate?

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

//    public int getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(int phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    //Adds new reminder to User object, updates both sides of relationship
    public void addReminder(Reminder reminder){
        reminder.setUser(this);
        this.reminders.add(reminder);
    }

    public Long getId() {
        return id;
    }

    public List<ShareGroup> getShareGroups() {
        return shareGroups;
    }

    public void setShareGroups(List<ShareGroup> shareGroups) {
        this.shareGroups = shareGroups;
    }

    //Might cause error
    public void createShareGroup(ShareGroup shareGroup){
        List<User> users = new ArrayList<>();
        users.add(this);
//        shareGroup.getUserList().add(this);
        shareGroup.setUserList(users);
        shareGroup.setFounder(this);
        this.shareGroups.add(shareGroup);
    }

    public String getPasswordVal() {
        return passwordVal;
    }

    public void setPasswordVal(String passwordVal) {
        this.passwordVal = passwordVal;
    }
}
