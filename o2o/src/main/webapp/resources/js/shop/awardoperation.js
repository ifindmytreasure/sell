$(function () {
    // 从URL里获取awardId参数的值
    var awardId = getQueryString('awardId');
    // 通过awardId获取奖品信息的URL
    var infoUrl = '/o2o/shopadmin/getawardbyid?awardId=' + awardId;
    // 更新奖品信息的URL
    var awardPostUrl = '/o2o/shopadmin/modifyaward';
    // 由于奖品添加和编辑使用的是同一个页面，
    // 该标识符用来标明本次是添加还是编辑操作
    var isEdit = false;
    if (awardId) {
        // 若有awardId则为编辑操作
        getInfo(awardId);
        isEdit = true;
    } else {
        awardPostUrl = '/o2o/shopadmin/addaward';
    }

    // 获取需要编辑的奖品信息，并赋值给表单
    function getInfo(id) {
        $.getJSON(infoUrl, function (data) {
            if (data.success) {
                // 从返回的JSON当中获取award对象的信息，并赋值给表单
                var award = data.award;
                $('#award-name').val(award.awardName);
                $('#priority').val(award.priority);
                $('#award-desc').val(award.awardDesc);
                $('#point').val(award.point);
            }
        });
    }

    $('#submit').click(function () {
        var award = {};
        award.awardName = $('#award-name').val();
        award.priority = $('#priority').val();
        award.awardDesc = $('#award-desc').val();
        award.point = $('#point').val();
        award.awardId = awardId ? awardId : '';
        var thumbnail = $('#small-img')[0].files[0];
        var formData = new FormData();
        formData.append("thumbnail", thumbnail);
        formData.append("awardStr", JSON.stringify(award));
        // 获取表单里输入的验证码
        var verifyCodeActual = $('#j_captcha').val();
        if (!verifyCodeActual) {
            $.toast('请输入验证码！');
            return;
        }
        formData.append("verifyCodeContent", verifyCodeActual);
        //将数据传递到后台处理相关操作
        $.ajax({
            url: awardPostUrl,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                if (data.success) {
                    $.toast('提交成功！');
                    $('#captcha_img').click();
                } else {
                    $.toast('提交失败！');
                    $('#captcha_img').click();
                }
            }
        });
    });
});