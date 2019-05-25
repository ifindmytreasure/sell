$(function () {
    var productName = '';
    getList();
    getProductSellDailyList();

    function getList() {
        // 获取用户购买信息的URL
        var listUrl = '/o2o/shopadmin/listuserproductmapsbyshop?pageIndex=1&pageSize=9999&productName='
            + productName;
        // 访问后台，获取该店铺的购买信息列表
        $.getJSON(listUrl, function (data) {
            if (data.success) {
                var userProductMapList = data.userProductMapList;
                var tempHtml = '';
                // 遍历购买信息列表，拼接出列信息
                userProductMapList.map(function (item, index) {
                    tempHtml += '' + '<div class="row row-productbuycheck">'
                        + '<div class="col-10">' + item.product.productName
                        + '</div>'
                        + '<div class="col-40 productbuycheck-time">'
                        + new Date(item.createTime).Format("yyyy-MM-dd hh:mm:ss")
                        + '</div>' + '<div class="col-20">'
                        + item.user.name + '</div>'
                        + '<div class="col-10">' + item.point + '</div>'
                        + '<div class="col-20">' + item.operator.name
                        + '</div>' + '</div>';
                });
                $('.productbuycheck-wrap').html(tempHtml);
            }
        });
    }

    $('#search').on('change', function (e) {
        // 当在搜索框里输入信息的时候
        // 依据输入的商品名模糊查询该商品的购买记录
        productName = e.target.value;
        // 清空商品购买记录列表
        $('.productbuycheck-wrap').empty();
        getList();
    });

    /**
     * 获取7天的销量
     */
    function getProductSellDailyList() {
        var listProductSellDailyUrl = '/o2o/shopadmin/listproductselldailyinfobyshop';
        $.getJSON(listProductSellDailyUrl, function (data) {
            if (data.success) {
                var mychart = echarts.init(document.getElementById('chart'));
                var option = generateStaticEchartPart();
                option.legend.data = data.legendData;
                option.xAxis = data.xAxis;
                option.series = data.series;
                mychart.setOption(option);
            }
        })
    }

    function generateStaticEchartPart() {
        var option = {
            title: {
                left: 'center',
                text: '商品每日销量柱状图',
                textStyle: {
                    //文字颜色
                    color: '#ccc',
                    //字体风格,'normal','italic','oblique'
                    fontStyle: 'normal',
                    //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
                    fontWeight: 'bold',
                    //字体系列
                    fontFamily: 'sans-serif',
                    //字体大小
                    fontSize: 18
                }
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {},
            grid: {
                left: '3%',
                bottom: '4%',
                right: '3%',
                containLabel: true
            },
            xAxis: [{},
            ],
            yAxis: [{type: 'value'}],
        };
        return option;
    }

    // var mychart = echarts.init(document.getElementById('chart'));
    // var option = {
    //     tooltip : {
    //         trigger : 'axis',
    //         axisPointer : { //坐标轴指示器
    //             type : 'shadow' //鼠标移动带轴的时候显示阴影
    //         }
    //     },
    //     //每张图表最多有一个图例
    //     legend: {
    //     //图例内容数组，数组通常为{string}，每一项代表一个系列的name
    //         data : ['茉香奶茶','绿茶拿铁','冰雪奇缘']
    //     },
    //     //直角坐标系中的网格
    //     grid : {
    //         left : '3%',
    //         bottom : '4%',
    //         right : '3%',
    //         containLabel : true
    //     },
    //     //直角坐标系中的横轴数组，数组中的每一项代表一条横轴坐标轴
    //     xAxis: [{
    //         //类目型：需要指定类目列表，坐标轴内有且仅有这些指定类目坐标
    //         type : 'category',
    //         data : ['周一','周二','周三','周四','周五','周六','周日']
    //     }],
    //     yAxis : [{
    //         type : 'value'
    //     }],
    //     series : [{
    //         name : '茉香奶茶',
    //         type : 'bar',
    //         data : [120,232,102,78,46,39,90]
    //     },{
    //         name : '绿茶拿铁',
    //         type : 'bar',
    //         data : [60,22,78,76,46,39,100]
    //     },{
    //         name : '冰雪奇缘',
    //         type : 'bar',
    //         data : [54,88,91,35,46,110,120]
    //     }]
    // };
    // mychart.setOption(option);
});