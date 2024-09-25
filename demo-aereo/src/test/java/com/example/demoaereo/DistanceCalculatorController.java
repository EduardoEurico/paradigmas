package com.example.demoaereo;

// Importa classes do Spring Framework necessárias para definir um controlador REST e mapear requisições HTTP
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Anotação que define a classe como um controlador REST, permitindo que ela trate requisições HTTP
@RestController
public class DistanceCalculatorController {

    // Constante que armazena o raio médio da Terra em quilômetros
    private static final double EARTH_RADIUS_KM = 6371.0; // Raio médio da Terra em km

    // Constante que armazena a velocidade de um objeto (avião, por exemplo) em km/h
    private static final double VELOCIDADE_KM_H = 1872.0; // Velocidade do objeto em km/h

    // Mapeia a URL "/calculate" para o método calculateDistance, definindo que ele será chamado em requisições GET
    @GetMapping("/calculate")
    public String calculateDistance(
            // Recebe parâmetros da requisição HTTP (latitudes e longitudes de dois pontos) via @RequestParam
            @RequestParam double x1, // Latitude do ponto 1
            @RequestParam double y1, // Longitude do ponto 1
            @RequestParam double x2, // Latitude do ponto 2
            @RequestParam double y2) { // Longitude do ponto 2

        // Converte as latitudes e longitudes de graus para radianos, pois a fórmula de Haversine requer isso
        double lat1 = Math.toRadians(x1);
        double lon1 = Math.toRadians(y1);
        double lat2 = Math.toRadians(x2);
        double lon2 = Math.toRadians(y2);

        // Calcula as diferenças entre as latitudes e longitudes
        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        // Fórmula de Haversine, utilizada para calcular a distância entre dois pontos na superfície da Terra
        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                Math.sin(dlon / 2) * Math.sin(dlon / 2);

        // Calcula o arco do círculo máximo baseado na fórmula de Haversine
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calcula a distância entre os pontos em quilômetros
        double distance = EARTH_RADIUS_KM * c;

        // Calcula o tempo estimado para percorrer a distância à velocidade definida (em horas)
        double tempo = distance / VELOCIDADE_KM_H;

        // Variável para armazenar o tempo formatado em minutos ou horas
        String tempoFormatado;

        // Se o tempo for menor que 1 hora, converte para minutos
        if (tempo < 1) {
            double minutos = tempo * 60;
            tempoFormatado = String.format("%.2f", minutos) + " minutos";
        } else {
            // Caso contrário, mantém o valor em horas
            tempoFormatado = String.format("%.2f", tempo) + " horas";
        }

        // Retorna uma resposta em HTML que mostra a distância e o tempo calculados
        return "<html><body>" +
                "<h2>Distância calculada: " + String.format("%.2f", distance) + " km</h2>" +
                "<h2>Tempo estimado a " + VELOCIDADE_KM_H + " km/h: " + tempoFormatado + "</h2>" +
                "</body></html>";
    }
}
