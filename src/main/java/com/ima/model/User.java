package com.ima.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体类
 * Created by 符柱成 on 17-3-11.
 */
@Entity
@Table(name = "user")
public class User {
    private Long id;
    //账号
    private String account;
    //头像
    private String avatar;
    //市
    private String city;
    //省
    private String province;
    //国家
    private String country;
    private Date createTime;
    //爱豆数目
    private Long iDouCount;
    //是否已经下线
    private boolean logOut;
    //最近下线时间
    private Date logOutTime;
    //纬度
    private Double latitude;
    //经度
    private Double longitude;
    //用户名
    private String name;
    //用户性别，F:女，M:男
    private String sex;
    //爱豆变化
    private Set<IDouChange> iDouChanges=new HashSet<IDouChange>();

    //充值金额
    private Long chargeMoney;
    //最新主动行为时间
    private Date updateTime;

    public User(Long id, String account, String avatar, String city, String province, String country, Date createTime, Long iDouCount, boolean logOut, Date logOutTime, Double latitude, Double longitude, String name, String sex, Set<IDouChange> iDouChanges,  Long chargeMoney, Date updateTime) {
        this.id = id;
        this.account = account;
        this.avatar = avatar;
        this.city = city;
        this.province = province;
        this.country = country;
        this.createTime = createTime;
        this.iDouCount = iDouCount;
        this.logOut = logOut;
        this.logOutTime = logOutTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.sex = sex;
        this.iDouChanges = iDouChanges;

        this.chargeMoney = chargeMoney;
        this.updateTime = updateTime;
    }

    public User() {
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time",nullable=true,columnDefinition="timestamp default current_timestamp")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "i_dou_count",nullable=false,columnDefinition = "BIGINT default 0")
    public Long getiDouCount() {
        return iDouCount;
    }

    public void setiDouCount(Long iDouCount) {
        this.iDouCount = iDouCount;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "log_out_time")
    public Date getLogOutTime() {
        return logOutTime;
    }

    public void setLogOutTime(Date logOutTime) {
        this.logOutTime = logOutTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @OneToMany(fetch= FetchType.LAZY,cascade={CascadeType.REMOVE},mappedBy="user")
    public Set<IDouChange> getiDouChanges() {
        return iDouChanges;
    }

    public void setiDouChanges(Set<IDouChange> iDouChanges) {
        this.iDouChanges = iDouChanges;
    }

    @Column(name = "log_out")
    public boolean getLogOut() {
        return logOut;
    }

    public void setLogOut(boolean logOut) {
        this.logOut = logOut;
    }



    @Column(name = "charge_money",nullable=false,columnDefinition = "BIGINT default 0")
    public Long getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(Long chargeMoney) {
        this.chargeMoney = chargeMoney;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
