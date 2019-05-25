$(function () {
    window.onload=function () {
        var detailId = getQueryString('detailId');
        url = '/o2o/fronted/getorderdetaillistbycondition?detailId=' + detailId;
        $.getJSON(url,function (data) {
            if (data.success){
                var productId = '';
                var orderDetailList = data.orderDetailList;
                orderDetailList.map(function (item,index) {
                    $('#order-number').val(item.orderMaster.orderId);
                    $('#create-time').val(new Date(item.createTime).Format("yyyy-MM-dd hh:mm:ss"));
                    $('#order-amount').val(item.orderMaster.orderAmount);
                    $('#shop-name').val(item.shop.shopName);
                    $('#buyer-name').val(item.orderMaster.buyerName);
                    $('#buyer-address').val(item.orderMaster.buyerAddress);
                    $('#buyer-phone').val(item.orderMaster.buyerPhone);
                    if (item.orderMaster.payStatus == 0){
                        $('#pay-status').val('等待支付');
                        productId = item.productId;
                    } else if (item.orderMaster.payStatus == 1){
                        $('#pay-status').val('支付成功');
                    }
                    if (item.orderMaster.orderStatus == 0){
                        $('#order-status').val('新订单');
                    } else if (item.orderMaster.orderStatus == 1){
                        $('#order-status').val('订单完成');
                    }else if (item.orderMaster.orderStatus == 2){
                        $('#order-status').val('订单已取消');
                    }
                });
            }else {
                $.toast(data.errMsg);
            }
        });
    }
});