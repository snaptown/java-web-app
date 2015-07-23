$(document).ready(function() {

$.ajax({
            url: "/snaptown/photos",
            method: 'GET',
            statusCode: {
                    204: function(){
                        console.log("no data");
                    },
                    200: function(res) {
                        var photos = JSON.parse(res);
                        var i = 0;
                        var $containerDiv = $("#photos-container");
                        var $currentRow = $("<div class='row'></div>");
                        photos.photos.forEach(function(photo) {
                            if(i % 3 == 0 && i >= 3) {
                                $containerDiv.append($currentRow);
                                $currentRow = $("<div class='row'></div>");
                            }
                            i+=1;
                            $divItem = $("<div class='col-md-4 portfolio-item'></div>");
                            $linkItem = $("<a></a>")
                                .attr("href", "page.html" );

                            $imgDiv = $("<div class='img-container'> </div>");
                            $linkItem
                                .append($imgDiv);

                            $img = $("<img class='img-responsive'></img>");
                            $img
                                .attr("src", "/snaptown/images/" + photo.imgPath);
                            $linkItem.append($img);
                            $upVote = $("<a class='vote positive-vote'></a>")
                                .append($("<i></i>")
                                        .append($("<span></span>")));

                            $downVote = $("<a class='vote negative-vote'></a>")
                                .append($("<i></i>")
                                        .append($("<span></span>")));

                            $divItem.append($linkItem);
                            $divItem.append($upVote);
                            $divItem.append($downVote);
                            $divItem
                                .append($("<p></p>")
                                        .html(photo.comment));
                            $currentRow.append($divItem);

                        });
                        $containerDiv.append($currentRow);
                }
            }
        });

});
