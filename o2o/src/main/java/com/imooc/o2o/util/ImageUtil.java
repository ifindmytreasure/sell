package com.imooc.o2o.util;

import com.imooc.o2o.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


/**
 * Created by Unruly Wind on 2018/12/24/024.
 *
 * @author BlueMelancholy
 * @desc:图片工具类实例，利用thumbnailator技术
 */
public class ImageUtil {
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final Random random = new Random();
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static Logger logger;

	static {
		logger = LoggerFactory.getLogger(ImageUtil.class);
	}

	/**
	 * 将CommonsMultipartFile转换成File类型
	 *
	 * @param cFile
	 * @return
	 */
	public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
		File newFile = new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
	}

	/**
	 * 处理缩略图，并返回新生成图片的相对值路径
	 *
	 * @return
	 */
	public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
		/**
		 * 随机产生文件名
		 */
		String realFileName = getRandomFileName();
		/**
		 * 获取文件的扩展名
		 */
		String extension = getFileExtension(thumbnail.getimageName());
		/**
		 * 创建目标路径
		 */
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is: " + relativeAddr);
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete addr is: " + PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getImage()).size(200, 200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f).outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return relativeAddr;
	}

	/**
	 * 处理详情图,并返回新生成图片的相对值路径
	 *
	 * @param thumbnail
	 * @param targetAddr
	 * @return
	 */
	public static String generateNormalThumbnail(ImageHolder thumbnail, String targetAddr) {
		/**
		 * 随机产生文件名
		 */
		String realFileName = getRandomFileName();
		/**
		 * 获取文件的扩展名
		 */
		String extension = getFileExtension(thumbnail.getimageName());
		/**
		 * 创建目标路径
		 */
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is: " + relativeAddr);
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete addr is: " + PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getImage()).size(337, 640).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f).outputQuality(0.9f).toFile(dest);
		} catch (IOException e) {
			logger.error(e.toString());
			throw new RuntimeException("创建图片失败" + e.toString());
		}
		return relativeAddr;
	}

	/**
	 * 创建目标路径所涉及到的目录
	 *
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 获取文件的扩展名
	 *
	 * @param fileName
	 * @return
	 */
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 作为测试的main方法
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println(basePath);
		/**
		 * 获取原图，调整大小，在加水印，
		 * 参数:1.放水印的位置
		 * 2.获取水印路径（线程逆推，先获取当前线程然后获得类加载器，再获得资源的路径）
		 * 3.水印的透明度透明度
		 *压缩率，最后一个方法表示最终图片输出位置
		 */
		Thumbnails.of(new File("C:\\Users\\Administrator\\Pictures\\李若嘉3.jpg")).size(300, 300).rotate(90)
				.watermark(Positions.TOP_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.5f).outputQuality(0.8f).toFile("C:\\Users\\Administrator\\Pictures\\" + UUID.randomUUID() + ".jpg");
	}

	/**
	 * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
	 *
	 * @return
	 */
	public static String getRandomFileName() {
		//生成五位随机数
		int randomNum = random.nextInt(89999) + 10000;
		String nowTimeStr = simpleDateFormat.format(new Date());
		return nowTimeStr + randomNum;
	}

	/**
	 * storePath是文件的路径还是目录的路径， 如果storePath是文件路径则删除该文件，
	 * 如果storePath是目录路径则删除该目录下的所有文件
	 *
	 * @param storePath
	 */
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
		if (fileOrPath.exists()) {
			if (fileOrPath.isDirectory()) {
				File[] files = fileOrPath.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}
}
