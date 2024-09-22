document.getElementById('rotinaForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const tarefa = document.getElementById('tarefa').value;
    const descricao = document.getElementById('descricao').value;
    const duracao = document.getElementById('duracao').value;

    if (tarefa.trim() === "" || descricao.trim() === "" || duracao.trim() === "") return;

    const tarefaCompleta = `${tarefa}, ${descricao}, ${duracao}`;

    fetch('/rotinas?tarefa=' + encodeURIComponent(tarefaCompleta), {
        method: 'POST'
    }).then(response => response.text())
        .then(message => {
            console.log(message);
            addTarefaToList(tarefaCompleta);
            document.getElementById('tarefa').value = '';
            document.getElementById('descricao').value = '';
            document.getElementById('duracao').value = '';
        });
});

function addTarefaToList(tarefa) {
    const list = document.getElementById('rotinaList');
    const li = document.createElement('li');
    li.textContent = tarefa;
    list.appendChild(li);
}

function loadRotinas() {
    fetch('/rotinas')
        .then(response => response.json())
        .then(rotinas => {
            const list = document.getElementById('rotinaList');
            list.innerHTML = '';
            rotinas.forEach(tarefa => {
                addTarefaToList(tarefa);
            });
        });
}

document.addEventListener('DOMContentLoaded', function() {
    const popup = document.createElement('div');
    popup.textContent = 'Olá, seja bem-vindo ao site da Rotina do Héroi!';
    popup.style.position = 'fixed';
    popup.style.top = '50%';
    popup.style.left = '50%';
    popup.style.transform = 'translate(-50%, -50%)';
    popup.style.padding = '10px';
    popup.style.backgroundColor = 'rgba(40,91,167,0.74)';
    popup.style.color = 'white';
    popup.style.borderRadius = '5px';
    popup.style.boxShadow = '0 0 10px rgba(0, 0, 0, 0.1)';
    document.body.appendChild(popup);

    setTimeout(function() {
        popup.remove();
    }, 5000);
});

loadRotinas();