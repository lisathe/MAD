function add()
{
    //Retrieve the entered form data
    var title= $('[name=title]').val();
    var status=$('[name=status]').find("option:selected").text();
    var rating= $('[name=rating]').val();
    var episode=$('[name=ep]').val();
    
    // Fetch the existing objects
	var objects = getObjects();
    
    var entry = new Array();
	// Push the new item into the existing list
	entry.push({
		title: title,
        status: status,
        rating: rating,
        episode: episode
	});
    
    var randomId = makeid();
    var temp = {};
    temp[randomId] = entry;
    
    objects.push(temp);
    
    // Store the new list
	saveObjects(objects);
	// Reload the page to show the new objects
    
    
	//window.location.reload();
    location.href='index.html';
}

function makeid()
{
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    for( var i=0; i < 5; i++ )
        text += possible.charAt(Math.floor(Math.random() * possible.length));

    return text;
}

function getObjects(){
	// See if objects is inside localStorage
	if (localStorage.getItem("serie")){
		// If yes, then load the objects
		objects = JSON.parse(localStorage.getItem("serie"));
	}else{
		// Make a new array of objects
		objects = new Array();

	}
	return objects;
}

function saveObjects(objects){
	// Save the list into localStorage
	localStorage.setItem("serie", JSON.stringify(objects));
}

// json
//id
//{
//	title: iets
//	lol: <3
//}

// array form
//[id: [title: iets, lol: <3]]


function showOnPage()
{
    //localStorage.setItem("serie", "");
    
    //Fetch the existing objects
    objects = getObjects();
    
    $('#items').find('li').remove();
    
    $.each(objects, function(index, item) {
        var randomId = Object.keys(item);
        var entry2 = item[randomId];
		element = '<li onclick=getNom("' + randomId + '")>Name: '+ entry2[0].title;
        element += ' <br/>Status: ' + entry2[0].status;
        element += ' <br/>Episode: ' +entry2[0].episode;
        element += ' <br/>Rating: ' +entry2[0].rating+'</li>';

		$('#items').append(element);
	});
  
   $('#items').listview();
   $('#items').listview("refresh");
}

function saveEdit()
{
    var idString = location.href.split("?")[1];
    var serieId = idString.split("=")[1];
    
    var title= $('[name=title]').val();
    var status=$('[name=status]').find("option:selected").text();
    var rating= $('[name=rating]').val();
    var episode=$('[name=ep]').val();
    var myObjects = getObjects();
    
    $.each(myObjects, function(index, item)
    {
        var randomId = Object.keys(item);
        if (randomId == serieId)
        {
            item[randomId][0].title = title;
            item[randomId][0].status = status;
            item[randomId][0].rating = rating;
            item[randomId][0].episode = episode;
        }
    });
    
    saveObjects(myObjects);
    
    location.href='index.html';
}

function getNom (nom)
{
    location.href = "editSerie.html?id=" + nom;
}

function fillInEditForm()
{
    var idString = location.href.split("?")[1];
    var serieId = idString.split("=")[1];
    
    /*
    var objects = getObjects();
    alert(JSON.stringify(objects));
    var serie = objects[1][serieId];
    
    $('[name=title]').val(serie[0].title);
    $('[name=status]').val(serie[0].status);
    $('[name=rating]').val(serie[0].rating);
    $('[name=ep]').val(serie[0].episode);
    */
    
    var objects = getObjects();
    $.each(objects, function(index, item) {
        var randomId = Object.keys(item);
        if (randomId == serieId)
        {
            var entry2 = item[randomId][0];
            $('[name=title]').val(entry2.title);
            $('[name=ep]').val(entry2.episode);
            $('[name=rating]').val(entry2.rating);
            //$('[name=status]').val(entry2.status);
            
            var dd = $('[name=status]');
            for (var i = 0; i < dd[0].length; i++) {
                //alert(dd.options[i].text + " == " + entry2.status);
                if (dd[0].options[i].text == entry2.status) {
                    dd[0].selectedIndex = i;
                    //alert("whoo!: " + dd.options[i].text);
                    //dd.options[i].prop("selected", 1);
                    break;
                }
            }
            
            dd.selectmenu("refresh", true);
        }
	});
}
    
    /*
    // Clear the list
	$('#items').find('li').remove(); 

	// Add every object to the objects list 
	$.each(objects, function(index, item){
        var entry2 = item[Object.keys(item)];
		element = 'Name: '+ entry2[0].title;
        element += ' <br/>Status: ' + entry2[0].status;
        element += ' <br/>Episode: ' +entry2[0].episode;
        element += ' <br/>Rating: ' +entry2[0].rating;  
        alert( entry2[0].title);

		$('#items').append(element);
	});
  
   $('#items').listview();
   $('#items').listview("refresh");
   */