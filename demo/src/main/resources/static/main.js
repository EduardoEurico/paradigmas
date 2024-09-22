document.getElementById('rotinaForm').addEventListener('submit', function(e) {
    e.preventDefault();
    // Quando o formulário for enviado, o comportamento padrão é prevenido (para não recarregar a página).

    const tarefa = document.getElementById('tarefa').value;
    const descricao = document.getElementById('descricao').value;
    const duracao = document.getElementById('duracao').value;
    // Captura os valores dos campos de entrada (tarefa, descrição e duração) no formulário.

    if (tarefa.trim() === "" || descricao.trim() === "" || duracao.trim() === "") return;
    // Verifica se algum dos campos está vazio. Se estiver, a função retorna sem continuar.

    const tarefaCompleta = `${tarefa}, ${descricao}, ${duracao}`;
    // Combina as três informações em uma string única.

    fetch('/rotinas?tarefa=' + encodeURIComponent(tarefaCompleta), {
        method: 'POST'
    }).then(response => response.text())
        // Envia uma requisição HTTP POST ao endpoint "/rotinas", passando a tarefa completa como um parâmetro na URL.

        .then(message => {
            console.log(message);
            // Exibe a mensagem de resposta no console (provavelmente confirmando que a tarefa foi adicionada).

            addTarefaToList(tarefaCompleta);
            // Chama a função para adicionar a tarefa à lista visível.

            document.getElementById('tarefa').value = '';
            document.getElementById('descricao').value = '';
            document.getElementById('duracao').value = '';
            // Limpa os campos de entrada após adicionar a tarefa.
        });
});
function addTarefaToList(tarefa) {
    const list = document.getElementById('rotinaList');
    // Captura a lista de rotinas na interface.

    const li = document.createElement('li');
    li.textContent = tarefa;
    // Cria um novo item de lista e define o texto como a tarefa completa.

    list.appendChild(li);
    // Adiciona o novo item à lista no HTML, exibindo-o na página.
}
function loadRotinas() {
    fetch('/rotinas')
        // Faz uma requisição GET ao servidor para obter a lista de rotinas.

        .then(response => response.json())
        // Espera a resposta em formato JSON.

        .then(rotinas => {
            const list = document.getElementById('rotinaList');
            list.innerHTML = '';
            // Limpa a lista de rotinas exibidas antes de recarregá-la.

            rotinas.forEach(tarefa => {
                addTarefaToList(tarefa);
                // Adiciona cada rotina à lista, iterando sobre o array de rotinas recebido do servidor.
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
    // Cria e estiliza um popup centralizado na tela, que dá boas-vindas ao usuário.

    setTimeout(function() {
        popup.remove();
    }, 5000);
    // O popup é removido após 5 segundos.
});
loadRotinas();