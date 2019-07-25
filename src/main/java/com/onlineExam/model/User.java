package com.onlineExam.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "onlineexamuser")
public class User {
    /**
     * 主键ID
     */
    @Id
    private Integer id;

    /**
     * 姓名
     */
    @Column(name = "job_no")
    private String jobNo;

    /**
     * 账号,一般为学号或者教工号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * QQ
     */
    private String qq;

    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 个人描述
     */
    private String description;

    /**
     * 0表示学生,1表示管理员
     */
    private Integer level;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return job_no - 姓名
     */
    public String getJobNo() {
        return jobNo;
    }

    /**
     * 设置姓名
     *
     * @param jobNo 姓名
     */
    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    /**
     * 获取账号,一般为学号或者教工号
     *
     * @return username - 账号,一般为学号或者教工号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置账号,一般为学号或者教工号
     *
     * @param username 账号,一般为学号或者教工号
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取QQ
     *
     * @return qq - QQ
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置QQ
     *
     * @param qq QQ
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取个人描述
     *
     * @return description - 个人描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置个人描述
     *
     * @param description 个人描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取0表示学生,1表示管理员
     *
     * @return level - 0表示学生,1表示管理员
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置0表示学生,1表示管理员
     *
     * @param level 0表示学生,1表示管理员
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}