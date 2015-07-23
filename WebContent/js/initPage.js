function initialize() {
    var mapCanvas = document.getElementById('map-canvas');
    var mapOptions = {
      center: new google.maps.LatLng(geolocation.lat,geolocation.lng),
      zoom: 17,
      mapTypeId: google.maps.MapTypeId.HYBRID
    };
    var map = new google.maps.Map(mapCanvas, mapOptions);
  }

function getURLParameter(name) {
  return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||null
}

$(document).ready(function() {

$.ajax({
            url: "/snaptown/photo",
            method: 'GET',
            data: {photoId: getURLParameter("photoId")},
            statusCode: {
                    500: function(){
                        console.log("oops");
                    },
                    200: function(res) {
                        var photoData = JSON.parse(res);
                        var $containerDiv = $("#photoContainer");
                        var photo = photoData.photo;
                        geolocation.lat = photo.latitude;
                        geolocation.lng = photo.longitude;
                        
                        $imgElem = $("<img class='img-responsive image-in-page'></img>")
                            .attr("src", "/snaptown/images/" + photo.imgPath);
                        $containerDiv.append($imgElem);
                        $containerDiv.append($("<p></p>").html(photo.comment));
                        $infoDiv = $("<div class='info'></div>");
                        $upVote = $("<a class='vote positive-vote'></a>")
                            .append($("<i></i>")
                                    .append($("<span></span>")));

                        $downVote = $("<a class='vote negative-vote'></a>")
                            .append($("<i></i>")
                                    .append($("<span></span>")));
                        $infoDiv.append($upVote);
                        $infoDiv.append($downVote);
                        $infoDiv.append($("<p id='address'></p>").html("address"));
                        $createdInfoDiv=$("<div class='created-info'></div>");
                        $createUserInfo = $("<p></p>")
                            .html("Created by ")
                            .append($("<span class='bold'></span>")
                                .html(photoData.userCreator));
                        $createDateInfo = $("<p></p>")
                            .html("date: ")
                            .append($("<span class='bold'></span>")
                                .html(photoData.date));
                        $createdInfoDiv.append($createUserInfo);
                        $createdInfoDiv.append($createDateInfo);
                        $infoDiv.append($createdInfoDiv);
                        $containerDiv.append($infoDiv);

                        $containerDiv
                            .append($("<div id='map-canvas'></div>"));
                }
            }
        });
 google.maps.event.addDomListener(window, 'load', initialize);
});

