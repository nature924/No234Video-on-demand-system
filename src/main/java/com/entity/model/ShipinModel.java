package com.entity.model;

import com.entity.ShipinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 视频信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ShipinModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 视频标题
     */
    private String shipinName;


    /**
     * 视频封面
     */
    private String shipinPhoto;


    /**
     * 视频
     */
    private String shipinVideo;


    /**
     * 赞
     */
    private Integer zanNumber;


    /**
     * 踩
     */
    private Integer caiNumber;


    /**
     * 视频类型
     */
    private Integer shipinTypes;


    /**
     * 视频热度
     */
    private Integer shipinClicknum;


    /**
     * 视频介绍
     */
    private String shipinContent;


    /**
     * 逻辑删除
     */
    private Integer shipinDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：视频标题
	 */
    public String getShipinName() {
        return shipinName;
    }


    /**
	 * 设置：视频标题
	 */
    public void setShipinName(String shipinName) {
        this.shipinName = shipinName;
    }
    /**
	 * 获取：视频封面
	 */
    public String getShipinPhoto() {
        return shipinPhoto;
    }


    /**
	 * 设置：视频封面
	 */
    public void setShipinPhoto(String shipinPhoto) {
        this.shipinPhoto = shipinPhoto;
    }
    /**
	 * 获取：视频
	 */
    public String getShipinVideo() {
        return shipinVideo;
    }


    /**
	 * 设置：视频
	 */
    public void setShipinVideo(String shipinVideo) {
        this.shipinVideo = shipinVideo;
    }
    /**
	 * 获取：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }


    /**
	 * 设置：赞
	 */
    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 获取：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }


    /**
	 * 设置：踩
	 */
    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 获取：视频类型
	 */
    public Integer getShipinTypes() {
        return shipinTypes;
    }


    /**
	 * 设置：视频类型
	 */
    public void setShipinTypes(Integer shipinTypes) {
        this.shipinTypes = shipinTypes;
    }
    /**
	 * 获取：视频热度
	 */
    public Integer getShipinClicknum() {
        return shipinClicknum;
    }


    /**
	 * 设置：视频热度
	 */
    public void setShipinClicknum(Integer shipinClicknum) {
        this.shipinClicknum = shipinClicknum;
    }
    /**
	 * 获取：视频介绍
	 */
    public String getShipinContent() {
        return shipinContent;
    }


    /**
	 * 设置：视频介绍
	 */
    public void setShipinContent(String shipinContent) {
        this.shipinContent = shipinContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getShipinDelete() {
        return shipinDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setShipinDelete(Integer shipinDelete) {
        this.shipinDelete = shipinDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：录入时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
