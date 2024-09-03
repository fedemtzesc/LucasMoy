// Call the dataTables jQuery plugin
$(document).ready(function() {
   // onready
});

async function initSession(){
      let datos = {};
      datos.email = document.getElementById("usr_email").value;
      datos.password = document.getElementById("usr_password").value;

      //const usrResponse = await fetch('api/get-user-by-id/7777', {
      const usrResponse = await fetch('api/login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });
      //const loginResponse = await usrResponse.json();
      const loginResponse = await usrResponse.text();
      console.log(loginResponse)

      if(loginResponse!='FAIL'){
        alert('Bienvenido al Sistema!');
        localStorage.token = loginResponse;
        localStorage.email = datos.email;
        window.location.href = 'users.html';
      }else{
        alert('Las credenciales que introdujo son incorrectas!');
      }
}