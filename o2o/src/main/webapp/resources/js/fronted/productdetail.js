/**
 * Created by BlueMelancholy on 2019/2/2/002.
 */
$(function () {
    var productId = getQueryString("productId");
    // 获取商品信息的URL
    var productUrl = '/o2o/fronted/listproductdetailpageinfo?productId=' + productId;
    $.getJSON(productUrl, function (data) {
        if (data.success) {
            // 获取商品信息
            var product = data.product;
            // 商品缩略图
            $('#product-img').attr('src', getImgUrl() + product.imgAddr);
            // 商品更新时间
            $('#product-time').text(
                new Date(product.lastEditTime).Format("yyyy-MM-dd"));
            if (product.point != undefined) {
                $('#product-point').text('购买可得' + product.point + '积分');
            }
            // 商品名称
            $('#product-name').text(product.productName);
            // 商品简介
            $('#product-desc').text(product.productDesc);
            // 商品价格展示逻辑，主要判断原价现价是否为空 ，所有都为空则不显示价格栏目
            if (product.normalPrice != undefined
                && product.promotionPrice != undefined) {
                // 如果现价和原价都不为空则都展示，并且给原价加个删除符号
                $('#price').show();
                $('#normalPrice').html(
                    '<del>' + '￥' + product.normalPrice + '</del>');
                $('#promotionPrice').text('￥' + product.promotionPrice);
            } else if (product.normalPrice != undefined
                && product.promotionPrice == undefined) {
                // 如果原价不为空而现价为空则只展示原价
                $('#price').show();
                $('#promotionPrice').text('￥' + product.normalPrice);
            } else if (product.normalPrice == undefined
                && product.promotionPrice != undefined) {
                // 如果现价不为空而原价为空则只展示现价
                $('#promotionPrice').text('￥' + product.promotionPrice);
            }
            var imgListHtml = '';
            // 遍历商品详情图列表，并生成批量img标签
            product.productImgList.map(function (item, index) {
                imgListHtml += '<div> <img src="' + getImgUrl() + item.imgAddr
                    + '" width="100%" /></div>';
            });
            // if (data.needQRCode) {
            //     // 若顾客已登录，则生成购买商品的二维码供商家扫描
            //     imgListHtml += '<div> <img src="/o2o/fronted/generateqrcode4product?productId='
            //         + product.productId
            //         + '" width="100%"/></div>';
            // }
            $('#imgList').html(imgListHtml);
        }
    });
    // 点击后打开右侧栏
    $('#me').click(function () {
        $.openPanel('#panel-right-demo');
    });
    $('#order').click(
        function () {
            window.location.href = '/o2o/fronted/addorderinfo?productId=' + productId;
        }
    );
    $.init();
});