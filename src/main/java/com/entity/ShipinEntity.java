package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 视频信息
 *
 * @author 
 * @email
 */
@TableName("shipin")
public class ShipinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShipinEntity() {

	}

	public ShipinEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 视频标题
     */
    @ColumnInfo(comment="视频标题",type="varchar(200)")
    @TableField(value = "shipin_name")

    private String shipinName;


    /**
     * 视频封面
     */
    @ColumnInfo(comment="视频封面",type="varchar(200)")
    @TableField(value = "shipin_photo")

    private String shipinPhoto;


    /**
     * 视频
     */
    @ColumnInfo(comment="视频",type="varchar(200)")
    @TableField(value = "shipin_video")

    private String shipinVideo;


    /**
     * 赞
     */
    @ColumnInfo(comment="赞",type="int(11)")
    @TableField(value = "zan_number")

    private Integer zanNumber;


    /**
     * 踩
     */
    @ColumnInfo(comment="踩",type="int(11)")
    @TableField(value = "cai_number")

    private Integer caiNumber;


    /**
     * 视频类型
     */
    @ColumnInfo(comment="视频类型",type="int(11)")
    @TableField(value = "shipin_types")

    private Integer shipinTypes;


    /**
     * 视频热度
     */
    @ColumnInfo(comment="视频热度",type="int(11)")
    @TableField(value = "shipin_clicknum")

    private Integer shipinClicknum;


    /**
     * 视频介绍
     */
    @ColumnInfo(comment="视频介绍",type="longtext")
    @TableField(value = "shipin_content")

    private String shipinContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "shipin_delete")

    private Integer shipinDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Shipin{" +
            ", id=" + id +
            ", shipinName=" + shipinName +
            ", shipinPhoto=" + shipinPhoto +
            ", shipinVideo=" + shipinVideo +
            ", zanNumber=" + zanNumber +
            ", caiNumber=" + caiNumber +
            ", shipinTypes=" + shipinTypes +
            ", shipinClicknum=" + shipinClicknum +
            ", shipinContent=" + shipinContent +
            ", shipinDelete=" + shipinDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
