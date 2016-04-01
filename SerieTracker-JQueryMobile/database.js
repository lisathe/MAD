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
    
    //Push array of new serie info into total series list
    objects.push(temp);
    
    // Store the new list
	saveObjects(objects);
	
    // Go back to the index page
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


function showOnPage()
{   
    //Fetch the existing objects
    objects = getObjects();
    
    $('#items').find('li').remove();
    
    $.each(objects, function(index, item) {
        var randomId = Object.keys(item);
        var entry = item[randomId];
		element = '<li onclick=getLink("' + randomId + '")>Name: '+ entry[0].title;
        element += ' <br/>Status: ' + entry[0].status;
        element += ' <br/>Episode: ' +entry[0].episode;
        element += ' <br/>Rating: ' +entry[0].rating+'</li>';

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

function getLink (link)
{
    location.href = "editSerie.html?id=" + link;
}

function fillInEditForm()
{
   //Get serie id from link
    var serieId = location.href.split("=")[1];
    
    var objects = getObjects();
    $.each(objects, function(index, item) {
        var randomId = Object.keys(item);
        if (randomId == serieId)
        {
            var entry = item[randomId][0];
            $('[name=title]').val(entry.title);
            $('[name=ep]').val(entry.episode);
            $('[name=rating]').val(entry.rating);
            
            var dd = $('[name=status]');
            for (var i = 0; i < dd[0].length; i++) {
                if (dd[0].options[i].text == entry.status) {
                    dd[0].selectedIndex = i;
                    break;
                }
            }
            dd.selectmenu("refresh", true);
        }
	});
}
    