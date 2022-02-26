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