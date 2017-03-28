<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.js" type="text/javascript"></script>
<canvas id="myCauseChart" width="100%" height="100%"></canvas>
<script>

var lab = [];
<c:forEach items="${topAccidentCauses}" var="aCause">
	lab.push("${aCause.cause}");
</c:forEach>

var countArr = [];

<c:forEach items="${topCauseCount}" var="count">
	countArr.push(${count});
</c:forEach>

var ctx = document.getElementById("myCauseChart");
ctx.width=window.innerWidth;
ctx.height=window.innerHeight;
var myChart = new Chart(ctx, {
    type: 'pie',
    data: {
        labels: lab,
        datasets: [{
            label: '# of Causes',
            data: countArr,
            backgroundColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)'
            ]
        }]
    },
	options: {
    	legend: {
        	position: 'bottom'
        	//display: false
        },
    	title: {
    		display: true,
    		text: ''
    	}
    }
});
</script>