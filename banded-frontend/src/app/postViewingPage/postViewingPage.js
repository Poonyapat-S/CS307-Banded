function toggleLike() {
    if(document.getElementById("heart").style.backgroundColor == "red") {
    document.getElementById("heart").style.backgroundColor = "grey";
    document.getElementById("heart-a").style.backgroundColor = "grey";
    document.getElementById("heart-b").style.backgroundColor = "grey";
    window.dispatchEvent(new CustomEvent('unlikePost'));
    //alert('Unliked Post!'); <-- calling this when successful in postViewingPage.component.ts
} else {
    document.getElementById("heart").style.backgroundColor = "red";
    document.getElementById("heart-a").style.backgroundColor = "red";
    document.getElementById("heart-b").style.backgroundColor = "red";
    window.dispatchEvent(new CustomEvent('likePost'));
    //alert('Liked Post!'); <-- calling this when successful in postViewingPage.component.ts
}}

function toggleSave()  {
    if(document.getElementById("savePost").textContent == 'Save Post') {
        document.getElementById("savePost").textContent = 'Unsave Post';
        window.dispatchEvent(new CustomEvent('savePost'));
    } else {
        document.getElementById("savePost").textContent = 'Save Post';
        window.dispatchEvent(new CustomEvent('unsavePost'));
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
    //document.getElementById("followThisUser").textContent = 'Follow This User';
    // } else {
        document.getElementById("followThisUser").textContent = 'Unfollow This User'
  window.dispatchEvent(new CustomEvent('checkInteractionStatus'));
}

function loadRedHeart() {
  document.getElementById("heart").style.backgroundColor = "red";
  document.getElementById("heart-a").style.backgroundColor = "red";
  document.getElementById("heart-b").style.backgroundColor = "red";
}

function loadGreyHeart() {
  document.getElementById("heart").style.backgroundColor = "grey";
  document.getElementById("heart-a").style.backgroundColor = "grey";
  document.getElementById("heart-b").style.backgroundColor = "grey";
}

function loadSaveButton() {
  document.getElementById("savePost").textContent = 'Save Post';
}

function loadUnsaveButton() {
  document.getElementById("savePost").textContent = 'Unsave Post'
}

function openForm() {
    document.getElementById("myForm").style.display = "block";
  }

function closeForm() {
    document.getElementById("myForm").style.display = "none";
  }

function submitForm() {
    let text = document.getElementById("newText").value;
    var event = new CustomEvent('reply', {
      detail: {
        postText: text,
      }
    })
    window.dispatchEvent(event);

    //HERE IS WHERE YOU SHOULD SEND BACKEND THE NEW COMMENT(text)

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




