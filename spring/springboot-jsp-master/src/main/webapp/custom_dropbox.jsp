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
<form action="#" enctype="multipart/form-data" method="post">
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
	var fileArray = new Array();

	function addObject( fFullName , fName , fExt , f , fSize , fType , fRelativePath , fStatus ){
		var fObject = {
			fileFullName : fFullName,
		    fileName : fName,
		    fileExt  : fExt,
		    file     : f,
		    fileSize : fSize,
		    fileType : fType,
		    fileRelativePath : fRelativePath,
		    fileStatus   : fStatus
		};
		fileArray.push( fObject );
	}

	function addInArray( fileList ){
		fileArray = new Array();
        
        for (var i = 0, file; file = fileList[i]; i++) {
        	var fFullName = file.name;
            var fName = file.name.substring( 0 , file.name.toLowerCase().lastIndexOf(".") );
			var fExt = file.name.substring( file.name.toLowerCase().lastIndexOf(".") , file.name.lenght );
			var fType = file.type;
			var fRelativePath = file.webkitRelativePath;
			var fStatus = "pendding";
			
			var fSize = 0;
            if (file.size > 1024 * 1024)
            	fSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
            else
            	fSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
            
            addObject( fFullName , fName , fExt , file , fSize , fType , fRelativePath , fStatus );
        }
        console.log('Total Object in fileArray ::: ' + fileArray.length);
	}

	function printTable( fileArrayList ){
		var dirinfo = '<table class="zaklad"><tr>'+
		'<th>File Name</th>'+
		'<th>Upload Name</th>'+
		'<th>Extension</th>'+
		'<th>Size</th>'+
		'<th>Type</th>'+
		'<th>Relative Path</th>'+
		'<th>Status</th>'+
		'</tr>';

		var i = 0;
		$.each( fileArrayList , function( index, item ) {
			dirinfo += '<tr>';
			dirinfo += '<td>'+item.fileFullName+'</td>';
			dirinfo += '<td><input type="text" id="nm'+i+'" value="'+ item.fileName +'" name="'+item.fileRelativePath+'"></td>';
			dirinfo += '<td>'+item.fileExt+'</td>';
			dirinfo += '<td>'+item.fileSize+'</td>';
			dirinfo += '<td>'+item.fileType+'</td>';
			dirinfo += '<td>'+item.fileRelativePath+'</td>';
			if( item.fileStatus == "pendding" ){
				dirinfo += '<td><input type="button" value="Upload" onclick=uploadSingleFile("'+item.fileRelativePath+'"); > '+item.fileStatus+'</td>';
				//uploadSingleFile("'+item.fileRelativePath+'");
			}else{
				dirinfo += '<td>'+item.fileStatus+'</td>';
			}
			dirinfo += '</tr>';
			i++;
		});

		dirinfo += '</table>';
        document.getElementById('dirinfo').innerHTML = dirinfo;
        
		console.log('Table Loaded with Size ::: ' + fileArrayList.length);
	}
	
    function loadData(e) {
        console.log('onchange called with e=' + e);
        var fileList = filterFiles(document.getElementById('dir_input').files);

        addInArray( fileList );
        
        printTable( fileArray );
    }
    function loadDataByType(e) {
        console.log('onchange called with e=' + e);
        var fileList = filterFiles(document.getElementById('dir_input').files);

        var filteredFiles = [];
        var pripSel = document.getElementById('pripona');
		var pripona = pripSel.options[pripSel.selectedIndex].value;
		console.log('pripona=' + pripona);
		if(pripona=='all'){
			printTable( fileArray );
			return;
		}
		
		$.each( fileArray , function( index , item ){
			if (endsWithIgnoreCase(item.fileFullName, pripona))
                filteredFiles.push(item);
		});
        printTable( filteredFiles );
    }
    document.getElementById('dir_input').onchange = loadData;
    document.getElementById('pripona').onchange = loadDataByType;

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

    function uploadSingleFile( path ){
        path = path.trim();
		
		var fObject = "";
		$.each( fileArray , function( index , item ){
			if( item.fileRelativePath.trim() == path){
				console.log('file found');
				fObject = item;
				
				//alert('fObject : '+fObject.fileFullName);
				//alert( $('input[name="'+item.fileRelativePath.trim()+'"]').val() );
				
				fObject.fileName = $('input[name="'+item.fileRelativePath.trim()+'"]').val();
			}
		});
        
		var fd = new FormData();
		fd.append( "fileNM" , fObject.fileName + fObject.fileExt );
		fd.append( "fileData" , fObject.file );
		
		var xhr = new XMLHttpRequest();
		xhr.upload.addEventListener( "progress" , uploadProgress , false );
		xhr.addEventListener( "load" , uploadComplete , false );
		xhr.addEventListener( "error" , uploadFailed , false );
		xhr.addEventListener( "abort" , uploadCanceled , false );
		xhr.open( "POST" , "/upload" );
		xhr.send( fd );
		
		fObject.fileStatus = "uploaded";
		$.each( fileArray , function( index , item ){
			if( item.fileRelativePath.trim() == path){
				console.log('file status found');
				item = fObject;
			}
		});
		console.log( fileArray );
		printTable( fileArray );
    }

    //upload files
    function uploadFilteredFiles() {
        var xhr = new XMLHttpRequest();
        var fileList = filterFiles(document.getElementById('dir_input').files);
        var fd = new FormData();
        for (var i = 0, file; file = fileList[i]; i++) {
            var ext = "";
			if( file.name.toLowerCase().lastIndexOf(".") > 0 ){
				ext = file.name.substring( file.name.toLowerCase().lastIndexOf(".") , file.name.lenght );
			}
        	
        	console.log( $('#nm'+i).val() + '===' +ext );
			console.log( file );
			
			fd.append( "fileNM" , $('#nm'+i).val() + ext );
            fd.append( "fileData" , file );
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
        } else {
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