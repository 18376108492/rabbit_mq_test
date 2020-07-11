package com.itdan.rabbitmq.test.spring.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息记录表
 * 用于记录消息的消费情况，确保消息的一致性
 */
public class MsgLog implements Serializable {

    private Integer msgId;//消息的ID
    private Integer relationId;//与某事件关联的ID
    private Integer state;//传递的状态 0:消息投递中，1：消息投递成功，2:消息投递失败
    private String routeKey;//路由Key
    private String exchange;//转换器
    private Integer count;//请求总数
    private Date tryDate;//重试时间
    private Date createTime;
    private Date updateTime;
    private String meun;

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getTryDate() {
        return tryDate;
    }

    public void setTryDate(Date tryDate) {
        this.tryDate = tryDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMeun() {
        return meun;
    }

    public void setMeun(String meun) {
        this.meun = meun;
    }

    @Override
    public String toString() {
        return "MsgLog{" +
                "msgId=" + msgId +
                ", relationId=" + relationId +
                ", state=" + state +
                ", rountKey='" + routeKey + '\'' +
                ", exchange='" + exchange + '\'' +
                ", count=" + count +
                ", tryDate=" + tryDate +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", meun='" + meun + '\'' +
                '}';
    }
}
