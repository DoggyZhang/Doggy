package com.news.model.bean.user;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class LoginInfo {

    private long loginTime;

    private User user;

    public LoginInfo() {
        this.user = new User();
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class User {
        private String userName;
        private String userIcon;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserIcon() {
            return userIcon;
        }

        public void setUserIcon(String userIcon) {
            this.userIcon = userIcon;
        }
    }
}
