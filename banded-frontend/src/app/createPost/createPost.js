
function makeAnon() {
  let title = document.getElementById("EnterPostTitle").value;
  let caption = document.getElementById("EnterPostCaption").value;
  let topic = document.getElementById("EnterPostTopic").value;

  var str = document.getElementById("EnterPostTopic").value;

  if(title == "") {
    alert('Post title is blank!')
  } else if (caption == "") {
    alert('Post caption is blank!')
  } else if (topic == "") {
    alert('Post Topic is blank!')
  } else if (containsSpecialChars(str)) {
    alert('Special Characters Not Allowed in Topic Field')
  } else {
    //document.getElementById("aButton").style.color = "blue";
    //THIS IS THE PLACE TO SEND BACKEND INFORMATION FOR AN ANONYMOUS POST
    // -- post title
    // -- post caption
    // -- topic
    // -- anonymous bit
    var event = new CustomEvent('onPost', {
      detail: {
        postTitle: title,
        postText: caption,
        topicName: topic,
        isAnon: true
      }
    })
    window.dispatchEvent(event);
    alert('Posted Successfully!')
    //------------------------------------------------------------------
    //The Below Code is to reset the page
    //^This is set up just quote-on-quote "refreshing" the page by filling each text field with blanks. Can be changed if need be.
    document.getElementById("EnterPostTitle").value = null;
    document.getElementById("EnterPostCaption").value = null;
    topic = document.getElementById("EnterPostTopic").value = null;
    document.getElementById("capRem").textContent = "100";
    document.getElementById("titleRem").textContent = "100";
    document.getElementById("topicRem").textContent = "10";
  }

}

//no special characters or spaces in topic

  function makePublic() {
    console.log("Public")
    let title = document.getElementById("EnterPostTitle").value;
    let caption = document.getElementById("EnterPostCaption").value;
    let topic = document.getElementById("EnterPostTopic").value;


    var str = document.getElementById("EnterPostTopic").value;

    if(title == "") {
      alert('Post title is blank!')
    } else if (caption == "") {
      alert('Post caption is blank!')
    } else if (topic == "") {
      alert('Post Topic is blank!')
    } else if (containsSpecialChars(str)) {
      alert('Special Characters Not Allowed in Topic Field')
    } else {
      //document.getElementById("aButton").style.color = "blue";
      //THIS IS THE PLACE TO SEND BACKEND INFORMATION FOR A PUBLIC POST
      // -- post title
      // -- post caption
      // -- topic
      // -- anonymous bit
      var event = new CustomEvent('onPost', {
        detail: {
          postTitle: title,
          postText: caption,
          topicName: topic,
          isAnon: false
        }
      })
      window.dispatchEvent(event);
      alert('Posted Successfully!')
      //------------------------------------------------------------------
      //The Below Code is to reset the page
      //^This is set up just quote-on-quote "refreshing" the page by filling each text field with blanks. Can be changed if need be.
      document.getElementById("EnterPostTitle").value = null;
      document.getElementById("EnterPostCaption").value = null;
      topic = document.getElementById("EnterPostTopic").value = null;
      document.getElementById("capRem").textContent = "100";
      document.getElementById("titleRem").textContent = "100";
      document.getElementById("topicRem").textContent = "10";
    } }

    function countCapChars(){
     const l = document.getElementById("EnterPostCaption");
     const remChars =  255 - l.value.length;

     let lbl = document.getElementById("capRem");
     lbl.innerText = remChars;
  }


  function countTitleChars(){
    const a = document.getElementById("EnterPostTitle");
    const remChars1 =  255 - a.value.length;

    let lbl1 = document.getElementById("titleRem");
    lbl1.innerText = remChars1;
 }


 function containsSpecialChars(str) {


  let specialChars = `\`!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~`;

 // let x = specialChars.charAt(3);
  //let y = str.charAt(2);
 // alert(x + " " + y);
  for(let i = 0; i < specialChars.length; i++) {

    let c = specialChars.charAt(i);
    for(let j = 0; j < str.length; j++) {
      let cs = str.charAt(j);
      if(c == cs) {
        return true;
      }
      if (cs == ' ') {
        return true;
      }
    }
  }

}

 function countTopicChars(){
  const a1 = document.getElementById("EnterPostTopic");

  const remChars11 =  45 - a1.value.length;

  let lbl11 = document.getElementById("topicRem");
  lbl11.innerText = remChars11;
}


