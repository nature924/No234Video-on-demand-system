package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ShipinLiuyanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 视频留言
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("shipin_liuyan")
public class ShipinLiuyanView extends ShipinLiuyanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表

	//级联表 视频信息
		/**
		* 视频标题
		*/

		@ColumnInfo(comment="视频标题",type="varchar(200)")
		private String shipinName;
		/**
		* 视频封面
		*/

		@ColumnInfo(comment="视频封面",type="varchar(200)")
		private String shipinPhoto;
		/**
		* 视频
		*/

		@ColumnInfo(comment="视频",type="varchar(200)")
		private String shipinVideo;
		/**
		* 赞
		*/

		@ColumnInfo(comment="赞",type="int(11)")
		private Integer zanNumber;
		/**
		* 踩
		*/

		@ColumnInfo(comment="踩",type="int(11)")
		private Integer caiNumber;
		/**
		* 视频类型
		*/
		@ColumnInfo(comment="视频类型",type="int(11)")
		private Integer shipinTypes;
			/**
			* 视频类型的值
			*/
			@ColumnInfo(comment="视频类型的字典表值",type="varchar(200)")
			private String shipinValue;
		/**
		* 视频热度
		*/

		@ColumnInfo(comment="视频热度",type="int(11)")
		private Integer shipinClicknum;
		/**
		* 视频介绍
		*/

		@ColumnInfo(comment="视频介绍",type="longtext")
		private String shipinContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer shipinDelete;
	//级联表 用户
		/**
		* 用户名称
		*/

		@ColumnInfo(comment="用户名称",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer yonghuDelete;



	public ShipinLiuyanView() {

	}

	public ShipinLiuyanView(ShipinLiuyanEntity shipinLiuyanEntity) {
		try {
			BeanUtils.copyProperties(this, shipinLiuyanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	//级联表的get和set 视频信息

		/**
		* 获取： 视频标题
		*/
		public String getShipinName() {
			return shipinName;
		}
		/**
		* 设置： 视频标题
		*/
		public void setShipinName(String shipinName) {
			this.shipinName = shipinName;
		}

		/**
		* 获取： 视频封面
		*/
		public String getShipinPhoto() {
			return shipinPhoto;
		}
		/**
		* 设置： 视频封面
		*/
		public void setShipinPhoto(String shipinPhoto) {
			this.shipinPhoto = shipinPhoto;
		}

		/**
		* 获取： 视频
		*/
		public String getShipinVideo() {
			return shipinVideo;
		}
		/**
		* 设置： 视频
		*/
		public void setShipinVideo(String shipinVideo) {
			this.shipinVideo = shipinVideo;
		}

		/**
		* 获取： 赞
		*/
		public Integer getZanNumber() {
			return zanNumber;
		}
		/**
		* 设置： 赞
		*/
		public void setZanNumber(Integer zanNumber) {
			this.zanNumber = zanNumber;
		}

		/**
		* 获取： 踩
		*/
		public Integer getCaiNumber() {
			return caiNumber;
		}
		/**
		* 设置： 踩
		*/
		public void setCaiNumber(Integer caiNumber) {
			this.caiNumber = caiNumber;
		}
		/**
		* 获取： 视频类型
		*/
		public Integer getShipinTypes() {
			return shipinTypes;
		}
		/**
		* 设置： 视频类型
		*/
		public void setShipinTypes(Integer shipinTypes) {
			this.shipinTypes = shipinTypes;
		}


			/**
			* 获取： 视频类型的值
			*/
			public String getShipinValue() {
				return shipinValue;
			}
			/**
			* 设置： 视频类型的值
			*/
			public void setShipinValue(String shipinValue) {
				this.shipinValue = shipinValue;
			}

		/**
		* 获取： 视频热度
		*/
		public Integer getShipinClicknum() {
			return shipinClicknum;
		}
		/**
		* 设置： 视频热度
		*/
		public void setShipinClicknum(Integer shipinClicknum) {
			this.shipinClicknum = shipinClicknum;
		}

		/**
		* 获取： 视频介绍
		*/
		public String getShipinContent() {
			return shipinContent;
		}
		/**
		* 设置： 视频介绍
		*/
		public void setShipinContent(String shipinContent) {
			this.shipinContent = shipinContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getShipinDelete() {
			return shipinDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setShipinDelete(Integer shipinDelete) {
			this.shipinDelete = shipinDelete;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户名称
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户名称
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getYonghuDelete() {
			return yonghuDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setYonghuDelete(Integer yonghuDelete) {
			this.yonghuDelete = yonghuDelete;
		}


	@Override
	public String toString() {
		return "ShipinLiuyanView{" +
			", shipinName=" + shipinName +
			", shipinPhoto=" + shipinPhoto +
			", shipinVideo=" + shipinVideo +
			", zanNumber=" + zanNumber +
			", caiNumber=" + caiNumber +
			", shipinClicknum=" + shipinClicknum +
			", shipinContent=" + shipinContent +
			", shipinDelete=" + shipinDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			", yonghuDelete=" + yonghuDelete +
			"} " + super.toString();
	}
}
