package example.demoaereo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistanceCalculatorController {

    private static final double EARTH_RADIUS_KM = 6371.0; // Raio médio da Terra em km
    private static final double VELOCIDADE_KM_H = 1872.0; // Velocidade do objeto em km/h

    @GetMapping("/calculate")
    public String calculateDistance(
            @RequestParam double x1,
            @RequestParam double y1,
            @RequestParam double x2,
            @RequestParam double y2) {
        // Converter graus para radianos
        double lat1 = Math.toRadians(x1);
        double lon1 = Math.toRadians(y1);
        double lat2 = Math.toRadians(x2);
        double lon2 = Math.toRadians(y2);

        // Fórmula de Haversine para calcular a distância em quilômetros
        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dlon / 2) * Math.sin(dlon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS_KM * c;

        // Calcular o tempo estimado para percorrer a distância
        double tempo = distance / VELOCIDADE_KM_H;
        String tempoFormatado;

        if (tempo < 1) {
            double minutos = tempo * 60;
            tempoFormatado = String.format("%.2f", minutos) + " minutos";
        } else {
            tempoFormatado = String.format("%.2f", tempo) + " horas";
        }

        // Retornar o resultado para exibir na página HTML
        return String.format("Distância: %.2f km<br>Tempo estimado: %s", distance, tempoFormatado);
    }
}
