import org.springframework.web.bind.annotation.*; // Importa as anotações Spring necessárias para criar controladores REST.
import java.util.ArrayList; // Importa a classe ArrayList para manipular listas de rotinas.
import java.util.List; // Importa a interface List, que será implementada pela classe ArrayList.

@RestController // Indica que esta classe será um controlador REST, que responde a requisições HTTP e retorna dados (geralmente em JSON).
@RequestMapping("/rotinas") // Define que todas as rotas desta classe começam com "/rotinas".
public class main { // Define a classe "main" que será o controlador da aplicação.

    private final List<String> rotinas = new ArrayList<>(); // Cria uma lista de strings para armazenar as rotinas. É final para impedir a reatribuição.

    @GetMapping // Mapeia requisições HTTP GET para o método abaixo, que será chamado quando um GET for feito para "/rotinas".
    public List<String> getRotinas() {
        return rotinas; // Retorna a lista de rotinas. Como a classe é anotada com @RestController, a lista será convertida automaticamente para JSON.
    }

    @PostMapping // Mapeia requisições HTTP POST para o método abaixo, que será chamado quando um POST for feito para "/rotinas".
    public String addRotina(@RequestParam String tarefa) { // O parâmetro "tarefa" é obtido da requisição POST, passado como parâmetro na URL ou corpo da requisição.
        rotinas.add(tarefa); // Adiciona a tarefa recebida à lista de rotinas.
        return "Tarefa adicionada: " + tarefa; // Retorna uma mensagem confirmando que a tarefa foi adicionada.
    }
}
