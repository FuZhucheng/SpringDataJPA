package com.ima.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 积分变化实体类
 * Created by 符柱成 on 17-3-11.
 */
@Entity
@Table(name = "i_dou_change")
public class IDouChange {
    private Long id;
    //积分变化的类型，拉黑或者在线或者充值
    private String changeType;
    private Date createTime;
    //积分变化数
    private Integer iDouCount;
    //积分变化者
    private User user;


    public IDouChange() {
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "change_type")
    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time",nullable=true,columnDefinition="timestamp default current_timestamp")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "i_dou_count")
    public Integer getiDouCount() {
        return iDouCount;
    }

    public void setiDouCount(Integer iDouCount) {
        this.iDouCount = iDouCount;
    }

    @JoinColumn(name="user_id")
    @ManyToOne(fetch= FetchType.LAZY)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
