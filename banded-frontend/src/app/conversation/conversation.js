function openMessageForm() {
    document.getElementById("infoForm").style.display = "block";
}

function closeMessageForm() {
    let msg = document.getElementById("newMessage").value;
    //Return msg to backend

    document.getElementById("infoForm").style.display = "none";
    alert('Message Sent Successfully!')

}