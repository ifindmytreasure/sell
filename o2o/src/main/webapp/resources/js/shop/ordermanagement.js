$(function () {
    var shopId = getQueryString('shopId');
    url = '/o2o/fronted/getorderdetaillistbycondition?shopId=' + shopId;
    $.getJSON(url,function (data) {
        if (data.success){
            var orderDetailList = data.orderDetailList;
            var html = '';
            orderDetailList.map(function (item,index) {
                html +=  '<div class="row row-order">'
                + '<div class="col-33" id="productinfo">' + item.productName
                + '</div><div class="col-20">' + item.productPrice
                + '</div><div class="col-20">' + item.productQuantity
                + '</div><div class="col-27"><a href="/o2o/shopadmin/orderdetailmanagement?detailId='
                + item.detailId  +'" id="preview">' + '订单详情' + '</a>' + '</div></div>';
            });
            $('.order-wrap').html(html);
        }
    });
});