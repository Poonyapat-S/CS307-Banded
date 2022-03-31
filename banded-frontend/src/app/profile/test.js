function openForm() {
    document.getElementById("myForm").style.display = "block";
  }

function closeForm() {
    document.getElementById("myForm").style.display = "none";
  }

function submitForm() {
    let text = document.getElementById("newText").value;
    document.getElementById("bio").innerText = text;
    closeForm();
  }

  function openFormDelete() {
    document.getElementById("myDeleteAcc").style.display = "block";
  }
  function closeFormDelete() {
    document.getElementById("myDeleteAcc").style.display = "none";
  }

  function submitDeletion(){
    /*Send back to timeline */
    
    closeFormDelete()
  }

  function followControl () {
    if(document.getElementById('followButton').textContent == 'Follow/Unfollow' || document.getElementById('followButton').textContent == 'Follow') {
      //Plug Back End Code to Tell backend the user is now FOLLOWED  
      document.getElementById('followButton').textContent = 'Unfollow';
      alert('Successfully Followed');
    } else {
      //Plug Back End Code to Tell backend the user is now UNFOLLOWED 
      document.getElementById('followButton').textContent = 'Follow';
      alert('Successfully Unfollowed');
    }
  }