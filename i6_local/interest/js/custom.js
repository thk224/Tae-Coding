$(document).ready( function(){

  //close lightbox

  $("#lightbox_close_btn").click(function(){
      console.log("Closing lightbox...");

      $("#lightbox").fadeOut();
  });
  // Open the lightbox

      $(".thumbImg").click(function(){
          console.log("Opening lightbox...");
          $("#lightbox").css("display", "flex");

          $("#lightbox_mainArea").html("<h2>For more information, please click the link in the nav bar</h2>");
      });

//Open main paragraph and close sub paragraph by clicking the main image

      $(".mainImg").click(function(){
          console.log("Opening mainPar...");
          $("#mainPar").css("display", "flex");
          console.log("Closing subPar...");
          $("#subPar").css("display", "none");
      });

      $("#mainPar_changeStyle_btn1").click(function(){
          console.log("changing style...");

          $("#mainPar").css("color", "white");
          $("#mainPar").css("background", "black");
          $("#mainPar_changeStyle_btn1").css("display", "none");
          $("#mainPar_changeStyle_btn2").css("display", "flex");

      });

      $("#mainPar_changeStyle_btn2").click(function(){
          console.log("changing style...");

          $("#mainPar").css("color", "black");
          $("#mainPar").css("background", "ivory");
          $("#mainPar_changeStyle_btn2").css("display", "none");
          $("#mainPar_changeStyle_btn1").css("display", "flex");

      });


} );
