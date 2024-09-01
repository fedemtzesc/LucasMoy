// Call the dataTables jQuery plugin
$(document).ready(function() {
   // onready
});

async function registerUser(){
      let datos = {};
      datos.name = document.getElementById("usr_name").value;
      datos.lastname = document.getElementById("usr_lastname").value;
      datos.email = document.getElementById("usr_email").value;
      datos.phone = document.getElementById("usr_phone").value;
      datos.password = document.getElementById("usr_password").value;
      let confPwd = document.getElementById("repeat_password").value;

      if(datos.password!=confPwd){
        alert('El password y su confirmacion son diferentes. Revise por favor.');
        return;
      }

      //const usrResponse = await fetch('api/get-user-by-id/7777', {
      const usrResponse = await fetch('api/users', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });
      const usuario = await usrResponse.json();
      console.log(usuario)
      alert('La cuenta fue registrada con exito!');
      document.forms[0].reset();
}