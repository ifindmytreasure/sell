$(function () {
    var productId = getQueryString("productId");
    var addOrderUrl = '/o2o/fronted/addorder?product=' + productId;
    var productUrl = '/o2o/fronted/listproductdetailpageinfo?productId=' + productId;
    $.getJSON(productUrl,function (data) {
        if (data.success){
            // 获取商品信息
            var product = data.product;
            // 商品名称
            $('#product-name').val(product.productName);
            if (product.normalPrice != undefined && product.promotionPrice != undefined){
                $('#product-price').val('￥' +product.promotionPrice);
            }else if (product.normalPrice != undefined && product.promotionPrice == undefined){
                $('#product-price').val('￥' +product.normalPrice);
            }
        }
    });
    $('#product-quantity').change(function () {
        var productPriceStr = $('#product-price').val();
        var productPrice = productPriceStr.substring(1,productPriceStr.length);
        var productQuantity = $('#product-quantity').val();
        var priceAmount = parseInt(productPrice)*parseInt(productQuantity);
        $('#price-amount').val('￥' + priceAmount);
        console.log($('#price-amount').val());
    });
    $('#back').click(function () {
       window.location.href = '/o2o/fronted/productdetail?productId=' + productId;
    });
    $('#submit').click(function () {
        var orderMaster = {};
        var orderDetail = {};
        orderMaster.buyerName = $('#user-name').val();
        orderMaster.buyerPhone = $('#user-phone').val();
        orderMaster.buyerAddress = $('#user-address').val();
        orderDetail.productId = productId;
        orderDetail.productName = $('#product-name').val();
        orderDetail.productPrice = $('#product-price').val();
        orderDetail.productQuantity = $('#product-quantity').val();
        var formData = new FormData();
        formData.append('orderMasterStr',JSON.stringify(orderMaster));
        formData.append('orderDetailStr',JSON.stringify(orderDetail));
        $.ajax({
            url: addOrderUrl,
            type: 'POST',
            data: formData,
            contentType: false,
            cache: false,
            processData: false,
            success:function (data) {
                if (data.success){
                    $.toast("提交成功");
                    window.location.href = '/o2o/fronted/orderinfo'
                } else {
                    $.toast("提交失败," + data.errMsg);
                }
            }
        });
    });
    // 点击后打开右侧栏
    $('#me').click(function () {
        $.openPanel('#panel-right-demo');
    });
});