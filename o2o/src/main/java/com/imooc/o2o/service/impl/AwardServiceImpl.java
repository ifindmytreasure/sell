package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.AwardDao;
import com.imooc.o2o.dto.AwardExecution;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.entity.Award;
import com.imooc.o2o.enums.AwardStateEnum;
import com.imooc.o2o.exceptions.AwardOperateException;
import com.imooc.o2o.service.AwardService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PageCalculator;
import com.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Unruly Wind on 2019/3/11/011.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Service
public class AwardServiceImpl implements AwardService {
	@Autowired
	private AwardDao awardDao;

	/**
	 * 根据传入的条件分页返回奖品列表，并返回该查询条件下的总数
	 *
	 * @param awardCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@Override
	public AwardExecution getAwardList(Award awardCondition, int pageIndex, int pageSize) {
		if (awardCondition != null && pageIndex > -1 && pageSize > 0) {
			int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
			List<Award> awardList = awardDao.queryAwardList(awardCondition, rowIndex, pageSize);
			int count = awardDao.queryAwardCount(awardCondition);
			AwardExecution awardExecution = new AwardExecution();
			awardExecution.setAwardList(awardList);
			awardExecution.setCount(count);
			return awardExecution;
		} else {
			return null;
		}
	}


	/**
	 * 根据awardId查询奖品信息
	 *
	 * @param awardId
	 * @return
	 */
	@Override
	public Award getAwardById(long awardId) {
		return awardDao.queryAwardByAwardId(awardId);
	}

	/**
	 * 添加奖品信息，并添加奖品图片
	 *
	 * @param award
	 * @param thumbnail
	 * @return
	 */
	@Override
	@Transactional
	public AwardExecution addAward(Award award, ImageHolder thumbnail) {
		if (award != null && award.getShopId() != null) {
			award.setCreateTime(new Date());
			award.setLastEditTime(new Date());
			// award默认可用，即出现在前端展示系统中
			award.setEnableStatus(1);
			if (thumbnail != null) {
				addThumbnail(award, thumbnail);
			}
			try {
				int effectedNum = awardDao.insertAward(award);
				if (effectedNum <= 0) {
					throw new AwardOperateException("创建奖品失败");
				}
			} catch (Exception e) {
				throw new AwardOperateException("创建商品失败:" + e.toString());
			}
			return new AwardExecution(AwardStateEnum.SUCCESS, award);
		} else {
			return new AwardExecution(AwardStateEnum.EMPTY);
		}
	}

	/**
	 * 根据传入的奖品实例修改对应的奖品信息， 若传入图片则替换掉原先的图片
	 *
	 * @param award
	 * @param thumbnail
	 * @return
	 */
	@Override
	@Transactional
	public AwardExecution modifyAward(Award award, ImageHolder thumbnail) {
		if (award != null && award.getAwardId() != null) {
			award.setLastEditTime(new Date());
			if (thumbnail != null) {
				Award tempAward = awardDao.queryAwardByAwardId(award.getAwardId());
				if (thumbnail.getImage() != null) {
					ImageUtil.deleteFileOrPath(tempAward.getAwardImg());
				}
				addThumbnail(award, thumbnail);
			}
			try {
				int effectedNum = awardDao.updateAward(award);
				if (effectedNum <= 0) {
					throw new AwardOperateException("更新商品信息失败");
				}
				return new AwardExecution(AwardStateEnum.SUCCESS, award);
			} catch (Exception e) {
				throw new AwardOperateException("更新商品信息失败:" + e.toString());
			}
		} else {
			return new AwardExecution(AwardStateEnum.EMPTY);
		}
	}

	private void addThumbnail(Award award, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImagePath(award.getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		award.setAwardImg(thumbnailAddr);
	}
}
