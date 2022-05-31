export function InitPieChart(chartDom, data) {
  console.log(chartDom, data)
  var myChart = echarts.init(chartDom);
  var option;

  var datas = [ data ];
  option = {
    title: {
      text: "开支占比",
      left: "center",
      textStyle: {
        color: "#999",
        fontWeight: "normal",
        fontSize: 14,
      },
    },
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
