$(function () {
    var url = '/o2o/fronted/getorderdetaillistbyorderid';
    $.getJSON(url,function (data) {
        if (data.success){
            var orderDetailList = data.orderDetailList;
            var html = '';
            orderDetailList.map(function (item,index) {
                html +=  '<div class="row row-order">'
                + '<div class="col-33" id="productinfo">' + item.productName
                + '</div><div class="col-20">' + item.productPrice
                + '</div><div class="col-20">' + item.productQuantity
                + '</div><div class="col-27"><a href="/o2o/fronted/orderdetailinfo?detailId='
                + item.detailId  +'" id="preview">' + '订单详情' + '</a>' + '</div></div>';
            });
            $('.order-wrap').html(html);
        }
    });
    // 点击后打开右侧栏
    $('#me').click(function () {
        $.openPanel('#panel-right-demo');
    });
});