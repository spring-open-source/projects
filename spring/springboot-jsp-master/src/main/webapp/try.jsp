<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table.zaklad {
	border-collapse: collapse;
	margin: 5px;
}
table.zaklad th, table.zaklad td {
	border: solid 1px black;
	padding: 4px;
}
</style>
<script src="https://code.jquery.com/jquery-3.1.1.js" integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA=" crossorigin="anonymous"></script>
</head>
<body>
<ul id="dir-tree"></ul>

<form id="f1" action="${pageContext.request.contextPath}/hlava" enctype="multipart/form-data" method="post">
    <input name="dir" id="dir_input" type="file" webkitdirectory directory multiple/><br/>
    <label for="pripona">Filter file by file extension: </label>
    <select id="pripona">
		<option value="all">all</option>
		<option value="swf">swf</option>
        <option value="jpg">jpg</option>
    </select>
    <div id="dirinfo"></div>
	
    <input type="button" onclick="uploadFilteredFiles()" value="Upload"/>
</form>

<div id="fileInfo">
    <div id="fileName"></div>
    <div id="fileSize"></div>
    <div id="fileType"></div>
</div>
<div id="progressNumber"></div>
<pre id="serverResponse"></pre>

<script type="text/javascript">
    //handler for displaying filtered files
    function printFiles(e) {
        console.log('onchange called with e=' + e);
        var fileList = filterFiles(document.getElementById('dir_input').files);
        var dirinfo = '<table class="zaklad"><tr><th>file name</th><th>path</th><th>size<th>type</tr>';
        for (var i = 0, file; file = fileList[i]; i++) {
            var fileSize = 0;
            if (file.size > 1024 * 1024)
                fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
            else
                fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
            dirinfo += '<tr><td>' + file.name + '<td>' + file.webkitRelativePath + '<td>' + fileSize + '<td><input type="text" id="nm'+i+'" value="'+file.name+'"> ' + file.type + '</tr>';
        }
        dirinfo += '</table>';
        document.getElementById('dirinfo').innerHTML = dirinfo;
    }

    document.getElementById('dir_input').onchange = printFiles;
    document.getElementById('pripona').onchange = printFiles;

    //filter files by extension from all files selected by the user
    function filterFiles(fileList) {
        var filteredFiles = [];
        var pripSel = document.getElementById('pripona');
		var pripona = pripSel.options[pripSel.selectedIndex].value;
		console.log('pripona=' + pripona);
		if(pripona=='all')
			return fileList;
		
        for (var i = 0, file; file = fileList[i]; i++) {
            if (endsWithIgnoreCase(file.name, pripona))
                filteredFiles.push(file);
        }
        return filteredFiles;
    }

    //JavaSript lacks this method on Strings
    function endsWithIgnoreCase(str, suffix) {
        return str.toLowerCase().indexOf(suffix.toLowerCase(), str.length - suffix.length) !== -1;
    }

    //upload files
    function uploadFilteredFiles() {
        var xhr = new XMLHttpRequest();
        var fileList = filterFiles(document.getElementById('dir_input').files);
        var fd = new FormData();
        for (var i = 0, file; file = fileList[i]; i++) {
        	console.log( $('#nm'+i).val() );
			console.log(file);
			fd.append("fileNM", $('#nm'+i).val() );
            fd.append("fileData", file);
        }
        xhr.upload.addEventListener("progress", uploadProgress, false);
        xhr.addEventListener("load", uploadComplete, false);
        xhr.addEventListener("error", uploadFailed, false);
        xhr.addEventListener("abort", uploadCanceled, false);
        xhr.open("POST", "/upload");
        xhr.send(fd);
    }

    //callbacks for upload
    function uploadProgress(evt) {
        if (evt.lengthComputable) {
            var percentComplete = Math.round(evt.loaded * 100 / evt.total);
            document.getElementById('progressNumber').innerHTML = percentComplete.toString() + '%';
        }
        else {
            document.getElementById('progressNumber').innerHTML = 'unable to compute';
        }
    }

    function uploadComplete(evt) {
        /* This event is raised when the server send back a response */
        document.getElementById('serverResponse').innerHTML = evt.target.responseText;
    }

    function uploadFailed(evt) {
        alert("There was an error attempting to upload the file.");
    }

    function uploadCanceled(evt) {
        alert("The upload has been canceled by the user or the browser dropped the connection.");
    }
</script>
</body>
</html>