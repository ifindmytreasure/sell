package com.imooc.o2o.dto;

import java.util.HashSet;

/**
 * Created by Unruly Wind on 2019/3/9/009.
 *
 * @author BlueMelancholy
 * @desc: 迎合echart里的xAxis项
 */
public class EchartXAxis {
	private String type = "category";
	//为了去重
	private HashSet<String> data;
	//	private Number splitNumber = 7;
	private Number nameRotator = 45;

	public Number getNameRotator() {
		return nameRotator;
	}

//	public Number getSplitNumber() {
//		return splitNumber;
//	}

	public HashSet<String> getData() {
		return data;
	}

	public void setData(HashSet<String> data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}
}
