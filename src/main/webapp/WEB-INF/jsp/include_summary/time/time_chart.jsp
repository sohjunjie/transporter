<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.js" type="text/javascript"></script>
<canvas id="myTimeChart" width="100%" height="100%"></canvas>
<script>
function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

var colors = [];
var i;
var lab = [];
for (i = 0; i < 24; i++) {
	lab.push(i+":00 to " + i+":59");
	colors.push(getRandomColor());
}

var countArr = [];

<c:forEach items="${hrAccidentCount}" var="count">
	countArr.push(${count});
</c:forEach>

var ctx = document.getElementById("myTimeChart");
ctx.width=window.innerWidth;
ctx.height=window.innerHeight;
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: lab,
        datasets: [{
            label: '# of Accidents at Each Timing',
            data: countArr,
            backgroundColor: colors
        }]
    },
	options: {
		legend: {
    		display: false
    	},
    	title: {
    		display: true,
    		text: 'Occurrence of accidents by time'
    	},
    	scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true,
                    userCallback: function(label, index, labels) {
                        // when the floored value is the same as the value we have a whole number
                        if (Math.floor(label) === label) {
                            return label;
                        }

                    },
                }
            }],
        }
	}
});
</script>