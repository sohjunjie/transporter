<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<h2>Spring MVC file upload example </h2>

<form method="POST" action="upload" enctype="multipart/form-data">


    Please select a file to upload : <input type="file" name="file" />
    <input type="submit" value="upload" />

</form>