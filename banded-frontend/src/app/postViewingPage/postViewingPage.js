function toggleLike() {
    if(document.getElementById("heart").style.backgroundColor == "red") {
    document.getElementById("heart").style.backgroundColor = "grey";
    document.getElementById("heart-a").style.backgroundColor = "grey";
    document.getElementById("heart-b").style.backgroundColor = "grey";
    //BACKEND CODE TO CHANGE POST TO UNLIKED
    alert('Unliked Post!');
} else {
    document.getElementById("heart").style.backgroundColor = "red";
    document.getElementById("heart-a").style.backgroundColor = "red";
    document.getElementById("heart-b").style.backgroundColor = "red";
    //BACKEND CODE TO CHANGE POST TO LIKED
    alert('Liked Post!');
}}

function toggleSave()  {
    if(document.getElementById("savePost").textContent == 'Save Post') {
        document.getElementById("savePost").textContent = 'Unsave Post';
        //INCLUDE BACKEND CODE TO MAKE POST SAVED
    } else {
        document.getElementById("savePost").textContent = 'Save Post';
        //INCLUDE BACKEND CODE TO MAKE POST UNSAVED
    }
}

function loadLikedStatus() {
//This is the function that loads the heart into the correct color, depending on if it was liked already or not
//  if (post is not liked) {
    document.getElementById("heart").style.backgroundColor = "grey";
    document.getElementById("heart-a").style.backgroundColor = "grey";
    document.getElementById("heart-b").style.backgroundColor = "grey";
// }
// else {
 //   document.getElementById("heart").style.backgroundColor = "red";
//    document.getElementById("heart-a").style.backgroundColor = "red";
//    document.getElementById("heart-b").style.backgroundColor = "red";
// }


//This is the part that loads in whether it was saved or not already
//if (post was NOT already saved) {
    document.getElementById("savePost").textContent = 'Save Post';
//} else {
    // document.getElementById("savePost").textContent = 'Unsave Post'
 
//}

//if(post User is NOT already followed) {
    document.getElementById("followThisUser").textContent = 'Follow This User';
    // } else {
        // document.getElementById("followThisUser").textContent = 'Unfollow This User'
}

function openForm() {
    document.getElementById("myForm").style.display = "block";
  }

function closeForm() {
    document.getElementById("myForm").style.display = "none";
  }

function submitForm() {
    let text = document.getElementById("newText").value;
    
    //HERE IS WHERE YOU SHOULD SEND BACKEND THE NEW COMMMENT(text)

    closeForm();
  }

function followUser() {
    //BACKEND CODE TO FOLLOW USER

    if(document.getElementById("followThisUser").textContent == 'Follow This User') {
        document.getElementById("followThisUser").textContent = 'Unfollow This User'
    } else {
        document.getElementById("followThisUser").textContent = 'Follow This User'
    }
    
}

  


