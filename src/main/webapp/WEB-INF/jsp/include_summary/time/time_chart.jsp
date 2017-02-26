<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.js" type="text/javascript"></script>
<canvas id="myChart" width="1000" height="600"></canvas>
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

var ctx = document.getElementById("myChart");
var myChart = new Chart(ctx, {
    type: 'pie',
    data: {
        labels: lab,
        datasets: [{
            label: '# of Accidents at Each Timing',
            data: countArr,
            backgroundColor: colors
        }]
    }
});
</script>