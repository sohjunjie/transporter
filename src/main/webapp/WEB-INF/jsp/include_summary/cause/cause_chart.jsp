<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.js" type="text/javascript"></script>
<canvas id="myCauseChart" width="600" height="600"></canvas>
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
var lab = [];
<c:forEach items="${accidentCauses}" var="aCause">
	lab.push("${aCause.cause}");
	colors.push(getRandomColor());
</c:forEach>

var countArr = [];

<c:forEach items="${causeCount}" var="count">
	countArr.push(${count});
</c:forEach>

var ctx = document.getElementById("myCauseChart");
var myChart = new Chart(ctx, {
    type: 'pie',
    data: {
        labels: lab,
        datasets: [{
            label: '# of Causes',
            data: countArr,
            backgroundColor: colors
        }]
    },
	options: {
    	legend: {
        	position: 'bottom'
        }
    }
});
</script>