function followUser() {
    //Code For Unfollowing User
    if(document.getElementById("follow").textContent =='Follow User') {
        document.getElementById("follow").textContent ='Unfollow User';
        //SEND BACKEND TO SIGNIFY FOLLOWER IS NOW FOLLOWED

        alert('Following successful');
    } else {
        document.getElementById("follow").textContent ='Follow User';
        //SEND BACKEND TO SIGNIFY FOLLOWER IS NOW UNFOLLOWED
        alert('Unfollowing successful');
    }
}

function loadfollowedStatus() {
    //This is the function that loads the heart into the correct color, depending on if it was liked already or not
    //  if (post is not followed) {
        document.getElementById("follow").textContent ='Follow User';
    // }
    // else {
 // document.getElementById("follow").textContent ='Unfollow User';
    // }
    
    }