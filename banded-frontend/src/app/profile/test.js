function openForm() {
    document.getElementById("myForm").style.display = "block";

    document.getElementById("infoForm").style.display = "none";
    document.getElementById("myDeleteAcc").style.display = "none";
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

    document.getElementById("myForm").style.display = "none";
    document.getElementById("infoForm").style.display = "none";
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
      window.dispatchEvent(new CustomEvent('follow'));
      document.getElementById('followButton').textContent = 'Unfollow';
      //alert('Successfully Followed');
    } else {
      window.dispatchEvent(new CustomEvent('unfollow'));
      document.getElementById('followButton').textContent = 'Follow';
      //alert('Successfully Unfollowed');
    }
  }


  //Below is the code for changing Profile Info for Sprint 3
  function openInfoForm() {
    document.getElementById("infoForm").style.display = "block";

    document.getElementById("myForm").style.display = "none";
    document.getElementById("myDeleteAcc").style.display = "none";
  }

  function closeInfo() {
    document.getElementById("infoForm").style.display = "none";
  }

  function submitNewEmail() {
    let oldEmail = document.getElementById("oldE").value;
    let newEmail = document.getElementById("newE").value;

    //Insert some code here to validify old email is correct and new email is accepted


    document.getElementById("oldE").value = null;
    document.getElementById("newE").value = null;

    //alert success or failure
    alert("Email Succesfully Changed!");
  }

  function submitNewPassword() {


    let oldPassword = document.getElementById("oldP").value;
    let newPassword = document.getElementById("newP").value;
    let newPassword2 = document.getElementById("newP2").value;

    if (oldPassword == "" || newPassword == "" || newPassword2 == "") {
      alert('Missing input!');
      return;
    } else if (document.getElementById("newP").value != document.getElementById("newP2").value) {
      alert('Your new password does not match!');
      return;
    } else if (document.getElementById("newP").value == document.getElementById("oldP").value) {
      alert('Your new password cannot be the same as the old!');
      return;
    } 
    
    


    //Insert some code here to validify old password is correct and new password is accepted
    document.getElementById("oldP").value = null;
    document.getElementById("newP").value = null;
    document.getElementById("newP2").value = null;
    //alert success or failure
    alert("Password Succesfully Changed!");
  }

  //Control for blocking/unblocking a user
  function blockControl() {
    if(document.getElementById("blockButton").textContent == "Block") {
      document.getElementById("blockButton").textContent = "Unblock";
    } else {
      document.getElementById("blockButton").textContent = "Block";
    }
  }