package com.imooc.o2o.config.quartz;

import com.imooc.o2o.service.ProductSellDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by Unruly Wind on 2019/3/8/008.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Configuration
public class QuartzConfiguration {
	@Autowired
	private ProductSellDailyService productSellDailyService;
	@Autowired
	private MethodInvokingJobDetailFactoryBean jobDetailFactory;
	@Autowired
	private CronTriggerFactoryBean productSellDailyTriggerFactory;

	/**
	 * 创建jobDetailFactory并返回
	 *
	 * @return
	 */
	@Bean(name = "jobDetailFactory")
	public MethodInvokingJobDetailFactoryBean createJodDetail() {
		MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
		jobDetailFactoryBean.setName("product_sell_daily_job");
		jobDetailFactoryBean.setGroup("job_product_sell_daily_group");
		// 对于相同的JobDetail，当指定多个Trigger时, 很可能第一个job完成之前，第二个job就开始了。
		// 指定concurrent设为false，多个job不会并发运行，第二个job将不会在第一个job完成之前开始。
		jobDetailFactoryBean.setConcurrent(false);
		jobDetailFactoryBean.setTargetObject(productSellDailyService);
		jobDetailFactoryBean.setTargetMethod("dailyCalculate");
		return jobDetailFactoryBean;
	}

	/**
	 * 创建cronTriggerFactory并返回
	 *
	 * @return
	 */
	@Bean("productSellDailyTriggerFactory")
	public CronTriggerFactoryBean createPoductSellDailyTrigger() {
		CronTriggerFactoryBean triggerFactory = new CronTriggerFactoryBean();
		// 设置triggerFactory的名字
		triggerFactory.setName("product_sell_daily_trigger");
		// 设置triggerFactory的组名
		triggerFactory.setGroup("job_product_sell_daily_group");
		triggerFactory.setJobDetail(jobDetailFactory.getObject());
		triggerFactory.setCronExpression("0 0 0 * * ? *");
		return triggerFactory;
	}

	public SchedulerFactoryBean createSchedulerFactory() {
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setTriggers(productSellDailyTriggerFactory.getObject());
		return schedulerFactory;
	}
}
