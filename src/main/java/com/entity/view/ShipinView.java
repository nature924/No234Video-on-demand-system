package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ShipinEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 视频信息
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("shipin")
public class ShipinView extends ShipinEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 视频类型的值
	*/
	@ColumnInfo(comment="视频类型的字典表值",type="varchar(200)")
	private String shipinValue;




	public ShipinView() {

	}

	public ShipinView(ShipinEntity shipinEntity) {
		try {
			BeanUtils.copyProperties(this, shipinEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
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




	@Override
	public String toString() {
		return "ShipinView{" +
			", shipinValue=" + shipinValue +
			"} " + super.toString();
	}
}
