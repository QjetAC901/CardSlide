package com.cgq.cardslide.bean;

/**
 * Android 网络抓取数据  封装对象
 * Created by 55492 on 2017/2/15.
 * <p>
 * 12/27
 */

public class Xiaohua {
    //类型
    private String type;
    //头像
    private String userPic;
    //昵称
    private String userName;
    //内容
    private String content;
    //图片
    private String image;


    public Xiaohua(String type) {
        this.type = type;
    }

    public Xiaohua(String type, String userPic, String userName, String content, String image) {
        this.type = type;
        this.userPic = userPic;
        this.userName = userName;
        this.content = content;
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
