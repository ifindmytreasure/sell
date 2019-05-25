/**
 * Created by BlueMelancholy on 2019/2/2/002.
 */
$(function () {
    var awardId = getQueryString("awardId");
    // 获取商品信息的URL
    var awardUrl = '/o2o/fronted/listawarddetailpageinfo?awardId=' + awardId;
    $.getJSON(awardUrl, function (data) {
        if (data.success) {
            // 获取商品信息
            var award = data.award;
            // 商品缩略图
            $('#award-img').attr('src', getImgUrl() + award.awardImg);
            // 商品更新时间
            $('#award-time').text(
                new Date(award.lastEditTime).Format("yyyy-MM-dd"));
            if (award.point != undefined) {
                $('#award-point').text('兑换需要' + award.point + '积分');
            }
            var imgListHtml = '';
            // 商品名称
            $('#award-name').text(award.awardName);
            // 商品简介
            $('#award-desc').text(award.awardDesc);
            // 商品价格展示逻辑，主要判断原价现价是否为空 ，所有都为空则不显示价格栏目
            if (data.needQRCode) {
                // 若顾客已登录，则生成购买商品的二维码供商家扫描
                imgListHtml += '<div> <img src="/o2o/fronted/generateqrcode4awarddetail?awardId='
                    + award.awardId
                    + '" width="100%"/></div>';
            }
            $('#imgList').html(imgListHtml);
        }
    });
    // 点击后打开右侧栏
    $('#me').click(function () {
        $.openPanel('#panel-right-demo');
    });
    $.init();
});