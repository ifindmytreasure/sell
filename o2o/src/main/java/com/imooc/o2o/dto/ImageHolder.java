package com.imooc.o2o.dto;

import java.io.InputStream;

/**
 * Created by Unruly Wind on 2019/1/22/022.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class ImageHolder {
	private String imageName;
	private InputStream image;

	public ImageHolder(String imageName, InputStream image) {
		this.imageName = imageName;
		this.image = image;
	}

	public String getimageName() {
		return imageName;
	}

	public void setimageName(String imageName) {
		this.imageName = imageName;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}
}
