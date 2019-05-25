/**
 * Created by BlueMelancholy on 2018/12/27/027.
 */
$(function () {
    // 从URL里获取shopId参数的值
    var shopId = getQueryString('shopId');
    // 用于店铺注册时候的店铺类别以及区域列表的初始化的URL
    var initUrl = "/o2o/shopadmin/getshopinitinfo";
    // 注册店铺的URL
    var registerShopUrl = "/o2o/shopadmin/registershop";
    // 编辑店铺前需要获取店铺信息，这里为获取当前店铺信息的URL
    var shopInfoUrl = "/o2o/shopadmin/getshopbyid?shopId=" + shopId;
    // 编辑店铺信息的URL
    var editShopUrl = "/o2o/shopadmin/modifyshop";
    // 由于店铺注册和编辑使用的是同一个页面，
    // 该标识符用来标明本次是添加还是编辑操作
    var isEdit = shopId ? true : false;
    // 判断是编辑操作还是注册操作
    if (!isEdit) {
        getShopInitInfo();
    } else {
        getShopInfo(shopId);
    }

    // 通过店铺Id获取店铺信息
    function getShopInfo(shopId) {
        $.getJSON(shopInfoUrl, function (data) {
            if (data.success) {
                var shop = data.shop;
                $('#shop-name').val(shop.shopName);
                $('#shop-addr').val(shop.shopAddr);
                $('#shop-phone').val(shop.phone);
                $('#shop-desc').val(shop.shopDesc);
                // 给店铺类别选定原先的店铺类别值
                var shopCategory = '<option data-id ="' + shop.shopCategory.shopCategoryId + '">'
                    + shop.shopCategory.shopCategoryName + '</option>';
                var tempAreaHtml = "";
                // 初始化区域列表
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">' + item.areaName + '</option>';
                    console.log(item.areaName);
                });
                $('#shop-category').html(shopCategory);
                $('#shop-category').attr('disabled', 'disabled');
                $('#shop-area').html(tempAreaHtml);
                // 给店铺选定原先的所属的区域
                $("#shop-area option[data-id='" + shop.area.areaId + "']").attr(
                    "selected", "selected");
            }
        });
    }

    // 取得所有二级店铺类别以及区域信息，并分别赋值进类别列表以及区域列表
    function getShopInitInfo() {
        $.getJSON(initUrl, function (data) {
            if (data.success) {
                var tempHtml = "";
                var tempAreaHtml = "";
                data.shopCategoryList.map(function (item, index) {
                    tempHtml += '<option data-id="' + item.shopCategoryId + '">' + item.shopCategoryName + '</option>';
                    console.log(item.shopCategoryName);
                    console.log(tempHtml);
                });
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">' + item.areaName + '</option>';
                    console.log(item.areaName);
                });
                $("#shop-category").html(tempHtml);
                $("#shop-area").html(tempAreaHtml);
            }
        });
    }

    // 提交按钮的事件响应，分别对店铺注册和编辑操作做不同响应
    $("#submit").click(function () {
        // 创建shop对象
        var shop = {};
        if (isEdit) {
            shop.shopId = shopId;
        }
        // 获取表单里的数据并填充进对应的店铺属性中
        shop.shopName = $("#shop-name").val();
        shop.shopAddr = $("#shop-addr").val();
        shop.phone = $("#shop-phone").val();
        shop.shopDesc = $("#shop-desc").val();
        // 选择选定好的店铺类别
        shop.shopCategory = {
            //.not not() 从匹配元素集合中删除元素。当前的类别被选中,并获取其id,即获取data-id的值
            shopCategoryId: $("#shop-category").find('option').not(function () {
                return !this.selected;
            }).data('id')
        };
        // 选择选定好的区域信息
        shop.area = {
            areaId: $("#shop-area").find('option').not(function () {
                return !this.selected;
            }).data('id')
        };
        // 获取上传的图片文件流
        var shopImg = $("#shop-img")[0].files[0];
        // 生成表单对象，用于接收参数并传递给后台
        var formData = new FormData();
        // 添加图片流进表单对象里
        formData.append('shopImg', shopImg);
        //JSON.stringify() 方法是将一个JavaScript值(对象或者数组)转换为一个 JSON字符串
        // 将shop json对象转成字符流保存至表单对象key为shopStr的的键值对里
        formData.append('shopStr', JSON.stringify(shop));
        var verifyCodeContent = $("#captcha").val();
        console.log(verifyCodeContent);
        if (!verifyCodeContent) {
            $.toast("验证码不能为空，请输入")
            return;
        }
        formData.append('verifyCodeContent', verifyCodeContent);
        $.ajax({
            url: (isEdit ? editShopUrl : registerShopUrl),
            type: 'POST',
            data: formData,
            contentType: false,
            cache: false,
            processData: false,
            success: function (data) {
                console.log(data.success)
                if (data.success) {
                    $.toast("提交成功");
                } else {
                    $.toast("提交失败," + data.errMsg);
                }
                $("#kaptcha_img").click();
            }
        });
    });
});
