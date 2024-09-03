// Call the dataTables jQuery plugin
$(document).ready(function() {
   loadUsers();
  $('#usersTable').DataTable();
});

async function loadUsers(){
      //const usrResponse = await fetch('api/get-user-by-id/7777', {
      const usrResponse = await fetch('api/users', {
        method: 'GET',
        headers: getHeaders(),
      });
      const usuarios = await usrResponse.json();
      let usrsHTML='';

      for(let usuario of usuarios){
          usr = '';
          usr += '<tr>';
          usr += '<td>'+usuario.id+'</td>';
          usr += '<td>'+usuario.name + ' ' + usuario.lastname +'</td>';
          usr += '<td>'+usuario.email+'</td>';
          usr += '<td>'+usuario.phone+'</td>';
          usr += '<td>';
          usr += '<a href="#" onclick="deleteUser('+usuario.id+')" class="btn btn-danger btn-circle btn-sm">';
          usr += '<i class="fas fa-trash"></i>';
          usr += '</a>';
          usr += '</td>';
          usr += '</tr>';

          usrsHTML += usr;
      }


      document.querySelector("#usersTable tbody").innerHTML=usrsHTML;
      //console.log(usuarios);
}

async function deleteUser(id){
    if(!confirm('Esta seguro de querer eliminar el usuarios seleccionado?')){
        return;
    }
    const usrResponse = await fetch('api/users/' + id, {
        method: 'DELETE',
        headers: getHeaders(),
    });
    location.reload();
}

function getHeaders(){
    return {
             'Accept': 'application/json',
             'Content-Type': 'application/json',
             'Authorization': localStorage.token
           };
}

/* PLANTILLA FETCH
(async () => {
  const rawResponse = await fetch('https://httpbin.org/post', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({a: 1, b: 'Textual content'})
  });
  const content = await rawResponse.json();

  console.log(content);
})();
*/