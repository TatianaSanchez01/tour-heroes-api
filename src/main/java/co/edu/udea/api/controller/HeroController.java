package co.edu.udea.api.controller;

import co.edu.udea.api.model.Hero;
import co.edu.udea.api.service.HeroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final Logger log = LoggerFactory.getLogger(HeroController.class);

    public HeroService heroService;

    public HeroController(HeroService heroService){
        this.heroService = heroService;
    }

    /**
     * Buscar un héroe en la base de datos por ID.
     * @param id El ID del héroe que se va a buscar.
     * @return El héroe encontrado.
     */
    @GetMapping("{id}")
    @Operation(summary = "Buscar un heroes por su id", description = "Busca un héroe en la base de datos por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Héroe encontrado exitosamente."),
            @ApiResponse(responseCode = "400", description = "Petición inválida. Asegúrate de proporcionar un ID válido para buscar al héroe."),
            @ApiResponse(responseCode = "404", description = "Héroe no encontrado. No se encontró un héroe con el ID especificado en la base de datos."),
            @ApiResponse(responseCode = "500", description = "Error interno para procesar respuesta. Se ha producido un error interno al intentar buscar al héroe. Por favor, intenta nuevamente más tarde."),
    })
    public ResponseEntity<Hero> getHero(@PathVariable Integer id){
        log.info("Rest request search heroe by id: "+ id);
        return ResponseEntity.ok(heroService.getHero(id));
    }

    /**
     * Listar héroes creados en la base de datos.
     * @return Una lista de héroes.
     */
    @GetMapping("")
    @Operation(summary = "Buscar todos los heroes", description = "Obtiene una lista de todos los héroes en la base de datos.")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Héroes encontrados exitosamente."),
            @ApiResponse(responseCode = "400", description = "Petición inválida. La solicitud no es válida para listar los héroes."),
            @ApiResponse(responseCode = "404", description = "No fue posible listar los héroes, no fueron encontrados."),
            @ApiResponse(responseCode = "500", description = "Error interno para procesar respuesta. Se ha producido un error interno al intentar listar los héroes. Por favor, intenta nuevamente más tarde."),
    })
    public ResponseEntity<List<Hero>> getHeroes(){
        log.info("Rest request list heroes");
        return ResponseEntity.ok(heroService.getHeroes());
    }

    /**
     * Almacena un nuevo héroe en la base de datos.
     * @param hero El héroe que se va a almacenar.
     * @return El héroe almacenado exitosamente.
     */
    @PostMapping()
    @Operation(summary = "Agregar un nuevo héroe", description = "Agrega un nuevo héroe a la base de datos.")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Héroe almacenado exitosamente."),
            @ApiResponse(responseCode = "400", description = "Petición inválida. Asegúrate de proporcionar datos válidos para agregar un nuevo héroe."),
            @ApiResponse(responseCode = "404", description = "No fue posible almacenar el héroe en la base de datos."),
            @ApiResponse(responseCode = "500", description = "Error interno para procesar respuesta. Se ha producido un error interno al intentar almacenar el héroe. Por favor, intenta nuevamente más tarde."),
    })
    public ResponseEntity<Hero> addHero(@RequestBody Hero hero){
        log.info("Rest request add new hero");
        return ResponseEntity.ok(heroService.addHero(hero));
    }

    /**
     * Actualizar un héroe en la base de datos.
     * @param hero El héroe con los datos actualizados.
     * @return El héroe actualizado.
     */
    @PutMapping()
    @Operation(summary = "Actualizar héroe", description = "Actualiza un héroe en la base de datos.")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Héroe actualizado exitosamente."),
            @ApiResponse(responseCode = "400", description = "Petición inválida. Asegúrate de proporcionar datos válidos para actualizar un héroe."),
            @ApiResponse(responseCode = "404", description = "No fue posible encontrar un héroe con el ID especificado."),
            @ApiResponse(responseCode = "500", description = "Error interno para procesar respuesta. Se ha producido un error interno al intentar actualizar el héroe. Por favor, intenta nuevamente más tarde."),
    })
    public ResponseEntity<Hero> updateHero(@RequestBody Hero hero){
        log.info("Rest request update heroes");
        return ResponseEntity.ok(heroService.updateHero(hero));
    }

    /**
     * Eliminar un héroe de la base de datos por ID.
     * @param id El ID del héroe que se va a eliminar.
     * @return ResponseEntity sin cuerpo con el código de respuesta apropiado.
     */
    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar héroe por ID", description = "Elimina un héroe de la base de datos por su ID.")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Héroe eliminado exitosamente."),
            @ApiResponse(responseCode = "400", description = "Petición inválida. Asegúrate de proporcionar un ID válido para eliminar un héroe."),
            @ApiResponse(responseCode = "404", description = "No fue posible eliminar el héroe, no fue encontrado."),
            @ApiResponse(responseCode = "500", description = "Error interno para procesar respuesta. Se ha producido un error interno al intentar eliminar el héroe. Por favor, intenta nuevamente más tarde."),
    })
    public ResponseEntity<Void> deteleteHero(@PathVariable Integer id){
        log.info("Rest request delete heroe = {}", id);
        heroService.deleteHero(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Buscar un héroe en la base de datos de acuerdo a un nombre.
     * @param name El nombre del héroe a buscar.
     * @return Una lista de héroes que coinciden con el nombre especificado.
     */
    @GetMapping("/name")
    @Operation(summary = "Buscar héroes por nombre", description = "Busca héroes en la base de datos por nombre.")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Héroes encontrados exitosamente."),
            @ApiResponse(responseCode = "400", description = "Petición inválida. Asegúrate de proporcionar un nombre válido para buscar héroes."),
            @ApiResponse(responseCode = "404", description = "No existen héroes con ese nombre."),
            @ApiResponse(responseCode = "500", description = "Error interno para procesar respuesta. Se ha producido un error interno al intentar buscar héroes por nombre. Por favor, intenta nuevamente más tarde."),
    })
    public ResponseEntity<List<Hero>> searchHeroes(@RequestParam String name){
        log.info("Rest request search heroes by name: {}", name);
        return ResponseEntity.ok(heroService.searchHeroes(name));
    }

}
