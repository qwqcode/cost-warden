export function InitPieChart(chartDom, data) {
  var myChart = echarts.init(chartDom);

  var datas = [ data ];
  var option = {
    series: datas.map(function (data, idx) {
      var top = idx * 33.3;
      return {
        type: "pie",
        radius: [20, 60],
        top: top + "%",
        height: "33.33%",
        left: "center",
        width: 400,
        itemStyle: {
          borderColor: "#fff",
          borderWidth: 1,
        },
        label: {
          alignTo: "edge",
          formatter: "{name|{b}}\n{money|{c} 元}",
          minMargin: 5,
          edgeDistance: 10,
          lineHeight: 15,
          rich: {
            money: {
              fontSize: 10,
              color: "#999",
            },
          },
        },
        labelLine: {
          length: 15,
          length2: 0,
          maxSurfaceAngle: 80,
        },
        labelLayout: function (params) {
          const isLeft = params.labelRect.x < myChart.getWidth() / 2;
          const points = params.labelLinePoints;
          // Update the end point.
          points[2][0] = isLeft
            ? params.labelRect.x
            : params.labelRect.x + params.labelRect.width;
          return {
            labelLinePoints: points,
          };
        },
        data: data,
      };
    }),
  };

  option && myChart.setOption(option);
}

export function InitMonthlyChart(chartDom, data) {
  var myChart = echarts.init(chartDom);

  var option = {
    xAxis: {
      type: 'category',
      data: [
        '一月',
        '二月',
        '三月',
        '四月',
        '五月',
        '六月',
        '七月',
        '八月',
        '九月',
        '十月',
        '十一月',
        '十二月'
      ],
      name: '月份'
    },
    yAxis: {
      type: 'value',
      name: '总消费 (元)'
    },
    series: [
      {
        data: data.map((o) => Number(o.value)),
        type: 'bar'
      }
    ]
  };
  
  option && myChart.setOption(option);
}
