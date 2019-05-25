$(function () {
    var productId = getQueryString("productId");
    // 获取商品信息的URL
    var productUrl = '/o2o/fronted/listproductdetailpageinfo?productId=' + productId;
    $.getJSON(productUrl,function (data) {
        if (data.success){
            var imgListHtml = '';
            if (data.needQRCode) {
                // 若顾客已登录，则生成购买商品的二维码供商家扫描
                imgListHtml += '<div> <img src="/o2o/fronted/generateqrcode4product?productId='
                    + data.product.productId
                    + '" width="100%"/></div>';
            }
            $('#imgList').html(imgListHtml);
        }
    });
    // 点击后打开右侧栏
    $('#me').click(function () {
        $.openPanel('#panel-right-demo');
    });

});